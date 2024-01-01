package com.stock.analysis.fileUpload;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebIntegrationTest {

    @LocalServerPort
    private int port;

    protected TestRestTemplate client = new TestRestTemplate();

    protected URI uri(String path) {
        return URI.create(String.format("http://localhost:%d%s", port, path));
    }
}
