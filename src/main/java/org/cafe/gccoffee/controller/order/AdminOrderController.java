package org.cafe.gccoffee.controller.order;


import lombok.RequiredArgsConstructor;
import org.cafe.gccoffee.common.BaseResponse;
import org.cafe.gccoffee.model.dto.order.response.OrderIdResponse;
import org.cafe.gccoffee.model.dto.order.response.OrderResponse;
import org.cafe.gccoffee.model.service.OrderService;
import org.cafe.gccoffee.util.PageUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/order")
public class AdminOrderController {

    private final OrderService orderService;

    /**
     RQ-004 : 전체 주문 목록 조회
     */
    @GetMapping
    public BaseResponse<PageUtils<OrderResponse>> getOrder(@RequestParam("page") int page, @RequestParam("size") int size) {
        return BaseResponse.onSuccess(orderService.getOrderList(page, size));
    }

    /**
     RQ-005 : 배송 시작 일괄 처리
     */
    @PostMapping
    public BaseResponse<List<OrderIdResponse>> startShippingForPendingOrders() {
        return BaseResponse.onSuccess(orderService.startShippingForPendingOrders());
    }
}
