package com.msProject.productService.service.mappers;

import com.msProject.productService.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", implementationName = "MapProduct", implementationPackage = "com.msProject.productService.service.mappers.impl")
public interface IMapProduct {

    Product mapProductToProductDTO(Product product);
}
