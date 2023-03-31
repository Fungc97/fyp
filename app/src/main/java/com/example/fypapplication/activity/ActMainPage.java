package com.example.fypapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fypapplication.R;
import static com.example.fypapplication.FYPStatic.*;
public class ActMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(cCurrentUserAccountType=='S'){
            setContentView(R.layout.act_main_page_staff);
        }
        else if(cCurrentUserAccountType=='R'){
            setContentView(R.layout.act_main_page_reader);
        }



    }
}