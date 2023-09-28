package com.chrispbacon.chrispbaconend.controller;

import com.chrispbacon.chrispbaconend.model.category.Category;
import com.chrispbacon.chrispbaconend.model.menu.OverviewDto;
import com.chrispbacon.chrispbaconend.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/chrispbacon/overview")
@CrossOrigin
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping()
    public ResponseEntity<List<OverviewDto>> getCategoriesByLearningField() {
        return ResponseEntity.ok(menuService.getOverview());
    }

    @GetMapping("/category")
    public ResponseEntity<Category> getCategory(@RequestParam Long categoryId) {
        return ResponseEntity.ok(menuService.getCategory(categoryId));
    }

}
