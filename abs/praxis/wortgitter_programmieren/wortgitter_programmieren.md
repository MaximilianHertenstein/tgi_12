---
title: Wortgitter programmieren 
codebraid:
  jupyter: true
---

```{.java .cb-run}
import static java.lang.IO.println;
import java.util.ArrayList;


public record Suchwort(String aWort, boolean aGefunden) {



    public boolean pruefeWort(String wort) {
        return wort.equals(aWort);
    }
}

public record GUI() {
    void zeigeHinweis(String hinweis){
        println(hinweis);
    }
}



public record Steuerung (GUI gui,
    ArrayList<Suchwort> dasSuchwort )
    {

    public void pruefeWort(String wort){
        boolean istSuchWort = false;
        for (var sw: dasSuchwort){
            istSuchWort = istSuchWort || sw.pruefeWort(wort);
        }
        var aWG = alleWoerterGefunden();
        if (istSuchWort && aWG){
            gui.zeigeHinweis("Alle Wörter gefungen");
        }
        if (istSuchWort &&!aWG){
            gui.zeigeHinweis("Richtiges Wort");
        }
        else {
            gui.zeigeHinweis("Wort nicht vorhanden");
        }

    }

    public boolean alleWoerterGefunden() {
        for (var sw: dasSuchwort){
            if (!sw.aGefunden()){
                return false;
            }
        }
        return true;
    }
}

```

# Aufgabe: Klasse GUI definieren

Definiere ein Record `GUI`.

Dieses Record hat **keine Eigenschaften**.

```{.java .cb-nb line_numbers=false}
GUI gui = new GUI();
```

# Aufgabe: Methode zeigeHinweis in GUI

Ergänze in `GUI` eine Methode `zeigeHinweis`.

Die Methode soll einen `String` entgegennehmen
und diesen in der Konsole ausgeben.

```{.java .cb-nb line_numbers=false}
GUI gui = new GUI();
gui.zeigeHinweis("Richtiges Wort");
gui.zeigeHinweis("Alle Wörter gefunden");
```


# Aufgabe: Klasse Suchwort definieren

Definiere ein Record `Suchwort`.

Dieses Record hat folgende Eigenschaften:

- `aWort` vom Typ `String`
- `aGefunden` vom Typ `boolean`


```{.java .cb-nb line_numbers=false}
Suchwort sw1 = new Suchwort("apfel", true);
Suchwort sw2 = new Suchwort("banane", false);
```


<!-- # Aufgabe: Konstruktor für Suchwort

Ergänze in `Suchwort` einen Konstruktor,
der nur das Wort entgegennimmt und `aGefunden` auf `false` setzt.

```{.java .cb-nb line_numbers=false}
Suchwort sw1 = new Suchwort("apfel");
Suchwort sw2 = new Suchwort("banane");
``` -->

# Aufgabe: Methode pruefeWort in Suchwort

Ergänze in `Suchwort` eine Methode `pruefeWort`.

Die Methode soll ein Wort vom Typ `String` entgegennehmen
und prüfen, ob es mit `aWort` übereinstimmt.

Die Methode soll einen Wahrheitswert zurückgeben.

```{.java .cb-nb line_numbers=false}
Suchwort sw1 = new Suchwort("apfel", true);
sw1.pruefeWort("apfel");
```
```{.java .cb-nb line_numbers=false}
Suchwort sw2 = new Suchwort("banane", false);
sw2.pruefeWort("birne");
```

# Aufgabe: Klasse Steuerung definieren

Definiere ein Record `Steuerung`.

Dieses Record hat folgende Eigenschaften:

- `gui` vom Typ `GUI`
- `dasSuchwort` vom Typ `ArrayList` von `Suchwort`-Objekten

```{.java .cb-nb line_numbers=false}
var gui = new GUI();
var steuerung = new Steuerung(gui, new ArrayList());
steuerung
```

# Aufgabe: Konstruktor für Steuerung

Ergänze in `Steuerung` einen Konstruktor,
der die beiden Eigenschaften initialisiert.

```{.java .cb-nb line_numbers=false}
var gui = new GUI();
var steuerung = new Steuerung(gui, new ArrayList());
steuerung;
```
\tiny
```{.java .cb-nb line_numbers=false}
var xs = List.of(new Suchwort("Haskell", false), new Suchwort("Java", true),new Suchwort("Python", false));
var xsA = new ArrayList<>(xs);
var s = new  Steuerung(gui,xsA);
s;
```
\normalsize

# Aufgabe: Methode alleWoerterGefunden in Steuerung

Ergänze in `Steuerung` eine Methode `alleWoerterGefunden`.

Die Methode soll prüfen, ob bei allen `Suchwort`-Objekten
die Eigenschaft `aGefunden` den Wert `true`  hat.

Die Methode soll einen Wahrheitswert zurückgeben.

\tiny
```{.java .cb-nb line_numbers=false}
var xs = List.of(new Suchwort("Haskell", true), new Suchwort("Java", false),new Suchwort("Python", true));
var xsA = new ArrayList<>(xs);
var s = new  Steuerung(gui,xsA);
s.alleWoerterGefunden();
```

```{.java .cb-nb line_numbers=false}
var xs = List.of(new Suchwort("Haskell", true), new Suchwort("Java", true),new Suchwort("Python", true));
var xsA = new ArrayList<>(xs);
var s = new  Steuerung(gui,xsA);
s.alleWoerterGefunden();
```
\normalsize


# Aufgabe: Methode pruefeWort in Steuerung

Ergänze in `Steuerung` eine Methode `pruefeWort`.

Die Methode soll:
- ein Wort entgegennehmen
- alle gespeicherten Suchwörter prüfen
- über die `GUI` eine passende Meldung ausgeben:
  - „Alle Wörter gefunden“ falls das Wort  existiert und alle Suchwörter gefunden wurden
  - „Richtiges Wort“, falls das Wort existiert aber noch nicht alle Suchwörter gefunden wurden
  - „Wort nicht vorhanden“, falls es nicht existiert

**Hinweis:** Verwende `alleWoerterGefunden`!

\tiny

```{.java .cb-nb line_numbers=false}
var xs = List.of(new Suchwort("Haskell", true), new Suchwort("Java", false),new Suchwort("Python", true));
var xsA = new ArrayList<>(xs);
var s = new  Steuerung(gui,xsA);
s.pruefeWort("Java");
```
```{.java .cb-nb line_numbers=false}
var xs = List.of(new Suchwort("Haskell", true), new Suchwort("Java", false),new Suchwort("Python", true));
var xsA = new ArrayList<>(xs);
var s = new  Steuerung(gui,xsA);
s.pruefeWort("Rust");
```
```{.java .cb-nb line_numbers=false}
var xs = List.of(new Suchwort("Haskell", true), new Suchwort("Java", true),new Suchwort("Python", true));
var xsA = new ArrayList<>(xs);
var s = new  Steuerung(gui,xsA);
s.pruefeWort("Haskell");
```
\normalsize


# Aufgabe

Nutze ein `break`-Statement um nicht bei jedem Aufruf von `pruefeWort`  alle Suchwörter zu durchsuchen.