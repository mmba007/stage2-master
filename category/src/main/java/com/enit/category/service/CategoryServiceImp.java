package com.enit.category.service;

import com.enit.category.model.Category;
import com.enit.category.reposiory.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository repository;


    @Override
    public void createCategory(Category category) throws Exception {
        repository.save(category);
    }

    @Override
    public boolean updateCategory(String id, String categoryName) throws Exception {
        Optional<Category> category= repository.findById(id);
        if(category.isPresent())
        {   category.get().setCatergoryName(categoryName);
            repository.save(category.get());
            return true;
        }
       return false;
    }

    @Override
    public boolean deleteCategory(String id) throws Exception {
        Optional<Category> category= repository.findById(id);
        if(category.isPresent())
        {
            repository.deleteById(id);
            return true;
        }
        return false;

    }
    @Override
    public List<Category> getAllCategory() throws Exception{

        return repository.findAll();
    }



}
