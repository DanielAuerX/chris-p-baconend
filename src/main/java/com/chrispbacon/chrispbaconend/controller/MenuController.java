package com.chrispbacon.chrispbaconend.controller;

import com.chrispbacon.chrispbaconend.repository.CategoryRepository;
import com.chrispbacon.chrispbaconend.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/chrispbacon/overview")
@CrossOrigin
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping()
    public ResponseEntity<Object> getCategoriesByLearningField() {
        return ResponseEntity.ok(menuService.getOverview());
    }

//    @GetMapping()
//    public ResponseEntity<Object> getCategoriesByLearningField(@RequestParam Long learningFieldId) {
//        return ResponseEntity.ok(categoryRepository.findCategoriesByLearningFieldId(learningFieldId));
//    }
}
