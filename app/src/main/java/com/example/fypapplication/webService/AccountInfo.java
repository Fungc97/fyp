package com.example.fypapplication.webService;

import com.google.gson.annotations.SerializedName;

public class AccountInfo {

    @SerializedName("TypeId")
    private String typeId;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;


    @SerializedName("Gender")
    private String gender;

    @SerializedName("HKID")
    private String hkid;

    @SerializedName("DOB")
    private String dob;

    @SerializedName("EmailAddress")
    private String emailAddress;

    @SerializedName("Address")
    private String address;

    @SerializedName("ContactNo")
    private String contactNo;

    @SerializedName("MaxQuota")
    private String maxQuota;


    @SerializedName("Ac")
    private String ac;

    public String getMaxQuota() {
        return maxQuota;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getHkid() {
        return hkid;
    }

    public String getDob() {
        return dob.substring(0,10);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getAc() {
        return ac;
    }
}
