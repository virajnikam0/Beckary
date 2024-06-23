package com.pkg.services;

import com.pkg.models.Category;
import com.pkg.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void addCategory(Category category){
        categoryRepository.save(category);
    }
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public void deleteCategoryById(int id){
        categoryRepository.deleteById(id);
    }
    public Optional<Category> updateCategoryById(int id){
        return categoryRepository.findById(id);
    }





}
