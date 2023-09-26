package com.chrispbacon.chrispbaconend.repository;

import com.chrispbacon.chrispbaconend.model.category.Category;
import com.chrispbacon.chrispbaconend.model.category.CategoryInputRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRequestRepository extends JpaRepository<CategoryInputRequest, Long> {
}
