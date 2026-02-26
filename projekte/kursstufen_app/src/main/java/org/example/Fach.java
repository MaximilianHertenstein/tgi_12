package org.example;

import java.util.ArrayList;
import java.util.List;

public record Fach(List<Halbjahr> hj, Abipruefung abipruefung) {
    public float avg(){
        var summe = 0;
        for (var halbjahr : hj){
            summe += halbjahr.note();
        }
        return summe / ((float) hj.size());
    }

    public String auswertenFach() {
        var hjDurchschnitt = avg();
        if (istPruefungsFach()) {
            return abipruefung.vergleichMitHJ(hjDurchschnitt);
        }
        return hjDurchschnitt + "";
    }

    private static ArrayList<Halbjahr> notenZuHalbjahren(List<Integer> notenHJ) {
        var hjs = new ArrayList<Halbjahr>();
        for (var noteHJ : notenHJ){
            hjs.add(new Halbjahr(noteHJ));
        }
        return hjs;
    }



    Fach(List<Integer> notenHJ,  int noteAbipruefung){
        var hjs = notenZuHalbjahren(notenHJ);
        this(hjs, new Abipruefung(noteAbipruefung));
    }



    boolean istPruefungsFach(){
        return abipruefung != null;
    }




}
