package com.infnet.microorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stock-service", url = "http://localhost:8081")
public interface StockClient {

    @GetMapping("/stock/check")
    boolean checkStock(@RequestParam String productCode, @RequestParam int quantity);

    @PostMapping("/stock/decrease")
    void decreaseStock(@RequestParam String productCode, @RequestParam int quantity);
}
