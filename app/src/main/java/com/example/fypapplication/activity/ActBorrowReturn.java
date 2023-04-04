package com.example.fypapplication.activity;

import static com.example.fypapplication.FYPStatic.cCurrentUserAccountType;
import static com.example.fypapplication.FYPStatic.context;
import static com.example.fypapplication.FYPStatic.initCurrentContext;
import static com.example.fypapplication.FYPStatic.initToolBar;
import static com.example.fypapplication.FYPStatic.methods;
import static com.example.fypapplication.FYPStatic.sCurrentUserName;
import static com.example.fypapplication.FYPStatic.showErrorMsgDialogOK;
import static com.example.fypapplication.FYPConst.*;
import static com.example.fypapplication.FYPStatic.showInfoMsgDialogOK;
import static com.example.fypapplication.FYPStatic.showQuestionDialogYesNo;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.fypapplication.R;
import com.example.fypapplication.webService.Account;
import com.example.fypapplication.webService.BookCopiesStatus;
import com.example.fypapplication.webService.BorrowTrans;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActBorrowReturn extends AppCompatActivity {

    private EditText etBarcode;
    private ImageView ivErase, ivScan;
    private final ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            etBarcode.setText(result.getContents());
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_borrow_return);
        initCurrentContext(this);

        etBarcode = findViewById(R.id.etBarcode);
        ivErase = findViewById(R.id.ivErase);
        ivScan = findViewById(R.id.ivScan);

        initToolBar("Borrow/Return", this, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    finish();
                }
            }
        });

        etBarcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 10) {
                    getBookCopiesStatus();
                }
            }
        });
        ivErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etBarcode.setText("");
            }
        });
        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume Up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(ActCapture.class);
        barLaucher.launch(options);

    }

    private void getBookCopiesStatus() {
        String sBarcode = etBarcode.getText().toString();

        Call<BookCopiesStatus> call = methods.bookcopiesstatus(sBarcode);
        call.enqueue(new Callback<BookCopiesStatus>() {//execute the call and get the response;network op. need to be run in background thread
            @Override
            public void onResponse(Call<BookCopiesStatus> call, Response<BookCopiesStatus> response) {
                BookCopiesStatus bookCopiesStatus = response.body();
                if (response.isSuccessful()) {
                    switch (bookCopiesStatus.getStatus()) {
                        case BOOKCOPIES_STATUS_ONSHELF:
                            showQuestionDialogYesNo(context, "Confirmation", "Do u want to BORROW this book?\n" +
                                            "Title: " + bookCopiesStatus.getTitle(),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            borrowBook(sBarcode);
                                        }
                                    },
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                            etBarcode.setText("");
                                        }
                                    });
                        break;
                        case BOOKCOPIES_STATUS_BORROWOUT:
                            showQuestionDialogYesNo(context, "Confirmation", "Do u want to RETURN this book?\n" +
                                            "Title: " + bookCopiesStatus.getTitle(),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            returnBook(sBarcode);
                                        }
                                    },
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                            etBarcode.setText("");
                                        }
                                    });
                        break;
                        case BOOKCOPIES_STATUS_DELETED:
                            showErrorMsgDialogOK(context,"The book is invalid. (Status=Deleted)");
                            break;

                    }

                } else {
                    showErrorMsgDialogOK(context, "bookcopiesstatus: response not successful\n");
                }
            }

            @Override
            public void onFailure(Call<BookCopiesStatus> call, Throwable t) {
                showErrorMsgDialogOK(context, "bookcopiesstatus: failed getting response\n " + t.getMessage());
            }
        });

    }

    private void borrowBook(String sBarcode) {
        String username = sCurrentUserName;

        Call<BorrowTrans> call = methods.borrow(username, sBarcode);
        call.enqueue(new Callback<BorrowTrans>() {//execute the call and get the response;network op. need to be run in background thread
            @Override
            public void onResponse(Call<BorrowTrans> call, Response<BorrowTrans> response) {
                BorrowTrans borrowTrans = response.body();
                if (response.isSuccessful()) {

                    if (!borrowTrans.getTranState() .equals("SUCCESS") ) {
                        showErrorMsgDialogOK(context, "Some error occurs in borrowing transaction.\n" +borrowTrans.getTranState());
                    }
                    else {
                        showInfoMsgDialogOK(context, "Borrow Success. \nBorrowID: " + borrowTrans.getNewBorrowID()
                                +"\nDue Date: "+borrowTrans.getDueDate(), (dialogInterface, i) -> dialogInterface.dismiss());
                    }
                } else {
                    showErrorMsgDialogOK(context, "borrow: response not successful\n");
                }
            }

            @Override
            public void onFailure(Call<BorrowTrans> call, Throwable t) {
                showErrorMsgDialogOK(context, "borrow: failed getting response\n" + t.getMessage());
            }
        });
    }

    private void returnBook(String sBarcode) {
    }
}