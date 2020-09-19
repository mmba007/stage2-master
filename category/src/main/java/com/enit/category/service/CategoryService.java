package com.enit.category.service;

import com.enit.category.model.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    public void createCategory(Category category) throws Exception;
    public boolean updateCategory(String id,String categoryName) throws Exception;
    public boolean deleteCategory(String id) throws Exception;
    public List<Category> getAllCategory() throws Exception;


}
