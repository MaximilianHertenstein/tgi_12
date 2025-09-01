---
title: ToDo-App
codebraid:
  jupyter: true
---

```{.java .cb-run}



import java.util.ArrayList;
import java.util.List;


import static java.io.IO.println;
import static java.io.IO.readln;

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


public record View() {

    public String numberToFilter(int number) {
        return switch (number) {
            case 1 -> "All";
            case 2 -> "Active";
            case 3 -> "Completed";
            default -> throw new IllegalStateException("Unexpected value: " + number);
        };
    }


    public String showToDo(ToDo toDo) {
        var done = "X";
        if (toDo.completed()) {
            done = "C";
        }
        return done + " " + toDo.text() + "\t\t\t\t ID: " + toDo.id();
    }

    public void printToDos(List<ToDo> toDos) {
        println("\n\t\ttodos");
        for (var toDoItem : toDos) {
            println(showToDo(toDoItem));
        }
    }

    public List<Integer> getIDs(List<ToDo> toDos) {
        var ids = new ArrayList<Integer>();
        for (var toDoItem : toDos) {
            ids.add(toDoItem.id());
        }
        return ids;
    }

    public List<Integer> numbersFromOneTo(int number) {
        var smallerNumbersAsStrings = new ArrayList<Integer>();
        for (int i = 1; i <= number; i++) {
            smallerNumbersAsStrings.add(i);
        }
        return smallerNumbersAsStrings;
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




```

# ToDo

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

# View


##

Definiere  eine Klasse `View` in einer neuen Datei.


## Aufgabe

Erweitere die Klasse `View` um eine Methode `numberToFilter`. Diese gibt für 1 `All`, für 2 `Active` und für 3 `Completed` zurückgibt.





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

Erweitere die Klasse `View` um eine Methode `showToDo`. Dieser wird ein ToDo übergeben. Sie gibt eine Darstellung als String zurück.
Wenn ein ToDo abgeschlossen ist, wird das mit einem `C` dargestellt. Wenn es nicht abgeschlossen wird das mit einem `X` dargestellt.
Vor der ID sollen vier Tabulatorzeichen (`\t`) und ein Leerzeichen stehen.

```{.java .cb-nb}
var view = new View();
view.showToDo(new ToDo(1, "Testaufgabe", false))
```

```{.java .cb-nb}
view.showToDo(new ToDo(2, "Erledigte Aufgabe", true))
```

**Hinweis:** In Java kann man sowohl zwei String als auch einen String und ein Integer addieren.

## Aufgabe

Erweitere die Klasse `View` um eine Methode `printToDos`.
Implementiere die Methode `printToDos` in der Klasse `View`. Dieser wird eine Liste von ToDos übergeben. 
Sie gibt diese formatiert aus. Darüber zeigt sie die ÜberschrifÜberschrift `todos` an. Vor dieser stehen ein New-Line-Zeichen(`\n`) und zwei Tabulatorzeichen.

```{.java .cb-nb}
var view = new View();
view.printToDos(List.of(new ToDo(1, "Test", false)))
```

```{.java .cb-nb}
view.printToDos(List.of(new ToDo(1, "A", false), new ToDo(2, "B", true)))
```

**Hinweis**: Nutze `showToDo`!


## Aufgabe

Erweitere die Klasse `View` um eine Methode `getIDs`. Dieser wird eine Liste von ToDos übergeben. Sie gibt eine unveränderliche Liste aller IDs der ToDos zurückgibt.

```{.java .cb-nb}
var view = new View();
view.getIDs(List.of(new ToDo(42, "Kaffee trinken", false)))
```

```{.java .cb-nb}
view.getIDs(List.of(new ToDo(1, "A", false), new ToDo(2, "B", true)))
```



## Aufgabe

Erweitere die Klasse `View` um eine Methode `numbersFromOneTo`, die eine unveränderliche Liste aller Zahlen von 1 bis zur übergebenen Zahl (inklusive) zurückgibt.

```{.java .cb-nb}
var view = new View();
view.numbersFromOneTo(3)
```

```{.java .cb-nb}
view.numbersFromOneTo(5)
```



## Aufgabe

Erweitere die Klasse `View` um eine Methode `askForNewText`.  
Sie soll den Benutzer auffordern, einen neuen Text für ein ToDo einzugeben, und diesen Text zurückgeben.


```java
var view = new View();
view.askForNewText()
```
```
Enter the new text for the ToDo!
Programmieren            \\Eingabe
```

In diesem Beispiel ist die Rückgabe `"Programmieren"`


```java
var view = new View();
view.askForNewText()
```
```
Enter the new text for the ToDo!
Fußball spielen             \\Eingabe
```

In diesem Beispiel ist die Rückgabe `"Fußball spielen"`


## Aufgabe

Erweitere die Klasse `View` um eine Methode `askForNewToDo`.  
Sie fragt den Benutzer nach dem Text für ein **neues** ToDo und gibt diesen zurück. Nur die Frage ist anders als bei der letzen Aufgabe.

```java
var view = new View();
view.askForNewToDo()
```
```
Enter the text for the new item!
Programmieren            \\Eingabe
```

In diesem Beispiel ist die Rückgabe `"Programmieren"`


```java
var view = new View();
view.askForNewToDo()
```
```
Enter the text for the new item!
Fußball spielen             \\Eingabe
```

In diesem Beispiel ist die Rückgabe `"Fußball spielen"`




## Hilfsfunktionen

Für die nächste Aufgabe brauchst du die Listen-Methode `contains`. Mit dieser kann bestimt werden, ob ein Wert in einer Liste vorkommt. 

```{.java .cb-nb}
var xs = List.of(1, 3, 5);
xs.contains(3)
```

```{.java .cb-nb}
var xs = List.of(1, 3, 5);
xs.contains(2)
```

Außerdem brauchst du die Methode

`NumberUtils.isCreatable`. Dieser wird ein String übergeben. Sie gibt zurück, ob sich dieser in eine Zahl umwandeln lässt. Rufe die Methode folgendermaßen auf:


```{.java}
NumberUtils.isCreatable(myString)
```


## Aufgabe

Erweitere die Klasse `View` um die Methode `askUntilElementInList`.
Sie erhält eine Liste von Integern  und einen Prompt-Text. Die Methode fragt so lange nach einer Zahl, bis eine Zahl aus der Liste eingegeben wurde, und gibt diese als Integer zurück.

```java
var view = new View();
view.askUntilElementInList(List.of(1, 2, 3), "Bitte gib eine Zahl zwischen 1 und 3 ein!");
```
```
Bitte gib eine Zahl zwischen 1 und 3 ein!
4               \\Eingabe
Bitte gib eine Zahl zwischen 1 und 3 ein!
2               \\Eingabe
```

In diesem Beispiel ist die Rückgabe $2$.




```java
view.askUntilElementInList(List.of(10, 20), "Wähle eine ID:");
```
```
Wähle eine ID:
20              \\Eingabe
```

In diesem Beispiel ist die Rückgabe $20$.

## Aufgabe

Erweitere die Klasse `View` um die Methode `askUntilInputIsId`.
Sie bekommt eine Liste von ToDos und fragt so lange nach einer ID, bis eine gültige ID aus der Liste eingegeben wurde. Sie gibt die ID als Integer zurück.

```java
var view = new View();
view.askUntilInputIsId(List.of(new ToDo(1, "A", false), new ToDo(3, "B", true)));
```
```
Enter the ID of the ToDo
2               \\Eingabe
Enter the ID of the ToDo
1               \\Eingabe
```

In diesem Beispiel ist die Rückgabe $1$.

```java
view.askUntilInputIsId(List.of(new ToDo(2, "B", true)))
```
```
Enter the ID of the ToDo
2               \\Eingabe
```

In diesem Beispiel ist die Rückgabe $2$.

**Hinweis**: Nutze `askUntilElementInList` und `getIDs`!




## Aufgabe

Erweitere die Klasse `View` um die Methode `askUntilInputIsSmallerOrEqual`.
Sie erhält einen Parameter `upperBound` und fragt so lange nach einer Zahl, bis eine Zahl zwischen 1 und `upperBound` eingegeben wurde. Gibt die Zahl zurück.

```java
var view = new View();
view.askUntilInputIsSmallerOrEqual(3);
```
```
Enter a number between 1 and 3
0                   \\Eingabe
Enter a number between 1 and 3
4                   \\Eingabe
Enter a number between 1 and 3
3                   \\Eingabe

```

In diesem Beispiel ist die Rückgabe $3$.


```java
view.askUntilInputIsSmallerOrEqual(5);
```
```
Enter a number between 1 and 5
2                   \\Eingabe
```

In diesem Beispiel ist die Rückgabe $2$.


**Hinweis**: Nutze `askUntilElementInList` und `numbersFromOneTo`!





## Aufgabe

Implementiere die Methode `askForUpdateOperation` in der Klasse `View`.  
Sie fragt, was an einem ToDo geändert werden soll, und gibt die gewählte Option (1-3) zurück.
Sie fragt solange erneut nach bis eine der Zahlen  (1-3) eingegeben wurde.

```java
var view = new View();
view.askForUpdateOperation()
```
```
What do you want to change? 
 1 Update text 
 2 Change status 
 3 Delete item
Enter a number between 1 and 3
0
Enter a number between 1 and 3
1
```


In diesem Beispiel ist die Rückgabe $1$.


**Hinweis**: Nutze   `askUntilInputIsSmallerOrEqual`!



## Aufgabe

Erweitere die Klasse `View` um die Methode `askForFilter`.  
Sie fragt, welche ToDos angezeigt werden sollen, und gibt `All`, `Active` oder `Completed` zurück.
Sie fragt solange erneut nach bis eine der Zahlen  (1-3) eingegeben wurde.



```java
var view = new View();
view.askForFilter()
```
```
Which ToDos do you want to display? 
 1 All 
 2 Only active 
 3 Only completed
Enter a number between 1 and 3
4               \\Eingabe
Enter a number between 1 and 3
1               \\Eingabe
```

In diesem Beispiel ist die Rückgabe `All`.


```java
var view = new View();
view.askForUpdateOperation()
```
```
Which ToDos do you want to display? 
 1 All 
 2 Only active 
 3 Only completed
Enter a number between 1 and 3
3
```

In diesem Beispiel ist die Rückgabe `Completed`.


**Hinweis**: Nutze `numberToFilter` und `askUntilInputIsSmallerOrEqual`!


## Aufgabe

Erweitere die Klasse `View` um die Methode `showMainMenuAskForOption`
Sie bekommt eine Liste von ToDos, einen String zur Anzeige der Anzahl aktiver ToDos und den gewählten Filter.  
Die Methode gibt die übergebenen Daten und das Hauptmenü aus und fragt nach einer Option (1-5).
Sie fragt solange erneut nach bis eine der Zahlen  (1-3) eingegeben wurde.


\scriptsize
```java
var view = new View();
view.showMainMenuAskForOption(List.of(new ToDo(1, "A", false), new ToDo(1, "A", true)), "1 open", "All");
```
\normalsize
```
		todos
X A				 ID: 1
C A				 ID: 1
Current Filter: All
1 open

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
3           \\Eingabe
```

In diesem Beispiel ist die Rückgabe $3$.


```java
view.showMainMenuAskForOption(List.of(), "Nothing to do!", "Completed");
```
```
		todos
Nothing to do!
Current Filter: Completed

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
8           \\Eingabe
Enter a number between 1 and 5
5           \\Eingabe
```

In diesem Beispiel ist die Rückgabe $5$.

**Hinweis**: Nutze `printToDos` und `askUntilInputIsSmallerOrEqual`!


## Aufgabe
Schütze die Methoden `numberToFilter`, `showToDo`, `printToDos`, `getIDs`, `numbersFromOneTo`, `askUntilElementInList` und `askUntilInputIsSmallerOrEqual` vor dem Zugriff außerhalb der Klasse `View`.


# Model



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


## Aufgabe
Schütze die Methoden `getToDosCompleted`, `idToIndex`, `nextId` durch den Zugriff außerhalb der Klasse `Model`. Schütze auch die Liste der ToDos.

# Terminal-Controller


## Aufgabe

Erstelle eine Klasse `TerminalController`. Die Atrribute der Klasse sind ein Objekt der Klasse `View` und ein Objekt der Klasse `Model`.

## Aufgabe

Erweitere die Klasse `TerminalController` um die Methode `runApp`.  
Die Methode zeigt das Hauptmenü an, verarbeitet Benutzereingaben und steuert den Ablauf der ToDo-App, bis der Benutzer das Programm beendet.

Implementiere zunächst nur die Möglichkeit ToDos hinzuzufügen und das Programm zu beenden.

```{java .cb-nb}
var controller = new TerminalController();
controller.runApp();
```

```
		todos
0 items left
Current Filter: All

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
1
Enter the text for the new item!
IFT-Hausaufgaben

		todos
X IFT-Hausaufgaben				 ID: 1
1 item left
Current Filter: All

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
Fußball spielen
Enter a number between 1 and 5
5
```

**Hinweis:** Nutze die Methoden `showMainMenuAskForOption`, `askForNewToDo` der Klasse `View` und die Methoden
`getToDosWithFilter`, `showCountOfActiveToDoItems` und `add` der Klasse `Model`.

## Aufgabe

Erweitere `runApp` um eine Möglichkeit den gewählten Filter zu ändern.


```java
var controller = new TerminalController();
controller.runApp();
```

```
		todos
0 items left
Current Filter: All

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
4
Which ToDos do you want to display? 
 1 All 
 2 Only active 
 3 Only completed
Enter a number between 1 and 3
2

		todos
0 items left
Current Filter: Active

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
```

Nutze die Methode `askForFilter` der Klasse `View`!

## Aufgabe

Erweitere die Klasse `TerminalController` um die Methode `updateItem`.  
Die Methode fragt nach einer ToDo-ID, einer Änderungsoption und führt dann die entsprechende Aktion (Text ändern, Status umschalten oder löschen) aus.

```java
var controller = new TerminalController();
controller.runApp();
```
```
		todos
0 items left
Current Filter: All

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
1
Enter the text for the new item!
IFT-Hausaufgaben

		todos
X IFT-Hausaufgaben				 ID: 1
1 item left
Current Filter: All

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
2
Enter the ID of the ToDo
1
What do you want to change? 
 1 Update text 
 2 Change status 
 3 Delete item
Enter a number between 1 and 3
1
Enter the new text for the ToDo!
App programmieren

		todos
X App programmieren				 ID: 1
1 item left
Current Filter: All

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
2
Enter the ID of the ToDo
1
What do you want to change? 
 1 Update text 
 2 Change status 
 3 Delete item
Enter a number between 1 and 3
2

		todos
C App programmieren				 ID: 1
0 items left
Current Filter: All

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
2
Enter the ID of the ToDo
1
What do you want to change? 
 1 Update text 
 2 Change status 
 3 Delete item
Enter a number between 1 and 3
3

		todos
0 items left
Current Filter: All

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
5
```


**Hinweis**: Nutze die Methoden `toggle`, `delete`, `updateText` und `getToDosWithFilter` der Klasse `Model` und die Methoden `askUntilInputIsId`
`askForUpdateOperation` und `askForNewText` der Klasse `View`!

## Aufgabe

Erweitere `runApp` um die Möglichkeit alle erledigten ToDos zu löschen.

```java
var controller = new TerminalController();
controller.runApp();
```
```
		todos
0 items left
Current Filter: All

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
1
Enter the text for the new item!
Programmieren

		todos
X Programmieren				 ID: 1
1 item left
Current Filter: All

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
1
Enter the text for the new item!
Fußball spielen

		todos
X Programmieren				 ID: 1
X Fußball spielen				 ID: 2
2 items left
Current Filter: All

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
2
Enter the ID of the ToDo
2
What do you want to change? 
 1 Update text 
 2 Change status 
 3 Delete item
Enter a number between 1 and 3
2

		todos
X Programmieren				 ID: 1
C Fußball spielen				 ID: 2
1 item left
Current Filter: All

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
3

		todos
X Programmieren				 ID: 1
1 item left
Current Filter: All

What do you want to do?  
 1 Add ToDo 
 2 Edit ToDo 
 3 Delete all completed ToDos 
 4 Change ToDo filter 
 5 Quit the app
Enter a number between 1 and 5
5
```


**Hinweis:** Nutze die Methode `removeFinishedToDoItems()` der Klasse `Model`!


## Aufgabe

Teste dein Programm

## Aufgabe
Hilf den anderen SuS!
