package org.cafe.gccoffee.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUserEditRequest {
    private String email;
    private String address;
    private String postcode;
}
