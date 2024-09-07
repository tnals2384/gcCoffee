package org.cafe.gccoffee.controller;


import lombok.RequiredArgsConstructor;
import org.cafe.gccoffee.model.dto.order.OrderCreateRequest;
import org.cafe.gccoffee.model.dto.order.OrderIdResponse;
import org.cafe.gccoffee.model.service.OrderService;
import org.cafe.gccoffee.common.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public BaseResponse<OrderIdResponse> createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        return BaseResponse.onSuccess(orderService.createOrder(orderCreateRequest));
    }

}
