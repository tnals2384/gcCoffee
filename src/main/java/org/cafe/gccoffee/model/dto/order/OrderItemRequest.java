package org.cafe.gccoffee.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    private UUID productId;
    private int quantity;
}
