package org.cafe.gccoffee.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cafe.gccoffee.model.vo.Product;


import java.util.Optional;
import java.util.UUID;

@Mapper
public interface ProductMapper {
    Optional<Product> getProduct(UUID productId);
    void insertProduct(Product product);
    void updateProduct(Product product);
}
