package com.rba.ecommerce.util.api;

import com.rba.ecommerce.util.Constant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ricardo Bravo on 2/07/16.
 */

public class EcommerceApiManager {

    private static EcommerceApi ecommerceApi;

    public static EcommerceApi apiManager() {

        if(ecommerceApi == null){

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(logging).build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Constant.URL_BASE)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ecommerceApi = client.create(EcommerceApi.class);
        }

        return ecommerceApi;
    }


}
