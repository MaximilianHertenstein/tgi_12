---
title: KA1 TGI12
codebraid:
  jupyter: true
---

```{.java .cb-run}

public record V2(int x ,int y) {};

public record Rocket(V2 pos) {};


public record Alien(V2 pos) {
    public boolean collidesWith(List<Rocket> rockets){
        for (var rocket : rockets) {
            if (this.pos.equals(rocket.pos())) return true;
        }
        return false;
    }
}

public class Utils {
    public static ArrayList<Alien> onlyLivingAliens(List<Alien> aliens, List<Rocket> rockets) {
        var acc = new ArrayList<Alien>();
        for (var alien : aliens) {
            if (!alien.collidesWith(rockets)) acc.add(alien);
        }
        return acc;
    }
}


public class AlienManager {

    ArrayList<Alien> aliens;

    public AlienManager(List<Alien> aliens) {
        this.aliens = new ArrayList(aliens);
    }

    public AlienManager() {
        this.aliens = new ArrayList<>();
    }

    public List<Alien>  getAliens() {
        return aliens;
    }

    public void addAlien(V2 pos) {
        aliens.add(new Alien(pos));
    }

     public List<Alien> getLowestAliens() {
        if (aliens.isEmpty()) return List.of();
        int lowestLine = aliens.getFirst().pos().y();
        var acc = new ArrayList<Alien>();
        for (var alien : aliens) {
            if (alien.pos().y() == lowestLine) {
                acc.add(alien);
            }
        }
        return acc;
    }
    

    public void removeDeadAliens(List<Rocket>  rockets) {
        aliens = Utils.onlyLivingAliens(aliens, rockets);
    }


}

```

# Aufgabe (1)

Erstelle ein Record `V2`{.java}. Die Objekte dieser Klasse sind Vektoren im zweidimensionalen Raum mit ganzzahligen Komponenten. Beide Komponenten sind unveränderlich.

```{.java .cb-nb line_numbers=false}
new V2(3, 2);
```
```{.java .cb-nb line_numbers=false}
new V2(1, -5);
```


# Aufgabe (1)

Erstelle ein Record `Rocket`{.java}. Die einzige Eigenschaft ist `pos` mit dem Typ `V2`!

```{.java .cb-nb line_numbers=false}
new Rocket(new V2(3, 2));
```

```{.java .cb-nb line_numbers=false}
new Rocket(new V2(-1, 5));
```

# Aufgabe (1)
Erstelle ein Record `Alien`. Das Record hat eine Eigenschaft `pos` mit dem Typ `V2`


```{.java .cb-nb line_numbers=false}
new Alien(new V2(3, 2));
```


# Aufgabe (5)
Implementiere die Methode `collidesWith(List<Rocket> rockets)` in `Alien`.
Die Methode gibt zurück, ob irgendeine Rakete in `rockets` mit diesem Alien kollidiert.

```{.java .cb-nb line_numbers=false}
var a = new Alien(new V2(2,3));
var rockets = List.of(new Rocket(new V2(0,0)), new Rocket(new V2(2,3)));
a.collidesWith(rockets); 
```
```{.java .cb-nb line_numbers=false}
var a = new Alien(new V2(2,3));
var rockets = List.of(new Rocket(new V2(0,3)), new Rocket(new V2(2,4)));
a.collidesWith(rockets); 
```
 


# Aufgabe (6)

Erstelle die Klasse `Utils`! Implementiere die öffentliche statische Methode:

- `List<Alien> onlyLivingAliens(List<Alien> aliens, List<Rocket> rockets)`

Die Rückgabe ist eine neue `List<Alien>` mit allen Aliens, die **nicht** mit irgendeiner Rakete kollidieren.

```{.java .cb-nb line_numbers=false}
Utils.onlyLivingAliens(
    List.of(new Alien(new V2(1,1)), new Alien(new V2(2,2))),
    List.of(new Rocket(new V2(2,2)))
);
```


```{.java .cb-nb line_numbers=false}
var alive = Utils.onlyLivingAliens(
    List.of(new Alien(new V2(1,1)), new Alien(new V2(2,2))),
    List.of(new Rocket(new V2(1,1)))
);
```

# Aufgabe (2)

Erstelle eine Klasse `AlienManager`. Die einzige Eigenschaft ist eine `ArrayList` von Aliens.

# Aufgabe (2)
Schreibe einen Konstruktor für diese Klasse. Diesem wird eine Liste von Aliens übergeben. Er setzt diese als Eigenschaft des erzeugten Objekts.

```{.java .cb-nb line_numbers=false}
var amgr = new AlienManager(List.of(new Alien(new V2(1,1)), new Alien(new V2(2,2))));
amgr.aliens;
```


**Hinweis**:


```{.java .cb-nb line_numbers=false}
var l = List.of(1, 2, 3);
var al = new ArrayList(l);
```

# Aufgabe (2)
Schreibe einen Konstruktor für diese Klasse. Diesem wird nichts übergeben. `aliens` wird auf die leere Liste gesetzt.

```{.java .cb-nb line_numbers=false}
var amgr = new AlienManager();
amgr.aliens;
```


# Aufgabe (1)
Schreibe eine Methode `getAliens`. Diese gibt die Liste der Aliens zurück.

```{.java .cb-nb line_numbers=false}
var amgr = new AlienManager(List.of(new Alien(new V2(1,1)), new Alien(new V2(2,2))));
amgr.getAliens();
```

# Aufgabe (1)
Schreibe eine Methode `addAlien`. Diese fügt ein Alien an der übergebenen Position hinzu!

```{.java .cb-nb line_numbers=false}
var amgr = new AlienManager(List.of(new Alien(new V2(1,1)), new Alien(new V2(2,2))));
amgr.getAliens();
```

```{.java .cb-nb line_numbers=false}
amgr.addAlien(new V2(3, 5));
amgr.getAliens();
```



# Aufgabe (7)
Schreibe eine Methode `getLowestAliens`. Diese bestimmt die Aliens, die in der untersten Reihe sind, die überhaupt von Aliens besetzt ist. Du kannst davon ausgehen, dass die Aliens vorne in der Liste weiter unten sind als die Aliens, die in der Liste weiter hinten sind.
\tiny

```{.java .cb-nb line_numbers=false}
var aliens = List.of(new Alien(new V2(1,1)), new Alien(new V2(2,1)), new Alien(new V2(2,5)), new Alien(new V2(2,7)));
var amgr = new AlienManager(aliens);
amgr.getLowestAliens();
```

```{.java .cb-nb line_numbers=false}
var amgr = new AlienManager();
amgr.getAliens();
```
\normalsize



# Aufgabe (5)
Schreibe eine Methode `removeDeadAliens`. Diese entfernt alle Aliens, die von den übergebenen Raketen getroffen wurden.
\tiny

```{.java .cb-nb line_numbers=false}
var aliens = List.of(new Alien(new V2(1,1)), new Alien(new V2(2,1)), new Alien(new V2(2,5)), new Alien(new V2(2,7)));
var amgr = new AlienManager(aliens);
amgr.removeDeadAliens(List.of(new Rocket(new V2(2, 1)), new Rocket (new  V2(2, 7))));
amgr.getAliens();
```
\normalsize

# Aufgabe (10) 

Stelle den gesamten Code in einem Klassendiagramm dar. Eine Methode pro Klasse reicht aus. Stelle Bezeihungen zwischen Klassen mit Pfeilen dar.

# Aufgabe (8)

Stelle den Zustand nach der folgenden Zeile in einem Objektdiagramm dar!

```{.java .cb-code line_numbers=false}
var amgr = new AlienManager(List.of(new Alien(new V2(1,1)), new Alien(new V2(2,2))));
```

# Aufgabe (10)

Stelle einen Aufruf der Methode `collidesWith` in einem Sequenzdiagramm dar.