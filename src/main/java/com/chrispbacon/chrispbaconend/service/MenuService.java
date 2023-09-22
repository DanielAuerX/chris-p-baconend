package com.chrispbacon.chrispbaconend.service;

import com.chrispbacon.chrispbaconend.model.Category;
import com.chrispbacon.chrispbaconend.model.LearningField;
import com.chrispbacon.chrispbaconend.model.menu.CategoryDto;
import com.chrispbacon.chrispbaconend.model.menu.OverviewDto;
import com.chrispbacon.chrispbaconend.repository.CategoryRepository;
import com.chrispbacon.chrispbaconend.repository.LearningFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MenuService {

    private final LearningFieldRepository learningFieldRepository;
    private final CategoryRepository categoryRepository;

    public List<OverviewDto> getOverview(){
        List<LearningField> learningFields = learningFieldRepository.findAll();
        List <OverviewDto> overview = new ArrayList<>(learningFields.size());
        for (LearningField learningField : learningFields) {
            List<Category> categoriesByLearningFieldId = categoryRepository.findCategoriesByLearningFieldId(learningField.getId());
            List<CategoryDto> categoriesDto = categoriesByLearningFieldId.stream()
                    .map(category -> new CategoryDto(category.getId(), category.getName(), category.getDescription()))
                    .toList();
            overview.add(new OverviewDto(learningField, categoriesDto));
        }
        return overview;
    }
}
