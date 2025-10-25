---
title: ArrayListen
codebraid:
  jupyter: true
---

```{.java .cb-run}
import java.util.ArrayList;
```

# Erzeugen von ArrayListen und Hinzufügen von Elementen

Wir haben schon gesehen, dass man einer `ArrayList` nach dem Erzeugen, Elemente hinzufügen kann.

```{.java .cb-nb}
var xs = new ArrayList<Integer>();
xs
```

```{.java .cb-nb}
xs.add(5);
xs
```

```{.java .cb-nb}
xs.add(6);
xs.add(7);
xs
```

# Elemente entfernen



Es ist auch möglich Elemente zu entfernen. Dazu nutzt man die Methode `remove`. Dieser wird der Index des Elements, das entfernt werden soll, übergeben.

```{.java .cb-nb}
xs.remove(1);
xs
```

# Elemente ersetzen


Mit der Methode `set` können wir ein Element in der Liste durch ein neues Element ersetzen. Der Methode werden der Index des Elements, das ersetzt werden soll und das neue Element übergeben.

```{.java .cb-nb}
xs.set(0, 9);
xs
```
