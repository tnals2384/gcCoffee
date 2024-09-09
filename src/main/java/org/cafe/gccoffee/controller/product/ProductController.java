package org.cafe.gccoffee.controller.product;

import lombok.RequiredArgsConstructor;
import org.cafe.gccoffee.common.BaseResponse;
import org.cafe.gccoffee.model.dto.product.ProductResponse;
import org.cafe.gccoffee.model.service.ProductService;
import org.cafe.gccoffee.util.PageUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public BaseResponse<PageUtils<ProductResponse>> getProductList(@RequestParam("page") int page, @RequestParam("size") int size) {
        return BaseResponse.onSuccess(productService.getProductList(page, size));
    }

    @GetMapping("/{category}")
    public BaseResponse<PageUtils<ProductResponse>> getProductListWithCategory(@PathVariable("category") String category,
                                                                        @RequestParam("page") int page,
                                                                        @RequestParam("size") int size) {
        return BaseResponse.onSuccess(productService.getProductListWithCategory(category, page, size));
    }
}
