# ToDo


## Aufgabe

Definiere eine Klasse `SimpleEntry` mit den Feldern `key (String)` und `value (int)` als record.

```{.java .cb-nb line_numbers=false}
new SimpleEntry("FCH", 3);
```
```{.java .cb-nb line_numbers=false}
new SimpleEntry("A", 1);
```


## Aufgabe

Füge dem SimpleEntry-Record einen Konstruktor hinzu, der nur `key` als Parameter hat und value auf 0 setzt.

```{.java .cb-nb line_numbers=false}
new SimpleEntry("BMG");
```
```{.java .cb-nb line_numbers=false}
new SimpleEntry("M05");
```





## Aufgabe
Füge dem SimpleEntry-Record die Methode `setValue` hinzu, die ein neues Entry mit geändertem Wert zurückgibt.

```{.java .cb-nb line_numbers=false}
new SimpleEntry("FCH", 3).setValue(10);
```
```{.java .cb-nb line_numbers=false}
new SimpleEntry("A", 1).setValue(99);
```


# Utils

## Aufgabe


Implementiere in einer neuen Datei `Utils.java` eine Klasse `Utils`!
\
\

**Hinweis:** Keine der Methoden der Klasse `Utils` ändert eine Liste. Es wird immer eine neue Liste erzeugt und zurückgegeben.


## Aufgabe

Implementiere die statische Methode `values`, die alle Werte aus einer Liste von `SimpleEntry` extrahiert.

\small
```{.java .cb-nb line_numbers=false}
Utils.values(List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3)));
```
```{.java .cb-nb line_numbers=false}
Utils.values(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2), new SimpleEntry("A", 1)));
```
\normalsize



## Aufgabe

Implementiere die statische Methode `dedup`, die Duplikate aus einer Liste entfernt.
Die übergebene Liste wird nicht geändert. Es wird eine neue Liste erzeugt und zurückgegeben.


```{.java .cb-nb line_numbers=false}
Utils.dedup(List.of(1, 2, 2, 3, 1, 4));
```
```{.java .cb-nb line_numbers=false}
Utils.dedup(List.of("A", "B", "A", "C", "B"));
```




## Aufgabe

Implementiere die statische Methode `keySet`, die alle Schlüssel aus einer Liste von `SimpleEntry` extrahiert und Duplikate entfernt.

\small

```{.java .cb-nb line_numbers=false}
Utils.keySet(List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3), new SimpleEntry("M05", 4)));
```
```{.java .cb-nb line_numbers=false}
Utils.keySet(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2), new SimpleEntry("A", 3)));
```
\normalsize

**Hinweis:** Nutze `dedup`!


<!-- ## Aufgabe

Implementiere die statische Methode `remove`, die alle Einträge mit einem bestimmten Schlüssel aus einer Liste entfernt.

\small

```{.java .cb-nb line_numbers=false}
Utils.remove(List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3), new SimpleEntry("M05", 4)), "BMG");
```
```{.java .cb-nb line_numbers=false}
Utils.remove(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2)), "A");
```
\normalsize -->



<!-- 

## Aufgabe (7)

Implementiere die statische Methode `put`. Dieser wird eine Liste von `SimpleEntry`, ein Schlüssel und ein Wert übergeben. Wenn der Schlüssel nicht vorkommt, wird am Ende der Liste ein neuer Eintrag mit dem Schlüssel und dem Wert angehängt. 

\tiny

```{.java .cb-nb line_numbers=false}
Utils.put(List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3), new SimpleEntry("M05", 4)), "WOB", 5);
```
\normalsize

Sonst wird der Wert bei diesem Schlüssel durch den übergebenen Wert ersetzt.
\tiny


```{.java .cb-nb line_numbers=false}
Utils.put(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2)), "A", 99);
```

\normalsize -->



## Aufgabe

Definiere eine Klasse `InvertedEntry` mit den Feldern `key (int)` und `value (Liste von String)` als record.

```{.java .cb-nb line_numbers=false}
new InvertedEntry(5, List.of("A", "B"));
```




## Aufgabe 

Implementiere die statische Methode `invertedEntries`, die für jeden Wert die zugehörigen Schlüssel berechnet und eine Liste von `InvertedEntry` zurückgibt.

\tiny

```{.java .cb-nb line_numbers=false}
Utils.invertedEntries(List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3), new SimpleEntry("M05", 4)));
```
```{.java .cb-nb line_numbers=false}
Utils.invertedEntries(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2), new SimpleEntry("C", 1)));
```
\normalsize


# SimpleMap

## Aufgabe
Definiere die Klasse SimpleMap mit dem Schlüsselwort `class`.
Die Klasse hat eine eigenschaft mit dem Namen `entryList`. Diese Eigenschaft speichert eine `ArrayList` von `SimpleEntry`!


## Aufgabe
Definiere die Klasse SimpleMap mit einem Konstruktor, der eine leere Liste von `SimpleEntry` initialisiert. Dem Konstruktor wird nichts übergeben.

```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap();
map.entryList;
```


## Aufgabe
Füge einen Konstruktor hinzu, der eine Liste von SimpleEntry übernimmt.
```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2)));
map.entryList;
```

```{.java .cb-nb line_numbers=false}
SimpleMap map2 = new SimpleMap(List.of(new SimpleEntry("X", 10)));
map.entryList;
```


**Hinweis:** Du kannst eine Liste in eine ArrayListe umwandeln, wenn du sie dem Konstruktor von `ArrayList` übergibst.


```{.java .cb-nb line_numbers=false}
var list = List.of(1, 2, 3);
var al = new ArrayList(list);
```



## Aufgabe
Implementiere die Methode `entryList`, die alle Einträge zurückgibt.
```{.java .cb-nb line_numbers=false}
map.entryList();
```

```{.java .cb-nb line_numbers=false}
map2.entryList();
```


## Aufgabe

Schreibe die Methode `size`, die die Anzahl der Einträge zurückgibt.

```{.java .cb-nb line_numbers=false}
map.size();
```

```{.java .cb-nb line_numbers=false}
map2.size();
```


## Aufgabe

Implementiere die Methode `isEmpty`, die prüft, ob die Map leer ist.

```{.java .cb-nb line_numbers=false}
map.isEmpty();
```

```{.java .cb-nb line_numbers=false}
map2.isEmpty();
```

```{.java .cb-nb line_numbers=false}
var emptyMap = new SimpleMap();
emptyMap.isEmpty();
```


## Aufgabe

Schreibe die Methode `clear`, die alle Einträge entfernt.

```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2)));
map.clear();
map.entryList()
```




## Aufgabe
Implementiere die Methode `keySet`, die alle Schlüssel als Liste zurückgibt. Duplikate werden dabei entfernt.

\tiny
```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2), new SimpleEntry("A", 2)));
map.keySet()
```

```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("A", 2)));
```
\normalsize

**Hinweis:** Nutze die Methode `keySet` der Klasse `Utils`!

## Aufgabe
Schreibe die Methode `values`, die alle Werte als Liste zurückgibt.

\tiny
```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2), new SimpleEntry("A", 2)));
map.values();
```
```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap(List.of(new SimpleEntry("X", 10)));
map.values();
```
\normalsize

**Hinweis:** Nutze die Methode `values` der Klasse `Utils`!



## Aufgabe
Implementiere die Methode `containsKey`, die prüft, ob ein Schlüssel existiert.

\tiny

```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2), new SimpleEntry("A", 2)));
map.containsKey("A");
```

```{.java .cb-nb line_numbers=false}
map.containsKey("X");
```
\normalsize

## Aufgabe
Implementiere die Methode `containsValue`, die prüft, ob ein Wert vorhanden ist.

\tiny

```{.java .cb-nb line_numbers=false}
var map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2), new SimpleEntry("C", 2)));
map.containsValue(2); // true
```

```{.java .cb-nb line_numbers=false}
map.containsValue(5); // false
```
\normalsize

## Aufgabe

Implementiere die private Methode `keyToIndex`, die den Index eines Schlüssels in der Map zurückgibt oder $-1$, falls der Schlüssel nicht existiert.

\tiny

```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2), new SimpleEntry("C", 2)));
map.keyToIndex("A");
```
```{.java .cb-nb line_numbers=false}
map.containsKey("C");
```
\normalsize

## Aufgabe

Schreibe die Methode `get`, die den Wert zu einem Schlüssel zurückgibt.

\tiny

```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2), new SimpleEntry("C", 2)));
map.get("B");
```
\normalsize

Wenn der Schlüssel nicht vorkommt, wird `null` zurückgegeben.

```{.java .cb-nb line_numbers=false}
map.get("X");
```

**Hinweis:** Nutze die Methode `keyToIndex`! Damit du `null` zurückgeben kannst, muss der Rückgabetyp der Methode eine Klasse sein! `int` ist keine Klasse, `Integer` aber schon.


## Aufgabe

Implementiere die Methode `put`. Dieser werden ein Schlüssel und ein Wert übergeben. Wenn der Schlüssel schon vorkommt, wird der Eintrag aktualisiert. Sonst wird der Eintrag hinzugefügt.
Die Methode gibt den alten Wert des Schlüssels zurück. Wenn der Schlüssel bisher nicht vorkam, wird $-1$ zurückgegeben.
\tiny

```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2), new SimpleEntry("C", 2)));
map.put("C", 4);
```

```{.java .cb-nb line_numbers=false}
map.entryList;
```


```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2), new SimpleEntry("C", 2)));
map.put("F", 4);
```

```{.java .cb-nb line_numbers=false}
map.entryList;
```
\normalsize


**Hinweis:** Nutze die Methode `keyToIndex` und `set` der Klasse `SimpleMap` und die Methoden `add` und `set` der Klasse `ArrayList`!



## Aufgabe
Implementiere eine Methode `remove`, die einen Schlüssel als Parameter hat.
Entfernt den Eintrag mit dem gegebenen Schlüssel aus der Map und gibt den alten Wert zurück (oder null, falls nicht vorhanden).

```{.java .cb-nb line_numbers=false}
var map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2)));
map.remove("A"); // returns 1, map.entryList() no longer contains "A"
```
```{.java .cb-nb line_numbers=false}
map.remove("X"); // returns null, map unchanged
```



## Aufgabe

Implementiere eine Methode `putAll`, die eine andere SimpleMap als Parameter hat.
Die Methode fügt alle Einträge aus einer anderen SimpleMap hinzu bzw. überschreibt vorhandene Schlüssel.

```{.java .cb-nb line_numbers=false}
var m1 = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2)));
var m2 = new SimpleMap(List.of(new SimpleEntry("B", 99), new SimpleEntry("C", 3)));
m1.putAll(m2);
```


## Aufgabe

Implementiere die Methode `invertedEntries`, die für jeden Wert die zugehörigen Schlüssel als Liste zurückgibt.

\tiny

```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2), new SimpleEntry("C", 2)));
map.invertedEntries();
```

```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("A", 2), new SimpleEntry("C", 3)));
map.entryList;
```
\normalsize


**Hinweis:** Nutze die Methode `invertedEntries` der Klasse `Utils`!
