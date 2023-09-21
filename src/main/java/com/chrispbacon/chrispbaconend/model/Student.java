package com.chrispbacon.chrispbaconend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
public class Student {
    @Id
    private UUID id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;

}
