package com.chrispbacon.chrispbaconend.repository;

import com.chrispbacon.chrispbaconend.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findCategoriesByLearningFieldId(long learningFieldId);

}
