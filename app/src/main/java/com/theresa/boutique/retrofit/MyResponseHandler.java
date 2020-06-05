package com.theresa.boutique.retrofit;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.google.gson.JsonElement;
import com.theresa.boutique.util.Utils;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyResponseHandler implements Callback<JsonElement> {
    private Context context;
    private WebServiceCallback webServiceCallback;
    private Call<JsonElement> jsonObjectCall;


    private ProgressDialog progressDialog;

    private boolean showProgress;

    public MyResponseHandler(Context context, Call<JsonElement> jsonObjectCall, boolean showProgress, WebServiceCallback webServiceCallback) {
        this.context = context;
        this.webServiceCallback = webServiceCallback;
        this.jsonObjectCall = jsonObjectCall;
        this.showProgress = showProgress;

        if (showProgress) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading.....");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

    }

    @Override
    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        switch (response.code()) {
            case ResponseCode.OK:
                if (webServiceCallback != null) {
                    if (response.body() != null) {
                        webServiceCallback.getResponse(response.code(), response.body());
                    }
                }
                break;

            case ResponseCode.SERVER_ERROR:
                showAlertDialog("Can't Connect with server");
                webServiceCallback.getError(jsonObjectCall, "Can't Connect with server");
                break;

            case ResponseCode.NOT_FOUND:
                showAlertDialog("Error 404 Server Not Found");
                webServiceCallback.getError(jsonObjectCall, "Error 404 Server Not Found");
                break;


            default:
                showAlertDialog("Can't Connect with server");

                webServiceCallback.getError(jsonObjectCall, "Can't Connect with server");
                break;


        }

    }

    @Override
    public void onFailure(Call<JsonElement> call, Throwable t) {

        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        if (!Utils.isInternetAvailable(context)) {
            showAlertDialog("No Internet Available");
        } else {
            showAlertDialog("Can't Connect with server");

            webServiceCallback.getError(jsonObjectCall, t.getLocalizedMessage());

        }

    }

    private void showAlertDialog(String message) {

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Teresa Boutique");
            builder.setMessage(message);
            builder.setCancelable(false);
            builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    dialog.dismiss();

//                    jsonObjectCall.clone().enqueue(new MyResponseHandler(context, jsonObjectCall, showProgress, webServiceCallback));

                    jsonObjectCall.clone().enqueue(new MyResponseHandler(context, jsonObjectCall, showProgress, webServiceCallback));

                }
            });

            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();


                }
            });


            builder.show();

        } catch (Exception e) {

        }
    }

}
