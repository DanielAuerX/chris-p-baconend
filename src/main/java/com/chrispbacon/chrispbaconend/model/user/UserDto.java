package com.chrispbacon.chrispbaconend.model.user;

import lombok.Getter;

import java.util.List;

@Getter
public class UserDto {

    private final String email;
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final Role role;
    private final List<Long> finishedCategories;

    public UserDto(Student student) {
        this.email = student.getEmail();
        this.userName = student.getUsername();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.role = student.getRole();
        this.finishedCategories = student.getFinishedCategories();
    }
}
