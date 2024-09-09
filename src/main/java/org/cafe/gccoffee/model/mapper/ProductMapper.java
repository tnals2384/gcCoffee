package org.cafe.gccoffee.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cafe.gccoffee.model.dto.product.ProductResponse;
import org.cafe.gccoffee.model.vo.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface ProductMapper {
    Optional<Product> getProduct(UUID productId);

    List<ProductResponse> getProductList(int offset, int size);

    List<ProductResponse> getProductListWithCategory(String category, int offset, int size);

    int getTotalProductCount();
    int getCategoryProductCount(String category);

    void insertProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(UUID product);
}
