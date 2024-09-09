package org.cafe.gccoffee.model.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class OrderResponse {
    private UUID id;
    private String email;
    private String address;
    private String postcode;
    private String orderStatus;
    private String productName;
    private int quantity;
    private int totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
