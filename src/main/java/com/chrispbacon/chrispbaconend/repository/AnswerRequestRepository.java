package com.chrispbacon.chrispbaconend.repository;

import com.chrispbacon.chrispbaconend.model.answer.AnswerInputRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRequestRepository extends JpaRepository<AnswerInputRequest, Long> {
}
