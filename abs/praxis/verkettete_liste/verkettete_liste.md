---
title: Verkettete Liste
codebraid:
  jupyter: true
---

```{.java .cb-run}
import java.util.*;
import java.util.Iterator;
import static java.lang.IO.println;


record Node<T>(T content, Node<T> nextNode) {
    public Node(T content) {
        this(content, null);
    }
}


class LinkedListIterator<T> implements Iterator<T>  {
    public Node<T> current;

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
record ImmutableLinkedList<T>(Node<T> first) implements Iterable<T> {



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
        return reversed().reversed();
    }

    public ImmutableLinkedList<T>  plus(T other) {
        var r = reversed();
        var reversedRes = new Node<>(other,r.first);
        return new ImmutableLinkedList<>(reversedRes).reversed();

    }


    public ImmutableLinkedList <T> plus(ImmutableLinkedList <T> other) {
        var r = reversed().first;
        var curr = other.first;
        while (curr != null) {
            r = new Node<>(curr.content(), r);
            curr = curr.nextNode();
        }
        return new ImmutableLinkedList<>(r).reversed();
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

In den folgenden Beispielen verwenden wir immer wieder dieselben Listen:

- `empty = []`
- `single = ["a"]`
- `xs = ["1", "2"]`
- `ys = ["x", "y", "z"]`


# Aufgabe

Erstelle ein `record` `Node`. Dieses `record` hat zwei Eigenschaften:

- `content` vom Typ `String`
- `nextNode` vom Typ `Node`

```{.java .cb-nb line_numbers=false}
new Node("a", null);
```

```{.java .cb-nb line_numbers=false}
new Node("1", new Node("2", null));
```

# Aufgabe

Ergänze im `record` `Node` einen sekundären Konstruktor, der nur `content` entgegennimmt und `nextNode` auf `null` setzt.

```{.java .cb-nb line_numbers=false}
new Node("a");
```

```{.java .cb-nb line_numbers=false}
new Node("a");
```


# Aufgabe

Definiere die Klasse `ImmutableLinkedList` als `record`. Die einzige Eigenschaft ist:

- `first` vom Typ `Node`

Diese Klasse repräsentiert eine unveränderliche, einfach verkettete Liste von `Strings`.



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
ImmutableLinkedList.of("1", "2");
```

# Aufgabe

Ergänze in `ImmutableLinkedList` eine statische Methode `of(String e1, String e2, String e3)`, die eine Liste mit drei Elementen erstellt.

\tiny
```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("x", "y", "z");
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("x", "y", "z");
```
\normalsize


# Aufgabe

Implementiere die Methode `isEmpty()` in `ImmutableLinkedList`, die `true` zurückgibt, wenn die Liste leer ist.

```{.java .cb-nb line_numbers=false}
var empty = ImmutableLinkedList.of();
empty.isEmpty();
```

```{.java .cb-nb line_numbers=false}
var single = ImmutableLinkedList.of("a");
single.isEmpty();
```

# Aufgabe

Implementiere die Methode `getFirst()` in `ImmutableLinkedList`, die das erste Element zurückgibt. Bei leerer Liste soll eine `IndexOutOfBoundsException` geworfen werden.

```{.java .cb-nb line_numbers=false}
var single = ImmutableLinkedList.of("a");
single.getFirst();
```

```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("1", "2");
xs.getFirst();
```




# Aufgabe

Implementiere die Methode `printContents()` in `ImmutableLinkedList`, die alle Elemente auf der Konsole ausgibt.

```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("1", "2");
xs.printContents();
```

```{.java .cb-nb line_numbers=false}
var single = ImmutableLinkedList.of("a");
single.printContents();
```


# Aufgabe

Implementiere die Hilfsmethode `getLastNode()` in `ImmutableLinkedList`, die die letzte `Node` der Liste zurückgibt. Bei leerer Liste soll eine `NoSuchElementException` geworfen werden.

```{.java .cb-nb line_numbers=false}
var single = ImmutableLinkedList.of("a");
single.getLastNode();
```

```{.java .cb-nb line_numbers=false}
var ys = ImmutableLinkedList.of("x", "y", "z");
ys.getLastNode();
```


# Aufgabe

Implementiere die Methode `getLast()` in `ImmutableLinkedList`, die das letzte Element (`String`) der Liste zurückgibt.

```{.java .cb-nb line_numbers=false}
var ys = ImmutableLinkedList.of("x", "y", "z");
ys.getLast();
```

```{.java .cb-nb line_numbers=false}
var single = ImmutableLinkedList.of("a");
single.getLast();
```

Hinweis: Verwende `getLastNode()`.

# Aufgabe

Implementiere die Methode `size()` in `ImmutableLinkedList`, die die Anzahl der Elemente zurückgibt.

```{.java .cb-nb line_numbers=false}
var ys = ImmutableLinkedList.of("x", "y", "z");
ys.size();
```

```{.java .cb-nb line_numbers=false}
var empty = ImmutableLinkedList.of();
empty.size();
```

# Aufgabe

Implementiere die Methode `contains(String o)` in `ImmutableLinkedList`, die überprüft, ob ein Element in der Liste vorkommt.

```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("1", "2");
xs.contains("1");
```
```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("1", "2");
xs.contains("2");
```
```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("1", "2");
xs.contains("x");
```

Hinweis: Vergleiche mit `equals`.




<!-- 
# Aufgabe

Implementiere die Methode `show()` in `ImmutableLinkedList`, die die Liste als lesbare Zeichenkette darstellt, z. B. `[a, b, c]`.

```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("1", "2");
xs.show();
```

```{.java .cb-nb line_numbers=false}
var empty = ImmutableLinkedList.of();
empty.show();
```
 -->



# Aufgabe

Implementiere die Methode `getNode(int index)` in `ImmutableLinkedList`, die die `Node` an der gegebenen Position zurückgibt (Index 0 = erstes Element). 

```{.java .cb-nb line_numbers=false}
var ys = ImmutableLinkedList.of("x", "y", "z");
ys.getNode(1);
```
```{.java .cb-nb line_numbers=false}
var ys = ImmutableLinkedList.of("x", "y", "z");
ys.getNode(2);
```

```{.java .cb-nb line_numbers=false}
var single = ImmutableLinkedList.of("a");
single.getNode(0);
```

Bei ungültigem Index soll eine `IndexOutOfBoundsException` geworfen werden, z. B. bei
```java
var empty = ImmutableLinkedList.of();
empty.getNode(0);

var single = ImmutableLinkedList.of("a");
single.getNode(1);
single.getNode(-1);
```






# Aufgabe

Implementiere die Methode `get(int index)` in `ImmutableLinkedList`, die das Element am Index zurückgibt. Nutze `getNode`.

```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("1", "2");
xs.get(0);
```

```{.java .cb-nb line_numbers=false}
var ys = ImmutableLinkedList.of("x", "y", "z");
ys.get(2);
```







# Aufgabe

Implementiere die Methode `indexOf(String o)` in `ImmutableLinkedList`, die den Index des ersten Vorkommens zurückgibt oder `-1`, falls nicht vorhanden. Nutze **nicht** die Methoden `get` und `getNode`!

```{.java .cb-nb line_numbers=false}
var ys = ImmutableLinkedList.of("x", "y", "z");
ys.indexOf("z");
```

```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("1", "2");
xs.indexOf("z");
```

# Aufgabe

Implementiere die Methode `lastIndexOf(String o)` in `ImmutableLinkedList`, die den Index des letzten Vorkommens zurückgibt oder `-1`.
Nutze **nicht** die Methoden `get` und `getNode` nicht!

```{.java .cb-nb line_numbers=false}
var ys = ImmutableLinkedList.of("x", "y", "z");
ys.lastIndexOf("x");
```

```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("1", "2");
xs.lastIndexOf("2");
```



# Aufgabe
Implementiere die Methode `toArrayList`, die alle Inhalte der verketteten Liste in einer `ArrayList` in gleicher Reihenfolge zurückgibt. Nutze die Methoden `get` und `getNode` nicht!


```{.java .cb-nb line_numbers=false}
var empty = ImmutableLinkedList.of();
empty.toArrayList(); 
```

```{.java .cb-nb line_numbers=false}
var ys = ImmutableLinkedList.of("x", "y", "z");
ys.toArrayList();
```
\huge

**Hinweis:** Nutze die Methode  `toArrayList` **nicht** in den folgenden Methoden!

\normalsize

# Aufgabe

Implementiere die Methode `subList(int fromIndex, int toIndex)` in `ImmutableLinkedList`, die eine `List<String>` mit den Elementen im Bereich `[fromIndex, toIndex]` (inklusive) zurückgibt. Bei ungültigen Indizes soll eine `IndexOutOfBoundsException` geworfen werden. Nutze maximal einmal `getNode`!

```{.java .cb-nb line_numbers=false}
var ys = ImmutableLinkedList.of("x", "y", "z");
ys.subList(1, 2);
```

```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("1", "2");
xs.subList(0, 1);
```

**Hinweise:** Baue eine `ArrayList<String>` und füge passende Elemente hinzu. 


**Hinweis:** Nutze die Methode  `subList` **nicht** in den folgenden Methoden!





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
var it = new LinkedListIterator(new Node("1", new Node("2")));
it.current;
```
```{.java .cb-nb line_numbers=false}
var it = new LinkedListIterator(null);
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
var it = new LinkedListIterator(new Node("1", new Node("2")));
println(it.next());
println(it.current);
```

```{.java .cb-nb line_numbers=false}
var it = new LinkedListIterator(
    new Node("x", new Node("y", new Node("z")))
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
var ys = ImmutableLinkedList.of("x", "y", "z");
var it = ys.iterator();
it.current
```


# Aufgabe

Setze die Methoden `getNode` und `getLastNode` der Klasse `ImmutableLinkedList` auf `private`.
Setze außerdem die Eigenschaft `current` der Klasse `LinkedListIterator` auf `private`.


# Aufgabe

Definiere die Klasse `Utils`.

Diese Klasse enthält Hilfsmethoden zum Arbeiten mit verketteten Listen.

# Aufgabe

Implementiere die statische Methode `printLinkedList`.

Die Methode erhält eine verkettete Liste und gibt alle Elemente  
auf der Konsole aus. Die einzige Methode der Klasse `ImmutableLinkedList`, die dabei genutzt werden darf, ist `iterator`!

```{.java .cb-nb line_numbers=false}
var ys = ImmutableLinkedList.of("x", "y", "z");
Utils.printLinkedList(ys);
```










# Aufgabe
Implementiere die statische Methode `fromList`, die aus einer beliebigen `List` eine `ImmutableLinkedList` erzeugt.
Die Reihenfolge der Elemente muss erhalten bleiben. 

\tiny
```{.java .cb-nb line_numbers=false}
var empty = List.of();
ImmutableLinkedList.fromList(empty);
```

```{.java .cb-nb line_numbers=false}
var ys = List.of("x", "y", "z");
ImmutableLinkedList.fromList(ys);
```
\normalsize



\huge

Verwende in den folgen Methoden nicht die Methode `get`!

\normalsize

# Aufgabe

Ergänze die Methode `reversed` in `ImmutableLinkedList`. Gib eine neue Liste mit umgekehrter Reihenfolge zurück, ohne das Original zu verändern.

\tiny
```{.java .cb-nb line_numbers=false}
var ys = ImmutableLinkedList.of("x", "y", "z");
ys.reversed();
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
var ys = ImmutableLinkedList.of("x", "y", "z");
ys.copy();
```
\normalsize




# Aufgabe

Ergänze die Methode `plus(String other)` in `ImmutableLinkedList`. Diese erzeugt eine neue Liste, die zusätzlich das Element `other` enthält.

\tiny
```{.java .cb-nb line_numbers=false}
var ys = ImmutableLinkedList.of("x", "y", "z");
ys.plus("a");
```
\normalsize




# Aufgabe

Ergänze die Methode `plus(ImmutableLinkedList<T> other)` in `ImmutableLinkedList`. Diese erzeugt eine neue Liste, die zusätzlich alle Elemente aus `other` enthält.

\tiny
```{.java .cb-nb line_numbers=false}
var xs = ImmutableLinkedList.of("1", "2");
var ys = ImmutableLinkedList.of("x", "y", "z");
xs.plus(ys);
```
\normalsize


# Aufgabe 

Passe die Klasse `ImmutableLinkedList` so an, dass nicht nur `Strings`, sondern Elemente mit jedem beliebigen Typ gespeichert werden können.
In einer Liste sollen immer noch alle Elemente denselben Typ haben.

```{.java .cb-nb line_numbers=false}
var single = ImmutableLinkedList.of("a");
var xs = ImmutableLinkedList.of(1, 2);
var ys = ImmutableLinkedList.of(false, true, false);
```

**Hinweis 1:** Es müssen auch Hilfsklassen angepasst werden.

**Hinweis 2:** Verwende Klassen mit Typparametern nie ohne Typparameter. Das ist zwar möglich, aber nicht sinnvoll.


