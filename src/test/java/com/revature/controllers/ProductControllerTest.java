package com.revature.controllers;

import java.util.List;

import com.revature.models.ProductEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {

    @Autowired
    ProductController productController;

    @Test
    public void testGetAllProducts() {
        List<ProductEntity> products = productController.getAllProducts();
        for (ProductEntity product : products) {
            assert product.getProductName() != null;
            assert product.getProductCategory() != null;
            assert product.getAveragePrice() != null;
        }
    }
}
