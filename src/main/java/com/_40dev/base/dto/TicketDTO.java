package com._40dev.base.dto;
import java.sql.Timestamp;

public record TicketDTO(Integer id, Timestamp creationDttm, String text) {
    
}
