package org.cafe.gccoffee.model.service;

import lombok.RequiredArgsConstructor;
import org.cafe.gccoffee.model.dto.product.ProductResponse;
import org.cafe.gccoffee.model.vo.Product;
import org.cafe.gccoffee.model.dto.product.ProductCreateRequest;
import org.cafe.gccoffee.model.dto.product.ProductIdResponse;
import org.cafe.gccoffee.model.mapper.ProductMapper;
import org.cafe.gccoffee.util.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional
    public ProductIdResponse updateProduct(UUID productId, ProductCreateRequest request) {
        getProduct(productId); //해당 product Id가 DB에 있는지 있는지 확인
        Product product = Product.productOf(productId,
                request.getProductName(),
                request.getProductName(),
                request.getPrice(),
                request.getDescription());

        productMapper.updateProduct(product);

        return new ProductIdResponse(product.getId());
    }

    @Transactional
    public ProductIdResponse deleteProduct(UUID productId) {
        Product product = getProduct(productId);

        productMapper.deleteProduct(productId);
        return new ProductIdResponse(product.getId());
    }

    public PageUtils<ProductResponse> getProductList(int page, int size) {
        PageUtils.checkPagingRequest(page, size);

        int offset = page * size;
        List<ProductResponse> productList = productMapper.getProductList(offset, size);
        int totalCount = productMapper.getTotalProductCount();

        return PageUtils.pageUtilsOf(productList, page, size, totalCount);

    }

    public PageUtils<ProductResponse> getProductListWithCategory(String category, int page, int size) {
        PageUtils.checkPagingRequest(page, size);

        int offset = page * size;
        List<ProductResponse> productList = productMapper.getProductListWithCategory(category, offset, size);
        int totalCount = productMapper.getCategoryProductCount(category);

        return PageUtils.pageUtilsOf(productList, page, size, totalCount);
    }

    public Product getProduct(UUID productId) {
        return productMapper.getProduct(productId).orElseThrow(
                () -> new RuntimeException("Product를 찾을 수 없습니다."));
    }

}
