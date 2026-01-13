package org.teamscore.individualTask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.teamscore.individualTask.models.entity.Category;
import org.teamscore.individualTask.repositories.CategoryRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        category = categoryRepository.save(category);
        return category;
    }

    public Category updateCategory(Category category) {
        var oldCategory = categoryRepository.findById(category.getId()).orElseThrow();
        oldCategory.setColor(category.getColor());
        oldCategory.setName(category.getName());
        oldCategory.setDescription(category.getDescription());
        return categoryRepository.save(oldCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    public List<Category> getAllCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name).orElseThrow();
    }

//    public List<Category> getAllCategoryWithCostByPeriod(LocalDate from, LocalDate to){
//        return categoryRepository
//    }
}
