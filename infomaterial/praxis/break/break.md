---
title: Abruch von Schleifen mit break
codebraid:
  jupyter: true
---

```{.java .cb-run}
import static java.lang.IO.println;

```




Schleifen können mit `break`-Statements unterbrochen werden. Dabei wird im Gegensatz zu `return`-Statements nicht die Funktion abgebrochen.

```{.java .cb-nb line_numbers=false}
record Subject(String name, int grade){};

record Student(List<Subject> subjects) {
    void hasUnderCourse(){
        boolean found = false;
        for (var subject: subjects){
            println("Prüfe: " + subject.name());
            if (subject.grade() < 5){
                found = true;
                break;
            }
        }
        if (found){
            println("Hat einen Unterkurs");
        }
        else {
            println("Hat keinen Unterkurs");
        }
    }
}
```

\scriptsize

```{.java .cb-nb line_numbers=false}
var fritz = new Student(List.of(new Subject("Deutsch", 7), new Subject("Mathe", 4), new Subject("GGK", 2)));
fritz.hasUnderCourse();
```
