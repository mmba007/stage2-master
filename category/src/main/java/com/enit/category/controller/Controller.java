package com.enit.category.controller;

import com.enit.category.model.Category;
import com.enit.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    public Controller(CategoryService categoryService){
        this.categoryService=categoryService;
    }


   private CategoryService categoryService;

    @PostMapping("/save/{categoryName}")
    public ResponseEntity<String> createCategory(@PathVariable String categoryName) {
        try {
            categoryService.createCategory(new Category(categoryName));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(
                "category is created successfully",
                HttpStatus.OK);
    }
    @PutMapping("/update/{categoryName}/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable String id, @PathVariable String categoryName) {
        boolean isUpdated = false;
        try {
            isUpdated = categoryService.updateCategory(id, categoryName);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (isUpdated) {
            return new ResponseEntity<>(
                    "category is updated successfully",
                    HttpStatus.OK);

        }
        return new ResponseEntity<>(
                "category cannot be updated",
                HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id){
        boolean isDeleted;
        try {
            isDeleted = categoryService.deleteCategory(id);
            if(isDeleted){

                return new ResponseEntity<>(
                        "category is deleted successfully",
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(
                    "category does not exist",
                    HttpStatus.EXPECTATION_FAILED);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(
                "service is down",
                HttpStatus.SERVICE_UNAVAILABLE);

    }


    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategory(){

        try {
            List<Category> categories=categoryService.getAllCategory();
            return new ResponseEntity<>(categories ,HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.SERVICE_UNAVAILABLE);

    }


}
