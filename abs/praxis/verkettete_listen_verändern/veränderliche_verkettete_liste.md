---
title: Verkettete Listen verändern
codebraid:
  jupyter: true
---

```{.java .cb-run}
import static java.lang.IO.println;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class MutableNode<T> {
    public T content;
    public MutableNode<T> nextNode;


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
}


public class MutableList<T>   {
    public MutableNode<T>  first;

    public MutableList(MutableNode<T>  first) {
        this.first = first;
    }

    public MutableList() {
        this.first = null;
    }


    public static <T> MutableList<T> of() {
        return new MutableList<>(null);
    }

    public static <T> MutableList<T> of(T e1) {
        return new MutableList<>(new MutableNode<>(e1));
    }

    public static <T> MutableList<T> of(T e1, T e2) {
        return new MutableList<>(new MutableNode<>(e1, new MutableNode<>(e2)));
    }

    public static <T> MutableList<T> of(T e1, T e2, T e3) {
        return new MutableList<>(new MutableNode<>(e1, new MutableNode<>(e2, new MutableNode<>(e3))));
    }








    public MutableNode<T> getLastNode() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        var current = first;
        while (current.nextNode() != null) {
            current = current.nextNode();
        }
        return current;
    }


    public MutableNode<T> getNode(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        var current = first;
        while (index > 0 && current.nextNode() != null  ) {
            index--;
            current = current.nextNode();
        }
        if (index == 0 && current != null) {
            return current;
        }
        throw new IndexOutOfBoundsException();
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



    public void clear(){
        first = null;
    }

    public T replace(int index, T newElem){
        var node = getNode(index);
        var result = node.content();
        node.setContent(newElem);
        return result;
    }


    public void addFirst(T newElem){
        first = new MutableNode<>(newElem,first);
    }


    public void add(T newElem){
        if (first == null) {
            first = new MutableNode<>(newElem);
        }
        else {
            var last = getLastNode();
            last.setNextNode(new MutableNode<>(newElem));
        }
    }

    public void add(int index, T newElem){
        if (index == 0) {
            addFirst(newElem);
            return;
        }
        var nodeBefore = getNode(index-1);
        var nodeAfter = nodeBefore.nextNode();
        nodeBefore.setNextNode(new MutableNode<>(newElem,nodeAfter));
    }

    public T removeFirst(){
        if (first == null) {
            throw new NoSuchElementException();
        }
        var result = first.content();
        first = first.nextNode();
        return result;
    }





    public T remove(int index){
        if (index == 0) {
            return removeFirst();
        }
        var nodeBefore = getNode(index-1);
        var nodeToDelete = nodeBefore.nextNode();
        if (nodeToDelete == null) {
            throw new IndexOutOfBoundsException();
        }
        nodeBefore.setNextNode(nodeToDelete.nextNode());
        return nodeToDelete.content();
    }



}


   


```

Definiere jede Klasse in einer eigenen Datei.

# Aufgabe

Definiere die Klasse `MutableNode` in einer eigenen Datei. Sie hat einen Typparameter `T`.

Die Klasse besitzt zwei Attribute:

- `content` vom Typ `T`
- `nextNode` vom Typ `MutableNode<T>`



# Aufgabe

Implementiere einen Konstruktor, der Werte für `content` und `nextNode` entgegennimmt und die Eigenschaften auf diese Werte setzt.



```{.java .cb-nb line_numbers=false}
var secondNode = new MutableNode<String>("b", null);
var firstNode = new MutableNode<String>("a", secondNode);
println(firstNode.content);
println(firstNode.nextNode.content);
```



# Aufgabe

Implementiere einen Konstruktor, der nur einen Wert für `content` entgegennimmt und `nextNode` auf `null` setzt.



```{.java .cb-nb line_numbers=false}
var myNode = new MutableNode<Integer>(1);
println(myNode.content);
println(myNode.nextNode);
```



# Aufgabe

Implementiere die Methode `content()`, die den Inhalt des Knotens zurückgibt.

```{.java .cb-nb line_numbers=false}
var myNode = new MutableNode<Integer>(1);
println(myNode.content());
```




# Aufgabe

Implementiere die Methode `setContent(T newContent)`, die den Inhalt des Knotens durch den übergebenen Wert ersetzt.

```{.java .cb-nb line_numbers=false}
var myNode = new MutableNode<Integer>(1);
myNode.setContent(3);
println(myNode.content());
```



# Aufgabe

Implementiere die Methode `nextNode()`, die den nächsten Knoten zurückgibt.

```{.java .cb-nb line_numbers=false}
var myNode = new MutableNode<String>("a", new MutableNode<>("b", null));
var mySecondNode = myNode.nextNode();
println(mySecondNode.content());
```



# Aufgabe

Implementiere die Methode `setNextNode(MutableNode<T> nextNode)`, die den nächsten Knoten auf den übergebenen Wert setzt.

```{.java .cb-nb line_numbers=false}
var firstNode = new MutableNode<String>("a", null);
var secondNode = new MutableNode<String>("b", null);
firstNode.setNextNode(secondNode);
println(firstNode.nextNode().content());
```

# Aufgabe

Setze die beiden Eigenschaften der Klasse `MutableNode` auf `private`.



# Aufgabe

Definiere die Klasse `MutableList` in einer eigenen Datei. Die Klasse hat einen Typparameter `T`.

Die Klasse besitzt ein Attribut:

- `first` vom Typ `MutableNode<T>`


<!-- # Aufgabe

Implementiere einen Konstruktor, der eine `MutableNode` als Start der Liste entgegennimmt.

```{.java .cb-nb line_numbers=false}
var firstNode = new MutableNode<>("a", null);
var xs = new MutableList(firstNode);
println(xs.first.content());
``` -->

# Aufgabe

Implementiere einen Konstruktor, der eine leere `MutableList` erzeugt.

```{.java .cb-nb line_numbers=false}
var emptyList = new MutableList<String>();
println(emptyList.first == null)
```


# Aufgabe

Setze die Eigenschaft `first` der Klasse `MutableList` auf `private`.




# Aufgabe

Kopiere aus dem ersten AB zu *verketteten Listen* die Methoden `of`, `getLastNode`, `getNode` und `toArrayList`!
Passe die Methoden so an, dass sie mit den Klassen `MutableList` und `MutableNode` funktionieren!



# Aufgabe

Implementiere die Methode `clear()`. Diese entfernt alle Elemente in der Liste.

```{.java .cb-nb line_numbers=false}
var list = MutableList.of("a", "b", "c");
list.clear();
println(list.toArrayList());
```



# Aufgabe

Implementiere die Methode `replace(int index, T newElem)`. Diese ersetzt das Element an der Stelle `index` durch das übergebene Element. 

```{.java .cb-nb line_numbers=false}
var list = MutableList.of("a", "b", "c");
list.replace(1, "x");
println(list.toArrayList());
```

Wenn es kein Element mit diesem Index gibt, wird eine `IndexOutOfBoundsException` geworfen!





**Hinweis:** Nutze `getNode` und `setContent`!

# Aufgabe 

Erweitere die Methode `replace` so, dass das ursprüngliche Element zurückgegeben wird!

```{.java .cb-nb line_numbers=false}
var list = MutableList.of("a", "b", "c");
println(list.replace(1, "x"));
println(list.toArrayList());
```
```{.java .cb-nb line_numbers=false}
println(list.replace(2, "y"));
println(list.toArrayList());
```

**Hinweis:** Nutze `content`!


# Aufgabe

Implementiere die Methode `addFirst(T newElem)`. Sie fügt das übergebene Element vorne in der Liste ein!

```{.java .cb-nb line_numbers=false}
var list = MutableList.of("a", "b", "c");
list.addFirst("x");
println(list.toArrayList());
```

```{.java .cb-nb line_numbers=false}
var list = MutableList.of();
list.addFirst("a");
println(list.toArrayList());
```





# Aufgabe

Implementiere die Methode `add(T newElem)`. Sie fügt das übergebene Element am Ende der Liste ein!

```{.java .cb-nb line_numbers=false}
var list = MutableList.of("a", "b", "c");
list.add("d");
println(list.toArrayList());
```

```{.java .cb-nb line_numbers=false}
var list = MutableList.of();
list.add("x");
println(list.toArrayList());
```

Wenn es kein Element mit diesem Index gibt, wird eine `IndexOutOfBoundsException` geworfen!

**Hinweis:** Nutze `getLastNode` und `setNextNode`!


# Aufgabe

Implementiere die Methode `add(int index, T newElem)`. Sie fügt das übergebene Element an der gewünschten Stelle in der Liste ein!

```{.java .cb-nb line_numbers=false}
var list = MutableList.of("a", "b", "c");
list.add(2, "x");
println(list.toArrayList());
```

```{.java .cb-nb line_numbers=false}
list.add(0, "y");
println(list.toArrayList());
```

```{.java .cb-nb line_numbers=false}
var xs = MutableList.of();
xs.add(0, "a");
println(xs.toArrayList());
```
```{.java .cb-nb line_numbers=false}
xs.add(1, "b");
println(xs.toArrayList());
```


Wenn es auch nach dem Einfügen kein Element mit diesem Index gibt, wird eine `IndexOutOfBoundsException` geworfen!

**Hinweis:** Nutze `getNode`, `nextNode` und `setNextNode`!


# Aufgabe

Implementiere die Methode `removeFirst`. Sie entfernt das erste Element der Liste.

```{.java .cb-nb line_numbers=false}
var list = MutableList.of("a", "b", "c");
list.removeFirst();
println(list.toArrayList());
```

**Hinweis:** Nutze `content` und `nextNode`!



# Aufgabe

Erweitere die Methode `removeFirst` so, dass das entfernte Element zurückgegeben wird.

```{.java .cb-nb line_numbers=false}
var list = MutableList.of("a", "b");
println(list.removeFirst());
```

**Hinweis:** Nutze `content`!


<!-- # Aufgabe

Implementiere die Methode `removeLast`. Sie entfernt das letzte Element der Liste.

```{.java .cb-nb line_numbers=false}
var list = MutableList.of("a", "b", "c");
list.removeLast();
println(list.toArrayList());

```

```{.java .cb-nb line_numbers=false}
var list = MutableList.of("a");
list.removeLast();
``` -->



# Aufgabe

Implementiere die Methode `remove(int index)`. Sie entfernt das Element mit dem übergebenen Index.

```{.java .cb-nb line_numbers=false}
var list = MutableList.of("a", "b", "c");
list.remove(1);
println(list.toArrayList());
```
```{.java .cb-nb line_numbers=false}
var list = MutableList.of("a", "b", "c");
list.remove(2);
println(list.toArrayList());
```

```{.java .cb-nb line_numbers=false}
var list = MutableList.of("a");
println(list.remove(0));
println(list.toArrayList());
```

**Hinweis:** Nutze `getNode`, `nextNode` und `setNextNode`!


# Aufgabe

Erweitere die Methode `remove` so, dass das entfernte Element zurückgegeben wird.

```{.java .cb-nb line_numbers=false}
var list = MutableList.of("a", "b", "c");
println(list.remove(1));
```

**Hinweis:** Nutze `content`!