package com.shopping.shoppingmallcart.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import javax.persistence.*;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@ApiModel(description = "물건 정보를 위한 도메인 객체")
public class Product {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer product_id;

    @ApiModelProperty(notes = "물건 이름을 입력해주세요") //SWAGGER
    private String name;
    
    @ApiModelProperty(notes = "가격을 입력해주세요")
    private Integer price;

    @ApiModelProperty(notes = "갯수를 입력해주세요")
    private Integer count;

    // User : Post -> 1 : (0~N), Main : Sub -> Parent : Child
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Cart cart;
}