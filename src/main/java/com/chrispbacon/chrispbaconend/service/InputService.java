package com.chrispbacon.chrispbaconend.service;

import com.chrispbacon.chrispbaconend.model.category.CategoryInputRequest;
import com.chrispbacon.chrispbaconend.repository.CategoryRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InputService {

    private final CategoryRequestRepository categoryRequestRepository;

    public CategoryInputRequest insertIntoCategoryRequest(CategoryInputRequest categoryInputRequest){
        return categoryRequestRepository.save(categoryInputRequest);
    }

}
