---
title: Klassen mit veränderlichen Eigenschaften
codebraid:
  jupyter: true
---



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


Alle Konstruktoren muss selbst geschrieben werden. Konstruktoren werden ähnlich wie eine Methode definiert. Der Name des Konstruktors stimmt immer mit dem Namen der Klasse überein.
Ein Rückgabetyp wird nicht angebenen. 


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

Zwischen den geschweiften Klammern



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


Methoden können wie bei Records definiert werden.


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
public int getAge(){
    return age;
  }
```

oder ändern.

```java
  public void setAge(int age){
     this.age = age;
  }
}
```

# Invalide Werte durch Fehlermeldungen im Setter und Konstruktor verhindern

Wenn ein Setter definiert ist, kann es trotzdem seind, dass ein privates Attribut auf einen ungültigen Wert gesetzt wird.

```{.java .cb-nb first_number=1}
var matti = new MutableStudent("Matti", 17);
matti.setAge(-3);
matti.getAge();
```

Wir können das verhinden, wenn wir eine Fehlermelung werfen wenn, dem Setter ein ungültiger wert übergeben wird.

```java
...
  public void setAge(int age){
    if (age < 0){
     throw new IllegalArgumentException("age must be postive")
  }
  this.age = age;
}
```

Leider ist es immer noch möglich mit dem Kostruktor Objekte mit ungülitgen Eigenschaften  zu  erzeugen.

```{.java .cb-nb first_number=1}
var matti = new MutableStudent("Matti", -17);
matti.getAge();
```


Deshalb sollte die Überprüfung auch im Konstruktor genutzt werden.

```java
  public MutableStudent(String name, int age){
    if (age < 0){
     throw new IllegalArgumentException("age must be postive")
    }
    this.name = name;
    this.age = age;
  }
```


# Eigenschaften im Klassenkörper initialisieren

Wir können Eigenschaften auch direkt im Klassenkörper, statt im Konstruktor initialisieren.

```{.java .cb-nb first_number=1}
class Cat{
  String name;
  int lives = 7;

  Cat(String name){
    this.name = name;
  }
}
```

```{.java .cb-nb first_number=1}
var garfield = new Cat("Garfield");
garfield.lives;
```





<!-- 

```{.java .cb-nb first_number=1}
var micha = new MutableStudent("Micha", 18);
micha.getOlder();
micha.getAge();
```

Auch zum Setzen von Eigenschaften werden Methoden definiert. 
In diesen sollte ein Fehler geworfen werden, wenn ein invalider Wert gestzt werden soll. 



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
