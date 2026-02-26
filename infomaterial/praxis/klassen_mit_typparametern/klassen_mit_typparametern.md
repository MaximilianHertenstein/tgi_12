---
title: Klassen mit Typparametern
codebraid:
  jupyter: true
---

```{.java .cb-run}
import java.util.ArrayList;
```

# Grundlagen

Wir haben schon gesehen, dass man Typparameter nutzen kann um Methoden mit Werten von verschiedenen Typen aufrufen zu können.
Typparameter können auch beim Definieren von Klassen genutzt werden. Die Typparameter werden in spitzen Klammern hinter dem Namen der Klasse definiert.

```{.java .cb-nb line_numbers=false}
record  Box<T>(T content){}
```


Beim Erzeugen eines Objekts der Klasse `Box` kann jetzt ein beliebiger Typen verwendet werden.

```{.java .cb-nb line_numbers=false}
var box1 = new Box<Integer>(7);
var box2 = new Box<String>("hello");
```

```{.java .cb-nb line_numbers=false}
box1.content();
```
```{.java .cb-nb line_numbers=false}
box2.content();
```

Der konkrete Typ muss nicht angegeben werden, wenn der Copiler diesen anhand der Argumente des Konstruktors bestimmen kann.
Die spitzen Klammen sollte man aber immer dazu schreiben.

```{.java .cb-nb line_numbers=false}
var box1 = new Box<>(7);
```



Wenn man eine Klasse mit einem Typparamter als Eigenschaft einer anderen Klasse verwendet, sollte man immer in spitzen Klammern  einen Typ angeben.

```{.java .cb-nb line_numbers=false}
record IntAndString(Box<Integer> intBox, Box<String> stringBox){}
```

Es können auch wieder Typparamter verwendet werden.

```{.java .cb-nb line_numbers=false}
record Tuple<L, R>(Box<L> left, Box<R> right){}
```


# Typparameter in Eigenschaften, Methoden  und Konstuktoren verwenden

Die Typparameter einer Klasse können in den Eigenschaften, Methoden  und Konstuktoren der Klasse verwendet werden.

```{.java .cb-nb line_numbers=false}
class  Tuple<L, R>{
  
    L left; 
    R right;
    
    Tuple(L left, R right){
      this.left = left;
      this.right = right;
    }

    L getLeft(){
      return left;
    }

    R getRight(){
      return right;
    }

}
```


```{.java .cb-nb line_numbers=false}
var boolAndChar = new Tuple<Boolean, Character>(true, 'a');
boolAndChar.getLeft();
```
```{.java .cb-nb line_numbers=false}
boolAndChar.getRight();
```


```{.java .cb-nb line_numbers=false}
var stringAndInt = new Tuple<String, Integer>("Hello", 2);
stringAndInt.getLeft();
```
```{.java .cb-nb line_numbers=false}
stringAndInt.getRight();
```

