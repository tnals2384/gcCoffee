package org.cafe.gccoffee.model.dto.order.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class OrderIdResponse {
    private UUID orderId;
}
