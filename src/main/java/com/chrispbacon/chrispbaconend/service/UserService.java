package com.chrispbacon.chrispbaconend.service;

import com.chrispbacon.chrispbaconend.exception.IllegalInputException;
import com.chrispbacon.chrispbaconend.model.RegisterDto;
import com.chrispbacon.chrispbaconend.model.Student;
import com.chrispbacon.chrispbaconend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Student registerUser(RegisterDto registerDto){
        if (userRepository.existsStudentByUserName(registerDto.userName())){
            throw new IllegalInputException("The user name "+ registerDto.userName()+" already exists.");
        }
        Student student = new Student(UUID.randomUUID(), registerDto.userName(), registerDto.password(), registerDto.firstName(), registerDto.lastName());
        return userRepository.save(student);
    }
}
