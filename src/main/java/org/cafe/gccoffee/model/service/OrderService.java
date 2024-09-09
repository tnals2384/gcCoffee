package org.cafe.gccoffee.model.service;

import lombok.RequiredArgsConstructor;
import org.cafe.gccoffee.model.dto.order.*;
import org.cafe.gccoffee.model.dto.product.ProductResponse;
import org.cafe.gccoffee.model.vo.Product;
import org.cafe.gccoffee.model.mapper.OrderMapper;
import org.cafe.gccoffee.model.vo.Order;
import org.cafe.gccoffee.model.vo.OrderItem;
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

    @Transactional
    public OrderIdResponse createOrder(OrderCreateRequest request) {

        Order order = Order.orderOf(request.getEmail(), request.getAddress(), request.getPostcode());
        orderMapper.insertOrder(order);

        createOrderItem(order.getId(), request.getOrderItemList());

        return new OrderIdResponse(order.getId());

    }

    private void createOrderItem(UUID orderId, List<OrderItemRequest> list) {
        for (OrderItemRequest orderItemRequest : list) {
            Product product = productService.getProduct(orderItemRequest.getProductId());
            int quantity = orderItemRequest.getQuantity();

            OrderItem orderItem = OrderItem.orderItemOf(
                    orderId,
                    product.getId(),
                    quantity,
                    product.getCategory(),
                    product.getPrice()*quantity
            );

            orderMapper.insertOrderItem(orderItem);

        }
    }

    public PageUtils<OrderResponse> getOrderList(int page, int size) {
        PageUtils.checkPagingRequest(page, size);

        int offset = page*size;
        List<OrderResponse> orderList = orderMapper.getOrderList(offset, size);
        int totalCount = orderMapper.getTotalOrderCount();

        return PageUtils.pageUtilsOf(orderList, page, size, totalCount);

    }

}
