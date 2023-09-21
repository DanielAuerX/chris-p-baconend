package com.chrispbacon.chrispbaconend.repository;

import com.chrispbacon.chrispbaconend.model.Answer;
import com.chrispbacon.chrispbaconend.model.Question;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    @Query(value = """
            SELECT *
            FROM question q
            WHERE q.category_id = :category_id ORDER BY random() LIMIT 1""", nativeQuery = true)
    Question findRandomQuestionByCategoryId(long category_id);
}
