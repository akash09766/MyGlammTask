package com.myglamm.myglammtask.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myglamm.myglammtask.R;

/**
 * Created by Akash Wangalwar on 26-10-2016.
 */
public class BaseActivity extends AppCompatActivity{

    private static final String TAG = BaseActivity.class.getName();

    public void showShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public ProgressDialog getProgressDialog(String message) {
        ProgressDialog p = new ProgressDialog(this);
        p.setCancelable(false);
        p.setMessage(message);
        return p;
    }


    public void showShortSnackBar(String message) {
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public void showIndifiniteSnackBar(String message) {
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE);

        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setMaxLines(4);
        snackbar.show();
    }

    public void showLongSnackBar(String message) {
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void showLongSnackBar(String message,View view) {
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showKeyBoard(EditText mUserNameEdit) {
        InputMethodManager keyboard = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.showSoftInput(mUserNameEdit, 0);
    }
}
