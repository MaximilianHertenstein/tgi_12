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
Sie bekommt eine Liste von ToDos und einen String zur Anzeige der Anzahl aktiver ToDos.  
Die Methode gibt das Hauptmenü aus und fragt nach einer Option (1-5).
Sie fragt solange erneut nach bis eine der Zahlen  (1-3) eingegeben wurde.



```java
var view = new View();
view.showMainMenuAskForOption(List.of(new ToDo(1, "A", false), new ToDo(1, "A", true)), "1 open");
```
```
		todos
X A				 ID: 1
C A				 ID: 1
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
view.showMainMenuAskForOption(List.of(), "Nothing to do!");
```
```
		todos
Nothing to do!

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
