package com.example.fypapplication.activity;

import static com.example.fypapplication.FYPStatic.cCurrentUserAccountType;
import static com.example.fypapplication.FYPStatic.context;
import static com.example.fypapplication.FYPStatic.initCurrentContext;
import static com.example.fypapplication.FYPStatic.initToolBar;
import static com.example.fypapplication.FYPStatic.showQuestionDialogYesNo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fypapplication.R;

public class ActMainPageStaff extends AppCompatActivity {

    private Button btnProcessReturn, btnMangeRes, btnCheckReader, btnBrowseCat, btnMyAc;//staff

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main_page_staff);
        initCurrentContext(this);

        initToolBar("Main Screen (Staff)", this, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    showQuestionDialogYesNo(context, "Confirmation", "Do you want to log out?", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {//nth
                        }
                    });
                }
            }
        });
        btnProcessReturn = findViewById(R.id.btnProcessReturn);
        btnMangeRes = findViewById(R.id.btnManageRes);
        btnCheckReader = findViewById(R.id.btnCheckReader);
        btnBrowseCat = findViewById(R.id.btnBrowseCat);
        btnMyAc = findViewById(R.id.btnMyAc);


    }


}