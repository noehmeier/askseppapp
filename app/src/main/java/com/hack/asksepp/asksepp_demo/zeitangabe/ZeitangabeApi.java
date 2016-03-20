package com.hack.asksepp.asksepp_demo.zeitangabe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mateusz on 20.03.16.
 */
public interface ZeitangabeApi {
    @GET("/api/Zeitangabes/findOne")
    public Call<Zeitangabe> zeitangabe(@Query(value = "filter[where][timeInSeconds]") int timeInSeconds);
}
