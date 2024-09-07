package org.cafe.gccoffee.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cafe.gccoffee.model.vo.Order;
import org.cafe.gccoffee.model.vo.OrderItem;

@Mapper
public interface OrderMapper {
    void insertOrder(Order order);
    void insertOrderItem(OrderItem orderItem);
}
