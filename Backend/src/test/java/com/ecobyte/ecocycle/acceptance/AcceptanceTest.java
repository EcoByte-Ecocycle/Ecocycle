package com.ecobyte.ecocycle.acceptance;

import com.ecobyte.ecocycle.support.GoogleClient;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @LocalServerPort
    int port;

    @MockBean
    protected GoogleClient googleClient;

    @Value("${admin.email}")
    protected String adminEmail;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }
}
