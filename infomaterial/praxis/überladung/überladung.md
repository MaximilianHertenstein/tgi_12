---
title: Überladung
codebraid:
  jupyter: true
---

```{.java .cb-run}
import static java.lang.IO.println;

```




Es ist möglich in einer Klasse zwei Methoden mit dem selben Namen zu definieren. Die Voraussetzung dafür ist, dass sich die Typen oder die Anzahl der Parameter dieser Methoden unterscheiden.

```{.java .cb-nb line_numbers=false}
record Student() {
    void greet(){
        println("Hello");
    }

    void greet(String name){
        println("Hello " + name);
    }
}
```
Der Compiler prüft bei einem Aufruf der Methode `greet`, welche Version aufgerufen werden soll. Wird kein Argument übergeben, wird die Methode ohne Parameter aufgerufen:

```{.java .cb-nb line_numbers=false}
var nino = new Student();
nino.greet();
```

Wird ein `String` übergeben, wird die Methode mit einem Parameter vom Typ `String` aufgerufen:

```{.java .cb-nb line_numbers=false}
nino.greet("Alex");
```

Wenn in einer Klasse mehrere Methoden mit demselben Namen, aber unterschiedlichen Parametern definiert werden, spricht man von Methodenüberladung.

Genau wie Methoden können auch Konstruktoren überladen werden