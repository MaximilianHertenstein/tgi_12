---
title: records
codebraid:
  jupyter: true
---



# Unveränderliche Objekte

## Definition der Klasse und Erzeugung von Objekten 

Mit dem Schlüsselwort `record` können relativ einfach Klassen mit unveränderlichen Eigenschaften erzeugt werden.

```{.java .cb-nb first_number=1}
record Student(String name, int age){};
```

Vor jedem Aufruf des Konstruktors muss `new` stehen.

```{.java .cb-nb first_number=1}
var alex = new Student("Alex", 18);
```

## Zugriff auf Eigenschaften 


Auf die Eigenschaften von `Records` kann nicht direkt von außen zugegriffen werden.
Um den Zugriff zu ermöglichen, werden aber automatisch Methoden definiert, die genau wie die Eigenschaften heißen.

```{.java .cb-nb first_number=1}
alex.age();
```

## Methoden 


Methoden werden zwischen geschweiften Klammern definiert.

```{.java .cb-nb first_number=1}
record SpeakingStudent(String name, int age){

  String generateGreeting(){
    return "I am " + name + " and I am " + age + " years old!";
  }

};
```

```{.java .cb-nb first_number=1}
var speakingStudent = new SpeakingStudent("Alex", 18);
speakingStudent.generateGreeting()
```





## Sekundäre Konstruktoren 

Im Klassenkörper können auch weitere Konstruktoren definiert werden.

Die Definition beginnt mit dem Namen der Klasse. Anschließend folgen in runden Klammern die Parameter des Konstruktors.
In den geschweiften Klammern wird mit `this` der primäre Konstruktor aufgerufen.

```{.java .cb-nb first_number=1}
record Student(String name, int age){
    
    Student(String name){
      this(name, 14);
    }

};
```

In diesem Beispiel erzeugt der selbst geschriebene Konstruktor immer einen $14$ Jahre alten `Student`.


```{.java .cb-nb first_number=1}
var nino = new Student("Nino");
nino.age();
```

Wir können beliebig viele Konstruktoren definieren. Die einzigen Voraussetzungen sind:

-  jeder Konstruktor muss mit `this` den automatisch definierten Konstruktor aufrufen.
-  es darf keine zwei Konstruktoren mit den gleichen Parametern geben 

```{.java .cb-nb first_number=1}
record Student(String name, int age){
    
    Student(String name){
      this(name, 14);
    }

    Student(int age){
      this("Unknown Student", age);
    }

    Student(){
      this("Unknown Student", 0);
    }

};
```