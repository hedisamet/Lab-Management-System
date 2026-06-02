import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Outil } from 'src/modeles/Outil';
import { OutilService } from 'src/services/outil.service';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-tools',
  templateUrl: './tools.component.html',
  styleUrls: ['./tools.component.css'],
  providers: [DatePipe],
})
export class ToolsComponent implements OnInit {
  displayedColumns: string[] = ['id', 'source', 'date', 'actions'];
  dataSource: Outil[] = [];
  showForm = false;
  editingId: string | null = null;

  form = new FormGroup({
    source: new FormControl('', [Validators.required]),
    date: new FormControl('', [Validators.required]),
  });

  constructor(
    private outilService: OutilService,
    private dialog: MatDialog,
    private datePipe: DatePipe
  ) {}

  ngOnInit(): void {
    this.fetch();
  }

  fetch(): void {
    this.outilService.getAll().subscribe((data) => {
      this.dataSource = data;
    });
  }

  openForm(outil?: Outil): void {
    this.showForm = true;
    if (outil) {
      this.editingId = outil.id;
      this.form.patchValue({ source: outil.source, date: outil.date });
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
    const outil: any = {
      source: formValue.source,
      date: this.datePipe.transform(date, 'dd/MM/yyyy') || formValue.date,
    };

    if (this.editingId) {
      this.outilService.update(this.editingId, { ...outil, id: this.editingId }).subscribe(() => {
        this.closeForm();
        this.fetch();
      });
    } else {
      this.outilService.add(outil).subscribe(() => {
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
        this.outilService.delete(id).subscribe(() => this.fetch());
      }
    });
  }
}
