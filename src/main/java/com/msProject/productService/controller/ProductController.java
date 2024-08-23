package com.msProject.productService.controller;

import com.msProject.productService.models.Product;
import com.msProject.productService.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    @Autowired
    ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        boolean isProductCreated = productService.addProduct(product);
        if(isProductCreated) {
            return ResponseEntity.ok("Product created successfully");
        }
        return ResponseEntity.badRequest().body("Product creation failed");
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
