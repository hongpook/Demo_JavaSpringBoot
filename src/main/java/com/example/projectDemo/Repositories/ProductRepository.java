package com.example.projectDemo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.projectDemo.Entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.state = true AND p.quantity > 0")
    List<Product> findAllProduct();

    @Query("SELECT p FROM Product p WHERE p.state = false"
            + " OR p.quantity = 0")
    List<Product> findAllProductFalse();

    @Query("UPDATE Product p SET p.state = false WHERE p.id = ?1")
    Product deleteProduct(Long id);

    @Query("SELECT p FROM Product p WHERE  p.id=%?1% and p.state = true")
    Product findProductById (long id);


    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE ?1 and p.state = true"
            + " OR LOWER(p.categories) LIKE ?1 and p.state = true "
            + " OR LOWER(p.color) LIKE ?1 and p.state = true")
    List<Product> searchProduct (String keyword);


}
