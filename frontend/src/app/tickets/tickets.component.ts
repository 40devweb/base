import { Component } from '@angular/core';
import { TicketService } from './ticket.service';
import { Ticket } from './ticket';

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html'
})
export class TicketsComponent {

  public ticketList: Ticket[] = [];
  constructor(private ticketService: TicketService) { }

  ngOnInit() {
    console.log('SNI Llamando al servicio')
    this.ticketService.getTickets().subscribe(list => this.ticketList = list);
    console.log('SNI Llamó al servicio')
  }
}
