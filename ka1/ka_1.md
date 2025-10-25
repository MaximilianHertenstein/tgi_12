---
title: KA1 TGI12
codebraid:
  jupyter: true
---

```{.java .cb-run}

import java.util.ArrayList;
import java.util.List;

public record SimpleEntry(String key, int value) {
    SimpleEntry(String key){
        this(key, 0);
    }

    SimpleEntry setValue(int newValue) {
        return new SimpleEntry(key, newValue);
    }
}


public record InvertedEntry(int key, List<String> value) {
}


public class Utils {


    public static List<Integer> values(List<SimpleEntry> entryList) {
        var values = new ArrayList<Integer>();
        for (var entry : entryList) {
            values.add(entry.value());
        }
        return values;
    }

    public static <T> List<T> dedup(List<T> xs) {
        var acc = new ArrayList<T>();
        for (T x : xs) {
            if (!acc.contains(x)) {
                acc.add(x);
            }
        }
        return acc;
    }

    public static List<String> keySet(List<SimpleEntry> entryList) {
        var keys = new ArrayList<String>();
        for (var entry : entryList) {
            keys.add(entry.key());
        }
        return dedup(keys);
    }

    public static List<InvertedEntry> invertedEntries(List<SimpleEntry> entryList) {
        var res = new ArrayList<InvertedEntry>();
        for (var value : dedup(values(entryList))) {
            var acc = new ArrayList<String>();
            for (var entry : dedup(entryList)) {
                if (entry.value() == value) {
                    acc.add(entry.key());
                }
            }
            res.add(new InvertedEntry(value, acc));
        }
        return res;
    }
}


public class SimpleMap {
    public ArrayList<SimpleEntry> entryList;

    SimpleMap() {
        entryList = new ArrayList<SimpleEntry>();
    }

    SimpleMap(List<SimpleEntry> entryList) {
        this.entryList = new ArrayList<>(entryList);
    }

    public List<SimpleEntry> entryList() {
        return entryList;
    }

    public int size() {
        return entryList.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        entryList = new ArrayList<SimpleEntry>();
    }
    public List<String> keySet() {
        return Utils.keySet(entryList);
    }

    public List<Integer> values() {
        return Utils.values(entryList);
    }

    public boolean containsKey(String key) {
        return keySet().contains(key);
    }

    public boolean containsValue(int value) {
        return values().contains(value);
    }
    public Integer keyToIndex(String key) {
        for (int i = 0; i < entryList.size(); i++) {
            if (entryList.get(i).key().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public Integer get(String key) {
        var index = keyToIndex(key);
        if (index == -1) {
            return -1;
        }
        return entryList.get(index).value();
    }

    public List<InvertedEntry> invertedEntries() {
        return Utils.invertedEntries(entryList);
    }

}

```

# Aufgabe (10)

Zeichne ein Klassendiagramm mit den Klassen `SimpleEntry`, `InvertedEntry`, `Utils` und `SimpleMap`!
Bei jeder Klasse reichen 2 nicht automatisch definierte Methoden aus. Die einzige nicht öffentliche Methode ist `keyToIndex`!
Alle anderen Methoden sind öffentlich. Alle Eigenschaften sind privat.

\
\


- Definiere jede Klasse in einer eigenen Datei
- Du darfst keine Listenmethoden außer `size`, `add`, `get` und `contains` nutzen
- Definiere alle Methoden und Eigenschaften **zunächst** als öffentlich, um sie testen zu können!

# ToDo


## Aufgabe (2)

Definiere eine Klasse `SimpleEntry` mit den Feldern `key (String)` und `value (int)` als record.

```{.java .cb-nb line_numbers=false}
new SimpleEntry("FCH", 3);
```
```{.java .cb-nb line_numbers=false}
new SimpleEntry("A", 1);
```


## Aufgabe (3)

Füge dem SimpleEntry-Record einen Konstruktor hinzu, der nur `key` als Parameter hat und value auf 0 setzt.

```{.java .cb-nb line_numbers=false}
new SimpleEntry("BMG");
```
```{.java .cb-nb line_numbers=false}
new SimpleEntry("M05");
```





## Aufgabe (3)
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


## Aufgabe (4)

Implementiere die statische Methode `values`, die alle Werte aus einer Liste von `SimpleEntry` extrahiert.

\small
```{.java .cb-nb line_numbers=false}
Utils.values(List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3)));
```
```{.java .cb-nb line_numbers=false}
Utils.values(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2), new SimpleEntry("A", 1)));
```
\normalsize



## Aufgabe (5)

Implementiere die statische Methode `dedup`, die Duplikate aus einer Liste entfernt.
Die übergebene Liste wird nicht geändert. Es wird eine neue Liste erzeugt und zurückgegeben.


```{.java .cb-nb line_numbers=false}
Utils.dedup(List.of(1, 2, 2, 3, 1, 4));
```
```{.java .cb-nb line_numbers=false}
Utils.dedup(List.of("A", "B", "A", "C", "B"));
```




## Aufgabe (4)

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



## Aufgabe (1)

Definiere eine Klasse `InvertedEntry` mit den Feldern `key (int)` und `value (Liste von int)` als record.

```{.java .cb-nb line_numbers=false}
new InvertedEntry(5, List.of("A", "B"));
```



## Aufgabe (10)

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

## Aufgabe (2)
Definiere die Klasse SimpleMap mit dem Schlüsselwort `class`.
Die Klasse hat eine eigenschaft mit dem Namen `entryList`. Diese Eigenschaft speichert eine `ArrayList` von `SimpleEntry`!


## Aufgabe (2)
Definiere die Klasse SimpleMap mit einem Konstruktor, der eine leere Liste von `SimpleEntry` initialisiert. Dem Konstruktor wird nichts übergeben.

```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap();
map.entryList;
```


## Aufgabe (2)
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



## Aufgabe (1)
Implementiere die Methode `entryList`, die alle Einträge zurückgibt.
```{.java .cb-nb line_numbers=false}
map.entryList();
```

```{.java .cb-nb line_numbers=false}
map2.entryList();
```


## Aufgabe (1)

Schreibe die Methode `size`, die die Anzahl der Einträge zurückgibt.

```{.java .cb-nb line_numbers=false}
map.size();
```

```{.java .cb-nb line_numbers=false}
map2.size();
```


## Aufgabe (1)

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


## Aufgabe (2)

Schreibe die Methode `clear`, die alle Einträge entfernt.

```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2)));
map.clear();
map.entryList()
```




## Aufgabe (2)
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

## Aufgabe (2)
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



## Aufgabe (2)
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

## Aufgabe (3)

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

## Aufgabe (2)

Schreibe die Methode `get`, die den Wert zu einem Schlüssel zurückgibt.

\tiny

```{.java .cb-nb line_numbers=false}
SimpleMap map = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2), new SimpleEntry("C", 2)));
map.get("B");
```
\normalsize

Wenn der Schlüssel nicht vorkommt, wird $-1$ zurückgegeben.

```{.java .cb-nb line_numbers=false}
map.get("X");
```

**Hinweis:** Nutze die Methode `keyToIndex`!


<!-- ## Aufgabe  (2)

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


**Hinweis:** Nutze die Methode `put` der Klasse `Utils`! -->


## Aufgabe (1)

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
