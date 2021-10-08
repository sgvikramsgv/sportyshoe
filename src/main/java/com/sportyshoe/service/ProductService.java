package com.sportyshoe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoe.model.Product;
import com.sportyshoe.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;	
	
	
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public void removeProductById(long id) {
		productRepository.deleteById(id);
	}
	public Optional<Product> getProductById(long id) {
		return productRepository.findById(id);
	}
	
	public List<Product> getProductsByID(int id){
		return productRepository.findAllByCategoryId(id);
	}
}
