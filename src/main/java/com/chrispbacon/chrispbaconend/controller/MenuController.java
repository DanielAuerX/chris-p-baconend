package com.chrispbacon.chrispbaconend.controller;

import com.chrispbacon.chrispbaconend.model.category.Category;
import com.chrispbacon.chrispbaconend.model.menu.OverviewDto;
import com.chrispbacon.chrispbaconend.service.MenuService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("api/chrispbacon/overview")
@CrossOrigin
public class MenuController {

    private final MenuService menuService;
    private final Bucket bucket;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
        Bandwidth limit = Bandwidth.classic(3, Refill.greedy(3, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping()
    public ResponseEntity<List<OverviewDto>> getCategoriesByLearningField() {
        if(bucket.tryConsume(1)){
            return ResponseEntity.ok(menuService.getOverview());
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping("/category")
    public ResponseEntity<Category> getCategory(@RequestParam Long categoryId) {
        return ResponseEntity.ok(menuService.getCategory(categoryId));
    }

}
