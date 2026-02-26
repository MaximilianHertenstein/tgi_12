package org.example;

public record Abipruefung(int note) {
    String vergleichMitHJ(float hjDurchschnitt){
        if (note < hjDurchschnitt){
            return "Abiprüfung schlechter als Halbjahre";
        }
        else {
            return "Abiprüfung besser als Halbjahre";
        }
    }

}
