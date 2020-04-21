package org.upgrad.services;

import org.upgrad.models.Category;

	
public interface CategoryService {

    void addCategory(String title, String description);
    
    public Iterable<Category> getAllCategories();
}
