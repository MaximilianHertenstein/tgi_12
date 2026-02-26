package org.example;

import java.util.List;

public record Steuerung(List<TGTermin> derTGTermin, GUI gui) {

    public Steuerung(GUI gui, List<TGTermin> derTGTermin) {
        this(derTGTermin, gui);
        gui.setSteuerung(this);
    }

    public void anzeigenTGTermine(Datum von, Datum bis){
        for (TGTermin tgTermin : derTGTermin) {
            if (von.smallerOrEqual(tgTermin.datum()) &&
                tgTermin.datum().smallerOrEqual(bis)) {
                gui.anzeigenTGTermin(tgTermin.datum(), tgTermin.tgTermin());
            }
        }
    }
}
