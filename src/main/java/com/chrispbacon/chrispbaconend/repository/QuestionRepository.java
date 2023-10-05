package com.chrispbacon.chrispbaconend.repository;

import com.chrispbacon.chrispbaconend.model.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    @Query(value = """
            SELECT *
            FROM question q
            WHERE q.category_id = :categoryId ORDER BY random() LIMIT :numberOfQuestions""", nativeQuery = true)
    List<Question> findRandomQuestionByCategoryId(long categoryId, int numberOfQuestions);

    List<Question> findAllByCategoryId(long category_id);
}
