package com.shopping.cart.cart.cart;

import java.util.*;

public class Cart {

    private List<String> id, totalprice;
    ArrayList<String[]> product_list = new ArrayList<String[]>();
    private Map<Integer, String> menus;

    Cart() {
        id = new ArrayList<>();
        totalprice = new ArrayList<>();
        menus = new HashMap<>();
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

    //---

    public Map<Integer, String> getMenus() {
        return menus;
    }

    public void setMenus(Map<Integer, String> menus) {
        this.menus = menus;
    }

    //-----

    public String[] getProduct_listtoid(int id) {
        return product_list.get(id - 1);
    }

    public void setProduct_list(String[] product_list) {
        this.product_list.add(product_list);
    }

    public ArrayList<String[]> getProduct_list() {
        return product_list;
    }
}
