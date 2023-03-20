import { Component } from '@angular/core';
import { formatDate } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { Ticket } from './ticket';
import { TicketService } from './ticket.service';

enum Mode { Add, Update } 

@Component({
  selector: 'app-ticket-form',
  templateUrl: './ticket-form.component.html'
})
export class TicketFormComponent {
  
  ticket: Ticket;
  mode: Mode = Mode.Add;
  

  constructor(private ticketService: TicketService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.ticket=new Ticket(0, new Date().toISOString().toString(), '');
    this.loadTicket();
  }

  createTicket() {    
    this.ticketService.createTicket(this.ticket).subscribe(
      (t) => {
        this.ticket=t;
        console.log(this.ticket);
        this.router.navigate(['/tickets']);
      }
    )
  }

  updateTicket() {    
    this.ticketService.updateTicket(this.ticket).subscribe(
      (t) => {
        this.ticket=t;
        console.log(this.ticket);
        this.router.navigate(['/tickets']);
      }
    )
  }

  loadTicket(): void {
    this.activatedRoute.params.subscribe( params => {
      let tid=params['id'];
      if(tid) {
        this.ticketService.getTicket(tid).subscribe(t => this.ticket=t);
        this.mode=Mode.Update;
      }
    })
  }

  isAdd(){
    return this.mode==Mode.Add;
  }

  fDate(dt: any): string {
    return formatDate(dt, 'yyyy-MM-dd', 'en-US');
  }

  saveTicket(): void {
    if(this.isAdd()){
      this.createTicket();
    } else {
      this.updateTicket();
    }
  }

}
