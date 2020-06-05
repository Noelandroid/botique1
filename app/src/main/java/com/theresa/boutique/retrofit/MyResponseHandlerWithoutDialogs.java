package com.theresa.boutique.retrofit;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonElement;

import com.theresa.boutique.util.Constants;
import com.theresa.boutique.util.MyDialogHelper;
import com.theresa.boutique.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyResponseHandlerWithoutDialogs implements Callback<JsonElement> {
    private Context context;
    private WebServiceCallback webServiceCallback;
    private Call<JsonElement> jsonObjectCall;


    public MyResponseHandlerWithoutDialogs(Context context, Call<JsonElement> jsonObjectCall, WebServiceCallback webServiceCallback) {
        this.context = context;
        this.webServiceCallback = webServiceCallback;
        this.jsonObjectCall = jsonObjectCall;
    }

    @Override
    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

        switch (response.code()) {
            case ResponseCode.OK:
                if (webServiceCallback != null) {
                    if (response.body() != null) {
                        Log.d(Constants.WEB_SERVICE_TAG, "onResponse: OkHttpMMM handler : "+response.toString());
                        webServiceCallback.getResponse(response.code(), response.body());
                    }
                }
                break;

            case ResponseCode.SERVER_ERROR:
                Log.d(Constants.WEB_SERVICE_TAG, "onResponse: SERVER_ERROR");
                webServiceCallback.getError(jsonObjectCall, "Can't Connect with server");
                break;

            case ResponseCode.NOT_FOUND:
                Log.d(Constants.WEB_SERVICE_TAG, "onResponse: NOT_FOUND");
                webServiceCallback.getError(jsonObjectCall, "Error 404 Server Not Found");
                break;

            default:

                Log.d(Constants.WEB_SERVICE_TAG, "onResponse: ResponseCODE: " + response.code());
                webServiceCallback.getError(jsonObjectCall, "Can't Connect with server");
                break;


        }

    }

    @Override
    public void onFailure(Call<JsonElement> call, Throwable t) {

        if (!Utils.isInternetAvailable(context)) {
            Log.d(Constants.WEB_SERVICE_TAG, "onFailure: No Internet Available");
            MyDialogHelper.showSnackbar(context, "No internet connection!!!");
//            Toast.makeText(context, "No internet connection!!!", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(Constants.WEB_SERVICE_TAG, "onFailure: Can't Connect with server");

            webServiceCallback.getError(jsonObjectCall, t.getLocalizedMessage());

        }

    }

}