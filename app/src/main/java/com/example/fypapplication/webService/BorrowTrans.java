package com.example.fypapplication.webService;

import com.google.gson.annotations.SerializedName;

public class BorrowTrans {
    @SerializedName("tranState")
    private String tranState;

    @SerializedName("newBorrowID")
    private String newBorrowID;

    @SerializedName("dueDate")
    private String dueDate;

    public String getDueDate() {
        return dueDate.substring(0,10);
    }

    public String getTranState() {
        return tranState;
    }

    public String getNewBorrowID() {
        return newBorrowID;
    }
}
