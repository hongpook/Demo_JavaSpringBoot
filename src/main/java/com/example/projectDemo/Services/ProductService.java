package com.example.projectDemo.Services;

import com.example.projectDemo.Entity.Product;
import com.example.projectDemo.Repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@Slf4j
public class ProductService{
    @Autowired
    private ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);



    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public Product getProductById(long id) {
        logger.info("Sản phẩm có id= " + id);
        return productRepository.findById(id);
    }


}
