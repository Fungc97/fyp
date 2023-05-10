package com.example.fypapplication.webService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Methods {
    //if u write("/orders"), last part of base url will be replace:
    // base - >http://192.168.102.123:8090/api/
    // become -> http://192.168.102.123:8090/orders

    @GET("login/ac/{ac}/pw/{pw}") //u can do "http://192.168.102.123:8090/api/orders" overwrite whole base url
    Call<Account> login(@Path("ac") String ac, @Path("pw") String pw);

    @GET("bookcopiesstatus/barcode/{barcode}")
    Call<BookCopiesStatus>bookcopiesstatus(@Path("barcode")String barcode);

    @PUT("borrow/ac/{ac}/barcode/{barcode}")
    Call<BorrowRetTrans>borrow(@Path("ac")String ac, @Path("barcode")String barcode);

    @PUT("ret/ac/{ac}/barcode/{barcode}")
    Call<BorrowRetTrans>returnBook(@Path("ac")String ac, @Path("barcode")String barcode);


    @GET("borrowedbooks/ac/{ac}")
    Call<List<BorrowedBook>>borrowedbooks(@Path("ac")String ac);

    @PUT("renew/barcodeID/{barcodeID}")
    Call<RenewTrans>renew(@Path("barcodeID")String barcodeID);


    @GET("allbook")
    Call<List<Book>>getAllBook();

    @GET("getAllBookCopies/isbn/{isbn}")
    Call<List<BranchCopies>>getAllBookCopies(@Path("isbn")String isbn);

    @GET("getAcInfo/{ac}")
    Call<AccountInfo>getAcInfo(@Path("ac")String ac);


}
