package com.example.projectDemo.Services;

import com.example.projectDemo.Entity.Product;
import com.example.projectDemo.Exception.NoSuchProductExistsException;
import com.example.projectDemo.Exception.ProductAlreadyExistsException;
import com.example.projectDemo.Repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@Slf4j
public class ProductService{
    @Autowired
    private ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);



    public List<Product> getAllProducts() {

        return productRepository.findAllProduct();
    }

    public List<Product> getAllProductsfalse() {

        return productRepository.findAllProductFalse();
    }


    public List<Product> searchProduct(String keyword) {
        return productRepository.searchProduct(keyword);
    }

    public Product createProduct(@RequestBody Product product){
        logger.info("Tạo sản phẩm thành công!!");
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product){
        Optional<Product> sp = Optional.ofNullable(productRepository.findById(id).orElseThrow(()
                -> new NoSuchProductExistsException("Sản phẩm k tồn tại")));
        return  sp
                .map(prd -> {
                    prd.setPrice(product.getPrice());
                    prd.setDescription(product.getDescription());
                    prd.setProductName(product.getProductName());
                    prd.setColor(product.getColor());
                    prd.setQuantity(product.getQuantity());
                    prd.setState(product.getState());
                    prd.setCategories(product.getCategories());
                    logger.info("Sản phẩm " + id + " cập nhật thành công!!");
                    return productRepository.save(prd);
                })
                .orElseGet(() -> {
                    product.setId(id);
                    logger.info("Sản phẩm " + id + " cập nhật không thành công!!");
                    return productRepository.save(product);

                });
    }

    public Product getProductById(Long id) {
        logger.info("Sản phẩm có id= " + id);
        return productRepository.findProductById(id);
    }


    public Product deleteProduct(Long id){
        return productRepository.deleteProduct(id);
    }

}
