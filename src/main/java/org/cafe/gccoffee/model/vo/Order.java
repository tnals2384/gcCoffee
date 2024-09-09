package org.cafe.gccoffee.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class Order {
    private UUID id;
    private String email;
    private String address;
    private String postcode;
    private String orderStatus;

    @Builder
    private Order(UUID id, String email, String address, String postcode) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = OrderStatus.PENDING.toString();
    }

    public static Order orderOf(String email, String address, String postcode) {
        return Order.builder()
                .id(UUID.randomUUID())
                .email(email)
                .address(address)
                .postcode(postcode)
                .build();
    }

    public static Order OrderForUpdate(UUID orderId, String email, String address, String postcode) {
        return Order.builder()
                .id(orderId)
                .email(email)
                .address(address)
                .postcode(postcode)
                .build();
    }
}
