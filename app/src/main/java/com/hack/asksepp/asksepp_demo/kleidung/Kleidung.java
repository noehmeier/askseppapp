package com.hack.asksepp.asksepp_demo.kleidung;

/**
 * Created by mateusz on 20.03.16.
 */
public class Kleidung {
    private String Position;
    private String Typ;
    private String Farbe;
    private String Id;
    private String Url;

    public Kleidung(String position, String typ, String farbe, String id, String url) {
        this.Position = position;
        this.Typ = typ;
        this.Farbe = farbe;
        this.Id = id;
        this.Url = url;
    }

    public String getPosition() {
        return Position;
    }

    public String getTyp() {
        return Typ;
    }

    public String getFarbe() {
        return Farbe;
    }

    public String getId() {
        return Id;
    }

    public String getUrl() {
        return Url;
    }
}
