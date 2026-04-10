---
title: Warteschlange 
codebraid:
  jupyter: true
---
```{.java .cb-run}

import java.util.NoSuchElementException;


class MutableNode<T> {
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

}

```
```{.java .cb-run}

class SimpleQueue<T> {
    MutableNode<T> first;
    MutableNode<T> last;



    public SimpleQueue() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enQueue(T x) {
        var newNode = new MutableNode<T>(x);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
            return;
        }
        last.setNextNode(newNode);
        last = newNode;
    }

    public T deQueue() {
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

# Grundlagen

An eine Warteschlange können Elemente mit der Operation `enQueue` hinzugefügt werden.

```{.java .cb-nb line_numbers=false}
var queue = new SimpleQueue();
queue.enQueue("g")
```

Man kann sich vorstellen, dass die Elemente hinten an die Warteschlange angehängt werden.

```{.java .cb-nb line_numbers=false}
queue.enQueue("h")
```

**`enQueue`-Operation** – Ein neues Element wird hinten an die Warteschlange gehängt:

```{=latex}
\begin{center}
\begin{tikzpicture}[scale=0.8]
  % ---- Vorher ----
  \node[font=\bfseries] at (1.0, 1.8) {Vorher};
  % Box "g" (vorne = links)
  \draw[thick] (0,0) rectangle (2,1) node[midway] {"g"};
  % offene Enden der Schlange
  \draw[thick] (0,0) -- (-0.3,0)  (0,1) -- (-0.3,1);   % vorne offen
  \draw[thick] (2,0) -- ( 2.3,0)  (2,1) -- ( 2.3,1);   % hinten offen
  % Beschriftungen
  \node[font=\small, gray] at (0.0, -0.35) {vorne};
  \node[font=\small, gray] at (2.0, -0.35) {hinten};

  % ---- Pfeil enQueue ----
  \draw[->, very thick, blue] (3.3, 0.5) -- (5.0, 0.5)
        node[midway, above, font=\small] {enQueue("h")};

  % ---- Nachher ----
  \node[font=\bfseries] at (7.5, 1.8) {Nachher};
  % Box "g"
  \draw[thick] (5.8,0) rectangle (7.8,1) node[midway] {"g"};
  % Box "h" (hinten = rechts)
  \draw[thick] (7.8,0) rectangle (9.8,1) node[midway] {"h"};
  % offene Enden
  \draw[thick] (5.8,0) -- (5.5,0)  (5.8,1) -- (5.5,1);
  \draw[thick] (9.8,0) -- (10.1,0) (9.8,1) -- (10.1,1);
  % Beschriftungen
  \node[font=\small, gray] at (5.8, -0.35) {vorne};
  \node[font=\small, gray] at (9.8, -0.35) {hinten};

  % Pfeil: "h" kommt von rechts
  \draw[->, thick, red] (10.8, 0.5) -- (10.15, 0.5)
        node[midway, above, font=\small, red] {"h"};
\end{tikzpicture}
\end{center}
```

Mit der Operation `deQueue` wird das Element vorne aus der Warteschlange wieder entnommen.

```{.java .cb-nb line_numbers=false}
queue.deQueue()
```

**`deQueue`-Operation** – Das vorderste Element der Warteschlange wird entfernt und zurückgegeben:

```{=latex}
\begin{center}
\begin{tikzpicture}[scale=0.8]
  % ---- Vorher ----
  \node[font=\bfseries] at (1.8, 1.8) {Vorher};
  % Box "g" (vorne = links)
  \draw[thick] (0,0) rectangle (2,1) node[midway] {"g"};
  % Box "h"
  \draw[thick] (2,0) rectangle (4,1) node[midway] {"h"};
  % offene Enden
  \draw[thick] (0,0) -- (-0.3,0)  (0,1) -- (-0.3,1);
  \draw[thick] (4,0) -- ( 4.3,0)  (4,1) -- ( 4.3,1);
  % Beschriftungen
  \node[font=\small, gray] at (0.0, -0.35) {vorne};
  \node[font=\small, gray] at (4.0, -0.35) {hinten};

  % ---- Pfeil deQueue ----
  \draw[->, very thick, blue] (5.0, 0.5) -- (6.7, 0.5)
        node[midway, above, font=\small] {deQueue()};

  % ---- Nachher ----
  \node[font=\bfseries] at (9.3, 1.8) {Nachher};
  % Box "h" (jetzt vorne)
  \draw[thick] (7.5,0) rectangle (9.5,1) node[midway] {"h"};
  % offene Enden
  \draw[thick] (7.5,0) -- (7.2,0)  (7.5,1) -- (7.2,1);
  \draw[thick] (9.5,0) -- (9.8,0)  (9.5,1) -- (9.8,1);
  % Beschriftungen
  \node[font=\small, gray] at (7.5, -0.35) {vorne};
  \node[font=\small, gray] at (9.5, -0.35) {hinten};

  % Pfeil: "g" verlässt links
  \draw[->, thick, red] (7.2, -0.5) -- (6.2, 0.5)
        node[midway, above, font=\small, red] {return "g"};
\end{tikzpicture}
\end{center}
```

Da `deQueue` immer das zuerst hinzugefügte Element zurückgibt, spricht man vom **FIFO**-Prinzip.
Das bedeutet **First-In – First-Out**.


# Implementierung

Für eine Implementierung in Java wird zunächst eine Klasse für die Knoten benötigt.

```java
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
}
```

Damit man nicht immer mit einer `while`-Schleife durch die ganze Liste laufen muss,
wird neben einem Zeiger auf den ersten Knoten auch ein Zeiger auf den letzten Knoten verwendet.

```java
class SimpleQueue<T> {
    MutableNode<T> first;
    MutableNode<T> last;

    public SimpleQueue() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }
```

Beim Anhängen sind zwei Fälle zu unterscheiden:

- Wenn die Warteschlange **leer** ist, zeigen `first` und `last` auf den neuen Knoten.
```{=latex}
\begin{center}
\begin{tikzpicture}[
    box/.style  = {draw, thick, minimum width=1.2cm, minimum height=0.8cm},
    ptr/.style  = {draw, thick, minimum width=0.5cm,  minimum height=0.8cm},
    arr/.style  = {->, thick},
    null/.style = {font=\small\ttfamily}
  ]

  %% ---- VORHER ----
  \node[font=\bfseries] at (1.5, 1.5) {Vorher};

  \node[null] (first) at (0.2,  0.5) {\texttt{first}};
  \node[null] (last)  at (0.2, -0.5) {\texttt{last}};
  \draw[arr, blue] (first.east) -- ++(0.8,0) node[null, right] {null};
  \draw[arr, blue] (last.east)  -- ++(0.8,0) node[null, right] {null};

  %% ---- PFEIL ----
  \draw[->, very thick, blue] (3.5, 0) -- (5.1, 0)
        node[midway, above, font=\small] {enQueue("g")};

  %% ---- NACHHER ----
  \node[font=\bfseries] at (8.5, 1.5) {Nachher};

  \node[box] (gC) at (8.0, 0) {"g"};
  \node[ptr] (gN) at (8.95, 0) {$\bullet$};
  \draw[thick] (gC.north west) rectangle (gN.south east);
  \draw[arr] (gN.east) -- ++(0.6,0) node[null, right] {null};

  \node[null] (first2) at (6.0,  0.9) {\texttt{first}};
  \node[null] (last2)  at (6.0, -0.9) {\texttt{last}};
  \draw[arr, red] (first2.east) to[bend left=25]  (gC.north west);
  \draw[arr, red] (last2.east)  to[bend right=25] (gC.south west);

\end{tikzpicture}
\end{center}
```
- Wenn die Warteschlange **nicht** leer ist, wird der neue Knoten an den letzten Knoten
  angehängt. `last` muss dann auf den neuen Knoten gesetzt werden.

```{=latex}
\begin{center}
\begin{tikzpicture}[
    box/.style  = {draw, thick, minimum width=1.2cm, minimum height=0.8cm},
    ptr/.style  = {draw, thick, minimum width=0.5cm,  minimum height=0.8cm},
    arr/.style  = {->, thick},
    null/.style = {font=\small\ttfamily}
  ]

  %% ---- VORHER ----
  \node[font=\bfseries] at (2.2, 1.5) {Vorher};

  \node[box] (gC) at (1.5, 0) {"g"};
  \node[ptr] (gN) at (2.45, 0) {$\bullet$};
  \draw[thick] (gC.north west) rectangle (gN.south east);
  \draw[arr] (gN.east) -- ++(0.6,0) node[null, right] {null};

  \node[null] (first) at (0.0,  0.9) {\texttt{first}};
  \node[null] (last)  at (0.0, -0.9) {\texttt{last}};
  \draw[arr, blue] (first.east) to[bend left=20]  (gC.north);
  \draw[arr, blue] (last.east)  to[bend right=20] (gC.south);

  %% ---- PFEIL ----
  \draw[->, very thick, blue] (4.2, 0) -- (5.8, 0)
        node[midway, above, font=\small] {enQueue("h")};

  %% ---- NACHHER ----
  \node[font=\bfseries] at (9.0, 1.5) {Nachher};

  \node[box] (gC2) at (7.0, 0) {"g"};
  \node[ptr] (gN2) at (7.95, 0) {$\bullet$};
  \draw[thick] (gC2.north west) rectangle (gN2.south east);

  \node[box] (hC) at (10.0, 0) {"h"};
  \node[ptr] (hN) at (10.95, 0) {$\bullet$};
  \draw[thick] (hC.north west) rectangle (hN.south east);

  \draw[arr] (gN2.east) to[bend left=30] (hC.west);
  \draw[arr] (hN.east) -- ++(0.6,0) node[null, right] {null};

  \node[null] (first2) at (5.8,  0.9) {\texttt{first}};
  \node[null] (last2)  at (5.8, -0.9) {\texttt{last}};
  \draw[arr, blue] (first2.east) to[bend left=20]  (gC2.north);
  \draw[arr, red]  (last2.east)  to[bend right=30] (hC.south);

\end{tikzpicture}
\end{center}
```
```java
    public void enQueue(T x) {
        var newNode = new MutableNode<T>(x);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
            return;
        }
        last.setNextNode(newNode);
        last = newNode;
    }
```

<!-- **`enQueue`-Operation, Fall 1** – Warteschlange ist leer: `first` und `last` zeigen beide auf den neuen Knoten. -->



<!-- **`enQueue`-Operation, Fall 2** – Warteschlange ist nicht leer: neuer Knoten wird hinten angehängt, `last` wird aktualisiert. -->


Auch beim Entfernen des ersten Elements sind zwei Fälle zu unterscheiden:

- Wenn die Warteschlange **leer** ist, wird eine Ausnahme geworfen.
- Wenn die Warteschlange **nicht** leer ist, wird `first` auf den nächsten Knoten
  gesetzt und der Inhalt des ehemaligen ersten Knotens zurückgegeben.
  Falls die Warteschlange dadurch leer wird, muss auch `last` auf `null` gesetzt werden.


**`deQueue`-Operation, Fall 1** – Entfernen des **einzigen** Elements: `first` und `last` werden beide auf `null` gesetzt.

```{=latex}
\begin{center}
\begin{tikzpicture}[
    box/.style  = {draw, thick, minimum width=1.2cm, minimum height=0.8cm},
    ptr/.style  = {draw, thick, minimum width=0.5cm,  minimum height=0.8cm},
    arr/.style  = {->, thick},
    null/.style = {font=\small\ttfamily}
  ]

  %% ---- VORHER ----
  \node[font=\bfseries] at (1.5, 1.5) {Vorher};

  \node[box] (gC) at (1.5, 0) {"g"};
  \node[ptr] (gN) at (2.45, 0) {$\bullet$};
  \draw[thick] (gC.north west) rectangle (gN.south east);
  \draw[arr] (gN.east) -- ++(0.6,0) node[null, right] {null};

  \node[null] (first) at (0.0,  0.9) {\texttt{first}};
  \node[null] (last)  at (0.0, -0.9) {\texttt{last}};
  \draw[arr, blue] (first.east) to[bend left=20]  (gC.north);
  \draw[arr, blue] (last.east)  to[bend right=20] (gC.south);

  %% ---- PFEIL ----
  \draw[->, very thick, blue] (4.2, 0) -- (5.8, 0)
        node[midway, above, font=\small] {deQueue()};
  \node[font=\small, red] at (5.0, -0.45) {return "g"};

  %% ---- NACHHER ----
  \node[font=\bfseries] at (8.5, 1.5) {Nachher};

  \node[null] (first2) at (6.8,  0.5) {\texttt{first}};
  \node[null] (last2)  at (6.8, -0.5) {\texttt{last}};
  \draw[arr, red] (first2.east) -- ++(0.8,0) node[null, right] {null};
  \draw[arr, red] (last2.east)  -- ++(0.8,0) node[null, right] {null};

\end{tikzpicture}
\end{center}
```

**`deQueue`-Operation, Fall 2** – Warteschlange hat **mehrere** Elemente: `first` rückt auf den nächsten Knoten vor.

```{=latex}
\begin{center}
\begin{tikzpicture}[
    box/.style  = {draw, thick, minimum width=1.2cm, minimum height=0.8cm},
    ptr/.style  = {draw, thick, minimum width=0.5cm,  minimum height=0.8cm},
    arr/.style  = {->, thick},
    null/.style = {font=\small\ttfamily}
  ]

  %% ---- VORHER ----
  \node[font=\bfseries] at (2.2, 1.5) {Vorher};

  \node[box] (gC) at (1.5, 0) {"g"};
  \node[ptr] (gN) at (2.45, 0) {$\bullet$};
  \draw[thick] (gC.north west) rectangle (gN.south east);

  \node[box] (hC) at (4.5, 0) {"h"};
  \node[ptr] (hN) at (5.45, 0) {$\bullet$};
  \draw[thick] (hC.north west) rectangle (hN.south east);

  \draw[arr] (gN.east) to[bend left=30] (hC.west);
  \draw[arr] (hN.east) -- ++(0.6,0) node[null, right] {null};

  \node[null] (first) at (0.2,  0.9) {\texttt{first}};
  \node[null] (last)  at (0.2, -0.9) {\texttt{last}};
  \draw[arr, blue] (first.east) to[bend left=20]  (gC.north);
  \draw[arr, blue] (last.east)  to[bend right=30] (hC.south);

  %% ---- PFEIL ----
  \draw[->, very thick, blue] (7.0, 0) -- (8.6, 0)
        node[midway, above, font=\small] {deQueue()};
  \node[font=\small, red] at (7.8, -0.45) {return "g"};

  %% ---- NACHHER ----
  \node[font=\bfseries] at (11.5, 1.5) {Nachher};

  \node[box] (hC2) at (10.5, 0) {"h"};
  \node[ptr] (hN2) at (11.45, 0) {$\bullet$};
  \draw[thick] (hC2.north west) rectangle (hN2.south east);
  \draw[arr] (hN2.east) -- ++(0.6,0) node[null, right] {null};

  \node[null] (first2) at (9.0,  0.9) {\texttt{first}};
  \node[null] (last2)  at (9.0, -0.9) {\texttt{last}};
  \draw[arr, red]  (first2.east) to[bend left=20]  (hC2.north);
  \draw[arr, blue] (last2.east)  to[bend right=20] (hC2.south);

\end{tikzpicture}
\end{center}
```

```java
    public T deQueue() {
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

