package org.cafe.gccoffee.controller.order;


import lombok.RequiredArgsConstructor;
import org.cafe.gccoffee.common.BaseResponse;
import org.cafe.gccoffee.model.dto.order.request.OrderCreateRequest;
import org.cafe.gccoffee.model.dto.order.request.OrderItemRequest;
import org.cafe.gccoffee.model.dto.order.request.OrderUserEditRequest;
import org.cafe.gccoffee.model.dto.order.response.OrderIdResponse;
import org.cafe.gccoffee.model.dto.order.response.OrderResponse;
import org.cafe.gccoffee.model.service.OrderService;
import org.cafe.gccoffee.util.PageUtils;
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
                                                        @RequestBody List<OrderItemRequest> orderItemEditRequest) {
        return BaseResponse.onSuccess(orderService.editOrderProduct(orderId, orderItemEditRequest));
    }

    /**
     RQ-010 : 주문 사용자 정보 수정하기
     */
    @PutMapping("/{orderId}/user")
    public BaseResponse<OrderIdResponse> editOrderUser(@PathVariable("orderId") UUID orderId,
                                                       @RequestBody OrderUserEditRequest orderUserEditRequest) {
        return BaseResponse.onSuccess(orderService.editOrderUser(orderId, orderUserEditRequest));
    }

    /**
     RQ-011 : 주문 취소하기
     */
    @DeleteMapping("/{orderId}")
    public BaseResponse<OrderIdResponse> cancelOrder(@PathVariable("orderId") UUID orderId) {
        return BaseResponse.onSuccess(orderService.cancelOrder(orderId));
    }

    /**
    RQ-012 : 유저 주문 목록보기
     */
    @GetMapping("/user")
    public BaseResponse<PageUtils<OrderResponse>> getUserOrderList(@RequestParam("email") String email,
                                                                   @RequestParam("page") int page,
                                                                   @RequestParam("size") int size) {
        return BaseResponse.onSuccess(orderService.getUserOrderList(email, page, size));
    }
}
