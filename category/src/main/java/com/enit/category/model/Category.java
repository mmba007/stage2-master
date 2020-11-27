package com.enit.category.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "advertiser")


public class Category {
    @Id
    private String categoryID;

    public Category(String categoryID, String catergoryName, List<String> subCategory) {
        this.categoryID = categoryID;
        this.catergoryName = catergoryName;
        this.subCategory = subCategory;
    }

    public Category(String catergoryName, List<String> subCategory) {
        this.catergoryName = catergoryName;
        this.subCategory = subCategory;
    }

    public void setSubCategory(List<String> subCategory) {
        this.subCategory = subCategory;
    }

    public List<String> getSubCategory() {
        return subCategory;
    }

    private String catergoryName;
    private List<String> subCategory;

    public Category(String catergoryName) {
        this.catergoryName = catergoryName;
    }

    public Category() {
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setCatergoryName(String catergoryName) {
        this.catergoryName = catergoryName;
    }

    public String getCatergoryName() {
        return catergoryName;
    }



}
