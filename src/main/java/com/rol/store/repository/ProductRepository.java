package com.rol.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rol.store.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("SELECT p FROM Product p WHERE p.isActive = true")
	List<Product> findAllActive();
	
}
