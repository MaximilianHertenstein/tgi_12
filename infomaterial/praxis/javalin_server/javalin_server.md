---
title: Server mit Javalin
codebraid:
  jupyter: true
---

```{.java .cb-run}
%%loadFromPOM
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



# Javalin-Framework

## App erstellen und starten

Das Framework `Javalin` ermöglicht es uns HTTP-Anfragen zu beantworten.
Um es zu nutzen, müssen wir die folgenden Abhängigkeiten in der Datei `build.gradle` ergänzen.


```java
implementation("io.javalin:javalin:7.0.1")
implementation("org.slf4j:slf4j-simple:2.0.17")
```

Wenn du Maven verwendest, benötigst du die folgenden Abhängigkeiten:

```xml
<dependencies>
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
</dependencies>

```



<!-- Anschließend importieren wir die Klasse `Javalin` in der Datei, in der wir eine `Javalin`-Anwendung definieren wollen.

```
import io.javalin.Javalin;
```

Nun können wir mit dem folgenden Code eine Anwendung erzeugen und starten.

```java
Javalin app = Javalin.create();
app.start(7000);
```

Diese warten auf Anfragen an dem Port $7000$. -->

## Behandlung von HTTP-Abfragen definieren


Im nächsten Schritt müssen wir definieren, wie wir Abfragen beantworten wollen.
Dafür definieren wir in einer neuen Klasse eine Methode, die einen Parameter vom Typ `Context` hat.

```{.java .cb-nb line_numbers=false}
import io.javalin.http.Context;

class Controller {
    public void respondHelloWorld(Context ctx){
        ctx.result("Hello World");
    }
}
```

Der `Context` enthält alle Informationen und Methoden, die notwendig sind, um Abfragen zu beantworten.
In diesem Beispiel wird nur die Methode `result` genutzt. Diese schickt den übergebenen String als Antwort auf eine Anfrage.



Als nächstes definieren wir in einer weiteren Klasse `JavalinConfigurator` eine Methode `configure` mit einem Parameter vom Typ `JavalinConfig`. 
<!-- Diese Methode konfiguriert einen `Javalin`-Server. -->


 
```{.java .cb-nb line_numbers=false}
import io.javalin.config.JavalinConfig;
import io.javalin.config.RoutesConfig;

public class JavalinConfigurator {

    public static void configure(JavalinConfig config) {
        var controller = new Controller();
        RoutesConfig routes = config.routes;
        routes.get("/pathToHelloWorld", controller::respondHelloWorld);
    }
}
```

Diese erzeugt einen Controller und ein Objekt `routes` der Klasse `RoutesConfig`. Diese Klasse hat eine Methode `get`.
Ihr wird ein Pfad und eine Methode, die GET-Anfragen an diesen Pfad beantworten soll, übergeben.
In diesem Beispiel wird jede HTTP-GET-Anfrage an die Adresse des Servers und den Pfad `/pathToHelloWorld`
mit der Methode `respondHelloWorld` beantwortet. Die Syntax `controller::respondHelloWorld` sorgt dafür, dass die Methode `respondHelloWorld` an die Methode `get` übergeben wird. Sie wird nicht aufgerufen.



Jetzt können wir in der `main`-Methode mit der statischen Methode `create` ein Objekt der Klasse `Javalin` erzeugen, das mit der eben definierten Methode `JavalinConfigurator.configure` eingestellt wird.

```java
import io.javalin.Javalin;
import org.example.JavalinConfigurator;

void main() {
    Javalin app = Javalin.create(JavalinConfigurator::configure);
    app.start(7070);
}
```



Zunächst läuft die Anwendung auf dem lokalen Server. Dieser hat den Domainnamen `localhost`. 
Wir erreichen unsere Anwendung also unter: `http://localhost:7070/pathToHelloWorld`
 

 ![](browser.png){ width=50% }

## Content-Type für Antworten definieren

Natürlich können wir dem Client auch `HTML`-Code schicken.

```java
// in Controller.java
ctx.result("<h1>Hello World</h1>");
```

Dieser wird zunächst als reiner Text angezeigt:


```
<h1>Hello World</h1>
```

Um zu erreichen, dass dieser korrekt gerendert wird, müssen wir den Content-Type-Header unserer Antwort auf `text/html` setzen.
Dies ist mit der `Context`-Methode `contentType` möglich.

```java
// in Controller.java
ctx.contentType("text/html");
ctx.result("<h1>Hello World</h1>");
```

![](html_browser.png){ width=50% }


# Informationen über die Abfrage erhalten

Die Methode `respondHelloWorld` beantwortet alle HTTP-Abfragen gleich. 
Das muss natürlich nicht so sein.
Die Klasse `Context` stellt einige Methoden bereit, um Details über die HTTP-Abfrage zu erhalten.

## Query-Parameter

Mit der Methode `queryParam` können wir den Wert zu einem bestimmten Query-Parameter abfragen.


```java
// in Controller.java
public void greetUser(Context ctx){
    String userName = ctx.queryParam("userName");
    ctx.result("Hello " + userName);
}
// in JavalinConfigurator.configure
routes.get("/getGreeting", controller::greetUser);
```

Beim Aufruf von
```
http://localhost:7070/getGreeting?userName=Nino
```

wird `Hello Nino` angezeigt.


## Path-Parameter

Mithilfe der Methode `pathParam` können Teile des Pfads abgefragt werden.

```{.java .cb-nb line_numbers=false}
public void greetUserPathParam(Context ctx){
    String userName = ctx.pathParam("userName");
    ctx.result("Hello " + userName);
}
```

Im Pfad müssen wir jetzt einen Parameter `userName` verwenden. Solche `Path-Parameter` werden mit geschweiften Klammern gekennzeichnet.
 
```java
// in JavalinConfigurator.configure
routes.get("/{userName}/greetGreeting/", controller::greetUserPathParam);
```

Beim Aufruf von `http://localhost:7070/Pana/greetGreeting` wird `Hello Pana` angezeigt.


## Form-Parameter

Das folgende Formular verschickt die Eingabe als POST-Request an die relative Adresse `/getGreeting`. 

```html
<form action="/getGreeting" method="post" >
    <input type="text"   name="userName" value="" />
    <button type="submit" > Submit  </button>
</form>
```
Der eingegebene Benutzername wird nicht in der `URL` übermittelt, sondern im `RequestBody` der Abfrage.
Bei der Eingabe des Namens `Frieda` steht im `RequestBody` `userName=Frieda`.

Daten, die über einen POST-Request eines Formulars übermittelt wurden, können mit der Methode `formParam` abgefragt werden.


```java
// in Controller.java
public void greetUserFormParam(Context ctx){
    String userName = ctx.formParam("userName");
    ctx.result("Hello " + userName);
}

// in JavalinConfigurator.configure
routes.post("/getGreeting", controller::greetUserFormParam);
```

Da wir mit dieser Methode POST-Anfragen beantworten, müssen wir die Methode `post` nutzen.


## Header-Informationen abfragen


Die Header einer HTTP-Abfrage können mit der `Context`-Methode `header` abgefragt werden.


```java
String acceptHeader = ctx.header("Accept");
``` 



# Webjars nutzen

Webjars sind Frontend-Bibliotheken, die man in JVM-Programmen nutzen kann. 
Um Webjars in Javalin-Anwendungen nutzen zu können, müssen wir in der Methode `JavalinConfigurator.configure` auf der Eigenschaft `staticFiles` des Parameters `javalinConfig` die Methode `enableWebjars` aufrufen.

```java
// in JavalinConfigurator.configure
javalinConfig.staticFiles.enableWebjars();
```
