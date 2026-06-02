import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pub } from 'src/modeles/Pub';

@Injectable({
  providedIn: 'root',
})
export class PublicationService {
  private baseUrl = 'http://localhost:3000/publications';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Pub[]> {
    return this.http.get<Pub[]>(this.baseUrl);
  }

  getById(id: string): Observable<Pub> {
    return this.http.get<Pub>(`${this.baseUrl}/${id}`);
  }

  add(pub: Pub): Observable<Pub> {
    return this.http.post<Pub>(this.baseUrl, pub);
  }

  update(id: string, pub: Pub): Observable<Pub> {
    return this.http.put<Pub>(`${this.baseUrl}/${id}`, pub);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
