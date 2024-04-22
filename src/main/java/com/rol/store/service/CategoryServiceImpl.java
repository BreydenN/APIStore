package com.rol.store.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rol.store.entity.Category;
import com.rol.store.repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	@Transactional(readOnly = true)
	public Collection<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Category> findAllActive() {
		return categoryRepository.findAllActive();
	}

	@Override
	public void insert(Category c) {
		categoryRepository.save(c);
		
	}

	@Override
	public void update(Category c) {
		categoryRepository.save(c);
		
	}

	@Override
	public Category delete(Category c) {
		Category obCategory = categoryRepository.findById(c.getId()).orElse(null);
		obCategory.setIsActive(false);
		return categoryRepository.save(obCategory);
	}

	@Override
	public Category findById(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}

}
