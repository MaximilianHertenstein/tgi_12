---
title: Statische Methoden
codebraid:
  jupyter: true
---

```{.java .cb-run}

```




Mit dem folgenden Code wird eine Methode definiert, die das Quadrat einer Zahl berechnet.

```{.java .cb-nb line_numbers=false}
class Utils {
    int square(int x){
        return x * x;
    }
}
```

Um diese aufzurufen, mussen wir zunächst ein Objekt der Klasse `Utils` erzeugen.

```{.java .cb-nb line_numbers=false}
var utils = new Utils();
utils.square(3);
```



Die Methode `Utils` verwendet keine Attribute. Deshalb können wir sie auch als statische Methode deklarieren.
Das geschiet mit dem Schlüsselwort `static`zu Beginn der Methodendefinition.


```{.java .cb-nb line_numbers=false}
class Utils {
    static int square(int x){
        return x * x;
    }
}
```

Statische Methoden werden nicht mit einem Objekt aufgerufen, sondern mit der Klasse selbst.

```{.java .cb-nb line_numbers=false}
Utils.square(3);
```
