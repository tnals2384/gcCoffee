package org.cafe.gccoffee.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cafe.gccoffee.model.dto.order.OrderResponse;
import org.cafe.gccoffee.model.vo.Order;
import org.cafe.gccoffee.model.vo.OrderItem;

import java.util.List;

@Mapper
public interface OrderMapper {
    void insertOrder(Order order);
    void insertOrderItem(OrderItem orderItem);
    List<OrderResponse> getOrderList(int offset, int size);
    int getTotalOrderCount();
}
