import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Ticket } from './ticket';
import { Observable, catchError, of } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class TicketService {

    private apiUrl = 'http://localhost:8080/api';  // URL to web api
    private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

    constructor(private http: HttpClient) { }

    getTickets(): Observable<Ticket[]> {
        return this.http.get<Ticket[]>(this.apiUrl + '/tickets')
            .pipe(
                catchError(this.handleError<Ticket[]>('getTickets', []))
            );
    }

    getTicket(id: number): Observable<Ticket> {
        return this.http.get<Ticket>(this.apiUrl + '/tickets/'+id)
            .pipe(
                catchError(this.handleError<Ticket>('getTicket/'+id, undefined))
            );
    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error(error); // log to console instead
            return of(result as T);
          };
    }

    createTicket(ticket: Ticket): Observable<Ticket> {
        return this.http.post<Ticket>(this.apiUrl+'/tickets', ticket, {headers:this.httpHeaders})
    }

    updateTicket(ticket: Ticket): Observable<Ticket> {
        return this.http.put<Ticket>(this.apiUrl+'/tickets/'+ticket.id, ticket, {headers:this.httpHeaders})
    }

}
