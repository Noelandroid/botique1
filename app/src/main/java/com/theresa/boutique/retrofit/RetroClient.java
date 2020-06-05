package com.theresa.boutique.retrofit;




import com.theresa.boutique.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static final String BASE_URL = "http://61.2.142.14/teresa_demo/";
    private static Retrofit retrofit = null;




    private static Retrofit getApiClient() {
        if (retrofit == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                                                .connectTimeout(1, TimeUnit.MINUTES)
                                                . readTimeout(1, TimeUnit.MINUTES);
            // add other interceptors if any..
        //    httpClient.retryOnConnectionFailure(false);

            //add logging at last interceptor

            if (BuildConfig.DEBUG) {
                httpClient.addInterceptor(logging);
            }


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //for string response
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit;
    }

    public static ApiInterface getAPIs() {

        return getApiClient().create(ApiInterface.class);


    }
}
