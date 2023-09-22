package com.chrispbacon.chrispbaconend.model.menu;

import com.chrispbacon.chrispbaconend.model.LearningField;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OverviewDto {
    private final LearningField learningField;
    private final List<CategoryDto> categories;
}
