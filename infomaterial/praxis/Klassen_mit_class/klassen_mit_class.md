---
title: Klassen mit veränderlichen Eigenschaften
codebraid:
  jupyter: true
---

# Motivation

Mit `records` lassen sich Klassen sehr einfach definieren. 
<!-- Bei der Verwendung von `record` wird vieles automatisch festgelegt. Vieles davon ist fast immer sinnvoll, z.B. der automatisch definierte Konstruktor. -->

Eine starke Einschränkung von `records` ist aber, dass deren Eigenschaften nach dem Erzeugen nicht mehr geändert werden können. 


```{.java .cb-nb first_number=1}
record Student(String name, int age){}
```
```{.java .cb-nb first_number=1}
var pana = new Student("Pana" , 17);
```

Der `Student` `pana` hat das Alter `17`. Dies kann nicht geändert werden. Man kann lediglich einen neuen `Student` mit einem anderen Alter erzuegen und den alten `Student` überchreiben.

```{.java .cb-nb first_number=1}
var pana = new Student("Pana" , 18);
```


Bei größeren Objekten mit vielen Eigenschaften ist das jedoch sehr ineffizient.

# Klassen mit class definieren

Klassen mit veränderlichen Eigenschaften können in Java mit dem Schlüsselwort `class` definiert werden.
Alle Eigenschaften werden im Klassenkörper definiert.

```{.java .cb-run first_number=1}
class MutableStudent{
  String name;
  int age;

  public MutableStudent(String name, int age){
    this.name = name;
    this.age = age;
  }

  void getOlder(){
    age = age + 1;
  }

}
```


```java
class MutableStudent{
  String name;
  int age;
}
```

# Konstruktoren definieren


Alle Konstruktoren müssen selbst geschrieben werden. Konstruktoren werden ähnlich wie Methoden definiert. Der Name des Konstruktors stimmt immer mit dem Namen der Klasse überein.
Ein Rückgabetyp wird nicht angebenden. 


```java
class MutableStudent{
  String name;
  int age;

  public MutableStudent(String pName, int pAge){
    name = pName;
    age = pAge;
  }
}
```

Zwischen den geschweiften Klammern kann jeder beliebige Java-Code stehen. In der Regel werden aber nur Werte für die Eigenschaften der Klasse gesetzt.

Hier wird 


- die Eigenschaft `name` auf den übergebenen Wert `pName` gesetzt 
- die Eigenschaft `age` auf den übergebenen Wert `pAge` gesetzt


Wenn die Argumente des Konstruktors dieselben Namen wie die Eigenschaften der Klasse haben,
muss mit `this.eigenschaft` auf eine Eigenschaft zugegriffen werden.


```java
  ...
  public MutableStudent(String name, int age){
    this.name = name;
    this.age = age;
  }
```


```{.java .cb-nb first_number=1}
var pana = new MutableStudent("Pana", 17);
```

# Methoden


Methoden können wie bei Records definiert werden. Es ist jetzt aber möglich, die Eigenschaften der Klasse in Methoden zu ändern.


```java
class MutableStudent{
  String name;
  int age;
...
  void getOlder(){
    age = age + 1;
  }
}
```

Die Methode `getOlder` erhöht das Alter eines `MutableStudent`. Dabei wird **kein** neues Objekt erzeugt.



```{.java .cb-nb first_number=1}
var luca = new MutableStudent("Luca", 18);
luca.getOlder();
luca.age;
```

In der Regel werden Eigenschaften als `private` und Konstruktoren sowie Methoden als `public` deklariert. 

```java
class MutableStudent{
  private String name;
  private int age;

  public MutableStudent(String name, int age){
    this.name = name;
    this.age = age;
  }

  public void getOlder(){
    age = age + 1;
  }

}
```



```{.java .cb-run}
class MutableStudent{
  private String name;
  private int age;

  public MutableStudent(String name, int age){
    this.name = name;
    this.age = age;
  }

  public void getOlder(){
    age = age + 1;
  }

  public int getAge(){
    return age;
  }

  public void setAge(int age){
     this.age = age;
  }

}
```



# Getter und Setter

Um trotzdem auf private Eigenschaften zuzugreifen, werden Methoden definiert, die den Wert einer Eigenschaft zurückgeben.

```java 
...
public int getAge(){
    return age;
  }
  ...
```

```{.java .cb-nb first_number=1}
var luca = new MutableStudent("Luca", 18);
luca.getAge();
```

oder ändern.

```java
...
  public void setAge(int age){
     this.age = age;
  }
  ...
}
```


```{.java .cb-nb first_number=1}
luca.setAge(19);
luca.getAge();
```

# Invalide Werte durch Fehlermeldungen im Setter und Konstruktor verhindern

Wenn ein Setter definiert ist, kann es trotzdem sein, dass ein privates Attribut auf einen ungültigen Wert gesetzt wird.

```{.java .cb-nb first_number=1}
var matti = new MutableStudent("Matti", 17);
matti.setAge(-3);
matti.getAge();
```

Wir können das verhindern, wenn wir eine Fehlermeldung werfen, wenn dem Setter ein ungültiger Wert übergeben wird.

```java
...
  public void setAge(int age){
    if (age < 0){
     throw new IllegalArgumentException("age must be positive");
  }
  this.age = age;
}
```

Leider ist es immer noch möglich, mit dem Konstruktor Objekte mit ungültigen Eigenschaften zu erzeugen.

```{.java .cb-nb first_number=1}
var matti = new MutableStudent("Matti", -17);
matti.getAge();
```


Deshalb sollte die Überprüfung auch im Konstruktor genutzt werden.

```java
  public MutableStudent(String name, int age){
    if (age < 0){
     throw new IllegalArgumentException("age must be positive");
    }
    this.name = name;
    this.age = age;
  }
```


# Eigenschaften im Klassenkörper initialisieren

Wir können Eigenschaften auch direkt im Klassenkörper, statt im Konstruktor initialisieren.

```{.java .cb-nb first_number=1}
class Cat{
  private String name;
  public int lives = 7;

  Cat(String name){
    this.name = name;
  }
}
```

```{.java .cb-nb first_number=1}
var garfield = new Cat("Garfield");
garfield.lives;
```

Bei jedem Objekt der Klasse `Cat` hat die Eigenschaft `lives` nach dem Erzeugen den Wert $7$. 

```{.java .cb-nb first_number=1}
var catmando = new Cat("Catmando");
catmando.lives;
```


<!-- 

```{.java .cb-nb first_number=1}
var micha = new MutableStudent("Micha", 18);
micha.getOlder();
micha.getAge();
```

Auch zum Setzen von Eigenschaften werden Methoden definiert. 
In diesen sollte ein Fehler geworfen werden, wenn ein invalider Wert gesetzt werden soll. 



```{.java .cb-nb first_number=1}
class MutableStudent{
  private String name;
  private int age;

  public MutableStudent(String name, int age){

    if (age < 0){
      throw new IllegalArgumentException("age must be positive");
    }

    this.name = name;
    this.age = age;
  }

  public void getOlder(){
    age = age + 1;
  }

  public String getName(){
    return name;
  }

  public int getAge(){
    return age;
  }

  public void setAge(){
    if (age < 0){
      throw new IllegalArgumentException("age must be positive");
    }

    this.age = age;
  }

}
``` -->
