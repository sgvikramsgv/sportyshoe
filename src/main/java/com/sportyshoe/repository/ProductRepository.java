package com.sportyshoe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoe.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAllByCategoryId(int id);

}
