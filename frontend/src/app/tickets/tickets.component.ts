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
    this.ticketService.getTickets().subscribe(list => this.ticketList = list);
  }
}
