---
title: Interfaces
codebraid:
  jupyter: true
---

```{.java .cb-run}
import static java.lang.IO.println;
import java.util.ArrayList;
import java.util.List;

        interface Greetable {
            void greet();
            String lastName();
        }

        record Teacher(String firstName, String lastName) implements Greetable {
            @Override
            public void greet() {
                println("Hello, I am " + firstName + " " + lastName);
            }
        }

        record Student(String firstName, String lastName, int age) implements Greetable {
            @Override
            public void greet() {
                println("Yo, I am " + firstName);
            }
        }

        class GreetingUtils {
            
            static void letTeacherGreetTwoTimes(Teacher teacher){
                teacher.greet();
                teacher.greet();
            }

            static void letStudentGreetTwoTimes(Student student){
                student.greet();
                student.greet();
            }

            static void letGreetTwoTimes(Greetable greetable){
                greetable.greet();
                greetable.greet();
            }

            static void showTeacherGreetings(List<Teacher> teachers) {
                for (Teacher teacher : teachers) {
                    teacher.greet();
                }
            }

            static void showStudentGreetings(List<Student> students) {
                for (Student student : students) {
                    student.greet();
                }
            }

            static void showGreetings(List<Greetable> greetables) {
                for (Greetable greetable : greetables) {
                    greetable.greet();
                }
            }

            static <T extends Greetable>  void showGreetingsNotMixed(List<T> greetables) {
                for (Greetable greetable : greetables) {
                    greetable.greet();
                }
            }


        }


```

# Motivation


Mit dem folgenden Code werden eine `Teacher` und eine `Student`-Klasse definiert. Beide Klassen haben eine `greet`-Methode.

```java
record Teacher(String firstName, String lastName)  {
    
    public void greet() {
        println("Hello, I am " + firstName + " " + lastName);
    }
}

record Student(String firstName, String lastName, int age) {
    
    public void greet() {
        println("Yo, I am " + firstName);
    }
}
```


```{.java .cb-nb line_numbers=false}
var herrMüller = new Teacher("Dieter", "Müller");
herrMüller.greet();
```
```{.java .cb-nb line_numbers=false}
var alex = new Student("Alex", "Hoffmann", 18);
alex.greet();
```


Wenn man jetzt eine Methode schreiben will, mit der sich ein Schüler zweimal vorstellt, ist das folgendermaßen möglich:




```java
class GreetingUtils {
    static void letStudentGreetTwoTimes(Student student){
        student.greet();
        student.greet();
    }
}
```

```{.java .cb-nb line_numbers=false}
GreetingUtils.letStudentGreetTwoTimes(alex);
```

Obwohl die Methode für Lehrer genauso aufgebaut wäre, kann sie nur mit Schülern aufgerufen werden.

```java
GreetingUtils.letStudentGreetTwoTimes(herrMüller); // Typfehler
```


Eine Methode, bei der sich ein Lehrer zweimal vorstellt, muss separat programmiert werden.


```java
class GreetingUtils {
    static void letTeacherGreetTwoTimes(Teacher teacher){
        teacher.greet();
        teacher.greet();
    }
}
```

```java
GreetingUtils.letTeacherGreetTwoTimes(herrMüller);
```

# Interfaces


Um diese Art von Codewiederholung zu vermeiden, kann man ein *Interface* definieren.

```java  
interface Greetable {
    void greet();
}
```

Jede Klasse, die das Interface `Greetable` erfüllt, muss die Methode `greet` implementieren. Diese Methode hat keine Parameter und liefert keinen Rückgabewert.

Wir können jetzt deklarieren, dass die Klasse `Teacher` das Interface erfüllt. Dies geschieht mit dem Schlüsselwort `implements`.

```java
record Teacher(String firstName, String lastName) implements Greetable {
```

Vor jeder Implementierung einer Methode des Interfaces muss `@Override` stehen.
```java
@Override
    public void greet() {
        println("Hello, I am " + firstName + " " + lastName);
    }
}
```

Genauso können wir deklarieren, dass die Klasse `Student` das Interface erfüllt.

```java
record Student(String firstName, String lastName, int age) implements Greetable {
    @Override
    public void greet() {
        println("Yo, I am " + firstName);
    }
}
```

`Student` und `Teacher` können jetzt als `Greetable`-Referenzen gespeichert werden.

```{.java .cb-nb line_numbers=false}
Greetable greetingAlex = alex;
Greetable greetingMüller = herrMüller;
```

Die Methoden des Interfaces können dann weiterhin verwendet werden.
```{.java .cb-nb line_numbers=false}
greetingAlex.greet();
```

Alle anderen Methoden der konkreten Klasse sind dann nicht mehr direkt nutzbar.
```java
greetingAlex.firstName(); // Typfehler
```



Indem wir das Interface `Greetable` als Parametertyp nutzen, können wir die beiden Methoden oben zu einer Methode zusammenfassen.

```java
class GreetingUtils {
    static void letGreetTwoTimes(Greetable greetable){
        greetable.greet();
        greetable.greet();
    }
}
```

```{.java .cb-nb line_numbers=false}
GreetingUtils.letGreetTwoTimes(greetingAlex);
GreetingUtils.letGreetTwoTimes(greetingMüller);
```


# Interfaces und Listen

Die Objekte `alex` und `herrMüller` gehören zwar zu unterschiedlichen Klassen, sie erfüllen aber beide das Interface `Greetable`.
Deshalb können sie in einer `List<Greetable>` gespeichert werden.

```{.java .cb-nb line_numbers=false}
List<Greetable> greeters = List.of(alex, herrMüller);
```

Es ist möglich, Methoden zu definieren, denen eine Liste von Objekten übergeben wird, die ein Interface erfüllen.


```java
class GreetingUtils {
    static void showGreetings(List<Greetable> greetables) {
        for (Greetable greetable : greetables) {
            greetable.greet();
        }
    }
}
```


```{.java .cb-nb line_numbers=false}
GreetingUtils.showGreetings(greeters);
```

# Typen, die ein Interface erfüllen, spezifizieren


Wenn man solche gemischten Listen mit `Student`- und `Teacher`-Objekten nicht zulassen will, die Methode aber sowohl mit einer `List<Student>` als auch mit einer `List<Teacher>` funktionieren soll,
kann man einen Typparameter (`T`) verwenden und festlegen, dass dieser das Interface `Greetable` erfüllt.

```java
static <T extends Greetable>  void showGreetingsNotMixed(List<T> greetables) {
    for (Greetable greetable : greetables) {
        greetable.greet();
    }
}
```

```{.java .cb-nb line_numbers=false}
var students = List.of(new Student("Alex", "Hoffmann", 18), new Student("Nino", "Clermont", 15));
var teachers = List.of(new Teacher("Dieter", "Müller"), new Teacher("Klaus", "Müller"));

GreetingUtils.showGreetingsNotMixed(students);
GreetingUtils.showGreetingsNotMixed(teachers);
```

# Interface-Erweiterung

Wir können auch Interfaces definieren, die ein bestehendes Interface erweitern.

```{.java .cb-nb line_numbers=false}
interface CanGreetAndSayGoodBye extends Greetable {
    void sayGoodbye();
}
```

Damit eine Klasse das Interface `CanGreetAndSayGoodBye` erfüllt, muss sie das Interface `Greetable` erfüllen und zusätzlich die Methode `void sayGoodbye()` implementieren.

\small
```{.java .cb-nb line_numbers=false}
record Student(String firstName, String lastName, int age) implements CanGreetAndSayGoodBye {
    @Override
    public void greet() {
        println("Yo, I am " + firstName);
    }

    @Override
    public void sayGoodbye() {
        println("Yo, I am " + firstName);
    }
}
```
