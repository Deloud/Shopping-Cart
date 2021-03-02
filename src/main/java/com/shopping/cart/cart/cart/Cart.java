package com.shopping.cart.cart.cart;

import java.util.*;

public class Cart {

    private List<String> id, totalprice;
    private Map<Integer, List<String>> product_list;

    Cart() {
        id = new ArrayList<>();
        totalprice = new ArrayList<>();
        product_list = new HashMap<>();
    }

    public List<String> getId() {
        return id;
    }

    public String getIdtoid(int ids) {
        return id.get(ids - 1);
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    //---

    public List<String> getTotalprice() {
        return totalprice;
    }

    public String getTotalpricetoid(int id) {
        return totalprice.get(id - 1);
    }

    public void setTotalprice(List<String> totalprice) {
        this.totalprice = totalprice;
    }

    public void setTotalpricetoid(int id, int plus) {
        this.totalprice.set(id-1,Integer.toString(plus));
    }

    //---

    public Map<Integer, List<String>> getProduct_list() {
        return product_list;
    }

    public void setProduct_list(Map<Integer, List<String>> product_list) {
        this.product_list = product_list;
    }

    public List<String> getProduct_listtoid(int id) {
        return product_list.get(id);
    }

    public void setProduct_listtoid(int id, int product_id, int count) {
        List<String> input = this.product_list.get(id);
        input.add(product_id+" : "+count);
//        input.add(Integer.toString(product_id)+"번 상품 "+Integer.toString(count)+"개");
        this.product_list.put(id,input);
    }

}
