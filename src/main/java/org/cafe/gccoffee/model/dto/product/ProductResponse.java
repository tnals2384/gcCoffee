package org.cafe.gccoffee.model.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor //mybatis sql 매핑을 위해서는 기본 생성자가 필요
public class ProductResponse {
    private UUID id;
    private String productName;
    private String category;
    private Integer price;
    private String description;
 }
