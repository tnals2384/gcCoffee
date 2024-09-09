package org.cafe.gccoffee.controller.product;

import lombok.RequiredArgsConstructor;
import org.cafe.gccoffee.model.dto.product.ProductCreateRequest;
import org.cafe.gccoffee.model.dto.product.ProductIdResponse;
import org.cafe.gccoffee.model.service.ProductService;
import org.cafe.gccoffee.common.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/product")
public class AdminProductController {

    private final ProductService productService;
    @PostMapping()
    public BaseResponse<ProductIdResponse> createProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        return BaseResponse.onSuccess(productService.createProduct(productCreateRequest));
    }

    @PutMapping("/{productId}")
    public BaseResponse<ProductIdResponse> createProduct(@PathVariable("productId") UUID productId,
                                                         @RequestBody ProductCreateRequest productCreateRequest) {
        return BaseResponse.onSuccess(productService.updateProduct(productId, productCreateRequest));
    }

    @DeleteMapping("/{productId}")
    public BaseResponse<ProductIdResponse> deleteProduct(@PathVariable("productId") UUID productId) {
        return BaseResponse.onSuccess(productService.deleteProduct(productId));
    }

}
