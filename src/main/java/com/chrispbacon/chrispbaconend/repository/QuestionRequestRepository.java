package com.chrispbacon.chrispbaconend.repository;

import com.chrispbacon.chrispbaconend.model.question.QuestionInputRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRequestRepository extends JpaRepository<QuestionInputRequest, Long> {
}
