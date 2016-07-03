package com.rba.ecommerce.util;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.rba.ecommerce.R;
import com.rba.ecommerce.listener.OnSnackbarListener;

/**
 * Created by Ricardo Bravo on 2/07/16.
 */

public class Util {

    public static void showSnackBar(View view, String text){

        final Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);

        snackbar.show();
    }

    public static void showSnackBarLong(View view, String text,
                                        final OnSnackbarListener onSnackbarListener, final int variable){

        final Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);

        snackbar.setAction(view.getContext().getString(R.string.retry), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSnackbarListener.onSnackbarPressed(variable);
                snackbar.dismiss();
            }
        });

        snackbar.show();
    }

}
