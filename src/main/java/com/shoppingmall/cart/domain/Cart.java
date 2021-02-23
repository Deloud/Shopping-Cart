package com.shoppingmall.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    private Integer id;

    private Integer total_price;

    //1대 다 속성 주기
    @OneToMany(mappedBy = "cart")
    private List<Product> products;
}
