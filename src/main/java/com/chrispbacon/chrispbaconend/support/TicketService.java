package com.chrispbacon.chrispbaconend.support;

import com.chrispbacon.chrispbaconend.config.JwtService;
import com.chrispbacon.chrispbaconend.model.user.Student;
import com.chrispbacon.chrispbaconend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class TicketService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Value("${chrispbacon.ticket.url}")
    private String GITHUB_API_URL;

    @Value("${chrispbacon.ticket.owner}")
    private String OWNER;

    @Value("${chrispbacon.ticket.repo}")
    private String REPO;

    @Value("${chrispbacon.ticket.token}")
    private String TOKEN;

    public HttpStatusCode createGitHubIssue(TicketDto ticketDto, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        String url = GITHUB_API_URL.replace("{owner}", OWNER).replace("{repo}", REPO);

        String jsonBody = String.format("{\"title\": \"%s\", \"body\": \"%s\"}", compileTitle(ticketDto), compileBody(ticketDto, request));

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode() != HttpStatus.CREATED) {
            log.error("Failed to create issue. Status code: " + response.getStatusCode());
        }
        return response.getStatusCode();
    }

    private  String compileTitle(TicketDto ticketDto) {
        return "Support ticket: " + ticketDto.title();
    }

    private String compileBody(TicketDto ticketDto, HttpServletRequest request) {
        Student user = getUser(request);
        return String.format("\"User\": \"%s\"\n\"Email\": \"%s\"\n\"%s\"", user.getUsername(), user.getEmail(), ticketDto.body());
    }

    private Student getUser(HttpServletRequest request) {
        String username = getUsername(request);
        return userRepository.findByUsername(username).orElseThrow();
    }

    private String getUsername(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken = authHeader.substring(7);
        return jwtService.extractUsername(refreshToken);
    }

}
