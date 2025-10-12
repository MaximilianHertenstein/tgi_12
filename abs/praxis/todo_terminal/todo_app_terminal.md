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

public record UIState(String selectedFilter, List<ToDo> selectedToDos ,String displayOfActiveToDos) {
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
        System.out.println("\n\t\ttodos");
        for (var toDoItem : toDos) {
            System.out.println(showToDo(toDoItem));
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
    public String selectedFilter = "All";
    
    
    public Model() {
        this.toDos = new ArrayList<>();
        selectedFilter = "All";
    }

    public Model(List<ToDo> toDos, String selectedFilter) {
        this.selectedFilter = selectedFilter;
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



    public List<ToDo> getFilteredToDos() {
        return switch (selectedFilter) {
            case "Completed" -> getToDosCompleted(true);
            case "Active" -> getToDosCompleted(false);
            case null, default -> toDos;
        };
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

    public void removeFinishedToDoItems() {
        toDos = new ArrayList<>(getToDosCompleted(false));
    }

    public String showCountOfActiveToDoItems() {
        int countOfActiveToDoItems = getToDosCompleted(false).size();
        var sOrEmpty = "s";
        if (countOfActiveToDoItems == 1) {
            sOrEmpty = "";
        }
        return countOfActiveToDoItems + " item" + sOrEmpty + " left";
    }

    public void setFilter(String newFilter) {
        selectedFilter = newFilter;
    }

    public UIState getUIState(){
        return new UIState(selectedFilter, getFilteredToDos(), showCountOfActiveToDoItems());
    }

}




```

Definiere jede Klasse in einer eigenen Datei.


# ToDo


## Aufgabe

Definiere ein `record` `ToDo` mit den Feldern `id` (int), `text` (String) und `completed` (boolean).

```{.java .cb-nb line_numbers=false}
new ToDo(1, "Kaffee trinken", false);
```

```{.java .cb-nb line_numbers=false}
new ToDo(5, "App programmieren", true);
```

## Aufgabe

Füge dem `ToDo`-Record einen Konstruktor hinzu, der nur `id` und `text` als Parameter hat und `completed` auf `false` setzt.

```{.java .cb-nb line_numbers=false}
new ToDo(2, "Einkaufen gehen");
```

```{.java .cb-nb line_numbers=false}
new ToDo(3, "Sport machen");
```

## Aufgabe

Füge dem `ToDo`-Record die Methode `toggle` hinzu, die ein neues To-do mit umgekehrtem Status zurückgibt.

```{.java .cb-nb line_numbers=false}
new ToDo(1, "Test", false).toggle();
```

```{.java .cb-nb line_numbers=false}
new ToDo(2, "Sport machen", true).toggle();
```

## Aufgabe

Füge dem `ToDo`-Record die Methode `updateText` hinzu, die ein neues To-do mit geändertem Text zurückgibt.

```{.java .cb-nb line_numbers=false}
new ToDo(1, "Test", false).updateText("Sport machen");
```

```{.java .cb-nb line_numbers=false}
new ToDo(2, "Zocken", true).updateText("Lesen");
```

# View


##

Definiere eine Klasse `View` in einer neuen Datei.


## Aufgabe

Ergänze die Klasse `View` um eine Methode `numberToFilter`. Diese gibt für 1 `All`, für 2 `Active` und für 3 `Completed` zurückgibt.





```{.java .cb-nb line_numbers=false}
var view = new View();
view.numberToFilter(1);
```

```{.java .cb-nb line_numbers=false}
view.numberToFilter(2);
```
```{.java .cb-nb line_numbers=false}
view.numberToFilter(3);
``` 




## Aufgabe

Ergänze die Klasse `View` um eine Methode `showToDo`. Dieser wird ein To-do übergeben. Sie gibt eine Darstellung als String zurück.
Wenn ein To-do abgeschlossen ist, wird das mit einem `C` dargestellt. Wenn es nicht abgeschlossen wird das mit einem `X` dargestellt.
Vor der ID sollen vier Tabulatorzeichen (`\t`) und ein Leerzeichen stehen.

```{.java .cb-nb line_numbers=false}
var view = new View();
view.showToDo(new ToDo(1, "Testaufgabe", false));
```

```{.java .cb-nb line_numbers=false}
view.showToDo(new ToDo(2, "Erledigte Aufgabe", true));
```

**Hinweis:** In Java kann man sowohl zwei String als auch einen String und ein Integer addieren.

## Aufgabe

Ergänze die Klasse `View` um eine Methode `printToDos`.
Implementiere die Methode `printToDos` in der Klasse `View`. Dieser wird eine Liste von To-dos übergeben. 
Sie gibt diese formatiert aus. Darüber zeigt sie die Überschrift `todos` an. Vor dieser stehen ein New-Line-Zeichen(`\n`) und zwei Tabulatorzeichen.

```{.java .cb-nb line_numbers=false}
var view = new View();
view.printToDos(List.of(new ToDo(1, "Test", false)));
```

```{.java .cb-nb line_numbers=false}
view.printToDos(List.of(new ToDo(1, "A", false), new ToDo(2, "B", true)));
```

**Hinweis**: Nutze `showToDo`!


## Aufgabe

Ergänze die Klasse `View` um eine Methode `getIDs`. Dieser wird eine Liste von To-dos übergeben. Sie gibt eine unveränderliche Liste aller IDs der To-dos zurück.

```{.java .cb-nb line_numbers=false}
var view = new View();
view.getIDs(List.of(new ToDo(42, "Kaffee trinken", false)))
```

```{.java .cb-nb line_numbers=false}
view.getIDs(List.of(new ToDo(1, "A", false), new ToDo(2, "B", true)))
```



## Aufgabe

Ergänze die Klasse `View` um eine Methode `numbersFromOneTo`, die eine unveränderliche Liste aller Zahlen von 1 bis zur übergebenen Zahl (inklusive) zurückgibt.

```{.java .cb-nb line_numbers=false}
var view = new View();
view.numbersFromOneTo(3)
```

```{.java .cb-nb line_numbers=false}
view.numbersFromOneTo(5)
```



## Aufgabe

Ergänze die Klasse `View` um eine Methode `askForNewText`.  
Sie soll den Benutzer auffordern, einen neuen Text für ein To-do einzugeben, und diesen Text zurückgeben.


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

**Hinweis** In Java kann mit `readln` eine Eingabe an der Konsole eingelesen werden.



## Aufgabe

Ergänze die Klasse `View` um eine Methode `askForNewToDo`.  
Sie fragt den Benutzer nach dem Text für ein **neues** To-do und gibt diesen zurück. Nur die Frage, die dem Benutzer gestellt wird, ist anders als bei der letzten Aufgabe.

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

Für die nächste Aufgabe brauchst du die Listen-Methode `contains`. Mit dieser kann bestimmt werden, ob ein Wert in einer Liste vorkommt. 

```{.java .cb-nb line_numbers=false}
var xs = List.of(1, 3, 5);
xs.contains(3)
```

```{.java .cb-nb line_numbers=false}
var xs = List.of(1, 3, 5);
xs.contains(2)
```

Außerdem brauchst du die Methode

`NumberUtils.isCreatable`. Dieser wird ein String übergeben. Sie gibt zurück, ob sich dieser in eine Zahl umwandeln lässt. Rufe die Methode folgendermaßen auf:


```{.java}
NumberUtils.isCreatable(myString)
```

Um die Methode nutzen zu können, musst du die folgende Abhängigkeit in `pom.xml` ergänzen.


```
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.18.0</version>
</dependency>
```
Diese Abhängigkeit muss im `dependencies`-Block stehen.
Wenn du noch keinen `dependencies`-Block hast, musst du diesen noch erstellen.
Der entsrpechende Block sieht dann folgendermaßen aus:

```
<dependencies>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.18.0</version>
    </dependency>

    <!-- eventuell weitere Abhängigkeiten-- >

<dependencies>
```

Klicke nach dem Einfügen auf das `M` auf der rechten Seite.


Strings zu Integern konvertieren kannst du mit `Integer.parseInt`.


```{.java .cb-nb line_numbers=false}
Integer.parseInt("3")
```


## Aufgabe

Ergänze die Klasse `View` um die Methode `askUntilElementInList`.
Sie erhält eine Liste von Integern und einen Prompt-Text. Die Methode fragt so lange nach einer Zahl, bis eine Zahl aus der Liste eingegeben wurde, und gibt diese als Integer zurück.

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

Ergänze die Klasse `View` um die Methode `askUntilInputIsId`.
Sie bekommt eine Liste von To-dos und fragt so lange nach einer ID, bis eine gültige ID aus der Liste eingegeben wurde. Sie gibt die ID als Integer zurück.

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

Ergänze die Klasse `View` um die Methode `askUntilInputIsSmallerOrEqual`.
Sie erhält einen Parameter `upperBound` und fragt so lange nach einer Zahl, bis eine Zahl zwischen 1 und `upperBound` eingegeben wurde. Die Methode gibt diese Zahl zurück.

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
Sie fragt, was an einem To-do geändert werden soll. 
die Frage wird gestellt, bis eine der Zahlen  (1-3) eingegeben wurde.
Anschließend wird die Eingabe zurückgegeben.


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

Ergänze die Klasse `View` um die Methode `askForFilter`. Diese fragt, welche To-dos angezeigt werden sollen.
Sie fragt so lange nach einer Eingabe bis eine der Zahlen  (1-3) eingegeben wurde.
Die Methode gibt anschließend den entsprechenden Filter (`All`, `Active` oder `Completed`) zurück.




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

Erstelle in einer neuen Datei ein Record namens `UIState` mit den Feldern 

- `selectedFilter` (String), 
- `selectedToDos` (Liste von To-dos) und 
- `displayOfActiveToDos` (String).

\scriptsize
```{.java .cb-nb line_numbers=false}
new UIState("Active", List.of(new ToDo(1, "Test", false)), "1 item left");
```
```{.java .cb-nb line_numbers=false}
new UIState("Completed", List.of(), "0 items left");
```
\normalsize
## Aufgabe

Ergänze die Klasse `View` um die Methode `showMainMenuAskForOption`!
Sie bekommt ein Objekt der Klasse `UIState` übergeben.
Die Methode gibt die übergebenen Daten und das Hauptmenü aus und fragt nach einer Option (1-5).
Sie fragt so lange erneut nach bis eine der Zahlen  (1-5) eingegeben wurde. Diese Eingabe wird anschließend zurückgegeben.


\scriptsize
```java
var uiState = new UIState("All", List.of(new ToDo(1, "A", false), new ToDo(1, "A", true)), "1 open")
var view = new View();
view.showMainMenuAskForOption(uiState);
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
view.showMainMenuAskForOption(UIState("Completed", List.of(), "Nothing to do!"));
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
Schütze die Methoden `numberToFilter`, `showToDo`, `printToDos`, `getIDs`, `numbersFromOneTo`, `askUntilElementInList` und `askUntilInputIsSmallerOrEqual` vor einem Zugriff außerhalb der Klasse `View`.


# Model



## Aufgabe

Definiere mit dem Schlüsselwort `class` eine Klasse `Model` mit einem Attribut 

- `toDos`, das eine `ArrayList` von `ToDo`s ist
- `filter`, das ein `String` ist




## Aufgabe

Füge der Klasse `Model` einen Konstruktor hinzu, der 

- `toDos` mit einer leeren `ArrayList` 
- `filter` mit dem String `All"`

initialisiert.

```{.java .cb-nb line_numbers=false}
Model model = new Model();
model.toDos;
```
```{.java .cb-nb line_numbers=false}
model.toDos;
```


## Aufgabe

Füge der Klasse `Model` einen Konstruktor hinzu, der eine bestehende Liste von To-dos und einen Filter übernimmt.

\scriptsize

```{.java .cb-nb line_numbers=false}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list, "Completed");
model.toDos;
```

\normalsize

## Aufgabe

Füge der Klasse `Model` die Methode `getToDosCompleted` hinzu, die alle To-dos mit dem gewünschten Status zurückgibt.

\scriptsize

```{.java .cb-nb line_numbers=false}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list, "All");
model.getToDosCompleted(false);
```

\normalsize

```{.java .cb-nb line_numbers=false}
model.getToDosCompleted(true);
```

## Aufgabe

Füge der Klasse `Model` die Methode `getFilteredToDos` hinzu, die je nach Filter (`All`, `Active` oder `Completed`) die passenden To-dos liefert.

\scriptsize

```{.java .cb-nb line_numbers=false}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list, "All");
model.getFilteredToDos();
```

```{.java .cb-nb line_numbers=false}
model = new Model(list, "Active");
model.getFilteredToDos();
```

```{.java .cb-nb line_numbers=false}
model.getFilteredToDos();
```

\normalsize

**Hinweis:** Nutze `getToDosCompleted`!


## Aufgabe

Füge der Klasse `Model` die Methode `showCountOfActiveToDoItems` hinzu, die die Anzahl der offenen Aufgaben als String zurückgibt (z.B. "2 items left").

\scriptsize

```{.java .cb-nb line_numbers=false}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list, "All");
model.showCountOfActiveToDoItems();
```

```{.java .cb-nb line_numbers=false}
model.add("Neue Aufgabe");
model.showCountOfActiveToDoItems();
```

\normalsize

## Aufgabe

Ergänze die Klasse `Model` um die Methode `getUIState`.
Die Methode gibt ein Objekt der Klasse `UIState` zurück. Diesen enthält den aktuellen Filter, die gefilterten To-dos und die Anzeige der Anzahl offener Aufgaben.

\scriptsize
```{.java .cb-nb line_numbers=false}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list, "Active");
model.getUIState();
```
\normalsize


**Hinweis:** Nutze `getFilteredToDos` und `showCountOfActiveToDoItems`!


## Aufgabe

Füge der Klasse `Model` die Methode `idToIndex` hinzu, die den Index eines To-dos mit einer bestimmten ID findet.
\scriptsize

```{.java .cb-nb line_numbers=false}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list, "All");
model.idToIndex(3);
```

```{.java .cb-nb line_numbers=false}
model.idToIndex(1);
```

\normalsize
Wenn kein ToDo mit dieser ID in der Liste ist, wird $-1$ zurückgegeben.

```{.java .cb-nb line_numbers=false}
model.idToIndex(2);
```

## Aufgabe

Füge der Klasse `Model` die Methode `getToDoItem` hinzu, die das To-do mit einer bestimmten ID zurückgibt.

\scriptsize

```{.java .cb-nb line_numbers=false}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list, "All");
model.getToDoItem(3);
```

```{.java .cb-nb line_numbers=false}
model.getToDoItem(1);
```

\normalsize

**Hinweis:** Nutze `idToIndex`!

## Aufgabe

Füge der Klasse `Model` die Methode `nextId` hinzu, die die ID für ein neues To-do berechnet.
Dafür wird die bisher größte ID bestimmt und $1$ mehr zurückgegeben.

\scriptsize

```{.java .cb-nb line_numbers=false}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list, "All");
model.nextId();
```

Wenn keine To-dos vorhanden sind, wird mit der ID $1$ angefangen.

```{.java .cb-nb line_numbers=false}
Model model = new Model();
model.nextId();
```

\normalsize

## Aufgabe

Füge der Klasse `Model` die Methode `add` hinzu, die ein neues To-do mit eindeutiger `id` und dem übergebenen Text hinzufügt.

\scriptsize

```{.java .cb-nb line_numbers=false}
List<ToDo> list = List.of(new ToDo(1, "Test1", false));
Model model = new Model(list, "All");
model.add("Einkaufen");
model.toDos;
```

```{.java .cb-nb line_numbers=false}
Model model = new Model();
model.add("Coden");
model.toDos;
```

\normalsize

**Hinweis:** Nutze `nextId`!

## Aufgabe

Füge der Klasse `Model` die Methode `delete` hinzu, die das To-do mit der entsprechenden ID entfernt.

\scriptsize

```{.java .cb-nb line_numbers=false}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list, "All");
model.delete(1);
model.toDos;
```

```{.java .cb-nb line_numbers=false}
model.delete(7);
model.toDos;
```

\normalsize

**Hinweis:** Nutze `idToIndex`!

## Aufgabe

Füge der Klasse `Model` die Methode `toggle` hinzu, die den Status eines To-dos umschaltet.

\scriptsize

```{.java .cb-nb line_numbers=false}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list, "All");
model.toggle(7);
model.getToDoItem(7);
```

```{.java .cb-nb line_numbers=false}
model.toggle(3);
model.getToDoItem(3);
```

\normalsize

**Hinweis:** Nutze `idToIndex` und die Methode `toggle` der Klasse `ToDo`!

## Aufgabe

Füge der Klasse `Model` die Methode `updateText` hinzu, die den Text eines To-dos ändert.

\scriptsize

```{.java .cb-nb line_numbers=false}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(3, "Test2", true), new ToDo(7, "Test3", false));
Model model = new Model(list, "All");
model.updateText(3, "Hausaufgaben machen");
model.getToDoItem(3);
```

```{.java .cb-nb line_numbers=false}
model.updateText(7, "Joggen");
model.getToDoItem(7);
```

\normalsize


## Aufgabe

Ergänze die Klasse `Model` um die Methode `setFilter`.
Die Methode setzt den aktuellen Filter für die Anzeige der To-dos auf den übergebenen Wert.

```{.java .cb-nb line_numbers=false}
var model = new Model();
model.setFilter("Completed");
model.selectedFilter;
```
```{.java .cb-nb line_numbers=false}
model.setFilter("Active");
model.selectedFilter;
```

## Aufgabe

Füge der Klasse `Model` die Methode `removeFinishedToDoItems` hinzu, die alle erledigten To-dos aus der Liste entfernt.

\scriptsize

```{.java .cb-nb line_numbers=false}
List<ToDo> list = List.of(new ToDo(1, "Test1", false), new ToDo(2, "Test2", true), new ToDo(3, "Test3", false));
Model model = new Model(list, "All");
model.removeFinishedToDoItems();
model.toDos;
```

\normalsize

**Hinweis**: Nutze `getToDosCompleted!`!





## Aufgabe
Schütze die Methoden `getToDosCompleted`, `idToIndex` und `nextId` durch einen Zugriff außerhalb der Klasse `Model`. Schütze auch die Liste der To-dos.

# Terminal-Controller


## Aufgabe

Erstelle eine Klasse `TerminalController`. Die Attribute der Klasse sind ein Objekt der Klasse `View` und ein Objekt der Klasse `Model`.

## Aufgabe

Ergänze die Klasse `TerminalController` um die Methode `runApp`.  
Die Methode zeigt das Hauptmenü an, verarbeitet Benutzereingaben und steuert den Ablauf der ToDo-App, bis der Benutzer das Programm beendet.

Implementiere zunächst nur die Optionen To-dos hinzuzufügen und das Programm zu beenden.

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
Fußball spielen
Enter a number between 1 and 5
5
```

**Hinweis:** Nutze die Methoden `showMainMenuAskForOption`, `askForNewToDo` der Klasse `View` und die Methoden
`getFilteredToDos`, `showCountOfActiveToDoItems` und `add` der Klasse `Model`.

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

Ergänze die Klasse `TerminalController` um die Methode `updateItem`.  
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


**Hinweis**: Nutze die Methoden `toggle`, `delete`, `updateText` und `getFilteredToDos` der Klasse `Model` und die Methoden `askUntilInputIsId`
`askForUpdateOperation` und `askForNewText` der Klasse `View`!

## Aufgabe

Erweitere `runApp` um die Möglichkeit alle erledigten To-dos zu löschen.

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

Definiere so viele Methoden wie möglich als statische Methoden.

## Aufgabe

Teste dein Programm

## Aufgabe
Hilf den anderen SuS!
