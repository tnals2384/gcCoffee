package org.cafe.gccoffee.model.service;

import lombok.RequiredArgsConstructor;
import org.cafe.gccoffee.model.dto.order.request.OrderCreateRequest;
import org.cafe.gccoffee.model.dto.order.request.OrderItemRequest;
import org.cafe.gccoffee.model.dto.order.request.OrderUserEditRequest;
import org.cafe.gccoffee.model.dto.order.response.OrderIdResponse;
import org.cafe.gccoffee.model.dto.order.response.OrderResponse;
import org.cafe.gccoffee.model.mapper.OrderMapper;
import org.cafe.gccoffee.model.vo.Order;
import org.cafe.gccoffee.model.vo.OrderItem;
import org.cafe.gccoffee.model.vo.OrderStatus;
import org.cafe.gccoffee.model.vo.Product;
import org.cafe.gccoffee.util.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final ProductService productService;

    //주문 생성
    @Transactional
    public OrderIdResponse createOrder(OrderCreateRequest request) {
        Order order = Order.orderOf(request.getEmail(), request.getAddress(), request.getPostcode());

        orderMapper.insertOrder(order);
        addOrderItems(order.getId(), request.getOrderItemList());

        return new OrderIdResponse(order.getId());

    }

    //주문 목록 페이징 조회
    public PageUtils<OrderResponse> getOrderList(int page, int size) {
        PageUtils.checkPagingRequest(page, size);
        int offset = PageUtils.calculateOffset(page, size);

        List<OrderResponse> orderList = orderMapper.getOrderList(offset, size);
        int totalCount = orderMapper.getTotalOrderCount();

        return PageUtils.pageUtilsOf(orderList, page, size, totalCount);
    }

    //배송 시작 일괄 처리
    @Transactional
    public List<OrderIdResponse> startShippingForPendingOrders() {
        List<OrderIdResponse> pendingOrderList = orderMapper.getPendingOrderIdList();

        orderMapper.startShippingForPendingOrders();
        return pendingOrderList;
    }

    //주문 메뉴 수정하기
    @Transactional
    public OrderIdResponse editOrderProduct(UUID orderId, List<OrderItemRequest> list) {
        validateOrderExists(orderId);

        //해당 order의 orderItem 전부 삭제
        orderMapper.deleteOrderItemsByOrderId(orderId);
        //수정된 list로 다시 추가
        addOrderItems(orderId, list);

        return new OrderIdResponse(orderId);
    }

    //주문한 사용자 정보 수정하기
    @Transactional
    public OrderIdResponse editOrderUser(UUID orderId, OrderUserEditRequest request) {
        validateOrderExists(orderId);
        Order order = Order.OrderForUpdate(orderId, request.getEmail(), request.getAddress(), request.getPostcode());

        orderMapper.editOrderUser(order);
        return new OrderIdResponse(orderId);
    }

    //주문 취소하기(PENDING 인 경우에만)
    @Transactional
    public OrderIdResponse cancelOrder(UUID orderId) {
        Order order = getOrder(orderId);

        if (!(order.getOrderStatus()).equals(OrderStatus.PENDING.toString())) {
            throw new RuntimeException("PENDING중인 주문만 취소할 수 있습니다.");
        }

        orderMapper.deleteOrder(orderId);
        return new OrderIdResponse(orderId);
    }

    //내 주문 목록 조회
    public PageUtils<OrderResponse> getUserOrderList(String email, int page, int size) {
        PageUtils.checkPagingRequest(page, size);
        int offset = PageUtils.calculateOffset(page, size);

        List<OrderResponse> orderList = orderMapper.getUserOrderList(email, offset, size);
        int totalCount = orderMapper.getUserOrderCount(email);

        return PageUtils.pageUtilsOf(orderList, page, size, totalCount);
    }

    //OrderItems 생성
    private void addOrderItems(UUID orderId, List<OrderItemRequest> orderItems) {
        for (OrderItemRequest itemRequest : orderItems) {
            addOrderItem(orderId, itemRequest);
        }
    }

    //OrderItem 생성

    private void addOrderItem(UUID orderId, OrderItemRequest itemRequest) {
        Product product = productService.getProduct(itemRequest.getProductId());
        int quantity = itemRequest.getQuantity();

        OrderItem orderItem = OrderItem.orderItemOf(
                orderId,
                product.getId(),
                quantity,
                product.getCategory(),
                product.getPrice() * quantity
        );

        orderMapper.insertOrderItem(orderItem);
    }

    private Order getOrder(UUID orderId) {
        return orderMapper.getOrder(orderId)
                .orElseThrow(() -> new RuntimeException("Order를 찾을 수 없습니다."));
    }

    private void validateOrderExists(UUID orderId) {
        if (orderMapper.getOrder(orderId).isEmpty()) {
            throw new RuntimeException("Order를 찾을 수 없습니다.");
        }
    }

}
