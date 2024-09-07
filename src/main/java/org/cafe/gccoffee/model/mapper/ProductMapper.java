package org.cafe.gccoffee.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cafe.gccoffee.model.vo.Product;


import java.util.UUID;

@Mapper
public interface ProductMapper {
    Product getProduct(UUID productId);
    void insertProduct(Product product);
}
