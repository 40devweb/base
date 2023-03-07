import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TicketsComponent } from './tickets/tickets.component'
import { TicketFormComponent } from './tickets/ticket-form.component'

const routes: Routes = [
    { path:'tickets', component:TicketsComponent },
    { path:'app-ticket-form', component:TicketFormComponent }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
