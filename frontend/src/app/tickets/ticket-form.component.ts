import { Component } from '@angular/core';
import { Ticket } from './ticket';
import { TicketService } from './ticket.service';

@Component({
  selector: 'app-ticket-form',
  templateUrl: './ticket-form.component.html'
})
export class TicketFormComponent {
  ticket: Ticket;

  constructor(private ticketService: TicketService) {
    this.ticket=new Ticket(0, Date.now().toString(), '')
  }

  createTicket() {
    console.log('Create ticket');
    console.log(this.ticket);
    this.ticketService.createTicket(this.ticket).subscribe(
      (t) => {
        this.ticket=t
      }
    )    
  }

}
