package com._40dev.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com._40dev.base.dto.TicketDTO;
import com._40dev.base.entity.Ticket;
import com._40dev.base.entity.TicketRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BaseApplication.class)
@AutoConfigureMockMvc
@ExtendWith(PostgreSQLExtension.class)
class TicketControllerIntegrationTests {

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
    private TestRestTemplate restTemplate;

	@BeforeEach
    public void insertTicket(){
        ticketRepository.save(new Ticket());
    }

	@Test
    void whenCallingTickets_thenReturnList() {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/api/v1/tickets", TicketDTO[].class).length > 1);
    }
}
