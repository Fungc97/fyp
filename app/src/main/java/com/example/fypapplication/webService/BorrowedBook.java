package com.example.fypapplication.webService;

import com.google.gson.annotations.SerializedName;

public class BorrowedBook {
    @SerializedName("BarcodeID")
    private String barcodeId;

    @SerializedName("title")
    private String title;

    @SerializedName("BorrowDate")
    private String borrowDate;

    @SerializedName("DueDate")
    private String dueDate;

    @SerializedName("RenewalTimes")
    private String renewalTimes;


    public String getBorrowDate() {
        return borrowDate.substring(0,10);
    }

    public String getDueDate() {
        return dueDate.substring(0,10);
    }

    public String getBarcodeId() {
        return barcodeId;
    }

    public String getTitle() {return title;}

    public String getRenewalTimes() {return renewalTimes;}
}
