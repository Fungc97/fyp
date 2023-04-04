package com.example.fypapplication.webService;

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
    Call<BorrowTrans>borrow(@Path("ac")String ac,@Path("barcode")String barcode);


}
