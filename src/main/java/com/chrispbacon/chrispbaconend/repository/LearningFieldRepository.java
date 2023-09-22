package com.chrispbacon.chrispbaconend.repository;

import com.chrispbacon.chrispbaconend.model.Category;
import com.chrispbacon.chrispbaconend.model.LearningField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningFieldRepository extends JpaRepository<LearningField, Long> {
}
