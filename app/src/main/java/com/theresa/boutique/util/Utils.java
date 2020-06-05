package com.theresa.boutique.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utils {

    public static void hideKeyboard(Activity activity, View view) {
        InputMethodManager methodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert methodManager != null && view != null;
        methodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showKeyboard(Activity activity, View view) {
        InputMethodManager methodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert methodManager != null && view != null;
        methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public static int getBodyMeasurementType(String id) {
        Log.d("XXX", "getBodyMeasurementType: "+id);
        if (id.contains("AN")||id.contains("CH")) {         // Anarkali, churidar
            return Constants.BodyMeasurementType.ANARKALI_CHURIDAR;

        } else if (id.contains("SH")||id.contains("SA")||id.contains("OV")) { //shawl, saree, overcoat
            return Constants.BodyMeasurementType.NoUI;

        } else if (id.contains("FR")||id.contains("SK")||id.contains("GN")) { // frock, top/skirt, gown
            return Constants.BodyMeasurementType.TOP_SKIRT;
        } else if (id.contains("BL")) {
            return Constants.BodyMeasurementType.BLOUSE;
        } else {
            return Constants.BodyMeasurementType.NoUI;
        }
    }




    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
        } else {
            return false;
        }
    }


}
