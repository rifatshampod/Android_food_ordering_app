package com.example.foodapp_ssoapp;


public class FoodListModel{

    private String price;
    private String name;
    private String menuId;

    public FoodListModel() {
    }

    public FoodListModel(String price, String name, String menuId) {
        this.price=price;
        this.name = name;
        this.menuId=menuId;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    private String getMenuId(){
        return menuId;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenuId(String menuId){
        this.menuId=menuId;
    }
}