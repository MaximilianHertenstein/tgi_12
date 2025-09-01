---
title: ToDo-App
codebraid:
  jupyter: true
---

```{.java .cb-run}

import java.util.ArrayList;
import java.util.List;


public record ToDo(int id, String text, boolean completed) {

    public ToDo(int id, String text) {
        this(id, text, false);
    }

    public ToDo toggle() {
        return new ToDo(id, text, !completed);
    }

    public ToDo updateText(String text) {
        return new ToDo(id, text, completed);
    }

}


public class Model {
    public ArrayList<ToDo> toDos;

    public Model() {
        this.toDos = new ArrayList<>();
    }

    public Model(List<ToDo> toDos) {
        this.toDos = new ArrayList<>(toDos);
    }






    public List<ToDo> getToDosCompleted(boolean status) {
        var acc = new ArrayList<ToDo>();
        for (var item : toDos) {
            if (item.completed() == status) {
                acc.add(item);
            }
        }
        return acc;
    }



    public List<ToDo> getToDosWithFilter(String completed) {
        return switch (completed) {
            case "Completed" -> getToDosCompleted(true);
            case "Active" -> getToDosCompleted(false);
            case null, default -> toDos;
        };
    }


    public int nextId() {
        if (toDos.size() == 0) {
            return 1;
        }
        int max = toDos.get(0).id();
        for (int i = 1; i < toDos.size(); i++) {
            if (toDos.get(i).id() > max) {
                max = toDos.get(i).id();
            }
        }
        return max + 1;
    }

    public void add(String text) {
        toDos.add(new ToDo(nextId(), text));
    }

    public int idToIndex(int id) {
        for (int i = 0; i < toDos.size(); i++) {
            if (toDos.get(i).id() == id) {
                return i;
            }
        }
        return -1;
    }

    public ToDo getToDoItem(int id) {
        return toDos.get(idToIndex(id));
    }



    public void removeFinishedToDoItems() {
        toDos = new ArrayList<>(getToDosCompleted(false));
    }



    public void toggle(int id) {
        int index = idToIndex(id);
        toDos.set(index, toDos.get(index).toggle());
    }

    public void updateText(int id, String text) {
        int index = idToIndex(id);
        toDos.set(index, toDos.get(index).updateText(text));
    }

    public void delete(int id) {
        int index = idToIndex(id);
        toDos.remove(index);
    }

    public String showCountOfActiveToDoItems() {
        int countOfActiveToDoItems = getToDosCompleted(false).size();
        var sOrEmpty = "s";
        if (countOfActiveToDoItems == 1) {
            sOrEmpty = "";
        }
        return countOfActiveToDoItems + " item" + sOrEmpty + " left";
    }

}



public record View() {

    String numberToFilter(int number){
        return switch (number){
            case 1 -> "All";
            case 2 -> "Active";
            case 3 -> "Completed";
            default -> throw new IllegalStateException("Unexpected value: " + number);
        };
    }


    public String showToDo(ToDo toDo) {
        var done = "❌";
        if (toDo.completed()) {
            done = "✓";
        }
        return done + " " + toDo.text() + "\t\t\t\t ID: " + toDo.id();
    }

    public void printToDos(List<ToDo> toDos) {
        println("\t\ttodos");
        for (var toDoItem : toDos) {
            println(showToDo(toDoItem));
        }
    }

    public List<Integer> getIDs(List<ToDo> toDos){
        var ids = new ArrayList<Integer>();
        for (var toDoItem : toDos) {
                ids.add(toDoItem.id());
        }
        return ids;
    }

    private List<Integer> numbersFromZeroTo(int number){
        var smallerNumbersAsStrings = new ArrayList<Integer>();
        for (int i = 1; i <= number; i++){
            smallerNumbersAsStrings.add(i);
        }
        return smallerNumbersAsStrings;
    }







}

```

# Model

## Aufgabe

Definiere ein `record` `ToDo` mit den Feldern `id` (int), `text` (String) und `completed` (boolean).

```{.java .cb-nb}
new ToDo(1, "Kaffee trinken", false);
```

```{.java .cb-nb}
new ToDo(5, "App programmieren", true);
```

## Aufgabe

Füge dem `ToDo`-Record einen Konstruktor hinzu, der nur `id` und `text` als Parameter hat und `completed` auf `false` setzt.

```{.java .cb-nb}
new ToDo(2, "Einkaufen gehen");
```

```{.java .cb-nb}
new ToDo(3, "Sport machen");
```

## Aufgabe

Füge dem `ToDo`-Record die Methode `toggle` hinzu, die ein neues ToDo mit umgekehrtem Status zurückgibt.

```{.java .cb-nb}
new ToDo(1, "Test", false).toggle();
```

```{.java .cb-nb}
new ToDo(2, "Sport machen", true).toggle();
```

## Aufgabe

Füge dem `ToDo`-Record die Methode `updateText` hinzu, die ein neues ToDo mit geändertem Text zurückgibt.

```{.java .cb-nb}
new ToDo(1, "Test", false).updateText("Sport machen");
```

```{.java .cb-nb}
new ToDo(2, "Zocken", true).updateText("Lesen");
```

## Aufgabe

Definiere eine Klasse `Model` mit einem privaten Attribut `toDos`, das eine `ArrayList` von `ToDo`s ist.
Füge der Klasse `Model` einen Konstruktor hinzu, der eine leere ToDo-Liste anlegt.

```{.java .cb-nb}
Model model = new Model();
model.toDos;
```

## Aufgabe

Füge der Klasse `Model` einen Konstruktor hinzu, der eine bestehende Liste von ToDos übernimmt.

\scriptsize

```{.java .cb-nb}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list);
model.toDos;
```

\normalsize

## Aufgabe

Füge der Klasse `Model` die Methode `getToDosCompleted` hinzu, die alle ToDos mit dem gewünschten Status zurückgibt.

\scriptsize

```{.java .cb-nb}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list);
model.getToDosCompleted(false);
```

\normalsize

```{.java .cb-nb}
model.getToDosCompleted(true);
```

## Aufgabe

Füge der Klasse `Model` die Methode `getToDosWithFilter` hinzu, die je nach Filter (`All`, `Active` oder `Completed`) die passenden ToDos liefert.

\scriptsize

```{.java .cb-nb}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list);
model.getToDosWithFilter("Active");
```

```{.java .cb-nb}
model.getToDosWithFilter("Completed");
```

```{.java .cb-nb}
model.getToDosWithFilter("All");
```

\normalsize

**Hinweis:** Nutze `getToDosCompleted`!

## Aufgabe

Füge der Klasse `Model` die Methode `idToIndex` hinzu, die den Index eines ToDos mit einer bestimmten ID findet.
\scriptsize

```{.java .cb-nb}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list);
model.idToIndex(3);
```

```{.java .cb-nb}
model.idToIndex(1);
```

\normalsize
Wenn kein ToDo mit dieser ID in der Liste ist, wird $-1$ zurückgegeben.

```{.java .cb-nb}
model.idToIndex(2);
```

## Aufgabe

Füge der Klasse `Model` die Methode `getToDoItem` hinzu, die das ToDo mit einer bestimmten ID zurückgibt.

\scriptsize

```{.java .cb-nb}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list);
model.getToDoItem(3);
```

```{.java .cb-nb}
model.getToDoItem(1);
```

\normalsize

**Hinweis:** Nutze `idToIndex`!

## Aufgabe

Füge der Klasse `Model` die Methode `nextId` hinzu, die die ID für ein neues ToDo berechnet.
Dafür wird die bisher größte ID bestimmt und $1$ mehr zurückgegeben.

\scriptsize

```{.java .cb-nb}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list);
model.nextId();
```

Wenn keine ToDos vorhanden sind, wird mit der ID $1$ angefangen.

```{.java .cb-nb}
Model model = new Model();
model.nextId();
```

\normalsize

## Aufgabe

Füge der Klasse `Model` die Methode `add` hinzu, die ein neues ToDo mit eindeutiger `id` und dem übergebenen Text hinzufügt.

\scriptsize

```{.java .cb-nb}
List<ToDo> list = List.of(new ToDo(1, "Test1", false));
Model model = new Model(list);
model.add("Einkaufen");
model.toDos;
```

```{.java .cb-nb}
Model model = new Model();
model.add("Coden");
model.toDos;
```

\normalsize

**Hinweis:** Nutze `nextId`!

## Aufgabe

Füge der Klasse `Model` die Methode `delete` hinzu, die das ToDo mit der entsprechenden ID entfernt.

\scriptsize

```{.java .cb-nb}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list);
model.delete(1);
model.toDos;
```

```{.java .cb-nb}
model.delete(7);
model.toDos;
```

\normalsize

**Hinweis:** Nutze `idToIndex`!

## Aufgabe

Füge der Klasse `Model` die Methode `toggle` hinzu, die den Status eines ToDos umschaltet.

\scriptsize

```{.java .cb-nb}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list);
model.toggle(7);
model.getToDoItem(7);
```

```{.java .cb-nb}
model.toggle(3);
model.getToDoItem(3);
```

\normalsize

**Hinweis:** Nutze `idToIndex` und die Methode `toggle` der Klasse `ToDo`!

## Aufgabe

Füge der Klasse `Model` die Methode `updateText` hinzu, die den Text eines ToDos ändert.

\scriptsize

```{.java .cb-nb}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list);
model.updateText(3, "Hausaufgaben machen");
model.getToDoItem(3);
```

```{.java .cb-nb}
model.updateText(7, "Joggen");
model.getToDoItem(7);
```

\normalsize

## Aufgabe

Füge der Klasse `Model` die Methode `removeFinishedToDoItems` hinzu, die alle erledigten ToDos aus der Liste entfernt.

\scriptsize

```{.java .cb-nb}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(2, "Test2", true), new ToDo(3, "Test3", false));
Model model = new Model(list);
model.removeFinishedToDoItems();
model.toDos;
```

\normalsize

**Hinweis**: Nutze `getToDosCompleted!`!

## Aufgabe

Füge der Klasse `Model` die Methode `showCountOfActiveToDoItems` hinzu, die die Anzahl der offenen Aufgaben als String zurückgibt (z.B. "2 items left").

\scriptsize

```{.java .cb-nb}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list);
model.showCountOfActiveToDoItems();
```

```{.java .cb-nb}
model.add("Neue Aufgabe");
model.showCountOfActiveToDoItems();
```

\normalsize

# View

## Aufgabe

Definiere die Methode `numberToFilter` in der Klasse `View`, die für 1 `All`, für 2 `Active` und für 3 `Completed` zurückgibt.
<!--
```{.java .cb-nb}
var view = new View();
view.numberToFilter(1);
```


```{.java .cb-nb}
view.numberToFilter(2);
```
```{.java .cb-nb}
view.numberToFilter(3);
``` 




## Aufgabe

Implementiere die Methode `showToDo` in der Klasse `View`, die ein ToDo als String mit Häkchen oder Kreuz und ID darstellt.

```{.java .cb-nb}
var view = new View();
view.showToDo(new ToDo(1, "Testaufgabe", false))
```

```{.java .cb-nb}
view.showToDo(new ToDo(2, "Erledigte Aufgabe", true))
```
-->

## Aufgabe

Schreibe die Methode `getIDs` in der Klasse `View`, die eine Liste aller IDs der ToDos zurückgibt.

```{.java .cb-nb}
var view = new View();
view.getIDs(List.of(new ToDo(42, "Kaffee trinken", false)))
```

```{.java .cb-nb}
view.getIDs(List.of(new ToDo(1, "A", false), new ToDo(2, "B", true)))
```
-->
<!--
## Aufgabe

Implementiere die Methode `askUntilInputIsId` in der Klasse `View`, die so lange nach einer ID fragt, bis eine gültige eingegeben wurde.
Die Methode bekommt eine Liste von ToDos und fragt nach einer ID, die in dieser Liste vorkommt.

```{.java}
var view = new View();
view.askUntilInputIsId(List.of(new ToDo(1, "A", false)))
```

```{.java}
view.askUntilInputIsId(List.of(new ToDo(2, "B", true), new ToDo(3, "C", false)))
```

## Aufgabe

Schreibe die Methode `askUntilInputIsSmallerOrEqual` in der Klasse `View`, die so lange nach einer Zahl fragt, bis eine Zahl zwischen 1 und upperBound eingegeben wurde.
Der Parameter upperBound gibt die Obergrenze an.

```{.java}
var view = new View();
view.askUntilInputIsSmallerOrEqual(3)
```

```{.java}
view.askUntilInputIsSmallerOrEqual(5)
```


 -->
