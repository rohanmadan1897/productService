package com.msProject.productService.gateways.service;

import com.msProject.productService.service.models.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COUPON-SERVICE")
public interface CouponGatewayService {

    @GetMapping("/coupon/{code}")
    Coupon getCouponByCode(@PathVariable("code") String code);


}
