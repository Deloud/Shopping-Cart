package com.shopping.shoppingmallcart.cart;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {

    private static RestTemplate restTemplate;


//    public ProductClient(@Value("${product-service.base-url}") String baseUrl) {
//        this.restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
//    }

    public Product getProduct(String id) {
//        final Product product = restTemplate.getForObject("/cart/9001/" + id, Product.class);
//        Assert.hasText(product.getName(), "Name is blank.");
        return restTemplate.getForObject("/cart/9001/" + id, Product.class);
//        return product;
    }

}
