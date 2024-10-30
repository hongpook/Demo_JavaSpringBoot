package com.example.projectDemo.Services;

import com.example.projectDemo.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ApiServices {
//    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private RestTemplate restTemplate;

    public Object fetchProductList() {
        String apiUrl = "http://localhost:8080/api/products";
        return restTemplate.getForObject(apiUrl, Object.class);
    }

    public Object fetchDetailProduct(Long id){
        String apiUrl = "http://localhost:8080/api/products/" + id;
        return restTemplate.getForObject(apiUrl, Object.class);
    }

    public Object postProduct(Object product) {
        String apiUrl = "http://localhost:8080/api/products";
        return restTemplate.postForObject(apiUrl, product, Object.class);
    }

//    public Object updateProduct(Long id, Product product){
//        String apiUrl = "http://localhost:8080/api/products/" + id;
//        return restTemplate.exchange(apiUrl, HttpMethod.PUT, null, Object.class);
//    }

    public Object deleteProduct(Long id){
        String apiUrl = "http://localhost:8080/api/products/" + id;
        return restTemplate.exchange(apiUrl, HttpMethod.DELETE, null, Object.class);
    }


}
