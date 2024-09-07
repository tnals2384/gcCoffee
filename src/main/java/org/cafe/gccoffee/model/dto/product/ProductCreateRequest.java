package org.cafe.gccoffee.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {
    private String productName;
    private String category;
    private int price;
    private String description;
}
