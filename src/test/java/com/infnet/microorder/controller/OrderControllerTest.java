package com.infnet.microorder.controller;

import com.infnet.microorder.client.StockClient;
import com.infnet.microorder.model.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockClient stockClient;

    @Test
    void createOrderSuccess() throws Exception {
        Order order = new Order(1L, "PROD001", 10);
        Mockito.when(stockClient.checkStock("PROD001", 10)).thenReturn(true);
        Mockito.doNothing().when(stockClient).decreaseStock("PROD001", 10);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType("application/json")
                        .content("{\"id\":1,\"productCode\":\"PROD001\",\"quantity\":10}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Order created successfully."));
    }

    @Test
    void createOrderInsufficientStock() throws Exception {
        Order order = new Order(1L, "PROD001", 10);
        Mockito.when(stockClient.checkStock("PROD001", 10)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType("application/json")
                        .content("{\"id\":1,\"productCode\":\"PROD001\",\"quantity\":10}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Insufficient stock."));
    }
}