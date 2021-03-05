package com.shopping.cart.cart.cart;

import java.util.*;

public class Cart {

    private List<String> id, totalprice;
    private Map<Integer, List<CartDetail>> product_list;

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

    public Map<Integer, List<CartDetail>> getProduct_list() {
        return product_list;
    }

    public void setProduct_list(Map<Integer, List<CartDetail>> product_list) {
        this.product_list = product_list;
    }

    public List<CartDetail> getProduct_listtoid(int id) {
        return product_list.get(id);
    }

    public List<String> getDetail(List<CartDetail> detaillist){
        List<String> forprint = new ArrayList<>();
        for (int i =0; i < detaillist.size(); i++){
            forprint.add( detaillist.get(i).getProduct_id()+ " : " + detaillist.get(i).getproduct_count() );
        }
        return forprint;
    }

    public void setProduct_listtoid(int id, int product_id, int count) {
        List<CartDetail> input = this.product_list.get(id);
        input.add(new CartDetail(Integer.toString(product_id),count));
    }

}
