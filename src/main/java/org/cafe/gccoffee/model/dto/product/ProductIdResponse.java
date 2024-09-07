package org.cafe.gccoffee.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProductIdResponse {
    private UUID productId;
}
