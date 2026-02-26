---
title: Rückgabewerte vs. Effekte
codebraid:
  jupyter: true
---

```{.java .cb-run}
class Student {
  public String name;
  public int age;

  public Student(String name, int age){
    this.name = name;
    this.age = age;
  }

  public int nextAge(){
    return age + 1;
  }

  public Student nextStudent(){
    return new Student(name, age);
  }

  public void getOlder(){
    age = age + 1;
  }

  public boolean getOlderCheckAllowedToBuyBeer(){
    age = age + 1;
    return age > 15;
  }

}
```

# Wdh. Definition von Klassen

Mit dem fogenden Code wird eine Klasse `Student` mit den Eigenschaften

- `name` vom Typ `String`
- `age` vom Typ `int`

definiert. Außerdem wird ein Konstruktor definiert, dem beide Werte übergeben werden.

```java
class Student {
  String name;
  int age;

  public Student(String name, int age){
    this.name = name;
    this.age = age;
  }
}
```



```{.java .cb-nb line_numbers=false}
var pana = new Student("Pana", 17);
```

# Methoden mit Rückgabewerten

Wir können in dieser Klasse eine Methode implementieren, die das nächste Alter eines Objekts berechnet.

```java
  int nextAge(){
    return age + 1;
  }
```

```{.java .cb-nb line_numbers=false}
pana.nextAge()
```

Ein Aufruf dieser Methode berechnet das neue Alter. Das Objekt, auf dem die Methode aufgerufen wird, ändert sich dabei aber nicht.

```{.java .cb-nb line_numbers=false}
pana.age
```

Bei der nächsten Methode wird ein neuer `Student` erzeugt, der ein Jahr älter ist.  

```java
  Student nextStudent(){
    return new Student(name, age);
  }
```

```{.java .cb-nb line_numbers=false}
var nextPana = pana.nextStudent();
```
```{.java .cb-nb line_numbers=false}
nextPana.name;
```
```{.java .cb-nb line_numbers=false}
nextPana.age;
```

Auch beim Aufruf dieser Methode ändert sich das Objekt, auf dem die Methode aufgerufen wird, nicht.

```{.java .cb-nb line_numbers=false}
pana.age;
```

# Methoden, die Objekte verändern

Die Methode `getOlder` gibt keinen Wert zurück.  Sie ändert aber die Eigenschaft `age` des Objekts, auf dem die Methode aufgerufen wird. 
Da in der Kopfzeile nur der Rückgabetyp angegben wird, steht hier `void`. 

```java
  void getOlder(){
    age = age + 1;
  }
```

```{.java .cb-nb line_numbers=false}
pana.getOlder()
```
```{.java .cb-nb line_numbers=false}
pana.age
```


# Methoden, die Objekte verändern und Werte zurückgeben

Es ist auch möglich, dass eine Methode das Objekt, auf dem sie aufgerufen wird, verändert **und** einen Wert zurückgibt.

```java
  public bool getOlderCheckAllowedToBuyBeer(){
    age = age + 1;
    return age >= 16;
  }
```



```{.java .cb-nb line_numbers=false}
var alex = new Student("Alex", 15);
```
```{.java .cb-nb line_numbers=false}
alex.getOlderCheckAllowedToBuyBeer()
```
```{.java .cb-nb line_numbers=false}
alex.age
```