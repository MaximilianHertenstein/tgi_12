---
title: SimpleStack
codebraid:
  jupyter: true
---

```{.java .cb-run}
import java.util.EmptyStackException;
import static java.lang.IO.println;


record   Node<T> (T content, Node<T> nextNode) {
    public Node(T content) {
        this(content, null);
    }
}

public class SimpleStack<T> {
    public Node<T> first;

    public SimpleStack(Node<T> first) {
        this.first = first;
    }

    public SimpleStack() {
        this.first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public T getFirst() {
        if (first == null) {
            throw new EmptyStackException();
        }
        return first.content();
    }

    public void push(T x) {
        first = new Node<T>(x, first);
    }

    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        var result = first.content();
        first = first.nextNode();
        return result;
    }
}

```
# Aufgabe

Definiere die Klasse `SimpleStack` in einer eigenen Datei. Die Klasse hat einen Typparameter `T`.

Die Klasse besitzt ein Attribut:

- `first` vom Typ `Node<T>`




# Aufgabe

Implementiere einen Konstruktor, der einen `Node` als Start des `SimpleStack` entgegennimmt.

```{.java .cb-nb line_numbers=false}
var node = new Node<String>("a", null);
var stack = new SimpleStack<String>(node);
println(stack.first);
```

# Aufgabe

Implementiere einen Konstruktor, der einen leeren `SimpleStack` erzeugt.

```{.java .cb-nb line_numbers=false}
var stack = new SimpleStack<String>();
println(stack.first);
```



# Aufgabe

Implementiere die Methode `isEmpty()`.  
Sie gibt `true` zurück, wenn der `SimpleStack` leer ist, sonst `false`.

```{.java .cb-nb line_numbers=false}
var stack = new SimpleStack<Integer>();
println(stack.isEmpty());
```
```{.java .cb-nb line_numbers=false}
var node = new Node<String>("a", null);
var stack = new SimpleStack<String>(node);
println(stack.isEmpty());
```



# Aufgabe

Implementiere die Methode `getFirst()`.  
Sie gibt das erste Element des `SimpleStack` zurück.

```{.java .cb-nb line_numbers=false}
var node = new Node<String>("a", null);
var stack = new SimpleStack<String>(node);
println(stack.getFirst());
```

Wenn der SimpleStack leer ist, soll eine `EmptyStackException` geworfen werden!



# Aufgabe

Setze die Eigenschaft `first` der Klasse `SimpleStack` auf `private`.




# Aufgabe

Implementiere die Methode `push(T x)`.  
Sie fügt ein Element am Anfang des `SimpleStack` hinzu.

```{.java .cb-nb line_numbers=false}
var stack = new SimpleStack<String>();
stack.push("a");
println(stack.getFirst());
```
```{.java .cb-nb line_numbers=false}
stack.push("b");
println(stack.getFirst());
```

**Hinweis:** Nutze den Konstruktor von `Node`!




# Aufgabe

Implementiere die Methode `pop()`.  
Sie entfernt das erste Element vom `SimpleStack`.

```{.java .cb-nb line_numbers=false}
var stack = new SimpleStack<String>();
stack.push("a");
stack.push("b");
println(stack.first);
```
```{.java .cb-nb line_numbers=false}
stack.pop();
println(stack.first);
```
```{.java .cb-nb line_numbers=false}
stack.pop();
println(stack.first);
```

Wenn der SimpleStack leer ist, soll eine `EmptyStackException` geworfen werden!

**Hinweis:** Nutze `nextNode()`!


# Aufgabe

Erweitere die Methode `pop()`.  
Sie soll den Wert des entfernten Elements zurückgeben.

```{.java .cb-nb line_numbers=false}
var stack = new SimpleStack<String>();
stack.push("a");
stack.push("b");
println(stack.pop());
```
```{.java .cb-nb line_numbers=false}
println(stack.pop());
```

Wenn der SimpleStack leer ist, soll eine `EmptyStackException` geworfen werden!

**Hinweis:** Nutze `nextNode()`!


