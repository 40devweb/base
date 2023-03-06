/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._40dev.base.services;

import com._40dev.base.entity.Ticket;
import com._40dev.base.entity.TicketRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author snishi
 */
@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;

    @Transactional
    public Ticket saveTicket(Ticket t) {
        Ticket newTicket = repository.save(t);
        return newTicket;
    }

    @Transactional(readOnly = true)
    public Ticket getTicket(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Ticket> getAll() {
        return repository.findAll();
    }

    @Transactional
    public void deleteTicket(int id) {
        repository.deleteById(id);
    }

}
