package org.cafe.gccoffee.model.dto.order.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemResponse {
    private String productName;
    private int quantity;
    private int totalPrice;
}
