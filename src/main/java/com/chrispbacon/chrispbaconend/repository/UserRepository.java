package com.chrispbacon.chrispbaconend.repository;

import com.chrispbacon.chrispbaconend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Student, UUID> {
    boolean existsStudentByUserName(String userName);
}
