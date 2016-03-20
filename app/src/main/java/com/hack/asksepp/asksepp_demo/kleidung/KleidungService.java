package com.hack.asksepp.asksepp_demo.kleidung;

import com.hack.asksepp.asksepp_demo.AbstractService;
import com.hack.asksepp.asksepp_demo.zeitangabe.Zeitangabe;
import com.hack.asksepp.asksepp_demo.zeitangabe.ZeitangabeService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Created by mateusz on 20.03.16.
 */
public class KleidungService extends AbstractService {

    private final KleidungApi kleidungApi = retrofit.create(KleidungApi.class);

    public List<Kleidung> findKleidungByTimeInSeconds(int timeInSeconds) throws IOException {
        final ZeitangabeService zeitangabeService = new ZeitangabeService();
        final Zeitangabe zeitangabe = zeitangabeService.findZeitangabeByTimeInSeconds(timeInSeconds);
        System.out.println(zeitangabe.getId());
        System.out.println(zeitangabe.getTimeInSeconds());
        final Call<List<Kleidung>> call = kleidungApi.kleiderByZeitangabeId(zeitangabe.getId());
        return call.execute().body();
    }
}

