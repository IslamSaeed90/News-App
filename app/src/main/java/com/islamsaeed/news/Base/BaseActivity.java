package com.islamsaeed.news.Base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    /* Reference on Activity*/

    public AppCompatActivity activity;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
    }

    /*دي هاستخدمها في كل مكان عايز اعمل فيه  dialog*/
    public AlertDialog showConfirmationDialog(int title, int message,
                                              int postext, AlertDialog.OnClickListener posAction, boolean isCancelable) {

        AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(postext, posAction)
                .setCancelable(isCancelable) //  عشان اليوزر يعرف يعمل dismiss لو هي ب true
                .show();
        return alertDialog;
    }


    /*دي هاستخدمها في كل مكان عايز اعمل فيه  dialog
     * دي هاتاخد String*/
    public AlertDialog showConfirmationDialog(String title, String message,
                                              String postext, AlertDialog.OnClickListener posAction, boolean isCancelable) {

        AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(postext, posAction)
                .setCancelable(isCancelable) //  عشان اليوزر يعرف يعمل dismiss لو هي ب true
                .show();
        return alertDialog;
    }


    /* عايز اطلع message  من غير action  بعملله  warning مثلا */
    public AlertDialog showMessage(int title, int message, int postext) {

        AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(postext, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        /* يبقي انا كده هاطلع message لل user
                         ولو داس علي ok  هايعمل dismiss  لل dialog */
                    }
                })
                .show();
        return alertDialog;
    }

    /* عايز اطلع message  من غير action  بعملله  warning مثلا
     * دي بتاخد String */
    public AlertDialog showMessage(int title, String message, int postext) {

        AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(postext, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        /* يبقي انا كده هاطلع message لل user
                         ولو داس علي ok  هايعمل dismiss  لل dialog */
                    }
                })
                .show();
        return alertDialog;
    }




    /*  لو انا عايز اطلع progress bar  اللي خو ال loading */

    ProgressDialog progressDialog;

    public ProgressDialog showProgressBar(int message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(message));
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }


    /* دي عشان ت hide ال progress dialog */
    public void hideProgressDialog() {

        if (progressDialog != null)
            progressDialog.dismiss();
    }


}
