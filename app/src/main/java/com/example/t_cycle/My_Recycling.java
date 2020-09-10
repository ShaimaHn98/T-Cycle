package com.example.t_cycle;

import java.util.Date;

public class My_Recycling {
private String Price_of_pap,Price_of_cu,Price_of_cart,price_of_alm,Price_of_plastic,price_of_iron;
private Double Total;
private Date Date;
String UID;

    public My_Recycling(String price_of_pap, String price_of_cu, String price_of_cart, String price_of_alm, String price_of_plastic, String price_of_iron, Double total, java.util.Date Date, String UID) {
        Price_of_pap = price_of_pap;
        Price_of_cu = price_of_cu;
        Price_of_cart = price_of_cart;
        this.price_of_alm = price_of_alm;
        Price_of_plastic = price_of_plastic;
        this.price_of_iron = price_of_iron;
        this.Total = total;
        this.Date = Date;
        this.UID = UID;
    }

    public Double getTotal() {
        return Total;
    }

    public String getUID() {
        return UID;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public String getPrice_of_pap() {
        return Price_of_pap;
    }

    public String getPrice_of_cu() {
        return Price_of_cu;
    }

    public String getPrice_of_cart() {
        return Price_of_cart;
    }

    public String getPrice_of_alm() {
        return price_of_alm;
    }

    public String getPrice_of_plastic() {
        return Price_of_plastic;
    }

    public String getPrice_of_iron() {
        return price_of_iron;
    }

    public My_Recycling()
    {

    }

}
