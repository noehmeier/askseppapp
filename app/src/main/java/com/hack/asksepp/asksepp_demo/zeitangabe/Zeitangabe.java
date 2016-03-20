package com.hack.asksepp.asksepp_demo.zeitangabe;

/**
 * Created by mateusz on 20.03.16.
 */
public class Zeitangabe {

    private final int id;
    private final int timeInSeconds;

    public Zeitangabe(int id, int timeInSeconds) {
        this.id = id;
        this.timeInSeconds = timeInSeconds;
    }

    public int getId() {
        return id;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }
}
