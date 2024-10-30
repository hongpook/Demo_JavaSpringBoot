package com.example.projectDemo.Controllers.admin;

import com.example.projectDemo.Entity.Product;
import com.example.projectDemo.Repositories.ProductRepository;
import com.example.projectDemo.Services.ApiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApiController {
    private final ApiServices apiServices;

    @Autowired
    public ApiController(ApiServices apiServices) {
        this.apiServices = apiServices;
    }

    @GetMapping("/admin/product")
    public String getAllProduct(Model model) {
        Object productList = apiServices.fetchProductList();
        model.addAttribute("productList", productList);
        return "admin/product/listProducts";
    }

    @GetMapping("/admin/product/{id}")
    public String getDetailProduct(@PathVariable("id") Long id, Model model) {
        Object productDetail = apiServices.fetchDetailProduct(id);
        model.addAttribute("productDetail", productDetail);
        return "admin/product/detailProduct";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model, Product product){
        return "admin/product/addNewProduct";
    }

    @PostMapping("/admin/product/create")
    public String createProduct(Model model, Product product){
        Object newProduct = apiServices.postProduct(product);
        model.addAttribute("newProduct", newProduct);
        return "redirect:/admin/product";
    }


    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(@PathVariable long id, Model model,Product product){
        return "admin/product/editProduct";
    }

//    @PostMapping("/admin/product/update/{id}")
//    public String updateProduct(@PathVariable long id, Model model, Product product){
//        Object currentProduct = apiServices.updateProduct(id, product);
//        model.addAttribute("currentProduct", currentProduct);
//        return "redirect:admin/product";
//    }


    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(@PathVariable long id, Model model){
        Object currentProduct = apiServices.deleteProduct(id);
        model.addAttribute("currentProduct", currentProduct);
        return "redirect:/admin/product";
    }

//    @PostMapping ("/admin/product/delete/{id}")
//    public String deleteProduct(@PathVariable long id, Model model){
//        return "redirect:admin/product";
//    }

}
