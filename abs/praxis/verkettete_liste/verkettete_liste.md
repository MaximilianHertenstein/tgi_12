---
title: Verkettete Liste
codebraid:
  jupyter: true
---

```{.java .cb-run}
import java.util.*;
import java.util.Iterator;
import static java.lang.IO.println;


record   Node<T> (T content, Node<T> nextNode) {
    public Node(T content) {
        this(content, null);
    }
}


class LinkedListIterator<T> implements Iterator<T>  {
    Node<T> current;

    public LinkedListIterator(Node<T> current) {
        this.current = current;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        var result = current.content();
        current = current.nextNode();
        return result;
    }
}



```
```{.java .cb-run}
record ImmutableLinkedList<T>(Node<T> first) implements Iterable {



    public static <T> ImmutableLinkedList<T> of() {
        return new ImmutableLinkedList<>(null);
    }

        public static <T> ImmutableLinkedList<T> of(T e1) {
        return new ImmutableLinkedList<>(new Node<>(e1));
    }

        public static <T> ImmutableLinkedList<T> of(T e1, T e2) {
        return new ImmutableLinkedList<>(new Node<>(e1, new Node<>(e2)));
    }

    public static <T> ImmutableLinkedList<T> of(T e1, T e2, T e3) {
        return new ImmutableLinkedList<>(new Node<>(e1, new Node<>(e2, new Node<>(e3))));
    }

    public boolean isEmpty() {
        return first == null;
    }

    public T getFirst(){
        return first().content();
    }



        public  void printContents() {
        var current = first;
        while (current != null) {
            System.out.println(current.content());
            current = current.nextNode();
        }

    }

    public Node<T> getLastNode() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        var current = first;
        while (current.nextNode() != null) {
            current = current.nextNode();
        }
        return current;
    }

    public T getLast() {
        return getLastNode().content();
    }


    private Node<T> getSecondLastNode() {
        return getNode(size() - 2);
    }

    public int size() {

        var current = first;
        int size = 0;
        while (current != null) {
            current = current.nextNode();
            size++;

        }
        return size;
    }


    public boolean contains(T o) {
        var current = first;
        while (current != null) {
            if (current.content().equals(o)) {
                return true;
            }
            current = current.nextNode();
        }
        return false;
    }


    public String show() {
        if (first == null) {
            return "[]";
        }
        var current = first;
        var res = current.content().toString();
        while (current.nextNode() != null) {
            current = current.nextNode();
            res = res + ", " + current.content();
        }
        return "[" + res + "]";
    }


    public Node<T> getNode(int index) {
        if (index < 0) {
        throw new IndexOutOfBoundsException();
        }
        var current = first;
        while (index > 0  && current.nextNode() != null) {
            index--;
            current = current.nextNode();
        }
        if (index == 0 && current != null) {
            return current;
        }
        throw new IndexOutOfBoundsException();
    }

    public T get(int index) {
        return getNode(index).content();
    }

    
    public int indexOf(T o) {
        var index = 0;
        var current = first;
        while (current != null) {
            if (current.content().equals(o)) {
                return index;
            }
            current = current.nextNode();
            index++;
        }
        return -1;
    }

    
    public int lastIndexOf(T o) {
        var index = 0;
        var result = -1;
        var current = first;
        while (current != null) {
            if (current.content().equals(o)) {
                 result = index;
            }
            current = current.nextNode();
            index++;
        }
        return result;

    }

    public ArrayList<T> toArrayList() {
        var result = new ArrayList<T>();
        var current = first;
        while (current != null) {
            result.add(current.content());
            current = current.nextNode();
        }
        return result;
    }

        public List<T> subList(int fromIndex, int toIndex) {
        var result = new ArrayList<T>();
        var index = 0;
        var current = first;
        while (current != null && index <= toIndex) {
            if (index >= fromIndex) {
                result.add(current.content());


            }
            current = current.nextNode();
            index++;
        }
        return result;
    }

        public static <T>ImmutableLinkedList<T> fromList(List<T> xs) {
        Node<T> res = null;
        for (int i = xs.size()-1; i >= 0; i--) {
            res = new Node<>(xs.get(i), res);
        }
        return new ImmutableLinkedList<>(res);
    }


    public  ImmutableLinkedList<T> reversed() {
        Node<T> res = null;
        Node<T> curr = first;
        while (curr != null) {
            res = new Node<>(curr.content(), res);
            curr = curr.nextNode();

        }
        return new ImmutableLinkedList<>(res);
    }


    public  ImmutableLinkedList<T> copy() {
        return fromList(toArrayList());
    }

    public ImmutableLinkedList<T>  plus(T other) {
        var res = new Node<>(other);
        for (var elem: reversed().toArrayList()) {
            res = new Node<>(elem, res);
        }
        return new ImmutableLinkedList<>(res);
    }


    public ImmutableLinkedList <T> plus(ImmutableLinkedList <T> other) {
        var res = other.first;
        for (var elem: reversed().toArrayList()) {
            res = new Node<>(elem, res);
        }
        return new ImmutableLinkedList<>(res);
    }

    public LinkedListIterator<T> iterator() {
        return new LinkedListIterator<>(first);
    }
}




public class Utils {
    static void printLinkedList(ImmutableLinkedList<String> xs){
        var it = xs.iterator();
        while (it.hasNext()){
            println(it.next());
        }
    }
}

   


```

Definiere jede Klasse in einer eigenen Datei.


# Aufgabe

Erstelle ein Record `Node`. Diese Klasse `Node` hat zwei Eigenschaften:

- `content` vom Typ `String`
- `nextNode` vom Typ `Node`

```{.java .cb-nb line_numbers=false}
new Node("hallo", null);
```

```{.java .cb-nb line_numbers=false}
new Node("hallo", new Node("welt", null));
```

# Aufgabe

Ergänze im Record `Node`  einen sekundären Konstruktor, der nur `content` entgegennimmt und `nextNode` auf `null` setzt.

```{.java .cb-nb line_numbers=false}
new Node("hallo");
```

```{.java .cb-nb line_numbers=false}
new Node("eins");
```


# Aufgabe

Definiere die Klasse `ImmutableLinkedList`  als `record`. Die einzige Eigenschaft ist:

- `first` vom Typ `Node` 

Diese Klasse repräsentiert eine unveränderliche einfach verkettete Liste von `Strings`



```{.java .cb-nb line_numbers=false}
new ImmutableLinkedList(null);
```
```{.java .cb-nb line_numbers=false}
new ImmutableLinkedList(new Node("a"));
```


# Aufgabe

Ergänze in `ImmutableLinkedList` eine statische Methode `of` ohne Parameter. Diese Methode gibt eine leere verkettete Liste zurück.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of();
```

# Aufgabe

Ergänze in `ImmutableLinkedList` eine statische Methode `of(String e1)`, die eine Liste mit einem Element erstellt.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("1");
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a");
```


# Aufgabe

Ergänze in `ImmutableLinkedList` eine statische Methode `of(String e1, String e2)`, die eine Liste mit zwei Elementen erstellt.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("1", "2");
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a", "b");
```

# Aufgabe

Ergänze in `ImmutableLinkedList` eine statische Methode `of(String e1, String e2, String e3)`, die eine Liste mit drei Elementen erstellt.

\tiny
```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("1", "2", "3");
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a", "b", "c");
```
\normalsize


# Aufgabe

Implementiere die Methode `isEmpty()` in `ImmutableLinkedList`, die `true` zurückgibt, wenn die Liste leer ist.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of().isEmpty();
```

```{.java .cb-nb line_numbers=false}
new ImmutableLinkedList(new Node("a")).isEmpty();
```

# Aufgabe

Implementiere die Methode `getFirst()` in `ImmutableLinkedList`, die das erste Element zurückgibt. Bei leerer Liste soll eine `IndexOutOfBoundsException` geworfen werden.

```{.java .cb-nb line_numbers=false}
new ImmutableLinkedList(new Node("1")).getFirst();
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a", "b").getFirst();
```




# Aufgabe

Implementiere die Methode `printContents()` in `ImmutableLinkedList`, die alle Elemente an der Konsole ausgibt.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a", "b").printContents();
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a").printContents();
```


# Aufgabe

Implementiere die Hilfsmethode `getLastNode()` in `ImmutableLinkedList`, die die letzte `Node` der Liste zurückgibt. Bei leerer Liste soll eine `NoSuchElementException` geworfen werden.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a").getLastNode();
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a","b","c").getLastNode();
```


# Aufgabe

Implementiere die Methode `getLast()` in `ImmutableLinkedList`, die das letzte Element (`String`) der Liste zurückgibt.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a","b","c").getLast();
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a").getLast();
```

Hinweis: Verwende `getLastNode()`.

# Aufgabe

Implementiere die Methode `size()` in `ImmutableLinkedList`, die die Anzahl der Elemente zurückgibt.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a","b","c").size();
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of().size();
```

# Aufgabe

Implementiere die Methode `contains(String o)` in `ImmutableLinkedList`, die überprüft, ob ein Element in der Liste vorkommt.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a","b").contains("b");
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("x", "y", "z").contains("a");
```

Hinweis: Vergleiche mit `equals`.


# Aufgabe
Implementiere die Methode `toArrayList`, die alle Inhalte der verketteten Liste in einer `ArrayList` in gleicher Reihenfolge zurückgibt.


```{.java .cb-nb line_numbers=false}
var list = ImmutableLinkedList.of();
list.toArrayList(); 
```

```{.java .cb-nb line_numbers=false}
var list = ImmutableLinkedList.of("a", "b", "c");
list.toArrayList();
```


# Aufgabe

Implementiere die Methode `show()` in `ImmutableLinkedList`, die die Liste als lesbare Zeichenkette darstellt, z. B. `[a, b, c]`.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a","b").show();
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of().show();
```







# Aufgabe

Implementiere die Methode `getNode(int index)` in `ImmutableLinkedList`, die die `Node` an der gegebenen Position zurückgibt (Index 0 = erstes Element). 

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a","b","c").getNode(1);
```
```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a","b","c").getNode(2);
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a").getNode(0);
```

Bei ungültigem Index soll eine `IndexOutOfBoundsException` geworfen werden, z. B. bei
```java
ImmutableLinkedList.of().getNode(0);
ImmutableLinkedList.of("a").getNode(1);
ImmutableLinkedList.of("a").getNode(-1);
```




# Aufgabe

Implementiere die Methode `get(int index)` in `ImmutableLinkedList`, die das Element am Index zurückgibt. Nutze `getNode`.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a","b").get(0);
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a","b","c").get(2);
```

# Aufgabe

Implementiere die Methode `indexOf(String o)` in `ImmutableLinkedList`, die den Index des ersten Vorkommens zurückgibt oder `-1`, falls nicht vorhanden.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a", "b","c").indexOf("c");
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a","b").indexOf("c");
```

# Aufgabe

Implementiere die Methode `lastIndexOf(String o)` in `ImmutableLinkedList`, die den Index des letzten Vorkommens zurückgibt oder `-1`.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a", "b", "c").lastIndexOf("a");
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("b", "b").lastIndexOf("b");
```




# Aufgabe

Implementiere die Methode `subList(int fromIndex, int toIndex)` in `ImmutableLinkedList`, die eine `List<String>` mit den Elementen im Bereich `[fromIndex, toIndex]` (inklusive) zurückgibt. Bei ungültigen Indizes soll eine `IndexOutOfBoundsException` geworfen werden.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a" , "b", "c").subList(1,2);
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a", "b").subList(0,1);
```

Hinweis: Baue eine `ArrayList<String>` und füge passende Elemente hinzu.





# Aufgabe
Implementiere die statische Methode `fromList`, die aus einer beliebigen `List` eine `ImmutableLinkedList` erzeugt.
Die Reihenfolge der Elemente muss erhalten bleiben. 

\tiny
```{.java .cb-nb line_numbers=false}
var empty = List.of();
ImmutableLinkedList.fromList(empty);
```

```{.java .cb-nb line_numbers=false}
var ys = List.of("a", "b", "c", "d", "e");
ImmutableLinkedList.fromList(ys);
```
\normalsize


# Aufgabe

Ergänze die Methode `reversed` in `ImmutableLinkedList`. Gib eine neue Liste mit umgekehrter Reihenfolge zurück, ohne das Original zu verändern.

\tiny
```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("a", "b", "c");
xs.reversed();
```
```{.java .cb-nb line_numbers=false}
var empty = ImmutableLinkedList.of();
empty.reversed();
```
\normalsize




# Aufgabe

Ergänze die Methode `copy` in `ImmutableLinkedList`. Diese erzeugt eine Kopie der Liste und gibt diese zurück.

\tiny
```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("a", "b", "c");
xs.copy();
```
\normalsize




# Aufgabe

Ergänze die Methode `plus(String other)` in `ImmutableLinkedList`. Diese erzeugt eine neue Liste, die zusätzlich das Element `other` enthält.

\tiny
```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("a", "b", "c");
xs.plus("d")
```
\normalsize




# Aufgabe

Ergänze die Methode `plus(ImmutableLinkedList<T> other)` in `ImmutableLinkedList`. Diese erzeugt eine neue Liste, die zusätzlich alle Elemente aus `other` enthält.

\tiny
```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("a", "b", "c");
var ys = ImmutableLinkedList.of("d", "e");
xs.plus(ys)
```
\normalsize



# Aufgabe

Definiere mit dem Schlüsselwort `class` die Klasse `LinkedListIterator`.  
Diese Klasse dient dazu, über eine verkettete Liste zu iterieren.

Die Klasse besitzt genau eine Eigenschaft:

- `current` vom Typ `Node`

<!-- ```{.java .cb-nb line_numbers=false}
var lli = new LinkedListIterator();
```
 -->



# Aufgabe

Implementiere den Konstruktor von `LinkedListIterator`.  
Der Konstruktor erhält eine `Node`, mit der die Iteration beginnen soll.

```{.java .cb-nb line_numbers=false}
var it = new LinkedListIterator(new Node("a"));
it.current;
```
```{.java .cb-nb line_numbers=false}
var it = new LinkedListIterator(new Node("a", new Node("b")));
it.current;
```
```{.java .cb-nb line_numbers=false}
new LinkedListIterator(null);
it.current;
```


# Aufgabe

Implementiere die Methode `hasNext()` in `LinkedListIterator`.

Die Methode soll `true` zurückgeben, wenn noch ein weiteres Element vorhanden ist,  
ansonsten `false`.

```{.java .cb-nb line_numbers=false}
var it = new LinkedListIterator(new Node("a"));
it.hasNext();
```

```{.java .cb-nb line_numbers=false}
var it = new LinkedListIterator(null);
it.hasNext();
```



# Aufgabe

Implementiere die Methode `next()` in `LinkedListIterator`.

Die Methode soll:

- den aktuellen Inhalt zurückgeben
- den Iterator auf das nächste Element weiterschalten

```{.java .cb-nb line_numbers=false}
var it = new LinkedListIterator(new Node("a", new Node("b")));
println(it.next());
println(it.current)
```

```{.java .cb-nb line_numbers=false}
var it = new LinkedListIterator(
    new Node("a", new Node("b"))
);
println(it.next());
println(it.current);
println(it.next());
println(it.current);
```

# Aufgabe

Ergänze in `ImmutableLinkedList` die Methode `iterator()`.

Die Methode soll einen neuen `LinkedListIterator` zurückgeben,  
der mit dem ersten Element der Liste startet.


```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("a", "b", "c");
var it = xs.iterator();
it.current
```


# Aufgabe

Setze die Methoden `getNode` und `getLastNode` der Klasse `ImmutableLinkedList` auf privat.
Setze außerdem die Eigenschaft `current` der Klasse `LinkedListIterator` auf privat.


# Aufgabe

Definiere die Klasse `Utils`.

Diese Klasse enthält Hilfsmethoden zum Arbeiten mit verketteten Listen.

# Aufgabe

Implementiere die statische Methode `printLinkedList`.

Die Methode erhält eine verkettete Liste und gibt alle Elemente  
auf der Konsole aus. Die einzige Methode der Klasse `ImmutableLinkedList`, die dabei genutzt werden darf, ist `iterator`!

```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("a", "b", "c");
Utils.printLinkedList(xs);
```





# Aufgabe 

Passe die Klasse `ImmutableLinkedList` so an, dass nicht nur `Strings`, sondern Elemente mit jedem beliebigen Typ abgespeichert werden können.
In einer Liste sollen immer noch alle Elemente denselben Typ haben.

```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("a", "b", "c");
var ys = ImmutableLinkedList.of(1, 3);
var zs = ImmutableLinkedList.of(false, true);
```

**Hinweis 1:** Es müssen auch Hilfsklassen angepasst werden.

**Hinweis 2:** Verwende Klassen mit Typparametern nie ohne Typparameter. Das ist zwar möglich, aber nicht sinnvoll.