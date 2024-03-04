package com.example.bookshop.services;

import com.example.bookshop.entities.Category;
import com.example.bookshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        Random random = new Random();
        long size = this.categoryRepository.count();
        int categoryCount = random.nextInt((int) size + 1);
        Set<Integer> categoryIds = new HashSet<>();
        for (int i = 0; i < categoryCount; i++) {
            int nextId = random.nextInt((int) size + 1);
            categoryIds.add(nextId);
        }
        List <Category> allById = this.categoryRepository.findAllById(categoryIds);
        return new HashSet<>(allById);
    }
}
