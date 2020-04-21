package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Category;
import org.upgrad.repositories.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategory(String title, String description) {
        categoryRepository.addCategory(title, description);
    }
    
    @Override
	public Iterable<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
}
