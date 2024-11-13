package com.example.projectDemo.Controllers.admin;

import com.example.projectDemo.Entity.Product;
import com.example.projectDemo.Services.RestTemplateProvider;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/prd")
public class RestTemplateController {

    private final RestTemplateProvider restTemplateProvider = new RestTemplateProvider();

    @GetMapping
    public String getAllProduct(Model model) {
        model.addAttribute("products", restTemplateProvider.getProductData());
        model.addAttribute("model", new Product());
        return "listProduct";
    }
    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        model.addAttribute("products", restTemplateProvider.getProductById(id));
        return "detailProduct";
    }

    @GetMapping("/create")
    public String getProductCreatePage (Product product, Model model){
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProduct(  Product product, Model model) {
        model.addAttribute("products", restTemplateProvider.createProduct(product));
        return "createProduct";
    }

}
