---
title: Kursstufen App
codebraid:
  jupyter: true
---

```{.java .cb-run}
import java.util.List;
public record Halbjahr(int note) {
}


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

    public static ArrayList<Halbjahr> notenZuHalbjahren(List<Integer> notenHJ) {
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
```

Definiere jede Klasse in einer eigenen Datei.


#  Abipruefung

## Record 


Definiere ein Record `Abipruefung` mit der Eigenschaft:

- `note` vom Typ `int`


```{.java .cb-nb line_numbers=false}
new Abipruefung(10);
```

```{.java .cb-nb line_numbers=false}
new Abipruefung(13);
```

## vergleichMitHJ

Ergänze die Methode  
`String vergleichMitHJ(float hjDurchschnitt)`

Die Methode 

- gibt `"Abiprüfung schlechter als Halbjahre"` zurück, wenn die Abinote schlechter ist als der Halbjahresdurchschnitt
- sonst wird `"Abiprüfung besser als Halbjahre"` zurückgegeben

```{.java .cb-nb line_numbers=false}
new Abipruefung(10).vergleichMitHJ(8.5f);
```

```{.java .cb-nb line_numbers=false}
new Abipruefung(12).vergleichMitHJ(13.0f);
```

# Halbjahr
## Record 

Definiere ein Record `Halbjahr` mit der Eigenschaft:

- `note` vom Typ `int`


```{.java .cb-nb line_numbers=false}
new Halbjahr(12);
```

```{.java .cb-nb line_numbers=false}
new Halbjahr(8);
```


#  Fach

## Record

Definiere ein Record `Fach` mit folgenden Eigenschaften:

- `hj` vom Typ `List<Halbjahr>`
- `abipruefung` vom Typ `Abipruefung`


```{.java .cb-nb line_numbers=false}
new Fach(
    List.of(new Halbjahr(10), new Halbjahr(12)),
    new Abipruefung(11)
);
```

```{.java .cb-nb line_numbers=false}
new Fach(
    List.of(new Halbjahr(8), new Halbjahr(9)),
    new Abipruefung(10)
);
```


## Durchschnitt der Halbjahre

Implementiere die Methode `avg`. Diese berechnet den Durchschnitt aller Halbjahresnoten und gibt diesen als `float` zurück.

```{.java .cb-nb line_numbers=false}
new Fach(
    List.of(new Halbjahr(10), new Halbjahr(12)),
    new Abipruefung(11)
).avg();
```

```{.java .cb-nb line_numbers=false}
new Fach(
    List.of(new Halbjahr(7), new Halbjahr(9), new Halbjahr(10)),
    new Abipruefung(9)
).avg();
```


## Fach auswerten

Implementiere die Methode
`auswertenFach`. Diese 

- berechnet den Halbjahresdurchschnitt
- wenn in dem Fach keine Abiprüfung geschrieben wurde, wird der Durschnitt aus den Halbjahren als `String` zurückgegeben
- Ansonsten wird der Durchscnitt aus den Halbjahren  mit der Abiprüfung verglichen.


```{.java .cb-nb line_numbers=false}
new Fach(
    List.of(new Halbjahr(12), new Halbjahr(13)),
    null
).auswertenFach();
```

```{.java .cb-nb line_numbers=false}
new Fach(
    List.of(new Halbjahr(12), new Halbjahr(13)),
    new Abipruefung(10)
).auswertenFach();
```

```{.java .cb-nb line_numbers=false}
new Fach(
    List.of(new Halbjahr(9), new Halbjahr(10)),
    new Abipruefung(11)
).auswertenFach();
```
**Hinweis:** Nutze `avg`!


## Hilfsmethode `notenZuHalbjahren`

Implementiere eine **statische** Methode `notenZuHalbjahren`.
Diese wandelt eine Liste von `Integer` in `Halbjahr`-Objekte um.

```{.java .cb-nb line_numbers=false}
List<Integer> noten = List.of(10, 11, 12);
Fach.notenZuHalbjahren(noten);
```

```{.java .cb-nb line_numbers=false}
List<Integer> noten = List.of(8, 9);
Fach.notenZuHalbjahren(noten);
```


## Zusätzlicher Konstruktor in `Fach`

Ergänze einen weiteren Konstruktor

```java
Fach(List<Integer> notenHJ, int noteAbipruefung)
```

Die Methode

- erzeugt Halbjahre aus `notenHJ`
- erzeugt eine `Abipruefung` mit der Note `noteAbipruefung`
- Ruft den automatisch definierten Konstruktor der Klasse `Fach` auf (`this`)

\tiny
```{.java .cb-nb line_numbers=false}
new Fach(List.of(10, 11, 12), 13);
```

```{.java .cb-nb line_numbers=false}
new Fach(List.of(8, 9), 10);
```
\normalsize
**Hinweis:** Nutze `notenZuHalbjahren`!

## Prüfungsfach erkennen

Implementiere die Methode `istPruefungsFach`. Diese bestimmt ob ein ein Objekt vom Typ `Fach` eine Eigenschaft vom Typ `Abipruefung` hat.

```{.java .cb-nb line_numbers=false}
new Fach(List.of(new Halbjahr(10)), new Abipruefung(12))
    .istPruefungsFach();
```

```{.java .cb-nb line_numbers=false}
new Fach(List.of(new Halbjahr(8), new Halbjahr(9)), null)
    .istPruefungsFach();
```


## Record `Schueler`

Definiere ein Record `Schueler` mit einer Eigenschaft:

- `fach` vom Typ `List<Fach>`


```{.java .cb-nb line_numbers=false}
new Schueler(List.of());
```

```{.java .cb-nb line_numbers=false}
new Schueler(
    List.of(
        new Fach(List.of(new Halbjahr(10)), new Abipruefung(12))
    )
);
```


## Profil auswerten

Implementiere die Methode `auswertenProfil`. Diese wertet das **erste Fach** der Liste `fach` aus

```{.java .cb-nb line_numbers=false}
new Schueler(
    List.of(
        new Fach(List.of(new Halbjahr(10)), new Abipruefung(12))
    )
).auswertenProfil();
```

```{.java .cb-nb line_numbers=false}
var s = new Schueler(
    List.of(
        new Fach(List.of(new Halbjahr(8), new Halbjahr(9)), new Abipruefung(9))
    )
);
s.auswertenProfil();
```

```{.java .cb-nb line_numbers=false}
Fach ift = new Fach(List.of(10,12,12,15),12);
var yvonne = new Schueler(List.of(ift));
yvonne.auswertenProfil();
```

**Hinweis:** Nutze `auswertenFach`!

## 100-Punkte-Abitur

Implementiere die Methode `hat100PunktelmAbi`. Diese prüft ob ein Schüler in allen Abiturprüfungen mindestens $100$ Punkte erreicht hat.
Dabei werden Prüfungsergebnisse vierfach gewertet.  

```{.java .cb-nb line_numbers=false}
var s = new Schueler(
    List.of(
        new Fach(List.of(new Halbjahr(12)), new Abipruefung(13)),
        new Fach(List.of(new Halbjahr(11)), new Abipruefung(12))
    )
);
s.hat100PunktelmAbi();
```

```{.java .cb-nb line_numbers=false}
new Schueler(
    List.of(
        new Fach(List.of(new Halbjahr(10)), new Abipruefung(10))
    )
).hat100PunktelmAbi();
```

```{.java .cb-nb line_numbers=false}
Fach ift = new Fach(List.of(10,12,12,15),12);


var mathe = new Fach(List.of(),7);
var deutsch = new Fach(List.of(),3);
var chemie = new Fach(List.of(),6);
var ktr = new Fach(List.of(),2);

var yvonne = new Schueler(List.of(ift, mathe, deutsch, chemie, ktr));
yvonne.hat100PunktelmAbi();
```