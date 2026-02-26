---
title: KA1 INF TGI12
codebraid:
  jupyter: true
---

```{.java .cb-run}
import static java.lang.IO.println;
import java.util.ArrayList;
import java.util.List;

record   Node<T> (T content, Node<T> nextNode) {
    public Node(T content) {
        this(content, null);
    }
}


record ImmutableLinkedList<T>(Node<T> first) {

    public static <T> ImmutableLinkedList<T> of() {
        return new ImmutableLinkedList<>(null);
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

    public int size() {
        var current = first;
        int size = 0;
        while (current != null) {
            current = current.nextNode();
            size++;
        }
        return size;
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



}


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

public record V2(int x, int y) {
    public V2 plus(V2 other) {
        return new V2(x + other.x, y + other.y);
    }
}

public record StringWithLocation(String string, V2 location) {
}

public record BasicGameObject(V2 pos, String displayString) {

    public  List<StringWithLocation> show(){
        var lines = displayString.lines().toList();
        var acc = new ArrayList<StringWithLocation>();
        for (int rowIndex = 0; rowIndex < lines.size(); rowIndex++) {
            acc.add(new StringWithLocation(lines.get(rowIndex), pos.plus(new V2(0, rowIndex))));
        }
        return acc;
    }

}

record Alien(V2 pos, String displayString){}


class AlienSwarm {

    static String rowToAlienStrings(int i){
        return switch (i){
            case 2 -> "{@@}\n/\"\"\\" ;
            case 1 -> "/MM\\\n\\~~/";
            case 0 -> "{OO}\n/VV\\";
            default -> throw new IllegalStateException("Unexpected value: " + i);
        };

    }

    public static  List<Alien>createAliens (){
        var res = new ArrayList<Alien>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 10; col++) {
                var x = 36 - col* 4;
                var y = 8 - row * 4;
                var pos = new V2(x, y);
                res.add(new Alien(pos, rowToAlienStrings(row)));
            }
        }return res;
    }
}


```

# Aufgabe (1)

Erstelle ein Record `Node`. Diese Klasse `Node` hat zwei Eigenschaften:

- `content` vom Typ `String`
- `nextNode` vom Typ `Node`

```{.java .cb-nb line_numbers=false}
new Node("hallo", null);
```

```{.java .cb-nb line_numbers=false}
new Node("hallo", new Node("welt", null));
```

**Hinweis:** Es ist wichtig hier `record` zu verwenden. Für `class` gibt es bei dieser und der nächsten Aufgabe keine Punkte.

# Aufgabe (2)

Ergänze im Record `Node`  einen sekundären Konstruktor, der nur `content` entgegennimmt und `nextNode` auf `null` setzt.

```{.java .cb-nb line_numbers=false}
new Node("hallo");
```

```{.java .cb-nb line_numbers=false}
new Node("eins");
```


# Aufgabe (1)

Definiere die Klasse `ImmutableLinkedList`  als `record`. Die einzige Eigenschaft ist:

- `first` vom Typ `Node` 

Diese Klasse repräsentiert eine unveränderliche einfach verkettete Liste von `Strings`



```{.java .cb-nb line_numbers=false}
new ImmutableLinkedList(null);
```
```{.java .cb-nb line_numbers=false}
new ImmutableLinkedList(new Node("a"));
```




# Aufgabe (2)

Ergänze in `ImmutableLinkedList` eine statische Methode `of` ohne Parameter. Diese Methode gibt eine leere verkettete Liste zurück.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of();
```




# Aufgabe (3)

Ergänze in `ImmutableLinkedList` eine statische Methode `of(String e1, String e2, String e3)`, die eine Liste mit drei Elementen erstellt.

\tiny
```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("1", "2", "3");
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a", "b", "c");
```
\normalsize



<!-- 

# Aufgabe

Implementiere die Methode `isEmpty()` in `ImmutableLinkedList`, die `true` zurückgibt, wenn die Liste leer ist.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of().isEmpty();
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a", "b", "c").isEmpty();
```

# Aufgabe

Implementiere die Methode `getFirst()` in `ImmutableLinkedList`, die das erste Element zurückgibt. Bei leerer Liste solle eine `IndexOutOfBoundException` geworfen werden.

```{.java .cb-nb line_numbers=false}
new ImmutableLinkedList(new Node("1")).getFirst();
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a", "b", "c").getFirst();
``` -->



# Aufgabe (4)

Implementiere die Methode `size()` in `ImmutableLinkedList`, die die Anzahl der Elemente zurückgibt.

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a","b","c").size();
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of().size();
```




# Aufgabe (7)

Implementiere die Methode `getNode(int index)` in `ImmutableLinkedList`, die die `Node` an der gegebenen Position zurückgibt (Index 0 = erstes Element). 

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a","b","c").getNode(1);
```
```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a","b","c").getNode(2);
```

```{.java .cb-nb line_numbers=false}
ImmutableLinkedList.of("a","b","c").getNode(0);
```

Bei ungültigem Index solle eine `IndexOutOfBoundException` geworfen werden. Z.B. bei
```java
ImmutableLinkedList.of().getNode(0);
ImmutableLinkedList.of("a","b","c")..getNode(3);
ImmutableLinkedList.of("a","b","c")..getNode(-1);
```


```java
throw new IndexOutOfBoundException();
```





# Aufgabe (2)

Definiere die Klasse `MutableNode` mit dem Schlüsselwort `class`. 

Die Klasse besitzt zwei Attribute:

- `content` vom Typ `String`
- `nextNode` vom Typ `MutableNode`



# Aufgabe (2)

Implementiere einen Konstruktor, der Werte für `content` und `nextNode` entgegennimmt und die Eigenschaften auf diese Werte setzt.



```{.java .cb-nb line_numbers=false}
var secondNode = new MutableNode("b", null);
var firstNode = new MutableNode("a", secondNode);
println(firstNode.content);
println(firstNode.nextNode.content);
```



# Aufgabe (2)

Implementiere einen Konstruktor, der nur einen Wert für `content` entgegennimmt und `nextNode` auf `null` setzt.



```{.java .cb-nb line_numbers=false}
var myNode = new MutableNode("a");
println(myNode.content);
println(myNode.nextNode);
```



# Aufgabe (1)

Implementiere die Methode `content()`, die den Inhalt des Knotens zurückgibt.

```{.java .cb-nb line_numbers=false}
var myNode = new MutableNode("a");
println(myNode.content());
```




# Aufgabe (2)

Implementiere die Methode `setContent(String newContent)`, die den Inhalt des Knotens durch den übergebenen Wert ersetzt.

```{.java .cb-nb line_numbers=false}
var myNode = new MutableNode("a");
myNode.setContent("b");
println(myNode.content());
```



# Aufgabe (1)

Implementiere die Methode `nextNode`, die den nächsten Knoten zurückgibt.

```{.java .cb-nb line_numbers=false}
var myNode = new MutableNode("a", new MutableNode("b", null));
var mySecondNode = myNode.nextNode();
println(mySecondNode.content());
```



# Aufgabe (2)

Implementiere die Methode `setNextNode(MutableNode nextNode)`, die den nächsten Knoten  auf den übergebenen Wert setzt.

```{.java .cb-nb line_numbers=false}
var firstNode = new MutableNode("a", null);
var secondNode = new MutableNode("b", null);
firstNode.setNextNode(secondNode);
println(firstNode.nextNode().content());
```


# Aufgabe (1)

Erstelle ein record `V2`. Die Eigenschaften sind

- `x` vom Typ `int`
- `y` vom Typ `int`

```{.java .cb-nb line_numbers=false}
new V2(3, 5)
```
```{.java .cb-nb line_numbers=false}
new V2(-2, 1)
```


# Aufgabe (1)

Erstelle ein record `StringWithLocation`.
Die Eigenschaften sind

- `string ` mit dem Typ `String`
-  `location` mit dem Typ V2

```{.java .cb-nb line_numbers=false}
new StringWithLocation("Hi", new V2(3, 4));
```
```{.java .cb-nb line_numbers=false}
new StringWithLocation("bye", new V2(5, 7));
```


# Aufgabe (1)

Erstelle ein `record` `BasicGameObject`! 
Die Eigenschaften sind

- `pos` mit dem Typ `V2`
-  `displayString` mit dem Typ `String`

```{.java .cb-nb line_numbers=false}
new BasicGameObject(new V2(3, 4), "xy");
```

```{.java .cb-nb line_numbers=false}
new BasicGameObject(new V2(10, 5), "abc\ndef");
```




# Hinweis

Du kannst die Methoden `lines` und `toList` nutzen, um einen String an den Zeilenumbrüchen (`\n`) aufzuteilen.

```{.java .cb-nb line_numbers=false}
"abc\ndef\nghi".lines().toList()
```

# Aufgabe (7)

Erweitere `BasicGameObject` um eine Methode `show`{.java}.  
Sie gibt eine `List<StringWithLocation>` zurück.  
Jede Zeile des `displayString` wird in ein `StringWithLocation` umgewandelt.  
Die erste Zeile beginnt an der Position des Objekts.  Jede weitere Zeile ist eine Zeile weiter unten.

\tiny

```{.java .cb-nb line_numbers=false}
var bgo1 = new BasicGameObject(new V2(3, 4), "xy");
bgo1.show();
```


```{.java .cb-nb line_numbers=false}
var bgo2 = new BasicGameObject(new V2(10, 5), "abc\ndef");
bgo2.show();
```

\normalsize 



<!-- ## Aufgabe 

Erstelle ein record `Alien`. Die Eigenschaften sind

- `pos` vom Typ `V2`
- `displayString` vom Typ `String`

```{.java .cb-nb line_numbers=false}
new Alien(new V2(3, 5), "{@@}\n/\"\"\\")
```
```{.java .cb-nb line_numbers=false}
new Alien(new V2(-2, 1), "/MM\\\n\\~~/")
```
```{.java .cb-nb line_numbers=false}
new Alien(new V2(-2, 1),"{OO}\n/VV\\")
```


## Aufgabe

Implementiere die statische Methode `rowToAlienStrings`. Dieser wird eine ganze Zahl zwischen $0$ und $2$ übergeben. Sie gibt einen `String` zurück, der das Alien in dieser Reihe darstellt.

```{.java .cb-nb line_numbers=false}
AlienSwarm.rowToAlienStrings(0);
```

```{.java .cb-nb line_numbers=false}
AlienSwarm.rowToAlienStrings(1);
```

```{.java .cb-nb line_numbers=false}
AlienSwarm.rowToAlienStrings(2);
```

## Aufgabe

Implementiere die statische Methode `createAliens`. Diese hat keine Parameter. Sie gibt immer die gleiche  `List<Alien>` zurück.

\tiny
```{.java .cb-nb line_numbers=false}
AlienSwarm.createAliens();
```
\normalsize


\tiny
```{.java .cb-nb line_numbers=false}
AlienSwarm.createAliens().size();
```
\normalsize -->
