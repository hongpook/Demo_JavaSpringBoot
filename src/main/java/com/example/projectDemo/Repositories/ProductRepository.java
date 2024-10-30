package com.example.projectDemo.Repositories;

import com.example.projectDemo.Entity.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById (long id);

    Product findByProductName (String productName);

    Product deleteById(long id);
}
