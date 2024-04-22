package com.rol.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rol.store.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query("SELECT c FROM Category c WHERE c.isActive = true")
	List<Category> findAllActive();
	
}
