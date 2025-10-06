---
title: Sequenzdiagramme
codebraid:
  jupyter: true
plantuml:
  # preamble: plantuml.txt
  executable: /home/max/Downloads/plantuml.jar
  
---



```java
record Student(String name, int age) {
    void greet() {
        println ("Hello, my name is " + name + ", I am " + age + " years old");
    }
}
```


```{.plantuml width=30mm format=svg}

```


```java
record Student(String name, int age) {
    void greet(){
        var greeting = getGreeting();
        println(greeting);
    }

    String getGreeting() {
        return ("Hello, my name is " + name + ", I am " + age + " years old");
    }
}
```






```java

record Student(String name, int age) {
    void implementSnakeAndPlay() {
        Game snakeGame = new Game("Snake");
        snakeGame.play();
    }
}
```

