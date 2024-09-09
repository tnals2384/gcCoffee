package org.cafe.gccoffee.model.dto.order.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class OrderResponse {
    private UUID id;
    private String email;
    private String address;
    private String postcode;
    private String orderStatus;
    private List<OrderItemResponse> orderItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
