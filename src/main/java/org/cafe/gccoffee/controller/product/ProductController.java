package org.cafe.gccoffee.controller.product;

import lombok.RequiredArgsConstructor;
import org.cafe.gccoffee.common.BaseResponse;
import org.cafe.gccoffee.model.dto.product.ProductResponse;
import org.cafe.gccoffee.model.service.ProductService;
import org.cafe.gccoffee.util.PageUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    /**
     * RQ-006 : 전체 상품 목록 조회
     * RQ-007 : 카테고리별 상품 조회
     */
    @GetMapping("/list")
    public BaseResponse<PageUtils<ProductResponse>> getProductList(@RequestParam(value = "category", required = false) String category,
                                                                   @RequestParam("page") int page,
                                                                   @RequestParam("size") int size) {
        return BaseResponse.onSuccess(productService.getProductList(category, page, size));
    }
}
