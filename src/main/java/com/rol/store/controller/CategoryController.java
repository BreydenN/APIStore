package com.rol.store.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rol.store.entity.Category;
import com.rol.store.service.CategoryService;


@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/list")
	public ResponseEntity<?> listAll(){
		Collection<Category> collection = categoryService.findAll();
		if(collection.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(collection, HttpStatus.OK);

	}
	
	@GetMapping("/listActive")
	public ResponseEntity<?> listAllCactive(){
		Collection<Category> collection = categoryService.findAllActive();
		if(collection.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(collection, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Category c){
		categoryService.insert(c);
		return new ResponseEntity<>("¡You have successfully registered!", HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category c){
		Category categoryDb = categoryService.findById(id);
		if (categoryDb != null) {
			categoryDb.setName(c.getName());
			categoryDb.setIsActive(c.getIsActive());
			categoryService.update(categoryDb);
			 
			return new ResponseEntity<>("¡It has been successfully updated!", HttpStatus.OK);
		}
		return new ResponseEntity<>("¡ID Category: " + id + " not found!", HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Category categoryDb = categoryService.findById(id);
		if (categoryDb != null) {
			categoryService.delete(categoryDb);
			
			return new ResponseEntity<>("¡Has been removed!", HttpStatus.OK);
		}
		return new ResponseEntity<>("¡ID Category: " + id + " not found!", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Category categoryDb = categoryService.findById(id);
		if (categoryDb != null) {
			return new ResponseEntity<>(categoryDb, HttpStatus.FOUND);
		}
		return new ResponseEntity<>("¡ID Category: " + id + " not found!", HttpStatus.NOT_FOUND);
	}

}
