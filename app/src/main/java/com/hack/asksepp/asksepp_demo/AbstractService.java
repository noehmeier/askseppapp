package com.hack.asksepp.asksepp_demo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mateusz on 20.03.16.
 */
public abstract class AbstractService {
    public static final String APP_URL = "http://7hackserver.eu-gb.mybluemix.net/";

    private HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    private OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

    protected final Retrofit retrofit = new Retrofit.Builder().baseUrl(APP_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();

    protected AbstractService() {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
