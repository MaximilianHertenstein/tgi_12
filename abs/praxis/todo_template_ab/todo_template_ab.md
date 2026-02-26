---
title: ToDo-Templates-AB
codebraid:
  jupyter: true
---

```{.java .cb-run}

class Utils {
  public static String statusToCompleted(boolean status) {
        if (status) {
            return "completed";
        }
        return "";
    }

    public static String computeFilterClass(String filter, String linkToFilter) {
        if (filter.equals(linkToFilter)) {
            return "selected";
        }
        return "";
    }
}



```

# Aufgabe

Definiere in einer neuen Datei `Utils.java` eine Klasse namens `Utils`.


# Aufgabe
Ergänze die Klasse `Utils` um eine statische Methode `statusToCompleted`. Dieser wird ein Boolean übergeben. Sie gibt einen String zurück. Wenn der Boolean-Wert `true` ist, wird "completed" zurückgegeben. Ansonsten wird ein leerer String zurückgegeben.

```{.java .cb-nb first_number=1}
Utils.statusToCompleted(true)
```
```{.java .cb-nb first_number=1}
Utils.statusToCompleted(false)
```

# Aufgabe
Ergänze die Klasse `Utils` um eine statische Methode `computeFilterClass`. Dieser werden zwei Strings übergeben. Sie gibt einen String zurück. Wenn beide Strings gleich sind, wird "selected" zurückgegeben. Ansonsten wird ein leerer String zurückgegeben.

```{.java .cb-nb first_number=1}
Utils.computeFilterClass("all", "all")
```
```{.java .cb-nb first_number=1}
Utils.computeFilterClass("active", "completed")
```
```{.java .cb-nb first_number=1}
Utils.computeFilterClass("completed", "completed")
```
```{.java .cb-nb first_number=1}
Utils.computeFilterClass("all", "active")
```





# Aufgabe


- Füge die Abhängigkeit `jte` wie auf dem Infoblatt `Template-Engines` zum Projekt hinzu (`implementation("gg.jte:jte:3.2.1")`).
- Lege ein Ordner `src/main/jte/web` für die Templates an!
- Erstelle in diesem Ordner eine leere `.jte`-Datei!
- Erstelle ein Template, rendere es und gib das Ergebnis an der Konsole aus.

# Aufgabe


Erstelle ein Template `toDo.jte`, mit dem ein einzelnes To-do gerendert wird.


```java
var learnJava = new ToDo(1, "Learn Java", false);
DirectoryCodeResolver htmlCodeResolver = new DirectoryCodeResolver(Path.of("src/main/jte/web"));
TemplateEngine htmlTemplateEngine = TemplateEngine.create(htmlCodeResolver, ContentType.Html);
StringOutput stringOutput = new StringOutput();
htmlTemplateEngine.render("toDo.jte", learnJava, stringOutput);
System.out.println(stringOutput);
```

```html
<li id="todo1"
    class="">
      <input class="toggle" type="checkbox">
      <label> Learn Java</label>
      <button class="destroy">
    </button>
</li>
```

```java
var learnUML = new ToDo(7, "Learn UML", true);
DirectoryCodeResolver htmlCodeResolver = new DirectoryCodeResolver(Path.of("src/main/jte/web"));
TemplateEngine htmlTemplateEngine = TemplateEngine.create(htmlCodeResolver, ContentType.Html);
StringOutput stringOutput = new StringOutput();
htmlTemplateEngine.render("toDo.jte", learnUML, stringOutput);
System.out.println(stringOutput);
```


```html
<li id="todo7"
    class="completed">
      <input class="toggle" type="checkbox" checked/>
      <label> Learn UML </label>
      <button class="destroy">
    </button>
</li>
``` 


# Aufgabe

Definiere in einer neuen Datei `TemplateRenderer.java` eine Klasse `TemplateRenderer`. Diese Klasse hat ein privates Attribut `htmlTemplateEngine` vom Typ `TemplateEngine`.



# Aufgabe

Ergänze die Klasse `TemplateRenderer` um einen Konstruktor. Dieser initialisiert das Attribut `htmlTemplateEngine`. 
Der Pfand zu den Templates ist `src/main/jte/web`.


# Aufgabe

Ergänze die Klasse `TemplateRenderer` um eine Methode `renderToString`. Dieser wird ein `ToDo` und der Name eines Templates übergeben.
Sie gibt das gerenderte Template als String zurück. 

```java
var learnJava = new ToDo(1, "Learn Java", false);
var templateRenderer = new TemplateRenderer();
System.out.println(templateRenderer.renderToString(learnJava, "toDo"));
```

```html
<li id="todo1"
    class="">
      <input class="toggle" type="checkbox">
      <label> Learn Java</label>
      <button class="destroy">
    </button>
</li>
```

```java
var learnUML = new ToDo(7, "Learn UML", true);
var templateRenderer = new TemplateRenderer();
System.out.println(templateRenderer.renderToString(learnUML, "toDo"));
```


```html
<li id="todo7"
    class="completed">
      <input class="toggle" type="checkbox" checked/>
      <label> Learn UML </label>
      <button class="destroy">
    </button>
</li>
``` 

**Hinweis:** Nutze die Methode `statusToCompleted` der Klasse `Utils`.


# Aufgabe 

Passe `renderToString` so an, dass für den ersten Parameter ein beliebiges Objekt übergeben werden kann. 



# Aufgabe


Erstelle ein Template `app.jte`, mit dem der Hauptteil der ToDo-App gerendert werden.
Dem Template wird ein Objekt der Klasse `UIState` übergeben.

```java
var templateRenderer = new TemplateRenderer();
var uiState = new UIState("All", List.of(
        new ToDo(1, "Learn Java", false),
        new ToDo(2, "Learn Javalin", true)
), "1 item left");
System.out.println(templateRenderer.renderToString(uiState, "app"));
```


```html
    <header class="header">
        <h1>todos</h1>
        <form action="/todos/new"
                method="post">
            <input 
                class="new-todo"
                placeholder="What needs to be done?"
                name="text_of_new_todo"
                autofocus>
            <button type="submit"
                style="display:none;"
                >
            Add
            </button>
        </form>
    </header>
    <main>
        <ul class="todo-list">
            <li id="todo1"
                class="">
                <input class="toggle"
                    type="checkbox"
                    />
                <label> Learn Java
                </label>
                <button class="destroy">
                </button>
            </li>
            <li id="todo2"
                class="completed">
                <input class="toggle"
                    type="checkbox"
                    checked/>
                <label> Learn Javalin
                </label>
                <button class="destroy">
                </button>
            </li>
        </ul>
    </main>
    <footer class="footer">
        <span class="todo-count">
        <strong>1 item left</strong>
        </span>
        <ul class="filters">
            <li>
                <a class="selected"> All </a>
            </li>
            <li>
                <a class=""> Active </a>
            </li>
            <li>
                <a class=""> Completed </a>
            </li>
        </ul>
        <button class="clear-completed">
        Clear completed
        </button>
    </footer>
``` 

```java
var templateRenderer = new TemplateRenderer();
var uiState = new UIState("Completed", List.of(), "0 items left");
System.out.println(templateRenderer.renderToString(uiState, "app"));
```


```html
    <header class="header">
        <h1>todos</h1>
        <form href="/todos/new"
            action="post"
            >
            <input 
                class="new-todo"
                placeholder="What needs to be done?"
                name="text_of_new_todo"
                autofocus>
            <button type="submit"
                style="display:none;"
                >
            Add
            </button>
        </form>
    </header>
    <main>
        <ul class="todo-list">
        </ul>
    </main>
    <footer class="footer">
        <span class="todo-count">
        <strong>0 items left</strong>
        </span>
        <ul class="filters">
            <li>
                <a 
                    class="">
                All
                </a>
            </li>
            <li>
                <a 
                    class="">
                Active
                </a>
            </li>
            <li>
                <a 
                    class="selected">
                Completed
                </a>
            </li>
        </ul>
        <button
            class="clear-completed"
            >
        Clear completed
        </button>
    </footer>
```



**Hinweis:** Nutze das bereits definierte Template `toDo.jte` und die Methode `computeFilterClass` der Klasse `Utils`.


# Aufgabe
Erstelle ein Template `mainPage.jte`, mit dem die komplette ToDo-App gerendert werden.
Dem Template wird ein Objekt der Klasse `UIState` übergeben.  Es wird ein HTML-Grundgerüst und `section`-Element erzeugt. In der `section`, steht der Inhalt von `app.jte`.


```java
var templateRenderer = new TemplateRenderer();
var uiState = new UIState("Completed", List.of(), "0 items left");
System.out.println(templateRenderer.renderToString(uiState, "mainPage"));
```


```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ToDo-App</title>
</head>
<body>
    <section class="todoapp" id="todoapp">
        <!-- Ausgabe von app.jte -->
    </section>
</body>
</html>
```


**Hinweis:** Nutze das bereits definierte Template `app.jte`.


# Aufgabe 
Ergänze die Klasse `TemplateRenderer` um eine Methode `renderAppToString`. 
Dieser wird der `UIState` und ein Boolean `partial` übergeben. Wenn `partial` `true` ist, wird  `app.jte` genutzt, um den UI-State darzustellen.
Ansonsten wird `mainPage.jte` verwendet.


```java
var templateRenderer = new TemplateRenderer();
var uiState = new UIState("Completed", List.of(), "0 items left");
System.out.println(templateRenderer.renderAppToString(uiState, true));
```
```html
<header class="header">
...
```


```java
var templateRenderer = new TemplateRenderer();
var uiState = new UIState("Completed", List.of(), "0 items left");
System.out.println(templateRenderer.renderAppToString(uiState, false));
```
```html
<!DOCTYPE html>
<html lang="en">
...
```


# Aufgabe

Erstelle ein Template `editingForm.jte`, mit diesem wird ein Formular gerendert wird, mit dem der Text eines To-dos geändert werden kann.
Dem Template wird das To-do übergeben. Das Formular wird in einem Listenelement dargestellt.


```java
var templateRenderer = new TemplateRenderer();
var learnJavalin = new ToDo(2, "Learn Javalin", true);
println(templateRenderer.renderToString(learnJavalin, "editingForm"));
```
```html
<li id="todo2"  class="editing">
    <input class="edit new-todo" 
           autofocus
           value="Learn Javalin"
           name="updated_text_of_new_todo"
    >
</li>
```

```java
var templateRenderer = new TemplateRenderer();
ToDo learnJava = new ToDo(2, "Learn Java", false);
println(templateRenderer.renderToString(learnJava, "editingForm"));
```
```html
<li id="todo2"  class="editing">
    <input class="edit new-todo" 
           autofocus
           value="Learn Java"
           name="updated_text_of_new_todo"
    >
</li>
```

## Aufgabe 
Ergänze die Klasse `TemplateRenderer` um eine Methode `renderToDoToString`. 
Dieser wird ein `ToDO` und ein Boolean `editing` übergeben. Wenn `editing` `true` ist, wird  `editingForm.jte` genutzt, um den das To-do darzustellen.
Ansonsten wird `toDo.jte` verwendet.

```java
var templateRenderer = new TemplateRenderer();
ToDo learnJava = new ToDo(2, "Learn Java", false);
println(templateRenderer.renderToDoToString(learnJava, true));
```
```html
<li id="todo2"  class="editing">
    <input class="edit new-todo" 
           autofocus
           value="Learn Java"
           name="updated_text_of_new_todo"
    >
</li>
```
```java
var templateRenderer = new TemplateRenderer();
ToDo learnJava = new ToDo(2, "Learn Java", false);
println(templateRenderer.renderToDoToString(learnJava, false));
```
```html
<li id="todo2"
    class="">
    <input class="toggle"
           type="checkbox"
    />
    <label> Learn Java </label>
    <button class="destroy"></button>
</li>
```


# Aufgabe

Schütze die Methode `renderToString` durch einen Zugriff von außen.
