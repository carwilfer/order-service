package com.infnet.microorder.controller;

import com.infnet.microorder.client.StockClient;
import com.infnet.microorder.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private StockClient stockClient;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        boolean isAvailable = stockClient.checkStock(order.getProductCode(), order.getQuantity());
        if (isAvailable) {
            stockClient.decreaseStock(order.getProductCode(), order.getQuantity());
            return ResponseEntity.ok("Order created successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock.");
        }
    }
}
