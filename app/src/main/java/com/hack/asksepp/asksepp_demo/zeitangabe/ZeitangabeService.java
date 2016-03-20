package com.hack.asksepp.asksepp_demo.zeitangabe;

import com.hack.asksepp.asksepp_demo.AbstractService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mateusz on 20.03.16.
 */
public class ZeitangabeService extends AbstractService {

    private final ZeitangabeApi zeitangabeApi = retrofit.create(ZeitangabeApi.class);

    public Zeitangabe findZeitangabeByTimeInSeconds(int timeInSeconds) throws IOException {
        Call<Zeitangabe> call = zeitangabeApi.zeitangabe(timeInSeconds);
        return  call.execute().body();
    }
}
