package org.cafe.gccoffee.controller;

import lombok.RequiredArgsConstructor;
import org.cafe.gccoffee.model.dto.product.ProductCreateRequest;
import org.cafe.gccoffee.model.dto.product.ProductIdResponse;
import org.cafe.gccoffee.model.service.ProductService;
import org.cafe.gccoffee.common.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/product")
public class AdminProductController {

    private final ProductService productService;
    @PostMapping()
    public BaseResponse<ProductIdResponse> createProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        return BaseResponse.onSuccess(productService.createProduct(productCreateRequest));
    }

}
