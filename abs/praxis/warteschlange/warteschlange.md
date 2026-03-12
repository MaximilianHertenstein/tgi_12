---
title: SimpleQueue (Warteschlange)
codebraid:
  jupyter: true
---

```{.java .cb-run}
import static java.lang.IO.println;
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class MutableNode<T> {
    private T content;
    private MutableNode<T> nextNode;


    public MutableNode(T content, MutableNode<T> next) {
        this.content = content;
        this.nextNode = next;
    }

    public MutableNode(T content) {
        this.content = content;
        this.nextNode = null;
    }

    public T content() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public MutableNode<T> nextNode() {
        return nextNode;
    }

    public void setNextNode(MutableNode<T> nextNode) {
        this.nextNode = nextNode;
    }

    public MutableNode<T> copyAll() {
        MutableNode<T> newFirst = new MutableNode<>(content);
        var newCurrent = newFirst;
        var current = nextNode();
        while (current != null) {
            MutableNode<T> newNode = new MutableNode<>(current.content());
            newCurrent.setNextNode(newNode);
            newCurrent = newNode;
            current = current.nextNode();
        }
        return newFirst;
    }

}

public class SimpleQueue<T> {
    MutableNode<T> first;
    MutableNode<T> last;

    public ArrayList<T> toArrayList() {
        var result = new ArrayList<T>();
        var current = first;
        while (current != null) {
            result.add(current.content());
            current = current.nextNode();
        }
        return result;
    }

    public SimpleQueue() {
        first = null;
        last = null;
    }

    public SimpleQueue(MutableNode<T> first) {
        this.first = first;
        this.last = first;
    }

    public static <T> SimpleQueue<T> of() {
        return new SimpleQueue<>();
    }

    public void setLast(MutableNode<T> n) {
        last = n;
    }

    public void setFirst(MutableNode<T> n) {
        first = n;
    }

    public static <T> SimpleQueue<T> of(T e1) {
        var q = new SimpleQueue<T>();
        var n = new MutableNode<>(e1);
        q.setFirst(n);
        q.setLast(n);
        return q;
    }

    public static <T> SimpleQueue<T> of(T e1, T e2) {
        var last = new MutableNode<T>(e2);
        var first = new MutableNode<T>(e1, last);
        var q = new SimpleQueue<T>();
        q.setLast(last);
        q.setFirst(first);
        return q;
    }

    public static <T> SimpleQueue<T> of(T e1, T e2, T e3) {
        var last = new MutableNode<T>(e3);
        var first = new MutableNode<T>(e1, new MutableNode<>(e2, last));
        var q = new SimpleQueue<T>();
        q.setLast(last);
        q.setFirst(first);
        return q;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(T x) {
        var newNode = new MutableNode<T>(x);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
            return;
        }
        last.setNextNode(newNode);
        last = newNode;
    }

    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        var result = first.content();
        first = first.nextNode();
        if (first == null) {
            last = null;
        }
        return result;
    }
}

```

Definiere jede Klasse in einer eigenen Datei.

# Aufgabe

Definiere die Klasse `SimpleQueue` in einer eigenen Datei. Sie hat einen Typparameter `T`.

Die Klasse besitzt zwei Attribute:

- `first` vom Typ `MutableNode<T>` – zeigt auf den ersten Knoten (vorne in der Schlange)
- `last` vom Typ `MutableNode<T>` – zeigt auf den letzten Knoten (hinten in der Schlange)

Setze beide Attribute auf `public`.

# Aufgabe

Implementiere einen öffentlichen Konstruktor, der eine leere `SimpleQueue` erzeugt. Beide Attribute werden auf `null` gesetzt.

```{.java .cb-nb line_numbers=false}
var q = new SimpleQueue<String>();
println(q.toArrayList());
```




# Aufgabe

Kopiere die Methode `toArrayList()` aus der Klasse `MutableList` und füge sie in `SimpleQueue` ein. Passe sie so an, dass sie mit `SimpleQueue` funktioniert.
<!-- 
```{.java .cb-nb line_numbers=false}
var q = SimpleQueue.of("a", "b", "c");
println(q.toArrayList());
``` -->


# Aufgabe

Implementiere die Methode `isEmpty()`. Sie gibt `true` zurück, wenn die `SimpleQueue` leer ist, sonst `false`.

```{.java .cb-nb line_numbers=false}
var q = new SimpleQueue<String>();
println(q.isEmpty());
```


# Aufgabe

Implementiere die öffentliche Methode `setFirst(MutableNode<T> n)`. Sie setzt das Attribut `first` auf den übergebenen Knoten.

# Aufgabe

Implementiere die öffentliche Methode `setLast(MutableNode<T> n)`. Sie setzt das Attribut `last` auf den übergebenen Knoten.

# Aufgabe

Implementiere die statische Fabrikmethode `of()`, die eine leere `SimpleQueue` zurückgibt.

```{.java .cb-nb line_numbers=false}
var q = SimpleQueue.<String>of();
println(q.toArrayList());
println(q.last);

```

# Aufgabe

Implementiere die statische Fabrikmethode `of(T e1)`, die eine `SimpleQueue` mit genau einem Element zurückgibt.

```{.java .cb-nb line_numbers=false}
var q = SimpleQueue.of("a");
println(q.toArrayList());
println(q.last.content());
```

**Hinweis:** Bei einem einzigen Element zeigen `first` und `last` auf denselben Knoten.

# Aufgabe

Implementiere die statische Fabrikmethode `of(T e1, T e2)`, die eine `SimpleQueue` mit zwei Elementen zurückgibt. `e1` ist vorne, `e2` hinten.

```{.java .cb-nb line_numbers=false}
var q = SimpleQueue.of("a", "b");
println(q.toArrayList());
println(q.last.content());
```

**Hinweis:** Nutze `setFirst` und `setLast`!


# Aufgabe

Implementiere die statische Fabrikmethode `of(T e1, T e2, T e3)`, die eine `SimpleQueue` mit drei Elementen zurückgibt.

```{.java .cb-nb line_numbers=false}
var q = SimpleQueue.of("a", "b", "c");
println(q.toArrayList());
println(q.last.content());
```

**Hinweis:** Nutze `setFirst` und `setLast`!


# Aufgabe

Implementiere die Methode `enqueue(T x)`. Sie fügt das übergebene Element **hinten** in die `SimpleQueue` ein.

```{.java .cb-nb line_numbers=false}
var q = SimpleQueue.<String>of();
q.enqueue("a");
println(q.toArrayList());
println(q.last.content());
```
```{.java .cb-nb line_numbers=false}
q.enqueue("b");
println(q.toArrayList());
println(q.last.content());
```
```{.java .cb-nb line_numbers=false}
q.enqueue("c");
println(q.toArrayList());
println(q.last.content());

```

<!-- **Hinweis:** Erzeuge einen neuen Knoten. Wenn die `SimpleQueue` leer ist, zeigen `first` und `last` beide auf den neuen Knoten. Sonst wird der neue Knoten mit `setNextNode` an `last` angehängt, und `last` wird aktualisiert. -->

# Aufgabe

Implementiere die Methode `dequeue()`. Sie entfernt das **vorderste** Element der `SimpleQueue`.

```{.java .cb-nb line_numbers=false}
var q = SimpleQueue.of("a", "b", "c");
println(q.toArrayList());
println(q.last.content());
```
```{.java .cb-nb line_numbers=false}
q.dequeue();
println(q.toArrayList());
println(q.last.content());
```
```{.java .cb-nb line_numbers=false}
q.dequeue();
println(q.toArrayList());
println(q.last.content());
```
```{.java .cb-nb line_numbers=false}
q.dequeue();
println(q.toArrayList());
println(q.last);
```

Wenn die `SimpleQueue` leer ist, wird eine `NoSuchElementException` geworfen!

# Aufgabe

Erweitere `dequeue()` so, dass das entfernte Element zurückgegeben wird.

```{.java .cb-nb line_numbers=false}
var q = SimpleQueue.of("a", "b", "c");
println(q.dequeue());
println(q.dequeue());
println(q.dequeue());
println(q.isEmpty());
```

# Aufgabe

Setze die beiden Eigenschaften und die Methoden `setFirst` und `setLast` auf `private`!