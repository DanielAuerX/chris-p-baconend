package com.chrispbacon.chrispbaconend.auth;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    private String email;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
}
