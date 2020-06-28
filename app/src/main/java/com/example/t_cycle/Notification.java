package com.example.t_cycle;

import java.util.Date;

public class Notification {
    Date date;
    String Msg,UserID;

    public Notification(Date date, String msg, String userID) {
        this.date = date;
        Msg = msg;
        UserID = userID;
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
