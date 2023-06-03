/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._40dev.base.controller;

import com._40dev.base.dto.TicketDTO;
import com._40dev.base.entity.Ticket;
import com._40dev.base.services.TicketService;

import lombok.extern.java.Log;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/v1")
@Log
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<?> getTicket(@PathVariable Integer id) {
        Ticket ticket = null;
        ResponseEntity<?> response;

        try {
            ticket = ticketService.getTicket(id);
            TicketDTO ticketDto = new TicketDTO(ticket.getId(), ticket.getCreationDttm(), ticket.getText());

            response = new ResponseEntity<TicketDTO>(ticketDto, HttpStatus.OK);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.toString());
            response = new ResponseEntity<RestErrorResponse>(RestErrorResponse.error("Internal error"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (ticket == null) {
            response = new ResponseEntity<RestErrorResponse>(RestErrorResponse.information("Not found"),
                    HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @GetMapping("/tickets")
    public ResponseEntity<?> getAllTicketsNew() {
        List<TicketDTO> lTickets;
        ResponseEntity<?> response;
        try {
            lTickets = ticketService.getAll().stream()
                    .map(t -> new TicketDTO(t.getId(), t.getCreationDttm(), t.getText())).toList();
            response = new ResponseEntity<List<TicketDTO>>(lTickets, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<RestErrorResponse>(
                    RestErrorResponse.error("Internal error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/tickets")
    public ResponseEntity<?> createTicket(@RequestBody TicketDTO newTicket) {

        Ticket ticket = new Ticket();
        ticket.setId(newTicket.id());
        ticket.setCreationDttm(newTicket.creationDttm());
        ticket.setText(newTicket.text());
        ResponseEntity<?> response;
        try {
            ticket = ticketService.saveTicket(ticket);
            TicketDTO createdTicket = new TicketDTO(ticket.getId(), ticket.getCreationDttm(), ticket.getText());
            response = new ResponseEntity<TicketDTO>(createdTicket, HttpStatus.CREATED);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.toString());
            response = new ResponseEntity<RestErrorResponse>(RestErrorResponse.error("Internal error"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<?> updateTicket(@RequestBody TicketDTO newTicket, @PathVariable Integer id) {
        Ticket currentTicket;
        TicketDTO ticketDto;

        ResponseEntity<?> response;
        
        try {
            currentTicket = ticketService.getTicket(id);
            if (currentTicket == null) {
                response = new ResponseEntity<RestErrorResponse>(RestErrorResponse.error("Not found"),
                        HttpStatus.NOT_FOUND);
            } else {
                currentTicket.setText(newTicket.text());
                currentTicket = ticketService.saveTicket(currentTicket);
                ticketDto = new TicketDTO(currentTicket.getId(), currentTicket.getCreationDttm(),
                        currentTicket.getText());
                response = new ResponseEntity<TicketDTO>(ticketDto, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, e.toString());
            response = new ResponseEntity<RestErrorResponse>(RestErrorResponse.error("Internal error"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Integer id) {
        ResponseEntity<?> response;

        try {
            ticketService.deleteTicket(id);
            response = new ResponseEntity<RestErrorResponse>(RestErrorResponse.information("Deleted"),
                    HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.toString());
            response = new ResponseEntity<RestErrorResponse>(RestErrorResponse.error("Internal error"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

}
