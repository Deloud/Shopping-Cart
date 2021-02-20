package com.shopping.shoppingmallcart;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.shoppingmallcart.cart.Product;
import com.shopping.shoppingmallcart.cart.ProductClient;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("contract")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = "product.base-url:http://localhost:9292",
        classes = ProductClient.class)

@PactTestFor(providerName = "ProductServiceProvider", port="9292")
//@Disabled
public class CartConsumerContractTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private ProductClient productClient;
    @Pact(consumer = "cartConsumerPact")
    public RequestResponsePact callCartClient(PactDslWithProvider builder) throws JsonProcessingException {
        List<Product> products = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        products.add(Product.builder()
                .id(1)
                .name("사이다")
                .price(2000)
                .build());
        products.add(Product.builder()
                .id(2)
                .name("콜라")
                .price(3000)
                .build());
        products.add(Product.builder()
                .id(3)
                .name("환타")
                .price(4000)
                .build());

        return builder
                .given("product list3")
                .uponReceiving("test-account-service")
                .path("/product/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .matchHeader("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(products), ContentType.APPLICATION_JSON)
                .toPact();
    }

    //@PactVerification(fragment = "pactUserExists")
    @Test
    @PactTestFor(pactMethod="callCartClient")
    public void userExists() {
        final RestTemplate restTemplate = new RestTemplate();
        final String forObject = restTemplate.getForObject("http://localhost:8880/cart", String.class);
        Assertions.assertNotNull(forObject);

    }
}