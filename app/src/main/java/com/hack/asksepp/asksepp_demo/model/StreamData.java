package com.hack.asksepp.asksepp_demo.model;

/**
 * Created by stefan on 20.03.16.
 */
public class StreamData {

    private String series;

    private int time;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "StreamData{" +
                "series='" + series + '\'' +
                ", time=" + time +
                '}';
    }
}
