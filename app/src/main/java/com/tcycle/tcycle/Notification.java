package com.tcycle.tcycle;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {

   private Date date;
   private String Msg,UserID,Date_order;

    public Notification(Date date, String msg, String userID, String date_order) {
        this.date = date;
        Msg = msg;
        UserID = userID;
        Date_order = date_order;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getDate_order() {
        return Date_order;
    }

    public void setDate_order(String date_order) {
        Date_order = date_order;
    }

    public Notification() {
    }
}
