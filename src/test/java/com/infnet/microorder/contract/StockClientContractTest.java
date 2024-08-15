package com.infnet.microorder.contract;


import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class, PactConsumerTestExt.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PactTestFor(providerName = "StockService", port = "8081")
@PactFolder("pacts")
public class StockClientContractTest {

    @Pact(provider = "StockService", consumer = "OrderService")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        return builder
                .given("Stock is available")
                .uponReceiving("Check stock availability")
                .path("/stock/check")
                .method("GET")
                .query("productCode=PROD001&quantity=10")
                .willRespondWith()
                .status(200)
                .body("true")
                .toPact();
    }

    @Test
    void testStockCheckContract() {
        // Aqui, você pode fazer uma chamada real ao serviço
        // utilizando o FeignClient ou RestTemplate para validar o contrato
    }
}
