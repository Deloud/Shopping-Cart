package com.shopping.shoppingmallcart.cart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel(description = "사용자-상세 정보를 위한 도메인 객체")
public class Cart {

    @ApiModelProperty(notes = "사용자 id") //SWAGGER
    @Id
    private Integer id;

    private Integer total_price;

    //1대 다 속성 주기
    @OneToMany(mappedBy = "cart")
    private List<Product> products;

    public Cart(int i) {
        this.id = id;
    }
}
