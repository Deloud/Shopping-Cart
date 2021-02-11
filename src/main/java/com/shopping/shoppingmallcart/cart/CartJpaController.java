package com.shopping.shoppingmallcart.cart;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    RestTemplate restTemplate;

//    private Logger LOGGER = LoggerFactory.getLogger(CartJpaController.class);

    @GetMapping("")
    public List<Cart> retrieveAllCarts(){
        return cartRepository.findAll();
    }

    //post

    // /jpa/users/90001/posts
    @GetMapping("/{id}")
    public Optional<Cart> retrieveAllPostsByUser(@PathVariable int id) {
        Optional<Cart> cart = cartRepository.findById(id);

        if (!cart.isPresent()) {
            throw new CartNotFoundException(String.format("ID[%s} not found", id));
        }

//        return cart.get().getProducts();
        return cart;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Product> addProduct(@PathVariable int id, @RequestBody Param param){

        Optional<Cart> cart = cartRepository.findById(id);

        if (!cart.isPresent()){
            throw new CartNotFoundException(String.format("ID[%s] not found", id));
        }


        Product product = restTemplate.getForObject("http://localhost:8088/".concat(Integer.toString(param.getProduct_id())), Product.class);

        product.setCart(cart.get());
        product.setCount(param.getCount());
        Product savedPost = productRepository.save(product);
//        System.out.println("&&&&&&&&&&"+cart.get() + "&&&&&&&&"+product.getPrice()+"==========================="+product.getId());

        Cart newcart = cart.get();
        newcart.setTotal_price(cart.get().getTotal_price() + product.getPrice()*product.getCount());
        cartRepository.save(newcart);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}/{product_id}")
    public void deleteProduct(@PathVariable int id, @PathVariable int product_id) {
        Optional<Cart> cart = cartRepository.findById(id);


        if (!cart.isPresent()) {
            throw new CartNotFoundException(String.format("ID[%s} not found", id));
        } else {
            Optional<Product> product = productRepository.findById(product_id);
            Cart newcart = cart.get();
            newcart.setTotal_price(cart.get().getTotal_price() - product.get().getPrice()*product.get().getCount() );
            cartRepository.save(newcart);
            productRepository.deleteById(product_id);
        }
    }

}