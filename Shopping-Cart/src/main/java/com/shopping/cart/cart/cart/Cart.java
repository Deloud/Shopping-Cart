package com.shopping.cart.cart.cart;
import org.json.simple.JSONObject;

public class Cart {

    private int id, totalprice;
    private JSONObject product_list = new JSONObject();

    public Cart(int id, int totalprice) {
        this.id = id;
        this.totalprice = totalprice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //---

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public void addTotalprice(int totalprice) {
        this.totalprice = this.totalprice + totalprice;
    }
    //----


    public JSONObject getProduct_list() {
        return product_list;
    }

    public void putInToProductList(Integer product_id, Integer count){
        this.product_list.put(product_id.toString(), count);
    }

    public void removeFromProductList(Integer product_id){
        this.product_list.remove(product_id.toString());
    }


}
