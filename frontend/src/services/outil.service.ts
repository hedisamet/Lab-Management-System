import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Outil } from 'src/modeles/Outil';

@Injectable({
  providedIn: 'root',
})
export class OutilService {
  private baseUrl = 'http://localhost:3000/tools';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Outil[]> {
    return this.http.get<Outil[]>(this.baseUrl);
  }

  getById(id: string): Observable<Outil> {
    return this.http.get<Outil>(`${this.baseUrl}/${id}`);
  }

  add(outil: Outil): Observable<Outil> {
    return this.http.post<Outil>(this.baseUrl, outil);
  }

  update(id: string, outil: Outil): Observable<Outil> {
    return this.http.put<Outil>(`${this.baseUrl}/${id}`, outil);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
