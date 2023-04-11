package com._40dev.base;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com._40dev.base.dto.TicketDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BaseApplication.class)
@AutoConfigureMockMvc
@Sql({ "/schema.sql", "/data.sql" })
class TicketControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void whenCallingTickets_thenReturnList() {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/api/v1/tickets", TicketDTO[].class).length > 1);
    }


}


