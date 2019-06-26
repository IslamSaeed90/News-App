package com.islamsaeed.news.Base;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

public class BaseFragment extends Fragment {

    /* Reference on Activity*/


    public BaseActivity activity;
    AlertDialog alertDialog;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = ((BaseActivity) context);
    }

    /*دي هاستخدمها في كل مكان عايز اعمل فيه  dialog*/
    public AlertDialog showConfirmationDialog(int title, int message,
                                              int postext, AlertDialog.OnClickListener posAction, boolean isCancelable) {

        return activity.showConfirmationDialog(title, message, postext, posAction, isCancelable);

    }


    /*دي هاستخدمها في كل مكان عايز اعمل فيه  dialog
     * دي هاتاخد String*/
    public AlertDialog showConfirmationDialog(String title, String message,
                                              String postext, AlertDialog.OnClickListener posAction, boolean isCancelable) {

        return activity.showConfirmationDialog(title, message, postext, posAction, isCancelable);
    }


    /* عايز اطلع message  من غير action  بعملله  warning مثلا */
    public AlertDialog showMessage(int title, int message, int postext) {

        return activity.showMessage(title, message, postext);
    }

    /* عايز اطلع message  من غير action  بعملله  warning مثلا
     * دي بتاخد String */
    public AlertDialog showMessage(String title, String message, String postext) {

        return showMessage(title, message, postext);
    }




    /*  لو انا عايز اطلع progress bar  اللي خو ال loading */

    ProgressDialog progressDialog;

    public ProgressDialog showProgressBar(int message) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle(message);
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
