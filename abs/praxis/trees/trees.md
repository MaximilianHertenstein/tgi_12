---
title: Binäre Bäume
codebraid:
  jupyter: true
---
```{.java .cb-run}
import static java.lang.IO.println;
import java.util.ArrayList;
import java.util.List;

public record Node<T>(T content, Node<T> left, Node<T> right) {

    boolean isLeaf() {
        return left == null && right == null;
    }
}

public class Utils {


    public static int max(int a, int b){
        if (a < b) return b;
        return a;
    }


}


class Tree<T>{
    Node<T> root;

    public Tree() {
        this.root = null;
    }

    public Node<T> root(){
        return root;
    }

    public Tree(Node<T> root) {
        this.root = root;
    }

    // ===== LEVEL 1: VERY EASY =====
    // === IS EMPTY ===
    public boolean isEmpty(){
        return root == null;
    }

    // ===== LEVEL 2: EASY =====

    static int sum(Node<Integer> node){
        if (node == null){
            return 0;
        }
        return node.content() + Tree.sum(node.left()) + Tree.sum(node.right());
    }

    static int product(Node<Integer> node){
        if (node == null){
            return 1;
        }
        return node.content() * Tree.product(node.left()) * Tree.product(node.right());
    }


    public static <T> int sizeHelper(Node<T> node){
        if (node == null){
            return 0;
        }
        return 1 + sizeHelper(node.left()) + sizeHelper(node.right());
    }




    // === SIZE ===
    public int size(){
        return Tree.sizeHelper(root);
    }

    public static <T> int countLeavesHelper(Node<T> node){
        if (node == null){
            return 0;
        }
        if (node.isLeaf()){
            return 1;
        }
        return countLeavesHelper(node.left()) + countLeavesHelper(node.right());
    }

    // === COUNT LEAVES ===
    public int countLeaves(){
        return Tree.countLeavesHelper(root);
    }

    public static <T> int countInnerNodesHelper(Node<T> node){
        if (node == null){
            return 0;
        }
        if (node.isLeaf()){
            return 0;
        }
        return 1 + countInnerNodesHelper(node.left()) + countInnerNodesHelper(node.right());
    }

    // === COUNT INNER NODES ===
    public int countInnerNodes(){
        return Tree.countInnerNodesHelper(root);
    }

    // ===== LEVEL 3: MEDIUM =====
    public static <T> int heightHelper(Node<T> node){
        if (node == null){
            return 0;
        }
        if (node.isLeaf()){
            return 1;
        }
        return 1 + Math.max(heightHelper(node.left()), heightHelper(node.right()));
    }

    // === HEIGHT ===
    public int height(){
        return Tree.heightHelper(root);
    }

    public static <T> void printInOrderHelper(Node<T> node) {
        if (node == null) return;
        printInOrderHelper(node.left());
        println(node.content());
        printInOrderHelper(node.right());
    }

    // === PRINT IN ORDER ===
    public void printInOrder() {
        Tree.printInOrderHelper(root);
    }

    public static <T> void printPreOrderHelper(Node<T> node) {
        if (node == null) return;
        println(node.content());
        printPreOrderHelper(node.left());
        printPreOrderHelper(node.right());
    }

    // === PRINT PRE ORDER ===
    public void printPreOrder() {
        Tree.printPreOrderHelper(root);
    }

    public static <T> void printPostOrderHelper(Node<T> node) {
        if (node == null) return;
        printPostOrderHelper(node.left());
        printPostOrderHelper(node.right());
        println(node.content());
    }

    // === PRINT POST ORDER ===
    public void printPostOrder() {
        Tree.printPostOrderHelper(root);
    }

    // ===== LEVEL 4: MEDIUM-HARD =====
    public static <T> boolean containsHelper(Node<T> node, T key){
        if (node == null) {
            return false;
        }
        return node.content().equals(key) || containsHelper(node.left(), key) || containsHelper(node.right(), key);
    }

    // === CONTAINS ===
    public boolean contains(T key){
        return Tree.containsHelper(root, key);
    }

    // ===== LEVEL 5: HARD =====
    public static <T> boolean isFullHelper(Node<T> node){
        if (node == null){
            return true;
        }
        if (node.isLeaf()){
            return true;
        }
        if (node.left() == null || node.right() == null){
            return false;
        }
        return isFullHelper(node.left()) && isFullHelper(node.right());
    }

    // === IS FULL ===
    public boolean isFull(){
        return Tree.isFullHelper(root);
    }

    public static Node<Integer> doubleHelper(Node<Integer> node){
        if (node == null){
            return null;
        }
        return new Node<>(2 * node.content(), doubleHelper(node.left()), doubleHelper(node.right()));
    }

    public static <T> Node<T> invertHelper(Node<T> node){
        if (node == null){
            return null;
        }
        if (node.isLeaf()){
            return node;
        }
        return new Node<>(node.content(), invertHelper(node.right()), invertHelper(node.left()));
    }

    // === INVERT ===
    public Tree<T> invert(){
        return new Tree<>(Tree.invertHelper(root));
    }
}


```


# Der Record `Node<T>`

## Aufgabe

Definiere den Record `Node<T>` in einer eigenen Datei. Er hat einen Typparameter `T` und drei Komponenten:

- `content` vom Typ `T`
- `left` vom Typ `Node<T>`
- `right` vom Typ `Node<T>`
```{.java .cb-nb line_numbers=false}
var treeA = new Node<>(2,
    new Node<>(1, null, null),
    new Node<>(3, null, null));

var treeB = new Node<>(5,
    new Node<>(3, new Node<>(1, null, null), null),
    new Node<>(6, null, null));

var treeC = new Node<>(7, null, null);
```
\tiny
```{.java .cb-nb line_numbers=false}
treeA
```
```{.java .cb-nb line_numbers=false}
treeB
```
```{.java .cb-nb line_numbers=false}
treeC
```
\normalsize
```
//  treeA:       treeB:            treeC:
//     2              5                7
//    / \            / \
//   1   3          3   6
//                 /
//                1
```

Diese Bäume werden zum Testen von allen Methoden verwendet.

## Aufgabe

Ein Blatt ist ein Knoten, der weder einen linken noch einen rechten Teilbaum hat.
Implementiere die Methode `isLeaf()`. Sie gibt zurück, ob ein Knoten ein Blatt ist.
```{.java .cb-nb line_numbers=false}
println(treeA.isLeaf());  
```
```{.java .cb-nb line_numbers=false}
println(treeB.isLeaf());
```
```{.java .cb-nb line_numbers=false}
println(treeC.isLeaf());   
```




# Die Klasse `Tree`


## Aufgabe 

Definiere die Klasse `Tree` in einer eigenen Datei. 

## Aufgabe

Die **Summe** eines Teilbaums ist die Summe aller Knoteninhalte.

Implementiere die statische Hilfsmethode `sum(Node<Integer> node)` in der Klasse `Tree`. Sie gibt die Summe aller Knoteninhalte im Teilbaum ab `node` zurück.

```{.java .cb-nb line_numbers=false}
println(Tree.sum(null));
```
```{.java .cb-nb line_numbers=false}
println(Tree.sum(treeA));
```
```{.java .cb-nb line_numbers=false}
println(Tree.sum(treeB));
```
```{.java .cb-nb line_numbers=false}
println(Tree.sum(treeC));
```

## Aufgabe

Das **Produkt** eines Teilbaums ist das Produkt aller Knoteninhalte.

Implementiere die statische Hilfsmethode `product(Node<Integer> node)` in der Klasse `Tree`. Sie gibt das Produkt aller Knoteninhalte im Teilbaum ab `node` zurück.

```{.java .cb-nb line_numbers=false}
println(Tree.product(null));
```
```{.java .cb-nb line_numbers=false}
println(Tree.product(treeA));
```
```{.java .cb-nb line_numbers=false}
println(Tree.product(treeB));
```
```{.java .cb-nb line_numbers=false}
println(Tree.product(treeC));
```

## Aufgabe

Die **Größe** eines Teilbaums ist die Anzahl seiner Knoten.

Implementiere die statische Hilfsmethode `sizeHelper(Node<T> node)` in der Klasse `Tree`. Sie gibt die Anzahl aller Knoten im Teilbaum ab `node` zurück.

```{.java .cb-nb line_numbers=false}
println(Tree.sizeHelper(null));
```
```{.java .cb-nb line_numbers=false}
println(Tree.sizeHelper(treeA));
```
```{.java .cb-nb line_numbers=false}
println(Tree.sizeHelper(treeB));
```
```{.java .cb-nb line_numbers=false}
println(Tree.sizeHelper(treeC));
```


## Aufgabe

Ein Blatt ist ein Knoten, der weder einen linken noch einen rechten Teilbaum hat.
Implementiere die statische Hilfsmethode `countLeavesHelper(Node<T> node)` in der Klasse `Tree`. Sie gibt die Anzahl der Blätter im Teilbaum ab `node` zurück.

```{.java .cb-nb line_numbers=false}
println(Tree.countLeavesHelper(null));
```
```{.java .cb-nb line_numbers=false}
println(Tree.countLeavesHelper(treeA));
```
```{.java .cb-nb line_numbers=false}
println(Tree.countLeavesHelper(treeB));
```
```{.java .cb-nb line_numbers=false}
println(Tree.countLeavesHelper(treeC));
```

**Hinweis:** Nutze `isLeaf!`

## Aufgabe

Ein innerer Knoten ist ein Knoten, der **kein** Blatt ist.
Implementiere die statische Hilfsmethode `countInnerNodesHelper(Node<T> node)` in der Klasse `Tree`. Sie gibt die Anzahl der inneren Knoten im Teilbaum ab `node` zurück.

```{.java .cb-nb line_numbers=false}
println(Tree.countInnerNodesHelper(null));
```
```{.java .cb-nb line_numbers=false}
println(Tree.countInnerNodesHelper(treeA));
```
```{.java .cb-nb line_numbers=false}
println(Tree.countInnerNodesHelper(treeB));
```
```{.java .cb-nb line_numbers=false}
println(Tree.countInnerNodesHelper(treeC));
```

**Hinweis:** Nutze `isLeaf!`


## Aufgabe

Bei der **Inorder**-Traversierung wird zuerst der linke Teilbaum besucht, dann der Knoten selbst, dann der rechte Teilbaum.

Implementiere die statische Hilfsmethode `printInOrderHelper(Node<T> node)` in der Klasse `Tree`. Sie gibt alle Elemente des Teilbaums ab `node` in Inorder-Reihenfolge aus.

```{.java .cb-nb line_numbers=false}
Tree.printInOrderHelper(treeA);
```
```{.java .cb-nb line_numbers=false}
Tree.printInOrderHelper(treeB);
```
```{.java .cb-nb line_numbers=false}
Tree.printInOrderHelper(treeC);
```
```{.java .cb-nb line_numbers=false}
Tree.printInOrderHelper(null);
```

## Aufgabe

Bei der **Preorder**-Traversierung wird zuerst der Knoten selbst besucht, dann der linke, dann der rechte Teilbaum.

Implementiere die statische Hilfsmethode `printPreOrderHelper(Node<T> node)` in der Klasse `Tree`. Sie gibt alle Elemente des Teilbaums ab `node` in Preorder-Reihenfolge aus.

```{.java .cb-nb line_numbers=false}
Tree.printPreOrderHelper(treeA);
```
```{.java .cb-nb line_numbers=false}
Tree.printPreOrderHelper(treeB);
```
```{.java .cb-nb line_numbers=false}
Tree.printPreOrderHelper(treeC);
```
```{.java .cb-nb line_numbers=false}
Tree.printPreOrderHelper(null);
```

## Aufgabe

Bei der **Postorder**-Traversierung wird zuerst der linke, dann der rechte Teilbaum besucht und danach der Knoten selbst.

Implementiere die statische Hilfsmethode `printPostOrderHelper(Node<T> node)` in der Klasse `Tree`. Sie gibt alle Elemente des Teilbaums ab `node` in Postorder-Reihenfolge aus.

```{.java .cb-nb line_numbers=false}
Tree.printPostOrderHelper(treeA);
```
```{.java .cb-nb line_numbers=false}
Tree.printPostOrderHelper(treeB);
```
```{.java .cb-nb line_numbers=false}
Tree.printPostOrderHelper(treeC);
```
```{.java .cb-nb line_numbers=false}
Tree.printPostOrderHelper(null);
```

## Aufgabe

Implementiere die statische Hilfsmethode `containsHelper(Node<T> node, T key)` in der Klasse `Tree`. Sie gibt zurück, ob `key` im Teilbaum ab `node` vorkommt. Nutze `equals` für den Vergleich.

```{.java .cb-nb line_numbers=false}
println(Tree.containsHelper(treeA, 1));
```
```{.java .cb-nb line_numbers=false}
println(Tree.containsHelper(treeA, 9));
```
```{.java .cb-nb line_numbers=false}
println(Tree.containsHelper(treeB, 3));
```
```{.java .cb-nb line_numbers=false}
println(Tree.containsHelper(null, 7));
```

## Aufgabe

Ein Teilbaum heißt **voll**, wenn jeder innere Knoten genau zwei Kinder hat. Blätter und leere Teilbäume gelten als voll.

Implementiere die statische Hilfsmethode `isFullHelper(Node<T> node)` in der Klasse `Tree`. Sie gibt zurück, ob der Teilbaum ab `node` voll ist.

```{.java .cb-nb line_numbers=false}
println(Tree.isFullHelper(null));
```
```{.java .cb-nb line_numbers=false}
println(Tree.isFullHelper(treeA));
```
```{.java .cb-nb line_numbers=false}
println(Tree.isFullHelper(treeB));
```
```{.java .cb-nb line_numbers=false}
println(Tree.isFullHelper(treeC));
```


# Die Klasse `Utils`

## Aufgabe

Definiere die Klasse `Utils` in einer eigenen Datei.

## Aufgabe

Implementiere die statische Methode `max`. Der Methode werden zwei Integer übergeben. Sie gibt das Maximum der beiden Integer zurück.

```{.java .cb-nb line_numbers=false}
println(Utils.max(3,5));
```
```{.java .cb-nb line_numbers=false}
println(Utils.max(7,1));
```
```{.java .cb-nb line_numbers=false}
println(Utils.max(1,1));
```

# Fortsetzung der Klasse `Tree`


## Aufgabe

Die Höhe ist die Länge des längsten Pfades von der Wurzel bis zu einem Blatt. Implementiere die statische Hilfsmethode `heightHelper(Node<T> node)` in der Klasse `Tree`. Sie gibt die Höhe des Teilbaums ab `node` zurück.

```{.java .cb-nb line_numbers=false}
println(Tree.heightHelper(null));
```
```{.java .cb-nb line_numbers=false}
println(Tree.heightHelper(treeA));
```
```{.java .cb-nb line_numbers=false}
println(Tree.heightHelper(treeB));
```
```{.java .cb-nb line_numbers=false}
println(Tree.heightHelper(treeC));
```



## Aufgabe

Das **Verdoppeln** eines Teilbaums bedeutet, dass jeder Integer-Knoteninhalt mit `2` multipliziert wird. Implementiere die statische Hilfsmethode `doubleHelper(Node<Integer> node)` in der Klasse `Tree`. Sie gibt den neuen Teilbaum ab `node` mit verdoppelten Knoteninhalten zurück.

\tiny
```{.java .cb-nb line_numbers=false}
println(Tree.doubleHelper(treeA));
```
```{.java .cb-nb line_numbers=false}
println(Tree.doubleHelper(treeB));
```
```{.java .cb-nb line_numbers=false}
println(Tree.doubleHelper(treeC));
```
```{.java .cb-nb line_numbers=false}
println(Tree.doubleHelper(null));
```
\normalsize


## Aufgabe

Implementiere die statische Hilfsmethode `invertHelper(Node<T> node)` in der Klasse `Tree`. Sie gibt den gespiegelten Teilbaum ab `node` als neuen Knoten zurück.

\tiny

```{.java .cb-nb line_numbers=false}
println(Tree.invertHelper(treeA));
```
```{.java .cb-nb line_numbers=false}
println(Tree.invertHelper(treeB));
```
```{.java .cb-nb line_numbers=false}
println(Tree.invertHelper(treeC));
```
```{.java .cb-nb line_numbers=false}
println(Tree.invertHelper(null));
```

\normalsize

## Aufgabe

Ergänze bei der Klasse `Tree` den Typparameter `T` und ergänze das Attribut `root` mit dem Typen `Node<T>`.

## Aufgabe

Implementiere einen **öffentlichen** Konstruktor, der eine `Node<T>` als Wurzel entgegennimmt.

```{.java .cb-nb line_numbers=false}
var ta = new Tree(treeA);
var tb = new Tree(treeB);
var tc = new Tree(treeC);
```

**Hinweis:** Die Bäume `ta`, `tb` und `tc` werden in den nächsten Beispielen benötigt.


## Aufgabe

Implementiere die Methode `root()`. Sie gibt die Wurzel des Baums zurück.

\tiny
```{.java .cb-nb line_numbers=false}
println(ta.root());
```
```{.java .cb-nb line_numbers=false}
println(tb.root());
```
```{.java .cb-nb line_numbers=false}
println(tc.root());
```
\normalsize

## Aufgabe

Implementiere einen **öffentlichen** Konstruktor ohne Parameter, der einen leeren Baum (ohne Knoten) erzeugt.

```{.java .cb-nb line_numbers=false}
var te = new Tree();
println(te.root())
```




## Aufgabe

Implementiere die Methode `isEmpty()`. Sie gibt zurück, ob der Baum leer ist.

```{.java .cb-nb line_numbers=false}
println(te.isEmpty());
```
```{.java .cb-nb line_numbers=false}
println(ta.isEmpty());
```
```{.java .cb-nb line_numbers=false}
println(tb.isEmpty());
```
```{.java .cb-nb line_numbers=false}
println(tc.isEmpty());
```

## Aufgabe

Implementiere die Methode `size()` in der Klasse `Tree`.

```{.java .cb-nb line_numbers=false}
println(ta.size());
```
```{.java .cb-nb line_numbers=false}
println(tb.size());
```
```{.java .cb-nb line_numbers=false}
println(tc.size());
```
```{.java .cb-nb line_numbers=false}
println(te.size());
```

**Hinweis:** Nutze `Tree.sizeHelper`.


## Aufgabe

Implementiere die Methode `countLeaves()` in der Klasse `Tree`.

```{.java .cb-nb line_numbers=false}
println(ta.countLeaves());
```
```{.java .cb-nb line_numbers=false}
println(tb.countLeaves());
```
```{.java .cb-nb line_numbers=false}
println(tc.countLeaves());
```
```{.java .cb-nb line_numbers=false}
println(te.countLeaves());
```

**Hinweis:** Nutze `Tree.countLeavesHelper`.


## Aufgabe

Implementiere die Methode `countInnerNodes()` in der Klasse `Tree`.

```{.java .cb-nb line_numbers=false}
println(ta.countInnerNodes());
```
```{.java .cb-nb line_numbers=false}
println(tb.countInnerNodes());
```
```{.java .cb-nb line_numbers=false}
println(tc.countInnerNodes());
```
```{.java .cb-nb line_numbers=false}
println(te.countInnerNodes());
```

**Hinweis:** Nutze `Tree.countInnerNodesHelper`.


## Aufgabe

Implementiere die Methode `height()` in der Klasse `Tree`.

```{.java .cb-nb line_numbers=false}
println(ta.height());
```
```{.java .cb-nb line_numbers=false}
println(tb.height());
```
```{.java .cb-nb line_numbers=false}
println(tc.height());
```
```{.java .cb-nb line_numbers=false}
println(te.height());
```

**Hinweis:** Nutze `Tree.heightHelper`.


## Aufgabe

Bei der **Inorder**-Traversierung wird zuerst der linke Teilbaum besucht, dann der Knoten selbst, dann der rechte Teilbaum. Implementiere `printInOrder()` in der Klasse `Tree`.

```{.java .cb-nb line_numbers=false}
ta.printInOrder();
```
```{.java .cb-nb line_numbers=false}
tb.printInOrder();
```
```{.java .cb-nb line_numbers=false}
tc.printInOrder();
```
```{.java .cb-nb line_numbers=false}
te.printInOrder();
```

**Hinweis:** Nutze `Tree.printInOrderHelper`.

## Aufgabe

Bei der **Preorder**-Traversierung wird zuerst der Knoten selbst besucht, dann der linke, dann der rechte Teilbaum. Implementiere `printPreOrder()` in der Klasse `Tree`.

```{.java .cb-nb line_numbers=false}
ta.printPreOrder();
```
```{.java .cb-nb line_numbers=false}
tb.printPreOrder();
```
```{.java .cb-nb line_numbers=false}
tc.printPreOrder();
```
```{.java .cb-nb line_numbers=false}
te.printPreOrder();
```

**Hinweis:** Nutze `Tree.printPreOrderHelper`.

## Aufgabe

Bei der **Postorder**-Traversierung wird zuerst der linke, dann der rechte Teilbaum besucht, dann der Knoten selbst. Implementiere `printPostOrder()` in der Klasse `Tree`.

```{.java .cb-nb line_numbers=false}
ta.printPostOrder();
```
```{.java .cb-nb line_numbers=false}
tb.printPostOrder();
```
```{.java .cb-nb line_numbers=false}
tc.printPostOrder();
```
```{.java .cb-nb line_numbers=false}
te.printPostOrder();
```

**Hinweis:** Nutze `Tree.printPostOrderHelper`.

## Aufgabe

Implementiere `contains(T key)` in der Klasse `Tree`. Sie gibt zurück, ob der gesuchte Wert im Baum vorkommt.

```{.java .cb-nb line_numbers=false}
println(ta.contains(1));
```
```{.java .cb-nb line_numbers=false}
println(ta.contains(2));
```
```{.java .cb-nb line_numbers=false}
println(ta.contains(9));
```
```{.java .cb-nb line_numbers=false}
println(tb.contains(3));
```
```{.java .cb-nb line_numbers=false}
println(tb.contains(9));
```
```{.java .cb-nb line_numbers=false}
println(te.contains(7));
```

**Hinweis:** Nutze `Tree.containsHelper`.


## Aufgabe

Ein Baum heißt **voll**, wenn jeder innere Knoten genau zwei Kinder hat. Blätter und leere Bäume gelten als voll. Implementiere `isFull()` in der Klasse `Tree`.

```{.java .cb-nb line_numbers=false}
println(ta.isFull());
```
```{.java .cb-nb line_numbers=false}
println(tb.isFull());
```
```{.java .cb-nb line_numbers=false}
println(tc.isFull());
```
```{.java .cb-nb line_numbers=false}
println(te.isFull());
```

**Hinweis:** Nutze `Tree.isFullHelper`.

## Aufgabe

`invert` gibt einen **neuen** gespiegelten Baum zurück. Implementiere `invert()` in der Klasse `Tree`.

\tiny
```{.java .cb-nb line_numbers=false}
println(ta.invert().root());
```
```{.java .cb-nb line_numbers=false}
println(tb.invert().root());
```
```{.java .cb-nb line_numbers=false}
println(tc.invert().root());
```
```{.java .cb-nb line_numbers=false}
println(te.invert().root());
```
\normalsize

**Hinweis:** Nutze `Tree.invertHelper`.
