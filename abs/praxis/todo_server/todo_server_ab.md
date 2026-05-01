---
title: ToDo-Server-AB
codebraid:
  jupyter: true

---

```{.java .cb-run}





```

## Aufgabe

Füge die folgende Abhängigkeit in deine `build.gradle` ein, wenn du Gradle verwendest:

```kotlin
implementation("io.javalin:javalin:7.2.0")
implementation("org.slf4j:slf4j-simple:2.0.17")
```

Oder füge diese Abhängigkeiten in deine `pom.xml` ein, wenn du Maven verwendest:

```xml
<dependency>
    <groupId>io.javalin</groupId>
    <artifactId>javalin</artifactId>
    <version>7.2.0</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>2.0.17</version>
</dependency>
```


## Aufgabe

Erzeuge in der `main`-Methode mit `Javalin.create` einen Javalin-Server. Starte ihn anschließend, indem du auf dem erzeugten Server-Objekt die Methode `start` aufrufst.



## Aufgabe

Teste den Server, indem du die von IntelliJ angezeigte URL im Browser öffnest.

![](endpoint_not_found.png){ width=50% }

Da noch keine Routen definiert sind, erscheint die Meldung `Endpoint GET / not found`.



## Aufgabe

Definiere in einer neuen Datei `ServerController.java` eine Klasse mit dem Namen `ServerController`.

## Aufgabe

Ergänze in der Datei `ServerController.java` den folgenden Import:
```java
import io.javalin.http.Context;
```

## Aufgabe

Erweitere die Klasse `ServerController` um die Methode `showApp(Context ctx)`. Diese Methode soll Anfragen beantworten, indem sie den String `"Keine ToDos"` zurückgibt.

**Hinweis:** Nutze die Methode `result` der Klasse `Context`.



## Aufgabe

Definiere in einer neuen Datei `JavalinConfigurator.java` eine Klasse mit dem Namen `JavalinConfigurator`.



## Aufgabe

Ergänze die Klasse `JavalinConfigurator` um die statische Methode `configureJavalin(JavalinConfig javalinConfig)`. Diese Methode erzeugt zunächst ein Objekt der Klasse `ServerController`.

Rufe anschließend über die Eigenschaft `routes` des Parameters `javalinConfig` die Methode `get` so auf, dass Anfragen an den Pfad `/todos` von der Methode `showApp` der Klasse `ServerController` beantwortet werden.

## Aufgabe

Passe die `main`-Methode so an, dass der Javalin-Server mit `JavalinConfigurator::configureJavalin` konfiguriert wird.



## Aufgabe

Teste die neue Route.

![](browser.png){ width=100% }



## Aufgabe

Definiere in einer neuen Datei `ServerView.java` eine Klasse mit dem Namen `ServerView`.

## Aufgabe
Ergänze die Klasse `ServerView` um die private Methode `setContentTypeAndSend(Context ctx, String response)`. Sie setzt den HTTP-Header `Content-Type` auf `text/html` und sendet anschließend den übergebenen String als Antwort.

**Hinweis:** Nutze die Methoden `contentType` und `result` der Klasse `Context`.


## Aufgabe

Ergänze die Klasse `ServerController` um ein Attribut vom Typ `ServerView`. Initialisiere es direkt in der Klasse.



## Aufgabe

Ergänze die Klasse `ServerView` um die Methode `showApp(Context ctx, String response)`. Sie sendet den Parameter `response` mithilfe der Methode `setContentTypeAndSend` als Antwort auf die Anfrage.






## Aufgabe

Passe die Methode `showApp` der Klasse `ServerController` so an, dass sie die Methode `showApp` der Klasse `ServerView` verwendet, um den Content-Type auf `text/html` zu setzen und den String `"<h1>Keine To-dos</h1>"` zurückzusenden.

## Aufgabe

Teste die Route `/todos` noch einmal.

![](html_browser.png){ width=100% }



# Implementierung der To-do-App

## Aufgabe

Ergänze die Klasse `ServerController` um ein Attribut vom Typ `Model`. Initialisiere es direkt in der Klasse.


## Aufgabe

Ergänze die Klasse `ServerView` um ein Attribut vom Typ `TemplateRenderer`. Initialisiere es direkt in der Klasse.

## Übersicht

Das folgende Klassendiagramm zeigt die wichtigsten Bestandteile der App und ihre Zusammenarbeit mit `Javalin`.

![](kd.png){ width=100% }

- `UIState` enthält alle Daten, die zum Anzeigen der Oberfläche benötigt werden.
- `Model` verwaltet den Zustand der App. Es stellt Methoden bereit, um To-dos hinzuzufügen, zu löschen und zu ändern. Der aktuelle Zustand wird mit `getUIState` abgefragt.
- `TemplateRenderer` übersetzt den Zustand der App in HTML.
- `ServerView` rendert HTML und sendet dieses als Antwort auf HTTP-Anfragen.
- `ServerController` verarbeitet HTTP-Anfragen und nutzt dafür `ServerView` und `Model`.
- `Javalin` stellt die Methoden `create` und `start` bereit.
- `Main` enthält die Methode `main`, in der die `Javalin`-App erstellt und gestartet wird.
- `Routes` hat die Methoden `get` und `post`, mit denen Routen zur App hinzugefügt werden.
- `JavalinConfig` hat eine Eigenschaft `routes` vom Typ `Routes`.
- `JavalinConfigurator` enthält die Methode `configureJavalin(JavalinConfig javalinConfig)`, in der Routen konfiguriert und mit Methoden von `ServerController` verknüpft werden.


In den nächsten Aufgaben implementierst du weitere Methoden in `ServerView` und `ServerController` und bindest sie in `configureJavalin` ein, damit weitere Routen verarbeitet werden können.



## Aufgabe
Füge der Klasse `ServerView` die Methode `showApp(Context ctx, UIState uiState)` hinzu. Sie soll aus dem `UIState` ein vollständiges HTML-Dokument erzeugen und dieses mit der Methode `setContentTypeAndSend` als Antwort senden.

**Hinweis:** Verwende dazu die Methoden `setContentTypeAndSend` und `renderAppToString` der Klasse `TemplateRenderer`.

## Aufgabe

Lösche die Methode `showApp(Context ctx, String response)` aus der Klasse `ServerView`!


## Aufgabe
Ändere die Methode `showApp(Context ctx)` der Klasse `ServerController`. Die Methode soll die folgenden Schritte durchführen:

- Sie fragt den aktuellen Zustand des Modells mit der Methode `getUIState` der Klasse `Model` ab.
- Sie sendet eine HTML-Darstellung des aktuellen Zustands mit der Methode `showApp` der Klasse `ServerView`.



## Aufgabe

Teste die App.




<!-- ```java
// in main
Javalin app =  Javalin.create();
ServerController serverController = new ServerController();
app.get("/todos", serverController::showApp);
``` -->


 ![](showApp.png){ width=50% }



Die Aufrufe der `Javalin`-Methoden `get` und `post` in `configureJavalin` werden in den nächsten Aufgaben schrittweise ergänzt. Bereits vorhandene Aufrufe werden dabei nicht gelöscht, sondern nur erweitert.


# Verwendung von CSS

## Aufgabe

Wir wollen in unserem Projekt Frontend-Bibliotheken nutzen können. Ergänze dafür in `configureJavalin(JavalinConfig javalinConfig)` einen Aufruf der Methode `enableWebjars` über die Eigenschaft `staticFiles` des Parameters.


## Aufgabe
Zunächst binden wir eine CSS-Bibliothek ein, um das Aussehen unserer App zu verbessern.

Füge dafür, wenn du Gradle verwendest, die folgende Abhängigkeit in `build.gradle` ein:

```kotlin
runtimeOnly("org.webjars.npm:todomvc-app-css:2.4.3")
```

Füge dafür, wenn du Maven verwendest, die folgende Abhängigkeit in `pom.xml` hinzu:

```xml
<dependency>
    <groupId>org.webjars.npm</groupId>
    <artifactId>todomvc-app-css</artifactId>
    <version>2.4.3</version>
</dependency>
```

Ergänze im `head`-Tag in `mainPage.jte` die folgende Zeile, um das CSS einzubinden:


```html
<link rel="stylesheet" href="/webjars/todomvc-app-css/2.4.3/index.css" type="text/css">
```

Starte deine App und lade die Seite erneut.

 ![](showApp2.png){ width=100% }


# Feature: Hinzufügen von To-dos


## Aufgabe
Ergänze die Klasse `ServerController` um die Methode `addToDo(Context ctx)`. Sie soll auf Eingaben über das Formular in `mainPage.jte` reagieren, die eingegebene Aufgabe zum `Model` hinzufügen und anschließend eine HTML-Darstellung des neuen Zustands als Antwort senden.


**Hinweis:** Nutze die Methoden `formParam` der Klasse `Context`, `add` der Klasse `Model` und `showApp` der Klasse `ServerController`.

## Aufgabe

Verknüpfe die POST-Anfrage des Formulars zum Hinzufügen von To-dos mit der Methode `addToDo` der Klasse `ServerController`. Registriere dafür in `JavalinConfigurator` eine passende Route für den Pfad `/todos/new`.

 ![](addToDo.png){ width=100% }



# Verwendung von HTMX




## Aufgabe

Füge die HTMX-Abhängigkeit zu deinem Projekt hinzu.

Für Gradle: Ergänze in `build.gradle` die Zeile:
    
```kotlin
runtimeOnly("org.webjars.npm:htmx.org:2.0.8")
```

Für Maven: Ergänze in `pom.xml`:
```xml
<dependency>
        <groupId>org.webjars.npm</groupId>
        <artifactId>htmx.org</artifactId>
        <version>2.0.8</version>
</dependency>
```

Füge im `head`-Tag von `mainPage.jte` die folgende Zeile ein, damit du HTMX in deinen Templates nutzen kannst:

```html
<script src="/webjars/htmx.org/2.0.8/dist/htmx.min.js"></script>
```

## Hinweis

Bisher sendet `showApp(Context ctx, UIState uiState)` in `ServerView` bei jeder Anfrage immer die **komplette HTML-Seite** zurück. Das ist unnötig, sobald die Seite bereits geladen ist: Bei Aktionen wie Hinzufügen, Löschen oder Statusändern ändert sich nur der Inhalt des folgenden Abschnitts:

```html
<section class="todoapp" id="todoapp">

</section>
```

enthalten ist. Dieser Teil der Website wird mit dem Template `app.jte` gerendert.

HTMX erkennt man daran, dass solche Anfragen automatisch den HTTP-Header `HX-Request` mitschicken. Die Logik soll daher wie folgt funktionieren:


Beim ersten Aufruf der Website wird eine normale HTTP-GET-Anfrage gesendet und es muss die komplette Website angezeigt werden. 
Alle Anfragen, mit denen der Zustand der bereits geladenen App geändert wird, erfolgen per HTMX. Solche Anfragen erkennt man daran, dass sie den HTTP-Header `HX-Request` enthalten. Die Logik soll daher wie folgt funktionieren:

- **Kein `HX-Request`-Header** (normaler Browser-Aufruf): Sende die komplette HTML-Seite.
- **`HX-Request`-Header vorhanden** (HTMX-Anfrage): Sende nur den Inhalt des `<section>`-Tags.

## Aufgabe


Ergänze die Methode `showApp(Context ctx, UIState uiState)` in `ServerView` so, dass dieser Header geprüft wird:

- Wenn er `null` ist, soll die komplette Webseite gesendet werden.
- Wenn er nicht `null` ist, soll nur der Teil im `section`-Tag gesendet werden.

**Hinweise:**

- Die Methode `renderAppToString` der Klasse `TemplateRenderer` erwartet einen `boolean`-Parameter, der steuert, ob nur der Teilbereich (`true`) oder die komplette Seite (`false`) gerendert wird.
- Nutze die Methode `header(String key)` der Klasse `Context`. Diese gibt den Wert des Headers mit dem Schlüssel `key` zurück. Wenn der Header nicht vorhanden ist, wird `null` zurückgegeben.






# Feature: Löschen von To-dos


## Aufgabe

Ergänze das Template `toDo.jte`. Bei einem Klick auf den rechten Button eines To-dos soll eine `POST`-Anfrage gesendet werden. Dadurch wird das To-do gelöscht. Der Zielpfad der Anfrage hängt nur von der ID des jeweiligen To-dos ab.
Beispiele:

- der rechte Button in dem To-do mit der ID $5$ sendet eine POST-Anfrage an `/todos/5/delete`.
- der rechte Button in dem To-do mit der ID $8$ sendet eine POST-Anfrage an `/todos/8/delete`.


Die Serverantwort soll den **Inhalt** des folgenden Elements ersetzen:

```html
<section class="todoapp" id="todoapp">

</section>
```

**Hinweis:** Verwende die HTMX-Attribute `hx-post` und `hx-target`.


## Aufgabe
Ergänze die Klasse `ServerController` um die Methode `deleteToDo(Context ctx)`. Die Methode soll:

1. Den Pfad-Parameter `id` aus der URL auslesen und in einen `int` umwandeln.
2. Das To-do mit dieser ID aus dem `Model` löschen.
3. Den aktuellen Zustand der App als HTML zurücksenden.


**Hinweis:** Nutze die Methode `pathParam` der Klasse `Context`, um den Wert des Pfad-Parameters `id` abzufragen. Nutze außerdem `Integer.parseInt`, `delete` der Klasse `Model` und `showApp` der Klasse `ServerController`.



## Aufgabe

Registriere in `JavalinConfigurator` eine Route, sodass `POST`-Anfragen an `/todos/{id}/delete` von der Methode `deleteToDo` der Klasse `ServerController` verarbeitet werden.


# Feature: Status eines To-dos ändern



## Aufgabe

Ergänze das Template `toDo.jte`. Bei einem Klick auf den linken Button eines To-dos soll eine `POST`-Anfrage gesendet werden. Dadurch wird der Status des To-dos geändert. Der Zielpfad der Anfrage hängt also von der ID des jeweiligen To-dos ab.
Beispiele: 

- das To-do mit der ID $5$ sendet eine POST-Anfrage an `/todos/5/toggle`
- das To-do mit der ID $8$ sendet eine POST-Anfrage an `/todos/8/toggle`


Die Serverantwort soll den **Inhalt** des folgenden Elements ersetzen:
```html
<section class="todoapp" id="todoapp">

</section>
```


## Aufgabe
Ergänze die Klasse `ServerController` um die Methode `toggleStatus(Context ctx)`.
Die Methode soll:

1. Den Pfad-Parameter `id` aus der URL auslesen und in einen `int` umwandeln.
2. Den Status des To-dos mit dieser ID im `Model` umschalten.
3. Den aktuellen Zustand der App als HTML zurücksenden.



**Hinweis:** Nutze die Methode `pathParam` der Klasse `Context`, um den Wert des Pfad-Parameters `id` abzufragen. Nutze außerdem `Integer.parseInt`, `toggle` der Klasse `Model` und `showApp` der Klasse `ServerController`.

## Aufgabe

Registriere in `JavalinConfigurator` eine Route, sodass `POST`-Anfragen an `/todos/{id}/toggle` von der Methode `toggleStatus` der Klasse `ServerController` verarbeitet werden.



# Feature: Alle fertigen To-dos löschen



## Aufgabe

Öffne das Template `app.jte`. Füge dem Button am Ende des Footers ein HTMX-Attribut hinzu, sodass beim Klick eine `POST`-Anfrage an `/todos/clearCompletedToDos` gesendet wird.

Die Serverantwort soll den **Inhalt** des folgenden Elements ersetzen:
```html
<section class="todoapp" id="todoapp">

</section>
```


## Aufgabe

Ergänze die Klasse `ServerController` um die Methode `clearCompletedToDos(Context ctx)`.

Die Methode soll:

1. Alle erledigten To-dos aus dem `Model` entfernen.
2. Den aktuellen Zustand der App als HTML zurücksenden.






**Hinweis:** Nutze die Methode `removeFinishedToDoItems` der Klasse `Model` und `showApp` der Klasse `ServerController`.


## Aufgabe

Registriere in `JavalinConfigurator` eine Route, sodass `POST`-Anfragen an `/todos/clearCompletedToDos` von der Methode `clearCompletedToDos` der Klasse `ServerController` verarbeitet werden.


# Feature: To-dos filtern


## Aufgabe

Ergänze das Template `app.jte`. Bei einem Klick auf einen der Filter-Links in der Mitte des Footers soll eine `POST`-Anfrage gesendet werden. Dadurch wird ein neuer Filter ausgewählt. Der Zielpfad der Anfrage hängt also vom Text des angeklickten Filters ab.
Beispiele:

- ein Klick auf den Link mit dem Text `All` sendet eine POST-Anfrage an `/todos/setFilter/All`.
- ein Klick auf den Link mit dem Text `Completed` sendet eine POST-Anfrage an `/todos/setFilter/Completed`.


Der gesendete HTML-Code soll den Inhalt des Elements

```html
<section class="todoapp" id="todoapp">

</section>
```

ersetzen.

## Aufgabe

Ergänze die Klasse `ServerController` um die Methode `setFilter(Context ctx)`.

Die Methode soll:

1. Den Pfad-Parameter `filter` aus der URL auslesen.
2. Den Filter im `Model` setzen.
3. Den aktuellen Zustand der App als HTML zurücksenden.

**Hinweis:** Nutze die Methoden `pathParam` der Klasse `Context`, um den Wert des Pfad-Parameters `filter` abzufragen. Nutze außerdem `setFilter` der Klasse `Model` und `showApp` der Klasse `ServerController`.

## Aufgabe

Registriere in `JavalinConfigurator` eine Route, sodass `POST`-Anfragen an `/todos/setFilter/{filter}` von der Methode `setFilter` der Klasse `ServerController` verarbeitet werden.

# Feature: Text eines To-dos ändern


## Aufgabe

Öffne das Template `toDo.jte`. Füge dem Text-Element eines To-dos ein HTMX-Attribut hinzu, sodass beim Klick eine `GET`-Anfrage gesendet wird, die ein Bearbeitungsformular anfordert.

- ein Klick auf den Text des To-dos mit der ID 5 sendet eine GET-Anfrage an `/todos/5/edit`
- ein Klick auf den Text des To-dos mit der ID 9 sendet eine GET-Anfrage an `/todos/9/edit`


Die Serverantwort soll das **komplette Listenelement** (`<li>`) des angeklickten To-dos ersetzen.

**Hinweis:** Verwende `hx-get`,  `hx-target` und `hx-swap`, damit das gesamte Listenelement durch die Antwort ersetzt wird.


## Aufgabe


Ergänze die Klasse `ServerView` um die Methode `showToDo(Context ctx, ToDo toDo, boolean editing)`.

Die Methode soll das To-do als HTML rendern und als Antwort senden:

- Ist `editing` gleich `true`: Rendere das Bearbeitungsformular für das To-do.
- Ist `editing` gleich `false`: Rendere die normale Anzeige (Status-Button + Text) des To-dos.


**Hinweis:** Nutze die Methoden `setContentTypeAndSend` der Klasse `ServerView` und `renderToDoToString` der Klasse `TemplateRenderer`.





## Aufgabe

Ergänze die Klasse `ServerController` um die Methode `showEditForm(Context ctx)`. 

Die Methode soll das Bearbeitungsformular für ein To-do zurücksenden:

1. Den Pfad-Parameter `id` aus der URL auslesen und in einen `int` umwandeln.
2. Das To-do mit dieser ID aus dem `Model` abrufen.
3. Das Bearbeitungsformular für dieses To-do als HTML zurücksenden.


**Hinweis:** Nutze die Methoden `pathParam` der Klasse `Context`, um den Wert des Pfad-Parameters `id` abzufragen. Nutze außerdem `Integer.parseInt`, `getToDoItem` der Klasse `Model` und `showToDo` der Klasse `ServerView`.
 <!-- ![](editToDo.png){ width=100% } -->

## Aufgabe

Registriere in `JavalinConfigurator` eine Route, sodass `GET`-Anfragen an `/todos/{id}/edit` von der Methode `showEditForm` der Klasse `ServerController` verarbeitet werden.

## Aufgabe

Öffne das Template `editingForm.jte`. Füge dem Eingabefeld ein HTMX-Attribut hinzu, sodass eine `POST`-Anfrage gesendet wird, sobald das Eingabefeld den Fokus verliert (`blur`-Ereignis).


- das Formular, das zu dem To-do mit der ID $5$ gehört, sendet eine POST-Anfrage an `/todos/5/edit`
- das Formular, das zu dem To-do mit der ID $2$ gehört, sendet eine POST-Anfrage an `/todos/2/edit`

Die Serverantwort soll das **komplette Listenelement** (`<li>`) des bearbeiteten To-dos ersetzen.

**Hinweis:** Verwende `hx-post`, `hx-trigger` und `hx-target`!


## Aufgabe


Ergänze die Klasse `ServerController` um die Methode `updateTextOfToDo(Context ctx)`.

Die Methode soll den Text eines To-dos aktualisieren:
1. Den Pfad-Parameter `id` aus der URL auslesen und in einen `int` umwandeln.
2. Den neuen Text aus dem Formular auslesen.
3. Den Text des To-dos mit dieser ID im `Model` aktualisieren.
4. Das aktualisierte To-do (ohne Bearbeitungsformular) als HTML zurücksenden.



**Hinweis:** Nutze die Methode `pathParam` der Klasse `Context`, um den Wert des Pfad-Parameters `id` abzufragen. Nutze `formParam`, um den gesendeten Text abzufragen. Nutze außerdem `Integer.parseInt`, `updateText` der Klasse `Model`, `getToDoItem` der Klasse `Model` und `showToDo` der Klasse `ServerView`.


## Aufgabe

Registriere in `JavalinConfigurator` eine Route, sodass `POST`-Anfragen an `/todos/{id}/edit` von der Methode `updateTextOfToDo` der Klasse `ServerController` verarbeitet werden.

# Fertigstellen der App

## Aufgabe



Nutze HTMX, damit beim Hinzufügen eines To-dos nicht immer die gesamte Seite neu geladen wird. Ändere dafür das Formular, mit dem To-dos hinzugefügt werden, so dass die Anfrage per HTMX gesendet und die `section` mit der To-do-App durch die Serverantwort aktualisiert wird.

## Aufgabe

Teste deine App.

## Aufgabe

Hilf den anderen Schülerinnen und Schülern!
