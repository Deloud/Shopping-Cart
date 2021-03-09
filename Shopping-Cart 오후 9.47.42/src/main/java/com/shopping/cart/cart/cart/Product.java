package com.shopping.cart.cart.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;

    private Integer product_id;

    private String name;

    private Integer price;

    private Integer count;

}