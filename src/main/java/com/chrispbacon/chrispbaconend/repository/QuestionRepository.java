package com.chrispbacon.chrispbaconend.repository;

import com.chrispbacon.chrispbaconend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    @Query(value = """
            SELECT *
            FROM question q
            WHERE q.category_id = :category_id ORDER BY random() LIMIT 1""", nativeQuery = true)
    Question findRandomQuestionByCategoryId(long category_id);
}
