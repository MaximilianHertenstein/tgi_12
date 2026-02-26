---
title: Sequenzdiagramme
codebraid:
  jupyter: true

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
        println(getGreeting());
    }

    String getGreeting() {
        var greeting = "Hello, my name is " + name + ", I am " + age + " years old"
        return greeting;
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

