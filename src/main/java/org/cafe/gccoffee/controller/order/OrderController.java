package org.cafe.gccoffee.controller.order;


import lombok.RequiredArgsConstructor;
import org.cafe.gccoffee.model.dto.order.request.OrderCreateRequest;
import org.cafe.gccoffee.model.dto.order.response.OrderIdResponse;
import org.cafe.gccoffee.model.dto.order.request.OrderProductEditRequest;
import org.cafe.gccoffee.model.dto.order.request.OrderUserEditRequest;
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

    /**
     RQ-008 : 주문하기
     */
    @PostMapping
    public BaseResponse<OrderIdResponse> createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        return BaseResponse.onSuccess(orderService.createOrder(orderCreateRequest));
    }

    /**
     RQ-009 : 주문 메뉴 수정하기
     */
    @PutMapping("/{orderId}/product")
    public BaseResponse<OrderIdResponse> editOrderProduct(@PathVariable("orderId") UUID orderId,
                                                        @RequestBody List<OrderProductEditRequest> orderProductEditRequestList) {
        return BaseResponse.onSuccess(orderService.editOrderProduct(orderId, orderProductEditRequestList));
    }

    /**
     RQ-010 : 주문 사용자 정보 수정하기
     */
    @PutMapping("/{orderId}/user")
    public BaseResponse<OrderIdResponse> editOrderUser(@PathVariable("orderId") UUID orderId,
                                                       @RequestBody OrderUserEditRequest orderUserEditRequest) {
        return BaseResponse.onSuccess(orderService.editOrderUser(orderId, orderUserEditRequest));
    }
}
