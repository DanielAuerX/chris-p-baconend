package com.chrispbacon.chrispbaconend.auth.token;

import com.chrispbacon.chrispbaconend.config.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtTokenUtil {

    private final JwtService jwtService;

    public String extractEmailFromToken(String token) {
        return jwtService.extractClaim(token, Claims::getSubject);
    }
}
