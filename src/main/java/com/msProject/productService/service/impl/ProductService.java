package com.msProject.productService.service.impl;

import com.msProject.productService.models.Product;
import com.msProject.productService.persistence.ProductRepository;
import com.msProject.productService.service.interfaces.IProductService;
import com.msProject.productService.service.mappers.IMapProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    private ProductRepository productRepository;
    private IMapProduct productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, IMapProduct productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public boolean addProduct(Product product) {
        Product productModel = productMapper.mapProductToProductDTO(product);
        productRepository.save(productModel);
        return true;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
