package com.example.projectDemo.Controllers.admin;

import com.example.projectDemo.Entity.Product;
import com.example.projectDemo.Entity.User;
import com.example.projectDemo.Repositories.ProductRepository;
import com.example.projectDemo.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;


@RequestMapping("/api/")
@RestController
public class ProductController {

    @Autowired
    private final ProductService productService;
    private final ProductRepository productRepository;
    private static final Logger logger = LogManager.getLogger(ProductController.class);

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }


    @GetMapping("/products")
    public List<Product> getAllProducts(Model model, @Param("keyword") String keyword){

        List<Product> products;

        if (keyword != null && !keyword.trim().isEmpty()) {
            logger.info("Tìm kiếm sản phẩm: " + keyword);
            return productService.searchProduct(keyword);
        }
        logger.info("Tất cả sản phẩm!!!");
        return productService.getAllProducts();
    }


    @GetMapping("/products-false")
    public List<Product> getAllProductsFalse(Model model){
        logger.info("Tất cả sản phẩm có trạng thái false!!!");
        return productService.getAllProductsfalse();
    }


    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }


    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable Long id){

        System.out.println("Xóa thành công");
        return productService.deleteProduct(id);
    }

}
