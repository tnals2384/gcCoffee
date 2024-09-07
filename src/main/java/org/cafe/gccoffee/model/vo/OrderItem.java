package org.cafe.gccoffee.model.vo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;



@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    private int id;
    private UUID orderId;
    private UUID productId;
    private int quantity;
    private String category;
    private int price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    private OrderItem(UUID orderId, UUID productId, int quantity, String category, int price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
    }

    public static OrderItem orderItemOf(UUID orderId, UUID productId, int quantity, String category, int price) {
        return OrderItem.builder()
                .orderId(orderId)
                .productId(productId)
                .quantity(quantity)
                .category(category)
                .price(price)
                .build();
    }
}
