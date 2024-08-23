package com.msProject.productService.service.impl;

import com.msProject.productService.gateways.service.CouponGatewayService;
import com.msProject.productService.models.Product;
import com.msProject.productService.persistence.ProductRepository;
import com.msProject.productService.service.interfaces.IProductService;
import com.msProject.productService.service.mappers.IMapProduct;
import com.msProject.productService.service.models.Coupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService implements IProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private ProductRepository productRepository;
    private IMapProduct productMapper;
    private CouponGatewayService couponClient;

    @Autowired
    public ProductService(ProductRepository productRepository, IMapProduct productMapper,
                          CouponGatewayService couponClient) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.couponClient = couponClient;
    }

    @Override
    public boolean addProduct(Product product) {
        Product productModel = productMapper.mapProductToProductDTO(product);
        try{
            Coupon coupon = couponClient.getCouponByCode(productModel.getCouponCode());
            if(isDiscountValid(productModel, coupon)){
                calculatePrice(productModel, coupon);
            }
        } catch (Exception e){
            logger.warn("No coupon found by code {} {}", productModel.getCouponCode(), e.getMessage());
        }
        productRepository.save(productModel);
        return true;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    private boolean isDiscountValid(Product product, Coupon coupon) {
        return Objects.nonNull(product.getCouponCode()) &&
                Objects.nonNull(coupon);
    }

    private void calculatePrice(Product product, Coupon coupon) {
        BigDecimal newPrice = product.getPrice().subtract(coupon.getDiscount());
        if(newPrice.compareTo(BigDecimal.ZERO) < 0){
            product.setPrice(BigDecimal.ZERO);
        } else{
            product.setPrice(newPrice);
        }
    }
}
