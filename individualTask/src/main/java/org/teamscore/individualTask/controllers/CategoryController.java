package org.teamscore.individualTask.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamscore.individualTask.models.entity.Category;
import org.teamscore.individualTask.services.CategoryService;

@Tag(name = "Category")
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Get all categories")
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        var result = categoryService.getAllCategory(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Search by name")
    @GetMapping("/search")
    public ResponseEntity<?> getByName(
            @Parameter(description = "Category name")
            @RequestParam(name = "name") String name) {
        var result = categoryService.getCategoryByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Create category")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Category category) {
        category = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @Operation(summary = "Update category")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Category category) {
        category = categoryService.updateCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @Operation(summary = "Delete category")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @Parameter(description = "Category ID")
            @PathVariable(name = "id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}