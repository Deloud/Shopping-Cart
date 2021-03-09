package com.shopping.cart.cart.cart;

public class CartDetail {
    private String product_id;
    private Integer product_count;

    public CartDetail(){

    }

    public CartDetail(String toString, int count) {
        this.product_id = toString;
        this.product_count = count;
    }

    public String getProduct_id(){
        return product_id;
    }

    public void setProduct_id(String product_id){
        this.product_id = product_id;
    }

    public Integer getproduct_count(){
        return product_count;
    }

    public void setProduct_count(Integer product_count){
        this.product_count = product_count;
    }


}
