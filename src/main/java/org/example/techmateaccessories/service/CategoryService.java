package org.example.techmateaccessories.service;

import org.example.techmateaccessories.domain.Category;
import org.example.techmateaccessories.repository.CategoryRepository;
import org.example.techmateaccessories.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository ;
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    public List<Category> findAllCategories(){
         return categoryRepository.findAll();
    }
}
