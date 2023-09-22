package com.chrispbacon.chrispbaconend.repository;

import com.chrispbacon.chrispbaconend.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    List<Answer> findAnswersByQuestionId(UUID question_id);

    @Query(value = """
            SELECT id
            FROM answer a
            WHERE a.question_id = :question_id and a.correct = true""", nativeQuery = true)
    List<UUID> findCorrectAnswerIdsByQuestionId(UUID question_id);
    List<Answer> findAnswersByQuestionIdAndCorrectIsTrue(UUID question_id);
}
