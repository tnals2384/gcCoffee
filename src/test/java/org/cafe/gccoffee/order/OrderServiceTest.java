package org.cafe.gccoffee.order;

import org.cafe.gccoffee.model.dto.order.OrderCreateRequest;
import org.cafe.gccoffee.model.dto.order.OrderIdResponse;
import org.cafe.gccoffee.model.dto.order.OrderItemRequest;
import org.cafe.gccoffee.model.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@DisplayName("Admin Order 서비스의 ")
@SpringBootTest
@AutoConfigureMockMvc
public class OrderServiceTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("주문 생성 테스트")
    @Transactional
    public void createOrderTest() {

        //given
        UUID productId = UUID.randomUUID();
        OrderCreateRequest request = new OrderCreateRequest("tnals2384@gmail.com","인천","22212",
                new ArrayList<>(List.of(new OrderItemRequest(productId,1))));

        //when
        OrderIdResponse orderId = orderService.createOrder(request);

        //Order order = orderService.getOrder(orderId);
        //then
        //Assertions.assertEquals(productId, );
    }




}
