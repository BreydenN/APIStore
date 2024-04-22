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

import com.rol.store.entity.Product;
import com.rol.store.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	public ResponseEntity<?> listAll(){
		Collection<Product> collection = productService.findAll();
		if (collection.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(collection, HttpStatus.OK);
	}
	
	@GetMapping("/listActive")
	public ResponseEntity<?> listAllActive(){
		Collection<Product> collection = productService.findAllActive();
		if (collection.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(collection, HttpStatus.OK);
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Product p){
		productService.insert(p);
		return new ResponseEntity<>("¡You have successfully registered!", HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product p){
		Product productDb = productService.findById(id);
		if (productDb != null) {
			productDb.setName(p.getName());
			productDb.setDescription(p.getDescription());
			productDb.setStock(p.getStock());
			productDb.setPrice(p.getPrice());
			productDb.setImage(p.getImage());
			productDb.setIsActive(p.getIsActive());
			productDb.setCategory(p.getCategory());
			
			productService.update(productDb);
			
			return new ResponseEntity<>("¡It has been successfully updated!", HttpStatus.OK);
		}
		return new ResponseEntity<>("¡ID Product: " + id + " not found!", HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Product productDb = productService.findById(id);
		if (productDb != null) {
			productService.delete(productDb);
			
			return new ResponseEntity<>("¡Has been removed!", HttpStatus.OK);
		}
		return new ResponseEntity<>("¡ID Product: " + id + " not found!", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Product productDb = productService.findById(id);
		if (productDb != null) {
			return new ResponseEntity<>(productDb, HttpStatus.FOUND);
		}
		return new ResponseEntity<>("¡ID Product: " + id + " not found!", HttpStatus.NOT_FOUND);
	}

}
