package com.example.shopjava6.imp;


import com.example.shopjava6.entity.Category;
import com.example.shopjava6.ripository.CategoryRepository;
import com.example.shopjava6.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
