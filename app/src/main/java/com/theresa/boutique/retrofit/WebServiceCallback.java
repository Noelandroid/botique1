package com.theresa.boutique.retrofit;

import com.google.gson.JsonElement;

import retrofit2.Call;

public interface WebServiceCallback {



    void getResponse(int code, JsonElement jsonElementResponse);

    void getError(Call<JsonElement> call, String message);
}
