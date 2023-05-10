package com.example.fypapplication.webService;

import com.google.gson.annotations.SerializedName;

public class BorrowRetTrans {
    @SerializedName("tranState")
    private String tranState;

    @SerializedName("borrowID")
    private String borrowID;

    @SerializedName("dueDate")
    private String dueDate;

    public String getDueDate() {
        return dueDate.substring(0,10);
    }

    public String getTranState() {
        return tranState;
    }

    public String getBorrowId() {return borrowID;}
}
