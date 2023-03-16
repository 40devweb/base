/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._40dev.base.controller;

import com._40dev.base.entity.Ticket;
import com._40dev.base.services.TicketService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<?> getTicket(@PathVariable Integer id){
        Ticket ticket=null;
        ResponseEntity<?> response;
        Logger log=Logger.getLogger(TicketController.class.getName());

        try {
            ticket=ticketService.getTicket(id);
            response=new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.toString());
            response=new ResponseEntity<RestErrorResponse>(RestErrorResponse.error("Internal error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(ticket==null) {
            response=new ResponseEntity<RestErrorResponse>(RestErrorResponse.information("Not found"), HttpStatus.NOT_FOUND);
        }

        return response;
    }
    
    @GetMapping("/tickets")
    public ResponseEntity<?> getAllTickets(){
        List<Ticket> lTickets;
        ResponseEntity<?> response;
        try {
            lTickets=ticketService.getAll();
            response=new ResponseEntity<List<Ticket>>(lTickets, HttpStatus.OK);
        } catch (Exception e) {
            response=new ResponseEntity<RestErrorResponse>(RestErrorResponse.error("Internal error: "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    @PostMapping("/tickets")
    public ResponseEntity<?> createTicket(@RequestBody Ticket newTicket){

        Ticket ticket=null;
        ResponseEntity<?> response;
        Logger log=Logger.getLogger(TicketController.class.getName());

        try {
            ticket=ticketService.saveTicket(newTicket);
            response=new ResponseEntity<Ticket>(ticket, HttpStatus.CREATED);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.toString());
            response=new ResponseEntity<RestErrorResponse>(RestErrorResponse.error("Internal error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
   
    @PutMapping("/tickets/{id}")
    public ResponseEntity<?> updateTicket(@RequestBody Ticket newTicket, @PathVariable Integer id){
        Ticket currentTicket;
        ResponseEntity<?> response;
        Logger log=Logger.getLogger(TicketController.class.getName());

        try {
            currentTicket=ticketService.getTicket(id);
            if(currentTicket==null) {
                response=new ResponseEntity<RestErrorResponse>(RestErrorResponse.error("Not found"), HttpStatus.NOT_FOUND);
            } else {
                currentTicket.setText(newTicket.getText());
                currentTicket=ticketService.saveTicket(currentTicket);
                response=new ResponseEntity<Ticket>(currentTicket, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, e.toString());
            response=new ResponseEntity<RestErrorResponse>(RestErrorResponse.error("Internal error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
        
    }
    
    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Integer id){
        ResponseEntity<?> response;
        Logger log=Logger.getLogger(TicketController.class.getName());   

        try {
            ticketService.deleteTicket(id);
            response=new ResponseEntity<RestErrorResponse>(RestErrorResponse.information("Deleted"), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.toString());
            response=new ResponseEntity<RestErrorResponse>(RestErrorResponse.error("Internal error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
}
