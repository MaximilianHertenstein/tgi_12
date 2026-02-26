package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        var projektArbeit = new TGTermin("Projektarbeit Abgabe", new Datum(30,6,2024));
        var probeABI = new TGTermin("Probeabitur IT", new  Datum(18,2,2025));
        var skiTag = new TGTermin("Skitag", new  Datum(25,2,2025));
        var facharbeitAbgabe = new TGTermin("Facharbeit Abgabe", new Datum(2,3,2025));

        GUI gui = new GUI();
        Steuerung steuerung = new Steuerung(gui, List.of(projektArbeit, probeABI, skiTag, facharbeitAbgabe));
        gui.anzeigenTGTermine(new Datum(18, 2, 2025), new Datum(25, 2, 2025));
    }
}
