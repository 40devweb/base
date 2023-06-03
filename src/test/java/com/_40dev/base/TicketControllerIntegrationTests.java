package com._40dev.base;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com._40dev.base.dto.TicketDTO;
import com._40dev.base.entity.Ticket;
import com._40dev.base.services.TicketService;

import lombok.extern.java.Log;

@Log
@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BaseApplication.class)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class TicketControllerIntegrationTests {

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TicketService ticketService;

	@Autowired
    private TestRestTemplate restTemplate;

	@BeforeAll
    public void initTestData(){
        Timestamp dttm = Timestamp.from(Instant.now().minusSeconds(3600));
        Ticket t = new Ticket(0, dttm, "TICKET G");
        t=ticketService.saveTicket(t);
        log.info("initTestData Created test ticket "+t);
    }

	@Test
    void whenCallingTickets_thenReturnList() {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/api/v1/tickets", TicketDTO[].class).length > 1);
    }

    @Test
    void whenCallingTicket1000_thenReturnText() {

        TicketDTO t=this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/tickets/7", TicketDTO.class);
        log.info("SNI: [" +t.text()+"] "+t.toString());
        assertTrue(t.text().equalsIgnoreCase("msg1000"));
    }

}
