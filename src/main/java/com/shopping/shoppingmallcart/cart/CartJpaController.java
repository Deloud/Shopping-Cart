package com.shopping.shoppingmallcart.cart;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("")
public class CartJpaController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;



    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
    @Autowired
    RestTemplate restTemplate;

//    private static int order_id;

//    private Logger LOGGER = LoggerFactory.getLogger(CartJpaController.class);

    @GetMapping("")
    public List<Cart> retrieveAllCarts(){
        return cartRepository.findAll();
    }

    //post

    // /jpa/users/90001/posts
    @GetMapping("/{id}")
    public Optional<Cart> retrieveAllPostsByUser(@PathVariable int id) {

//        int get_user_id = restTemplate.getForObject("http://localhost:8089/".concat(Integer.toString(id)),int);
//
//        product.setCart(cart.get());
//        product.setCount(param.getCount());
//        Product savedPost = productRepository.save(product);


        //----------
        Optional<Cart> cart = cartRepository.findById(id);

        if (!cart.isPresent()) {
            throw new CartNotFoundException(String.format("ID[%s} not found", id));
        }

//        return cart.get().getProducts();
//        System.out.println(cart.get().getProducts());
//        System.out.println("!!!!!!!!!!!!!");
        return cart;
    }

    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody Param param){

        Optional<Cart> cart = cartRepository.findById(param.getUser_id());

        if (!cart.isPresent()){
            throw new CartNotFoundException(String.format("ID[%s] not found", param.getUser_id()));
        }

        Product product = restTemplate.getForObject("http://localhost:8088/".concat(Integer.toString(param.getProduct_id())), Product.class);

        product.setProduct_id((param.getProduct_id()));
        product.setCart(cart.get());
        product.setCount(param.getCount());
        Product savedProduct = productRepository.save(product);

//
        Cart newcart = cart.get();
        newcart.setTotal_price(cart.get().getTotal_price() + product.getPrice()*product.getCount());
        cartRepository.save(newcart);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getProduct_id())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}/{order_id}")
    public void deleteProduct(@PathVariable int id, @PathVariable int order_id) {
        Optional<Cart> cart = cartRepository.findById(id);


        if (!cart.isPresent()) {
            throw new CartNotFoundException(String.format("ID[%s} not found", id));
        } else {
            Optional<Product> product = productRepository.findById(order_id);
            Cart newcart = cart.get();
            newcart.setTotal_price(cart.get().getTotal_price() - product.get().getPrice()*product.get().getCount() );
            cartRepository.save(newcart);
            productRepository.deleteById(order_id);
        }
    }

    //전체삭제
    @DeleteMapping("/{id}/all")
    public void deleteProductAll(@PathVariable int id) {
        Optional<Cart> cart = cartRepository.findById(id);


        if (!cart.isPresent()) {
            throw new CartNotFoundException(String.format("ID[%s} not found", id));
        } else {
            Cart newcart = new Cart();
            newcart.setId(cart.get().getId());
            newcart.setTotal_price(0);
            newcart.setProducts(null);
            List<Product> remain_pro = cart.get().getProducts();
            for(int i =0; i < remain_pro.size(); i++){
                productRepository.deleteById(remain_pro.get(i).getId());
            }
            cartRepository.deleteById(id);
            cartRepository.save(newcart);
        }
    }

//    //order에 추가하는 것 --> order서비스에 추가됨
//    @GetMapping("/{id}/purchase")
//    public int AddOrder(@PathVariable int id) {
//        Optional<Cart> cart = cartRepository.findById(id);
//
//        if (!cart.isPresent()) {
//            throw new CartNotFoundException(String.format("ID[%s} not found", id));
//        }
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        Cart newcart = cart.get();
//
//        Cart result = restTemplate.postForObject("http://localhost:8092/SpringMvcSample/test/jsonRequest", newcart, Cart.class);
//
//        return cart.get().getTotal_price();
//    }

}