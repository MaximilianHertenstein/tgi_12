---
title: ToDo-Server-AB
codebraid:
  jupyter: true
---

```{.java .cb-run}





```

# Grundlagen

## Aufgabe

Füge, wenn du `gradle` benutzt, die folgende Abhängigkeit in `build.gradle` hinzu.

```kotlin
implementation("io.javalin:javalin:7.0.1")
```

Füge, wenn du `Maven` benutzt, die folgende Abhängigkeit in `pom.xml` hinzu.


```xml
<dependency>
    <groupId>io.javalin</groupId>
    <artifactId>javalin</artifactId>
    <version>7.0.1</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>2.0.17</version>
</dependency>
```

## Aufgabe

Definiere in einer neuen Datei `ServerView.java` eine Klasse namens `ServerView`. Die Klasse hat ein Attribut vom Typ `TemplateRenderer`. Initialisiere das Attribut direkt in der Klasse.







## Aufgabe
Ergänze die Klasse `ServerView` um eine private Methode `setContentTypeAndSend`. Der Methode werden der `Context` einer Anfrage und ein String übergeben. Sie setzt den HTTP-Header `Content-Type` auf `text/html`. Anschließend schickt sie den übergebenen String als Antwort auf die Anfrage.




## Aufgabe
Ergänze die Klasse `ServerView` um eine Methode `showApp`. Der Methode werden der `Context` einer Anfrage und ein Objekt der Klasse `UIState` übergeben. 
Sie rendert aus dem `UIState` einen String. Dieser String ist ein vollständiges HTML-Dokument und wird anschließend mit der Methode `setContentTypeAndSend` zurückgeschickt.


**Hinweis:** Nutze die Methode `setContentTypeAndSend` und die Methode `renderAppToString` der Klasse `TemplateRenderer`. 


## Aufgabe

Definiere in einer neuen Datei `ServerController.java` eine Klasse namens `ServerController`. 
Die Klasse hat ein Attribut vom Typ `Model` und ein Attribut vom Typ `ServerView`. Initialisiere beide Attribute direkt in der Klasse.


## Aufgabe
Ergänze die Klasse `ServerController` um eine Methode `showApp`. Der Methode wird der `Context` einer Anfrage übergeben. 
Sie sendet eine HTML-Darstellung des aktuellen Zustands als Antwort auf die Anfrage.


**Hinweis:** Nutze die Methode `showApp` der Klasse `ServerView` und die Methode `getUIState` der Klasse `Model`.

## Aufgabe

Definiere in einer neuen Datei `JavalinConfigurator.java` eine Klasse namens `JavalinConfigurator`. 


## Aufgabe 

Ergänze die Klasse `JavalinConfigurator` um eine statische Methode `configureJavalin`. Der Methode wird ein Objekt vom Typ `JavalinConfig` übergeben. Die Methode erzeugt zunächst ein Objekt der Klasse `ServerController`.


Anschließend wird auf der Eigenschaft `routes` des Parameters die Methode `get` so aufgerufen,
dass Anfragen an den Pfad `/todos` mit der Methode `showApp` der Klasse `ServerController` beantwortet werden.

## Aufgabe

Erzeuge in der `main`-Methode einen Javalin-Server, der mit `JavalinConfigurator::configureJavalin` konfiguriert ist. Starte ihn anschließend.




<!-- ```java
// in main
Javalin app =  Javalin.create();
ServerController serverController = new ServerController();
app.get("/todos", serverController::showApp);
``` -->


 ![](showApp.png){ width=50% }



Die Aufrufe der `Javalin`-Methoden `get` und `post` werden in den nächsten Aufgaben ergänzt. Bestehende Aufrufe werden nicht gelöscht.


# Verwendung von CSS

## Aufgabe

Wir wollen in unserem Projekt Frontend-Bibliotheken nutzen können.
Ergänze dafür in `configureJavalin` einen Aufruf der Methode `enableWebjars` auf `javalinConfig.staticFiles`.


## Aufgabe
Zunächst binden wir eine CSS-Bibliothek ein, um das Aussehen unserer App zu verbessern.

Füge dafür die folgende Abhängigkeit in `build.gradle` ein.

```kotlin
runtimeOnly("org.webjars.npm:todomvc-app-css:2.4.3")
```

Oder füge sie in `pom.xml` hinzu.

```xml
<dependency>
    <groupId>org.webjars.npm</groupId>
    <artifactId>todomvc-app-css</artifactId>
    <version>2.4.3</version>
</dependency>
```

Ergänze im `head`-Tag in `mainPage.jte` die folgende Zeile, um den CSS-Code zu importieren.


```html
<link rel="stylesheet" href="/webjars/todomvc-app-css/2.4.3/index.css" type="text/css">
```

Starte deine App und lade die Seite erneut.

 ![](showApp2.png){ width=100% }


# Feature: Hinzufügen eines ToDos


## Aufgabe
Ergänze die Klasse `ServerController` um eine Methode `addToDo`. Der Methode wird der `Context` einer Anfrage übergeben. 
Sie soll auf Eingaben über das Formular in `mainPage.jte` reagieren. Sie fügt die eingegebene Aufgabe dem `Model` hinzu.

Anschließend sendet sie eine HTML-Darstellung des neuen Zustands als Antwort auf die Anfrage.


**Hinweis:** Nutze die Methode `showApp` und die Methode `add` der Klasse `Model`.

## Aufgabe

Verknüpfe die POST-Anfrage des Formulars zum Hinzufügen eines To-dos mit der Methode `ServerController::addToDo`.
Ergänze dafür die Methode `JavalinConfigurator::configureJavalin`.

 ![](addToDo.png){ width=100% }



# Verwendung von HTMX




## Aufgabe

Ergänze in `build.gradle` die Abhängigkeit für HTMX:

```kotlin
runtimeOnly("org.webjars.npm:htmx.org:2.0.8")
```
```xml
<dependency>
    <groupId>org.webjars.npm</groupId>
    <artifactId>htmx.org</artifactId>
    <version>2.0.8</version>
</dependency>
```

Ergänze im `head`-Tag in `mainPage.jte` die folgende Zeile, um die Bibliothek in den Templates verwenden zu können.

```html
<script src="/webjars/htmx.org/2.0.8/dist/htmx.min.js"></script>
```

## Aufgabe

Nach dem ersten Laden der App muss bei weiteren Anfragen nicht mehr das komplette HTML-Dokument mit `head`- und `body`-Tag gesendet werden.
In unserer App erfolgen alle weiteren Anfragen nach dem ersten Laden der Seite mit HTMX. Solche Anfragen haben den Header `HX-Request`. Ergänze die Methode `showApp` in `ServerView` so, dass geprüft wird, ob dieser Header `null` ist. 
- Wenn er `null` ist, wird die komplette Website geschickt. 
- Wenn er nicht `null` ist, wird nur der Teil im `section`-Tag geschickt.

**Hinweis:** Die Methode `renderAppToString` der Klasse `TemplateRenderer` hat bereits einen Parameter, der angibt, ob nur ein Teil der Website gerendert werden soll.


# Feature: Löschen von ToDos


## Aufgabe 

Ergänze das Template `toDo.jte`. Bei einem Klick auf den rechten Button in einem To-do soll eine `POST`-Anfrage gesendet werden. Dadurch soll das To-do gelöscht werden.
Z.B. sendet der rechte Button in dem To-do mit der ID $5$ eine POST-Anfrage an `/todos/5/delete`.



## Aufgabe
Ergänze die Klasse `ServerController` um eine Methode `deleteToDo`. Der Methode wird der `Context` einer Anfrage übergeben. 
Sie reagiert auf Anfragen, die durch das Drücken des rechten Buttons in einem To-do geschickt wurden. Dabei löscht sie das To-do mit der entsprechenden ID.

**Hinweis:** Nutze die Methode `showApp` und die Methode `delete` der Klasse `Model`.



## Aufgabe 
Nutze die Methode `ServerController::deleteToDo`, um auf POST-Abfragen zu reagieren.


# Feature: Status eines ToDos ändern



## Aufgabe 

Ergänze das Template `toDo.jte`. Bei einem Klick auf den linken Button eines To-dos soll eine `POST`-Anfrage gesendet werden. Dadurch wird der Status des To-dos geändert.
Z.B. sendet das To-do mit der ID $5$ eine POST-Anfrage an `/todos/5/toggle`.


## Aufgabe
Ergänze die Klasse `ServerController` um eine Methode `toggleStatus`. Der Methode wird der `Context` einer Anfrage übergeben. 
Sie reagiert auf Anfragen, die durch das Drücken des linken Buttons in einem To-do geschickt wurden, indem sie den Zustand des To-dos mit der entsprechenden ID ändert.

**Hinweis:** Nutze die Methode `showApp` und die Methode `toggle` der Klasse `Model`.

## Aufgabe 
Nutze die Methode `ServerController::toggleStatus`, um auf POST-Abfragen zu reagieren.


# Feature: Alle fertigen ToDos löschen



## Aufgabe 

Ergänze in `app.jte` die Möglichkeit, per Klick auf den Button am Ende des Footers `POST`-Anfragen an `/todos/clearCompletedToDos` zu senden. 

## Aufgabe
Ergänze die Klasse `ServerController` um eine Methode `clearCompletedToDos`. Der Methode wird der `Context` einer Anfrage übergeben. 
Sie reagiert auf Anfragen, die durch das Drücken des Buttons am Ende des Footers geschickt werden, indem sie alle erledigten To-dos löscht.

**Hinweis:** Nutze die Methode `showApp` und die Methode `removeFinishedToDoItems` der Klasse `Model`.


## Aufgabe 
Nutze die Methode `ServerController::clearCompletedToDos`, um auf POST-Abfragen zu reagieren.


# Feature: ToDos filtern


## Aufgabe 

Ergänze das Template `app.jte`. Bei einem Klick auf einen der Filter-Links in der Mitte des Footers soll eine `POST`-Anfrage gesendet werden. Damit wird ein neuer Filter ausgewählt.
Z.B. sendet ein Klick auf den Link mit dem Text `All` eine POST-Abfrage an `/todos/setFilter/All`


## Aufgabe
Ergänze die Klasse `ServerController` um eine Methode `setFilter`. Der Methode wird der `Context` einer Anfrage übergeben. 
Sie reagiert auf Anfragen, die durch das Drücken der Filter-Links in der Mitte des Footers geschickt werden.

**Hinweis:** Nutze die Methode `showApp` und die Methode `setFilter` der Klasse `Model`.

## Aufgabe 
Nutze die Methode `ServerController::setFilter`, um auf POST-Abfragen zu reagieren.


# Feature: Text eines ToDos ändern


## Aufgabe 

Ergänze das Template `toDo.jte`. Bei einem Klick auf den Text eines To-dos wird eine `GET`-Abfrage gesendet. Diese fordert ein Formular an, mit dem der Text des To-dos geändert werden kann. 
Z.B. sendet ein Klick auf den Text des To-dos mit der ID 5 eine GET-Anfrage an `/todos/5/edit`. Das komplette Listenelement soll durch die Serverantwort ersetzt werden.





## Aufgabe
Ergänze die Klasse `ServerView` um eine Methode `showToDo`. Der Methode werden der `Context` einer Anfrage, ein `ToDo` und ein Boolean `editing` übergeben.
Die Methode rendert das `ToDo` und schickt das Ergebnis als Antwort auf die Anfrage. Wenn `editing` `true` ist, wird für das `ToDo` das Formular gerendert, mit dem der Text des To-dos geändert werden kann.
Ansonsten werden der Status und der Text des To-dos angezeigt.

**Hinweis:** Nutze die Methode `setContentTypeAndSend` und die Methode `renderToDoToString` der Klasse `TemplateRenderer`. 





## Aufgabe

Ergänze die Klasse `ServerController` um eine Methode `showEditForm`. Der Methode wird der `Context` einer Anfrage übergeben. 
Sie sendet als Antwort auf die Anfrage ein Formular, das durch einen Klick auf den Text eines To-dos angefordert wurde. 

**Hinweis:** Nutze die Methode `showToDo` und die Methode `getToDoItem` der Klasse `Model`.

 <!-- ![](editToDo.png){ width=100% } -->

## Aufgabe 


Nutze die Methode `ServerController::showEditForm`, um auf GET-Abfragen zu reagieren.


## Aufgabe

Passe `editingForm.jte` so an, dass der Inhalt des To-dos mit einer POST-Abfrage gesendet wird, wenn das Element den Fokus verliert.
Der Pfad hängt dabei von der ID des To-dos ab. Z.B. sendet ein Formular, das zu dem To-do mit der ID $5$ gehört, eine POST-Anfrage an `/todos/5/edit`.
Das komplette Listenelement soll durch die Antwort ersetzt werden.


## Aufgabe

Ergänze die Klasse `ServerController` um eine Methode `updateTextOfToDo`. Der Methode wird der `Context` einer Anfrage übergeben. 
Sie reagiert auf Anfragen, die vom Input-Element in `editingForm` geschickt wurden. Dabei wird der Text des To-dos durch den eingegebenen Text ersetzt.

**Hinweis:** Nutze die Methode `showToDo` und die Methode `updateText` der Klasse `Model`.

## Aufgabe 


Nutze die Methode `ServerController::updateTextOfToDo`, um auf POST-Abfragen zu reagieren.

# Fertigstellen der App

## Aufgabe

Nutze HTMX, um beim Hinzufügen eines To-dos nicht immer die ganze Seite neu zu laden. Ändere dafür das Formular, mit dem To-dos hinzugefügt werden. 

## Aufgabe

Teste deine App!

## Aufgabe

Hilf den anderen SuS!
