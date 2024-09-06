package org.cafe.gccoffee.model.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private String id;
    private String email;
    private String address;
    private String postcode;
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
