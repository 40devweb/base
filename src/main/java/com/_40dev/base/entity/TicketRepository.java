/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com._40dev.base.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author snishi
 */
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
}
