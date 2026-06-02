import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Pub } from 'src/modeles/Pub';
import { PublicationService } from 'src/services/publication.service';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css'],
  providers: [DatePipe],
})
export class ArticleComponent implements OnInit {
  displayedColumns: string[] = ['id', 'titre', 'type', 'date', 'lien', 'sourcepdf', 'actions'];
  dataSource: Pub[] = [];
  showForm = false;
  editingId: string | null = null;

  publicationTypes = ['journal', 'conference', 'workshop', 'book', 'thesis', 'other'];

  form = new FormGroup({
    titre: new FormControl('', [Validators.required]),
    type: new FormControl('', [Validators.required]),
    lien: new FormControl('', [Validators.required]),
    date: new FormControl('', [Validators.required]),
    sourcepdf: new FormControl(''),
  });

  constructor(
    private pubService: PublicationService,
    private dialog: MatDialog,
    private datePipe: DatePipe
  ) {}

  ngOnInit(): void {
    this.fetch();
  }

  fetch(): void {
    this.pubService.getAll().subscribe((data) => {
      this.dataSource = data;
    });
  }

  openForm(pub?: Pub): void {
    this.showForm = true;
    if (pub) {
      this.editingId = pub.id;
      this.form.patchValue({
        titre: pub.titre,
        type: pub.type,
        lien: pub.lien,
        date: pub.date,
        sourcepdf: pub.sourcepdf,
      });
    } else {
      this.editingId = null;
      this.form.reset();
    }
  }

  closeForm(): void {
    this.showForm = false;
    this.editingId = null;
    this.form.reset();
  }

  onSubmit(): void {
    if (!this.form.valid) return;

    const formValue = this.form.value;
    const date = new Date(formValue.date!);
    const pub: any = {
      titre: formValue.titre,
      type: formValue.type,
      lien: formValue.lien,
      date: this.datePipe.transform(date, 'dd/MM/yyyy') || formValue.date,
      sourcepdf: formValue.sourcepdf || '',
    };

    if (this.editingId) {
      this.pubService.update(this.editingId, { ...pub, id: this.editingId }).subscribe(() => {
        this.closeForm();
        this.fetch();
      });
    } else {
      this.pubService.add(pub).subscribe(() => {
        this.closeForm();
        this.fetch();
      });
    }
  }

  delete(id: string): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      height: '200px',
      width: '300px',
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result === true) {
        this.pubService.delete(id).subscribe(() => this.fetch());
      }
    });
  }
}
