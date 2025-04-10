package com.company.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{ }