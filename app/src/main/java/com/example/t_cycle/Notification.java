package com.example.t_cycle;

import java.util.Date;

public class Notification {
    Date date;
    String Msg,UserID,Date_order;

    public Notification(Date date, String msg, String userID, String date_order) {
        this.date = date;
        Msg = msg;
        UserID = userID;
        this.Date_order = date_order;
    }
    public String getDate_order() {
        return Date_order;
    }

    public Date getDate() {
        return date;
    }

    public String getMsg() {
        return Msg;
    }

    public String getUserID() {
        return UserID;
    }

    public Notification() {
    }
}
