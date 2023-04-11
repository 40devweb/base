package com._40dev.base.services;

import java.util.List;

import com._40dev.base.entity.Ticket;

public interface TicketService {
    public Ticket saveTicket(Ticket t);

    public Ticket getTicket(int id);
    
    public List<Ticket> getAll();
    
    public void deleteTicket(int id); 

    
}
