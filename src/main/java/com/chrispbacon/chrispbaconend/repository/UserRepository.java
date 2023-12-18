package com.chrispbacon.chrispbaconend.repository;

import com.chrispbacon.chrispbaconend.model.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByUserName(String userName);
}
