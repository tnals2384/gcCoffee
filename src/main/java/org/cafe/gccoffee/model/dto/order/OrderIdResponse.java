package org.cafe.gccoffee.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class OrderIdResponse {
    private UUID orderId;
}
