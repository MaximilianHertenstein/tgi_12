package org.example;

import static java.lang.IO.println;

public class GUI {

    Steuerung dieSteuerung;


    public void anzeigenTGTermin(Datum datum, String text){
        println(datum.anzeigen() + " " +  text);
    }

    public void anzeigenTGTermine(Datum von, Datum bis){
        dieSteuerung.anzeigenTGTermine(von, bis);
    }

    public void setSteuerung(Steuerung steuerung) {
        dieSteuerung = steuerung;
    }
}
