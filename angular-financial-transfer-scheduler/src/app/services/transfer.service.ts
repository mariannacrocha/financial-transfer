import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
} )
export class TransferService {
  private apiUrl = 'http://localhost:8080/transferencias'; // URL da sua API Spring Boot

  constructor(private http: HttpClient ) { }

  scheduleTransfer(transfer: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, transfer );
  }

  getTransfers(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl );
  }
}
