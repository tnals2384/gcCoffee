package org.cafe.gccoffee.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {
    private String email;
    private String address;
    private String postcode;
    private List<OrderItemRequest> orderItemList;

}
