package org.example;


import java.util.List;

public record Schueler(List<Fach> fach) {

    String auswertenProfil(){
        return fach.getFirst().auswertenFach();
    }

    boolean hat100PunktelmAbi(){
        var summe = 0;
        for (var f : fach){
            if (f.istPruefungsFach()) {
                summe += f.abipruefung().note();
            }
        }
        return summe * 4 >= 100;
    }











}
