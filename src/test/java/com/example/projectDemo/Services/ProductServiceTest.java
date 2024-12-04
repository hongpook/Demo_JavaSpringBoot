package com.example.projectDemo.Services;

import com.example.projectDemo.Entity.Product;
import com.example.projectDemo.Repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @BeforeAll
    public static void display(){
        log.info("<-------- BEGIN -------->");
    }
    
    @Test
    void createProductTest(){
        Product prd = new Product();

        prd.setId(100L);
        prd.setProductName("Balo");
        prd.setPrice("12345");
        prd.setCategories("Balo");
        prd.setDescription("Balo");
        prd.setColor("Blue");
        prd.setQuantity(12);
        prd.setState(true);
        Mockito.when(productRepository.save(prd)).thenReturn(prd);

        Product addedProduct = productService.createProduct(prd);
        // đảm bảo rằng biến addedProduct không được null
        assertNotNull(addedProduct);
        // so sánh giá trị id của 2 đối tượng prd và addedProduct để đảm bảo cả 2 đều giống nhau
        assertEquals(prd.getId(),addedProduct.getId());
        // so sánh giá trị productName của 2 đối tượng prd và addedProduct để đảm bảo cả 2 đều giống nhau
        assertEquals(prd.getProductName(),addedProduct.getProductName());
        // kiểm tra id của prd có phải là 100 hay không
        assertTrue(prd.getId()==100);

        log.info("Sản phẩm vừa được thêm: " + prd.getProductName());
    }



    @AfterAll
    public static void display2(){
        log.info("<-------- END -------->");
    }
}
