package com.rol.store.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rol.store.entity.Product;
import com.rol.store.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	

	@Override
	public Collection<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Collection<Product> findAllActive() {
		return productRepository.findAllActive();
	}

	@Override
	public void insert(Product p) {
		productRepository.save(p);
		
	}

	@Override
	public void update(Product p) {
		productRepository.save(p);
	}

	@Override
	public Product delete(Product p) {
		Product obProduct = productRepository.findById(p.getId()).orElse(null);
		obProduct.setIsActive(false);
		return productRepository.save(obProduct);
	}

	@Override
	public Product findById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

}
