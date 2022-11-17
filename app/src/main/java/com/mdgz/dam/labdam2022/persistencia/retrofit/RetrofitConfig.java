package com.mdgz.dam.labdam2022.persistencia.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.persistencia.retrofit.rest.ReservaRest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    final private String baseURL = "https://dam-recordatorio-favoritos-api.duckdns.org/";
    final private String authorization = "Basic Q2hvcnRRdWFyaW5SZXlub3NvOkNob3J0UXVhcmluUmV5bm9zbw==";
    public ReservaRest reservaRest;
    private static RetrofitConfig instance;

    private RetrofitConfig() {
        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient httpClient = new OkHttpClient();
        httpClient.networkInterceptors().add(chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.header("Authorization", authorization);
            return chain.proceed(requestBuilder.build());
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        reservaRest = retrofit.create(ReservaRest.class);
    }

    public static RetrofitConfig getInstance() {
        if (instance == null) {
            instance = new RetrofitConfig();
        }
        return instance;
    }
}
