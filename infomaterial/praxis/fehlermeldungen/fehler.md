---
title: Fehler
codebraid:
  jupyter: true
---

```{.java .cb-run}

```




Wir haben bereits gesehen, dass bei der Ausführung des folgenden Codes
```java
var x= 1/0;
```

die Fehlermeldung `ArithmeticException: / by zero` ausgegeben wird



Im folgenden Codeblock

```java
var xs = List.of(1, 3);
xs.get(2);
```

wird die Fehlermeldung `IndexOutOfBoundsException: Index: 2 Size: 2` ausgegeben.



Fehler sind in Java Klassen und wir können mit einem Konstruktor Objekte von diesen Klassen erzeugen.

```{.java .cb-nb line_numbers=false session=block_error2}
new ArithmeticException("/ by zero")
```

Viel wichtiger ist, aber das die Fehler mit `trow` geworfen werden können.

```java
throw new ArithmeticException("/ by zero")
```

Dies führt dazu, dass die Fehlermeldung beim Programmierer oder Benutzer ankommt.
Fehlermeldungen können genutzt werden, um anzuzeigen, dass eine Methode/ein Konstruktor mit einem ungültigen Wert aufgerufen wurde.


```java
boolean allowedToDrinkBeer(int age)  {
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative");
    }
    return age >= 21;
}
```
