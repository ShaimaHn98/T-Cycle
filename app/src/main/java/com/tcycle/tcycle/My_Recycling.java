package com.tcycle.tcycle;

import java.io.Serializable;
import java.util.Date;

public class My_Recycling implements Serializable {
private String Price_of_pap,Price_of_cu,Price_of_cart,price_of_alm,Price_of_plastic,price_of_iron;
private Double Total;
private Date Date;
private String UID;


    public My_Recycling(String price_of_pap, String price_of_cu, String price_of_cart, String price_of_alm, String price_of_plastic, String price_of_iron, Double total, java.util.Date date, String UID) {
        Price_of_pap = price_of_pap;
        Price_of_cu = price_of_cu;
        Price_of_cart = price_of_cart;
        this.price_of_alm = price_of_alm;
        Price_of_plastic = price_of_plastic;
        this.price_of_iron = price_of_iron;
        Total = total;
        Date = date;
        this.UID = UID;
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

    public Double getTotal() {
        return Total;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public String getUID() {
        return UID;
    }

    public My_Recycling()
    {


    }

}
