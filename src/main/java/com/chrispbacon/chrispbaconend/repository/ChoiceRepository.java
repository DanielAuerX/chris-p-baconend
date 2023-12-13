package com.chrispbacon.chrispbaconend.repository;

import com.chrispbacon.chrispbaconend.chatbot.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, UUID> {

    List<Choice> findAllByPromptId (UUID promptId);
}
