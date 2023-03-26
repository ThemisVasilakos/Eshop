package gr.mindthecode.eshop.service;

import gr.mindthecode.eshop.model.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);
    List<Category> getCategories();
}
