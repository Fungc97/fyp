package com.example.fypapplication;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.fypapplication.webService.Methods;

public class FYPStatic {
    public static Context context;
    public static Methods methods;
    public static String sCurrentUserName;
    public static char cCurrentUserAccountType;


    public static void showErrorMsgDialogOK(Context context, String Msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Oops")
                .setIcon(R.drawable.baseline_error_24)
                .setMessage(Msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }



}
