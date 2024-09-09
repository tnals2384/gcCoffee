package org.cafe.gccoffee.controller.order;


import lombok.RequiredArgsConstructor;
import org.cafe.gccoffee.model.dto.order.OrderCreateRequest;
import org.cafe.gccoffee.model.dto.order.OrderIdResponse;
import org.cafe.gccoffee.model.dto.order.OrderProductEditRequest;
import org.cafe.gccoffee.model.dto.order.OrderUserEditRequest;
import org.cafe.gccoffee.model.service.OrderService;
import org.cafe.gccoffee.common.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public BaseResponse<OrderIdResponse> createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        return BaseResponse.onSuccess(orderService.createOrder(orderCreateRequest));
    }

    @PutMapping("/{orderId}/product")
    public BaseResponse<OrderIdResponse> editOrderProduct(@PathVariable("orderId") UUID orderId,
                                                        @RequestBody List<OrderProductEditRequest> orderProductEditRequestList) {
        return BaseResponse.onSuccess(orderService.editOrderProduct(orderId, orderProductEditRequestList));
    }

    @PutMapping("/{orderId}/user")
    public BaseResponse<OrderIdResponse> editOrderUser(@PathVariable("orderId") UUID orderId,
                                                       @RequestBody OrderUserEditRequest orderUserEditRequest) {
        return BaseResponse.onSuccess(orderService.editOrderUser(orderId, orderUserEditRequest));
    }
}
