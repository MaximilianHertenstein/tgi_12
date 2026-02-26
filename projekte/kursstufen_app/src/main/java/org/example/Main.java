package org.example;

import java.util.List;

import static java.lang.IO.println;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        Fach ift = new Fach(List.of(10,12,12,15),12);

        var x = new Abipruefung(10);
        println(x.vergleichMitHJ(12f));

        var mathe = new Fach(List.of(),7);
        var deutsch = new Fach(List.of(),3);
        var chemie = new Fach(List.of(),6);
        var ktr = new Fach(List.of(),2);

        var yvonne = new Schueler(List.of(ift, mathe, deutsch, chemie, ktr));
        println(yvonne.auswertenProfil());
        println(yvonne.hat100PunktelmAbi());
    }


}

