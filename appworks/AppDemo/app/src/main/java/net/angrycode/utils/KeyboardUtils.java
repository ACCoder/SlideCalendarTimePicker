package net.angrycode.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * utilities
 */
public class KeyboardUtils {

    private static final String TAG = "KeyboardUtils";

    /**
     * Hide soft input method manager
     *
     * @param view
     * @return view
     */
    public static View hideSoftInput(final View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(INPUT_METHOD_SERVICE);
        if (manager != null)
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        return view;
    }

    /**
     * 显示或隐藏IME
     *
     * @param context
     * @param hide
     */
    public static void toggle(Activity context, boolean hide) {
        InputMethodManager manager = ((InputMethodManager) context
                .getSystemService(Activity.INPUT_METHOD_SERVICE));
        View view = context.getCurrentFocus();
        if (view == null || view.getWindowToken() == null) {
            return;
        }
        try {
            if (hide) {
                manager.hideSoftInputFromWindow(view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            } else { // show IME
                manager.showSoftInput(context.getCurrentFocus(),
                        InputMethodManager.SHOW_IMPLICIT);
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * 在dialog开启前确定需要开启后跳出IME
     *
     * @param dialog
     */
    public static void showIMEonDialog(AlertDialog dialog) {
        try {
            Window window = dialog.getWindow();
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

} 