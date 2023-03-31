package com.example.fypapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.fypapplication.FYPStatic.*;

import com.example.fypapplication.webService.Account;
import com.example.fypapplication.webService.Methods;
import com.example.fypapplication.R;
import com.example.fypapplication.webService.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActLogin extends AppCompatActivity {
    private EditText etUserName, etPw;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /******every act should have******/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        initCurrentContext();
        /*******/


        initRetrofitClient();
        etUserName = findViewById(R.id.etUsername);
        etPw = findViewById(R.id.etPw);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etUserName.getText().toString().equals("")||etPw.getText().toString().equals("")){
                    showErrorMsgDialogOK(context,"Please enter Username and Password!");
                }else {
                    login();
                }
            }
        });

    }

    private void initCurrentContext() {
        context = this;
    }

    private static void initRetrofitClient() {
        methods = RetrofitClient.getRetrofitInstance().create(Methods.class); //retrofit create the implementation of methods
    }

    private void login() {
        String username = etUserName.getText().toString();
        String pw = etPw.getText().toString();

        Call<Account> call = methods.login(username, pw);
        call.enqueue(new Callback<Account>() {//execute the call and get the response;network op. need to be run in background thread
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Account account = response.body();
                if (response.isSuccessful()) {

                    if (account.getErrorMsgLogin() != null) {
                        showErrorMsgDialogOK(context, "Please enter a valid username and password\n"+account.getErrorMsgLogin());
                    }
                    else {
                        sCurrentUserName=username;
                        cCurrentUserAccountType=account.getAccountType();
                        Intent i =new Intent(context,ActMainPage.class);
                        startActivity(i);

                    }
                }
                else{
                    showErrorMsgDialogOK(context, "login: response not successful\n");
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                showErrorMsgDialogOK(context, "login: failed getting response\n" +t.getMessage());
            }
        });
    }
}