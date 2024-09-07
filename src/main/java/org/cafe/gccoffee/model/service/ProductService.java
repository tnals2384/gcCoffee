package org.cafe.gccoffee.model.service;

import lombok.RequiredArgsConstructor;
import org.cafe.gccoffee.model.vo.Product;
import org.cafe.gccoffee.model.dto.product.ProductCreateRequest;
import org.cafe.gccoffee.model.dto.product.ProductIdResponse;
import org.cafe.gccoffee.model.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    @Transactional
    public ProductIdResponse createProduct(ProductCreateRequest request) {
        Product product = Product.productOf(request.getProductName(), request.getCategory(),
                request.getPrice(), request.getDescription());

        productMapper.insertProduct(product);

        return new ProductIdResponse(product.getId());
    }

    public Product getProduct(UUID productId) {
        return productMapper.getProduct(productId);
    }
}
