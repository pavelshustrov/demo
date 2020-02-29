package com.example.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(JUnit4.class)
class CasesTests {

    @LocalServerPort
    private int randomServerPort;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private ResponseEntity<String> get(String id, HttpHeaders headers) {
        URI uri = URI.create("http://localhost:" + randomServerPort + "/airport/" + id);
        HttpEntity<String> request = new HttpEntity<>(headers);

        return this.restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("client.ID", UUID.randomUUID().toString());
        headers.add("client.ThreadName", Thread.currentThread().getName());
        headers.add("client.SendingTime", LocalDateTime.now().toString());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Test
    public void validEntryTest() {
        ResponseEntity<String> entity = get("1", getHttpHeaders());

        Assert.assertEquals(200, entity.getStatusCodeValue());
        Assert.assertNotNull(entity.getBody());
        Assert.assertTrue(entity.getBody().contains("Goroka Airport"));
    }


    @Test
    public void invalidEntryTest() {
        ResponseEntity<String> entity = get("-1", getHttpHeaders());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
        Assert.assertEquals("Airport not found", entity.getBody());
    }

    @Test
    public void requestWithoutHeader() {
        ResponseEntity<String> entity = get("1", new HttpHeaders());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, entity.getStatusCode());
        System.out.println(entity.toString());
    }
}
