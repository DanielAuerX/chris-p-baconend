package com.chrispbacon.chrispbaconend.auth;

import com.chrispbacon.chrispbaconend.config.JwtService;
import com.chrispbacon.chrispbaconend.model.token.Token;
import com.chrispbacon.chrispbaconend.model.user.Role;
import com.chrispbacon.chrispbaconend.model.user.Student;
import com.chrispbacon.chrispbaconend.repository.TokenRepository;
import com.chrispbacon.chrispbaconend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private TokenRepository tokenRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private AuthenticationService authenticationService;


    @Test
    void register_ShouldReturnCorrectAuthenticationResponse() {
        RegisterRequest request = new RegisterRequest("test@email.com", "testName", "xyz", "max", "mustermann");
        when(passwordEncoder.encode(request.getPassword())).thenReturn("xyz");
        when(userRepository.save(any(Student.class))).thenReturn(new Student(UUID.randomUUID(), request.getEmail(), request.getUserName(), request.getPassword(), request.getFirstName(), request.getLastName(), Role.USER, new ArrayList<>()));
        String token = "abcdefgh";
        when(jwtService.generateToken(any(UserDetails.class))).thenReturn(token);
        String refreshToken = "1234567890";
        when(jwtService.generateRefreshToken(any(UserDetails.class))).thenReturn(refreshToken);
        when(tokenRepository.save(any(Token.class))).thenReturn(null);

        AuthenticationResponse result = authenticationService.register(request);

        assertEquals(token, result.getAccessToken());
        assertEquals(refreshToken, result.getRefreshToken());
        assertNotNull(result.getUser());
        assertEquals(request.getUserName(), result.getUser().getUserName());
        verify(userRepository).save(any(Student.class));
    }

    @Test
    void authenticate_ShouldReturnCorrectAuthenticationResponse() {
        Student student = new Student(UUID.randomUUID(), "test@email.com", "maxCool", "123", "hans", "müller", Role.USER, new ArrayList<>());
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(student.getUsername(), student.getPassword());
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userRepository.findByUsername(authenticationRequest.getUserName())).thenReturn(Optional.of(student));
        String token = "abcdefgh";
        when(jwtService.generateToken(any(UserDetails.class))).thenReturn(token);
        String refreshToken = "1234567890";
        when(jwtService.generateRefreshToken(any(UserDetails.class))).thenReturn(refreshToken);

        AuthenticationResponse result = authenticationService.authenticate(authenticationRequest);

        assertEquals(token, result.getAccessToken());
        assertEquals(refreshToken, result.getRefreshToken());
        assertNotNull(result.getUser());
        assertEquals(authenticationRequest.getUserName(), result.getUser().getUserName());
        verify(tokenRepository).save(any(Token.class));
    }
}