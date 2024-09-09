package org.cafe.gccoffee.controller.order;


import lombok.RequiredArgsConstructor;
import org.cafe.gccoffee.common.BaseResponse;
import org.cafe.gccoffee.model.dto.order.OrderIdResponse;
import org.cafe.gccoffee.model.dto.order.OrderResponse;
import org.cafe.gccoffee.model.service.OrderService;
import org.cafe.gccoffee.util.PageUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/order")
public class AdminOrderController {

    private final OrderService orderService;

    @GetMapping
    public BaseResponse<PageUtils<OrderResponse>> getOrder(@RequestParam("page") int page, @RequestParam("size") int size) {
        return BaseResponse.onSuccess(orderService.getOrderList(page, size));
    }

    @PostMapping()
    public BaseResponse<List<OrderIdResponse>> startShippingForPendingOrders() {
        return BaseResponse.onSuccess(orderService.startShippingForPendingOrders());
    }

}
