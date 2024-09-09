package org.cafe.gccoffee.model.dto.order.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductEditRequest {
    private UUID productId;
    private int quantity;
}
