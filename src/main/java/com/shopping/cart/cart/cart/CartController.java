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
import java.util.Optional;

@RestController
@RequestMapping("")
public class CartController {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("data.xml");
    Cart resturant = ctx.getBean("cart1", Cart.class);

    @GetMapping("/{id}")
    public JSONObject retrieveAllPostsByUser(@PathVariable int id) throws ParseException {
        String output = "{" + "id:" + resturant.getIdtoid(id) + ", totalprice:" + resturant.getTotalpricetoid(id) + "}";

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(output);
        JSONObject jsonObj = (JSONObject) obj;
//        resturant.getProduct_list().stream().forEach(System.out::println);
        System.out.println(resturant.getProduct_listtoid(id).length);
        return jsonObj;
    }

    @PostMapping("")
    public String addProduct(@RequestBody Param param){

        resturant.product_list.add(new String[]{"1", "2"});
        resturant.product_list.set(param.getUser_id()-1,resturant.product_list.get(param.getUser_id()-1));

        return "add 1 ,2";
    }

}