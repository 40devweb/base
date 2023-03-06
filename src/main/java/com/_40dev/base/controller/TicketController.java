/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._40dev.base.controller;

import com._40dev.base.entity.Ticket;
import com._40dev.base.services.TicketService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author snishi
 */
@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    
    @GetMapping("/tickets/{id}")
    public Ticket getTicket(@PathVariable Integer id){
        return ticketService.getTicket(id);
    }
    
    @GetMapping("/tickets/")
    public List<Ticket> getAllTickets(){
        return ticketService.getAll();
    }
    
    @PostMapping("/tickets")
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket createTicket(@RequestBody Ticket newTicket){
        return ticketService.saveTicket(newTicket);
    }
    
}
