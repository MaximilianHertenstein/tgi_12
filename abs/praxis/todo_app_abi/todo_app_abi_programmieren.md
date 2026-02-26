---
title: Todo-App 
codebraid:
  jupyter: true
---

```{.java .cb-run}

import static java.lang.IO.println;
import java.util.List;


public record Datum(int day,int month, int year) {

    public boolean smallerOrEqual(Datum other) {
        return this.year < other.year ||
               (this.year == other.year && this.month < other.month) ||
               (this.year == other.year && this.month == other.month && this.day < other.day) ||
                (this.year == other.year && this.month == other.month && this.day == other.day);
    }

    public String anzeigen() {
        return day + "." + month + "." + year;
    }
}


public record TGTermin(String tgTermin, Datum datum) {
}

public class GUI {


    public void anzeigenTGTermin(Datum datum, String text){
        println(datum.anzeigen() + " " +  text);
    }


}

public record Steuerung(List<TGTermin> derTGTermin, GUI gui) {
        


public void anzeigenTGTermine(Datum von, Datum bis){
        for (TGTermin tgTermin : derTGTermin) {
            if (von.smallerOrEqual(tgTermin.datum()) &&
                tgTermin.datum().smallerOrEqual(bis)) {
                gui.anzeigenTGTermin(tgTermin.datum(), tgTermin.tgTermin());
            }
        }
    }

}






```

Definiere jede Klasse in einer eigenen Datei.






## Record `Datum`

Definiere ein Record `Datum` mit den Eigenschaften:

- `day` vom Typ `int`
- `month` vom Typ `int`
- `year` vom Typ `int`


```{.java .cb-nb line_numbers=false}
new Datum(1, 1, 2024);
```

```{.java .cb-nb line_numbers=false}
new Datum(15, 6, 2023);
```



## Datum vergleichen

Implementiere die Methode `boolean smallerOrEqual(Datum other)`.

Diese Methode gibt `true` zurück, wenn das aktuelle Datum **zeitlich vor** dem übergebenen Datum liegt oder mit diesem übereinstimmt.

```{.java .cb-nb line_numbers=false}
new Datum(1, 1, 2023).smallerOrEqual(new Datum(31, 12, 2023));
```

```{.java .cb-nb line_numbers=false}
new Datum(10, 5, 2024).smallerOrEqual(new Datum(10, 5, 2024));
```


```{.java .cb-nb line_numbers=false}
new Datum(9, 7, 2024).smallerOrEqual(new Datum(10, 5, 2024));
```


## Datum anzeigen

Implementiere die Methode `anzeigen`.

Das Datum soll im Format `Tag.Monat.Jahr` zurückgegeben werden.

```{.java .cb-nb line_numbers=false}
new Datum(1, 1, 2024).anzeigen();
```

```{.java .cb-nb line_numbers=false}
new Datum(31, 12, 2023).anzeigen();
```



## Record `TGTermin`

Definiere ein Record `TGTermin` mit den Eigenschaften:

- `tgTermin` vom Typ `String`
- `datum` vom Typ `Datum`


```{.java .cb-nb line_numbers=false}
new TGTermin("Mathe TG", new Datum(10, 3, 2024));
```

```{.java .cb-nb line_numbers=false}
new TGTermin("Info TG", new Datum(22, 4, 2024));
```



## Klasse `GUI`

Definiere mit dem Schlüsselwort `class` eine Klasse `GUI`. Diese hat zunächst keine Eigenschaften.

<!-- - einer Eigenschaft `dieSteuerung` vom Typ `Steuerung` -->


## Einzelnen Termin anzeigen

Implementiere in `GUI` die Methode `void anzeigenTGTermin(Datum datum, String text)`.

Diese gibt Datum und Text gemeinsam aus (z.B. auf der Konsole).
\tiny
```{.java .cb-nb line_numbers=false}
new GUI().anzeigenTGTermin(new Datum(1, 2, 2024), "Deutsch TG");
```

```{.java .cb-nb line_numbers=false}
new GUI().anzeigenTGTermin(new Datum(5, 6, 2024), "Mathe TG");
```
\normalsize




## Klasse `Steuerung`

Definiere ein `record` `Steuerung`. Diese Klasse die Eigenschaften

- `derTGTermin` vom Typ `List<TGTermin>`
- `gui` vom Typ `GUI`

\tiny
```{.java .cb-nb line_numbers=false}
var gui = new GUI();
var termine = List.of(new TGTermin("Mathe TG", new Datum(10, 3, 2024)));
new Steuerung(termine, gui);
```
\normalsize




## Termine filtern und anzeigen

Implementiere in `Steuerung` die Methode  
`void anzeigenTGTermine(Datum von, Datum bis)`.

- Iteriere über alle Termine
- Zeige nur Termine an, deren Datum im Bereich  
  `[von, bis]` (inklusive) liegt
- Verwende dafür `smallerOrEqual`

```{.java .cb-nb line_numbers=false}
var gui = new GUI();
var termine = List.of(new TGTermin("Mathe TG", new Datum(10, 3, 2024)));
var steuerung = new Steuerung(termine, gui);
steuerung.anzeigenTGTermine(
    new Datum(1, 3, 2024),
    new Datum(31, 3, 2024)
);
```

```{.java .cb-nb line_numbers=false}
var projektArbeit = new TGTermin("Projektarbeit Abgabe", new Datum(30,6,2024));
var probeABI = new TGTermin("Probeabitur IT", new  Datum(18,2,2025));
var skiTag = new TGTermin("Skitag", new  Datum(25,2,2025));
var facharbeitAbgabe = new TGTermin("Facharbeit Abgabe", new Datum(2,3,2025));
var termine = List.of(projektArbeit, probeABI, skiTag, facharbeitAbgabe);
GUI gui = new GUI();
Steuerung steuerung = new Steuerung(termine, gui);
steuerung.anzeigenTGTermine(
    new Datum(17, 2, 2025),
    new Datum(28, 2, 2025)
);
```





```{.java .cb-run}
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
```


## Gui ergänzen

Ergänze die Klasse `GUI` um eine Eigenschaft `dieSteuerung` vom Typ `Steuerung`!



## Steuerung in der GUI setzen

Implementiere in der Klasse `GUI` die Methode `void setSteuerung(Steuerung steuerung)`.

Diese Methode speichert die übergebene Steuerung in der Eigenschaft `dieSteuerung`, sodass die GUI später auf die Steuerung zugreifen kann.

Die Methode wird insbesondere im Konstruktor der Klasse `Steuerung` verwendet, um die Verbindung zwischen GUI und Steuerung herzustellen.

```{.java .cb-nb line_numbers=false}
var gui = new GUI();
var steuerung = new Steuerung(List.of(), gui);
gui.setSteuerung(steuerung);
```

```{.java .cb-nb line_numbers=false}
var gui = new GUI();
gui.setSteuerung(
    new Steuerung(
        
        List.of(new TGTermin("Mathe TG", new Datum(10, 3, 2024))), gui
    )
);
```




```{.java .cb-run}
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
```



## Zusätzlicher Konstruktor in `Steuerung`

Ergänze einen weiteren Konstruktor  
`Steuerung(GUI gui, List<TGTermin> derTGTermin)`. *Im Vergleich zum automatisch definierten Konsturktor sind die Parameter vertauscht*

Der neue Konstruktor.
- Ruft den automatisch definierten Konstruktor auf (`this(..)`)
- Setzt die Steuerung in der GUI über `gui.setSteuerung(this)`

\tiny
```{.java .cb-nb line_numbers=false}
var gui = new GUI();
new Steuerung(gui, List.of());
```

```{.java .cb-nb line_numbers=false}
var gui = new GUI();
new Steuerung(
    gui,
    List.of(new TGTermin("Info TG", new Datum(12,4,2024)))
);
```
\normalsize






## Mehrere Termine anzeigen (GUI)

Implementiere in `GUI` die Methode `void anzeigenTGTermine(Datum von, Datum bis)`.

Diese delegiert die Arbeit an die Steuerung.



```{.java .cb-nb line_numbers=false}
var projektArbeit = new TGTermin("Projektarbeit Abgabe", new Datum(30,6,2024));
var probeABI = new TGTermin("Probeabitur IT", new  Datum(18,2,2025));
var skiTag = new TGTermin("Skitag", new  Datum(25,2,2025));
var facharbeitAbgabe = new TGTermin("Facharbeit Abgabe", new Datum(2,3,2025));
var termine = List.of(projektArbeit, probeABI, skiTag, facharbeitAbgabe);
GUI gui = new GUI();
Steuerung steuerung = new Steuerung(gui, termine);
steuerung.anzeigenTGTermine(
    new Datum(17, 2, 2025),
    new Datum(28, 2, 2025)
);
```