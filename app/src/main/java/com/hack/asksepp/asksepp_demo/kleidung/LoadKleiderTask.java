package com.hack.asksepp.asksepp_demo.kleidung;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

/**
 * Created by mateusz on 20.03.16.
 */
public class LoadKleiderTask extends AsyncTask<Integer, Long, List<Kleidung>> {
    private final AsyncResponse<List<Kleidung>> delegate;
    public LoadKleiderTask(AsyncResponse<List<Kleidung>> delegate) {
        this.delegate = delegate;
    }
    @Override
    protected List<Kleidung> doInBackground(Integer... params) {
        try {
            return new KleidungService().findKleidungByTimeInSeconds(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Kleidung> kleidungs) {
        super.onPostExecute(kleidungs);
        delegate.asyncFinished(kleidungs);
    }
}
