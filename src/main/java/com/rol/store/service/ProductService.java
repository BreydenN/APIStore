package com.rol.store.service;

import java.util.Collection;

import com.rol.store.entity.Product;

public interface ProductService {

	Collection<Product> findAll();
	Collection<Product> findAllActive();
	void insert(Product p);
	void update(Product p);
	Product delete(Product p);
	Product findById(Long id);
	
}
