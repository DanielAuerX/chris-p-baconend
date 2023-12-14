package com.chrispbacon.chrispbaconend.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class TicketService {


    @Value("${chrispbacon.ticket.url}")
    private String GITHUB_API_URL;

    @Value("${chrispbacon.ticket.owner}")
    private String OWNER;

    @Value("${chrispbacon.ticket.repo}")
    private String REPO;

    @Value("${chrispbacon.ticket.token}")
    private String TOKEN;

    public void createGitHubIssue(TicketDto ticketDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        String url = GITHUB_API_URL.replace("{owner}", OWNER).replace("{repo}", REPO);

        String jsonBody = String.format("{\"title\": \"%s\", \"body\": \"%s\"}", ticketDto.title(), ticketDto.body());

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            log.info("Issue created successfully!");
        } else {
            log.error("Failed to create issue. Status code: " + response.getStatusCode());
        }
    }

}
