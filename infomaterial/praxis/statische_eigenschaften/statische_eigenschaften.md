---
title: Statische Eigenschaften
codebraid:
  jupyter: true
---

```{.java .cb-run}
import static java.lang.IO.println;

```


Wir haben schon gesehen, dass man mit dem Schlüsselwort `static` Methoden definieren kann, die nicht auf Objekten, sondern der Klasse aufgerufen werden.


Genauso können wir *statische* Eigenschaften definieren, die nicht zu den einzelnen Objekten, sondern der gesamten Klasse gehören.
Im folgenden Beispiel wird eine *statische* Eigenschaft `count` definiert. Diese wird bei jedem Aufruf des Konstruktors der Klasse erhöht.


```{.java .cb-nb line_numbers=false}
class Student {
    static int count;

    Student(){
        count = count + 1;
    }
}
```


```{.java .cb-nb line_numbers=false}
println(Student.count);
```
```{.java .cb-nb line_numbers=false}
var pana = new Student();
println(Student.count);
```
```{.java .cb-nb line_numbers=false}
var nino = new Student();
println(Student.count);
```

Im Gegensatz zu Objekteigenschaften kann auch in statischen Methoden auf statische Eigenschaften zugegriffen werden.

```{.java .cb-nb line_numbers=false}
class Student {
    private static int count;

    Student(){
        count = count + 1;
    }

    static int getCount(){
        return count;
    }
}
```

```{.java .cb-nb line_numbers=false}
var pana = new Student();
var nino = new Student();
println(Student.getCount());
```


Wir können aber auch in nicht-statischen Methoden auf statische Eigenschaften zugreifen.


```{.java .cb-nb line_numbers=false}
class Student {
    private static int count;

    Student(){
        count = count + 1;
    }

    static int getCount(){
        return count;
    }

    void gameOver(){
        count = count - 1;
    }
}
```

```{.java .cb-nb line_numbers=false}
var pana = new Student();
var nino = new Student();
println(Student.getCount());
```

```{.java .cb-nb line_numbers=false}
pana.gameOver();
nino.gameOver();
println(Student.getCount());
```