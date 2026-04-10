---
title: Stapelspeicher 
codebraid:
  jupyter: true
---
```{.java .cb-run}
import java.util.EmptyStackException;

record   Node<T> (T content, Node<T> nextNode) {
    public Node(T content) {
        this(content, null);
    }
}

```
```{.java .cb-run}


public class Stack<T> {
    private Node<T> first;

    public Stack() {
        this.first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }



    public T top() {
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

# Grundlagen
Zu einem Stapelspeicher können Elemente mit der Operation `push` hinzugefügt werden.

```{.java .cb-nb line_numbers=false}
var stack = new Stack();
stack.push("g")
```

Man kann sich vorstellen, dass die Elemente oben auf den Stapel gelegt werden.

```{.java .cb-nb line_numbers=false}
stack.push("h")
```

**`push`-Operation** – Ein neues Element wird oben auf den Stapel gelegt:

```{=latex}
\begin{center}
\begin{tikzpicture}[scale=0.6]
  % Vorher
  \node[font=\bfseries] at (1, 4.2) {Vorher};
  \draw[thick] (0,0) rectangle (2,1) node[midway] {"g"};
  \draw[thick] (0,-0.02) -- (0,-0.3) (2,-0.02) -- (2,-0.3);
  \draw[thick] (-0.2,-0.3) -- (2.2,-0.3);

  % Pfeil
  \draw[->, very thick, blue] (3,2) -- (4.5,2) node[midway, above, font=\small] {push("h")};

  % Nachher
  \node[font=\bfseries] at (6.5, 4.2) {Nachher};
  \draw[thick] (5.5,0)   rectangle (7.5,1)   node[midway] {"g"};
  \draw[thick] (5.5,1)   rectangle (7.5,2)   node[midway] {"h"};
  \draw[thick] (5.5,-0.02) -- (5.5,-0.3) (7.5,-0.02) -- (7.5,-0.3);
  \draw[thick] (5.3,-0.3) -- (7.7,-0.3);

  % Pfeil nach oben (neues Element)
  \draw[->, thick, red] (6.5, 3.8) -- (6.5, 2.2) node[midway, right, font=\small, red] {"h"};
\end{tikzpicture}
\end{center}
```

Mit der Operation `pop` wird das Element oben wieder weggenommen.

```{.java .cb-nb line_numbers=false}
stack.pop()
```

**`pop`-Operation** – Das oberste Element wird vom Stapel entfernt und zurückgegeben:

```{=latex}
\begin{center}
\begin{tikzpicture}[scale=0.6]
  % Vorher
  \node[font=\bfseries] at (1, 4.2) {Vorher};
  \draw[thick] (0,0) rectangle (2,1) node[midway] {"g"};
  \draw[thick] (0,1)   rectangle (2,2) node[midway] {"h"};
  \draw[thick] (0,-0.02) -- (0,-0.3) (2,-0.02) -- (2,-0.3);
  \draw[thick] (-0.2,-0.3) -- (2.2,-0.3);

  % Pfeil (pop)
  \draw[->, very thick, blue] (3,1.5) -- (4.5,1.5) node[midway, above, font=\small] {pop()};

  % Nachher
  \node[font=\bfseries] at (6.5, 4.2) {Nachher};
  \draw[thick] (5.5,0) rectangle (7.5,1) node[midway] {"g"};
  \draw[thick] (5.5,-0.02) -- (5.5,-0.3) (7.5,-0.02) -- (7.5,-0.3);
  \draw[thick] (5.3,-0.3) -- (7.7,-0.3);

  % Pfeil nach oben (Element wird entfernt)
  \draw[->, thick, red] (1, 2.2) -- (1, 3.8) node[midway, right, font=\small, red] {return "h"};
\end{tikzpicture}
\end{center}
```

Da `pop` immer das zuletzt hinzugefügte Element zurückgibt, spricht man vom **LIFO**-Prinzip.
Das bedeutet **Last-In – First-Out**.



# Implementierung

Eine Implemeniterung in Java kann folgendermaßen umgesetzt werden.

```java
record   Node<T> (T content, Node<T> nextNode) {
    public Node(T content) {
        this(content, null);
    }
}

public class Stack<T> {
    private Node<T> first;

    public Stack() {
        this.first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public T top() {
        if (isEmpty()) {
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