package com.shopping.cart.cart.cart;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;


@RestController
@RequestMapping("")
public class CartController {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    List<Cart> cartList;

    @PostConstruct
    public void init() {
        cartList = new ArrayList<>();
        cartList.add(new Cart(1,0));
        cartList.add(new Cart(2,0));
        cartList.add(new Cart(3,0));
        cartList.add(new Cart(4,0));
    }

    @GetMapping("")
    public List<Cart> test(){
        return cartList;
    }

    @GetMapping("/{id}")
    public Cart retrieveData(@PathVariable int id) {
        return cartList.get(id-1);
    }

    @PostMapping("")
    public String addProduct(@RequestBody Param param){
        Product product = restTemplate.getForObject("http://localhost:8088/".concat(Integer.toString(param.getProduct_id())), Product.class);
        cartList.get(param.getUser_id()-1).putInToProductList(param.getProduct_id(), param.getCount());
        cartList.get(param.getUser_id()-1).addTotalprice(product.getPrice() * param.getCount());
        return "Post Successful";
    }

    @DeleteMapping("/{id}/all")
    public String resetCart(@PathVariable int id){
        cartList.remove(id-1);
        cartList.add(id-1,new Cart(id,0));
        return "Reset Cart Successfull";
    }

}