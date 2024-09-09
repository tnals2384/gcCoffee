package org.cafe.gccoffee.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class Product {

    private UUID id;
    private String productName;
    private String category;
    private int price;
    private String description;

    @Builder
    private Product(UUID id, String productName, String category, int price, String description) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public static Product productOf(String productName, String category, int price, String description) {
        return Product.builder()
                .id(UUID.randomUUID())
                .productName(productName)
                .category(category)
                .price(price)
                .description(description)
                .build();
    }

    public static Product productForUpdate(UUID productId, String productName, String category, int price, String description) {
        return Product.builder()
                .id(productId)
                .productName(productName)
                .category(category)
                .price(price)
                .description(description)
                .build();
    }
}
