package com.company.app.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.entity.Product;
import com.company.app.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product/api")
@CrossOrigin(origins = "*")   //CORS 허용
public class ProductRestController {
    private final ProductService productService;

    @GetMapping("/list")
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/{no}")
    public Product getById(@PathVariable("no") Long no) {
        return productService.findById(no);
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/delete/{no}")
    public void deleteProduct(@PathVariable("no") Long no) {
        productService.delete(no);
    }
}
