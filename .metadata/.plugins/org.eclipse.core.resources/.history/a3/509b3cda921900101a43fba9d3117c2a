package com.company.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.app.entity.Product;
import com.company.app.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	
	public List<Product> findAll(){  	//제품목록
		return productRepository.findAll();
	}
	
	public Product findById(Long no) {  	//제품 1건 조회(제품 상세보기)
		return productRepository.findById(no).orElse(null);
	}
	
	public Product save(Product product) {  	//제품 등록
		return productRepository.save(product);
	}
	
	public Product update(Product product) { 	//제품 변경
		return productRepository.save(product);
	}
	
	public void delete(Long no) {  	//제품 삭제
		productRepository.deleteById(no);
	}
}
