package com.chrispbacon.chrispbaconend.repository;

import com.chrispbacon.chrispbaconend.model.QuestionAnswerInputRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRequestRepository extends JpaRepository<QuestionAnswerInputRequest, Long> {
}
