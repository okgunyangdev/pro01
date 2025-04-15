package com.company.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{ 
	List<Product> findTop3ByOrderByResdateDesc();
}