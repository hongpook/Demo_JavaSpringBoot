package com.example.projectDemo.Services;

import com.example.projectDemo.Entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

public class RestTemplateProvider {

    RestTemplate restTemplate= new RestTemplate();
    String productUrl = "http://localhost:8080/api/products";

    public List<Product> getProductData(){
        ResponseEntity<Product[]>  res = restTemplate.getForEntity(productUrl, Product[].class);
        return  Arrays.asList(res.getBody());
    }

    public Product getProductById(Long id){
        ResponseEntity<Product> res = restTemplate.getForEntity(productUrl +"/"+id, Product.class);
        return res.getBody();
    }

    public Product createProduct(Product product){
        ResponseEntity<Product> res = restTemplate.postForEntity(productUrl, product, Product.class);
        return res.getBody();
    }
}


