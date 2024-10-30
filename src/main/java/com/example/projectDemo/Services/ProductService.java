package com.example.projectDemo.Services;

import com.example.projectDemo.Entity.Product;
import com.example.projectDemo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public Product getProductById(long id) {
        return productRepository.findById(id);
    }

}
