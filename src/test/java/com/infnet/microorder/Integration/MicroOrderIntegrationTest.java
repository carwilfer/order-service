package com.infnet.microorder.Integration;

import com.infnet.microorder.client.StockClient;
import com.infnet.microorder.controller.OrderController;
import com.infnet.microorder.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@SpringBootTest
class MicroOrderIntegrationTest {

    @MockBean
    private StockClient stockClient;

    @Autowired
    private OrderController orderController;

    @Test
    void createOrderIntegrationTest() {
        Order order = new Order(1L, "PROD001", 10);
        when(stockClient.checkStock("PROD001", 10)).thenReturn(true);
        doNothing().when(stockClient).decreaseStock("PROD001", 10);

        // Simular criação de pedido e verificar se o estoque foi verificado e diminuído
        // Aqui você pode chamar diretamente os métodos do orderController para integração
    }
}
