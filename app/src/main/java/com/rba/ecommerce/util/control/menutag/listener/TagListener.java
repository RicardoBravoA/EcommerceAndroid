package com.rba.ecommerce.util.control.menutag.listener;

import android.view.View;

/**
 * Created by Ricardo Bravo on 12/07/16.
 */

public interface TagListener {
    void onTagSelected(View view, int index);
    void onTagDeselected(View view, int index);
}