package com.example.t_cycle;

import java.util.Date;

public class My_Recycling {
Double Price_of_pap,Price_of_cu,Price_of_cart,price_of_alm,Price_of_plastic,price_of_iron;
private Date Date;
String UID;
    public My_Recycling(String UID, Date date, Double price_of_pap, Double price_of_cu, Double price_of_cart, Double price_of_alm, Double price_of_plastic, Double price_of_iron) {
        this.UID = UID;
        Date = date;
        Price_of_pap = price_of_pap;
        Price_of_cu = price_of_cu;
        Price_of_cart = price_of_cart;
        this.price_of_alm = price_of_alm;
        Price_of_plastic = price_of_plastic;
        this.price_of_iron = price_of_iron;
    }

    public String getUID() {
        return UID;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public Double getPrice_of_pap() {
        return Price_of_pap;
    }

    public Double getPrice_of_cu() {
        return Price_of_cu;
    }

    public Double getPrice_of_cart() {
        return Price_of_cart;
    }

    public Double getPrice_of_alm() {
        return price_of_alm;
    }

    public Double getPrice_of_plastic() {
        return Price_of_plastic;
    }

    public Double getPrice_of_iron() {
        return price_of_iron;
    }

    public My_Recycling() {
    }
}
