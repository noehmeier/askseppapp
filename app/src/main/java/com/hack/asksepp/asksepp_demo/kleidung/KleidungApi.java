package com.hack.asksepp.asksepp_demo.kleidung;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mateusz on 20.03.16.
 */
public interface KleidungApi {

    @GET("/api/Zeitangabes/{id}/kleidungs")
    public Call<List<Kleidung>> kleiderByZeitangabeId(@Path("id") int id);

}
