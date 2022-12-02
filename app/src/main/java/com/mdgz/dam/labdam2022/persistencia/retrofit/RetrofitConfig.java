package com.mdgz.dam.labdam2022.persistencia.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.persistencia.retrofit.rest.FavoritoRest;
import com.mdgz.dam.labdam2022.persistencia.retrofit.rest.ReservaRest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    final private String baseURL = "https://dam-recordatorio-favoritos-api.duckdns.org/";
    final private String authorization = "Basic Q2hvcnRRdWFyaW5SZXlub3NvOkNob3J0UXVhcmluUmV5bm9zbw==";
    public ReservaRest reservaRest;
    public FavoritoRest favoritoRest;
    private static RetrofitConfig instance;

    private RetrofitConfig() {
        Gson gson = new GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request.Builder requestBuilder = chain.request().newBuilder();
                    requestBuilder.header("Authorization", authorization);
                    requestBuilder.header("Content-Type", "application/json");
                    return chain.proceed(requestBuilder.build());
                })
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        reservaRest = retrofit.create(ReservaRest.class);
        favoritoRest = retrofit.create(FavoritoRest.class);
    }

    public synchronized static RetrofitConfig getInstance() {
        if (instance == null) {
            instance = new RetrofitConfig();
        }
        return instance;
    }
}
