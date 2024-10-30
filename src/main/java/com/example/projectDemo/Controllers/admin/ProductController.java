package com.example.projectDemo.Controllers.admin;

import com.example.projectDemo.Entity.Product;
import com.example.projectDemo.Repositories.ProductRepository;
import com.example.projectDemo.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/api/")
@RestController
public class ProductController {

    @Autowired
    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }


    @GetMapping("/products")
    public List<Product> getAllProducts(Model model){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public Product createUser(@RequestBody Product product) {
        return productRepository.save(product);
    }


    @PutMapping("/products/{id}")
    public Product updateUser(@PathVariable Long id, @RequestBody Product product){

        return productRepository.findById(id)
                .map(prd -> {
                    prd.setPrice(product.getPrice());
                    prd.setDescription(product.getDescription());
                    prd.setProductName(product.getProductName());
                    prd.setColor(product.getColor());

                    return productRepository.save(prd);
                })
                .orElseGet(() -> {
                    product.setId(id);
                    return productRepository.save(product);

                });
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        System.out.println("Xóa thành công");
    }

}
