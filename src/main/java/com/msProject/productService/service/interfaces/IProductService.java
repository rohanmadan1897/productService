package com.msProject.productService.service.interfaces;

import com.msProject.productService.models.Product;

import java.util.List;

public interface IProductService {

    boolean addProduct(Product product);

    List<Product> getAllProducts();
}
