package com.rol.store.service;

import java.util.Collection;

import com.rol.store.entity.Category;

public interface CategoryService {

	Collection<Category> findAll();
	Collection<Category> findAllActive();
	void insert(Category c);
	void update(Category c);
	Category delete(Category c);
	Category findById(Long id);
	
}
