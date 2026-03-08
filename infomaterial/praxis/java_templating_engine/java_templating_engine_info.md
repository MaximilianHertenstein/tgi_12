---
title: Java-Templating-Engine
codebraid:
  jupyter: true
---

```{.java .cb-run}
%%loadFromPOM
<dependency>
    <groupId>gg.jte</groupId>
    <artifactId>jte</artifactId>
    <version>3.2.3</version>
</dependency>
```



# Templating-Engines


Eine Templating-Engine ist ein Programm, das aus einer Vorlage (Template) und einem Wert eine Ausgabe erzeugt.


Z. B. wird aus dem Template

```.html
@param String username

<p> Hallo ${username} </p>
```

und dem Wert 

```.java
"Alex"
```

die Ausgabe

```.html
<p> Hallo Alex </p>
```

erzeugt. Dieser Prozess wird *rendern* genannt.

Wir werden im Unterricht die Java-Templating-Engine (<https://jte.gg/>) verwenden.

# Parameter und Ausdrücke

Wie wir oben gesehen haben, werden die Parameter eines Templates ganz oben definiert.
Die Syntax lautet:

```
@param Typ parametername
```

In dem Template können wir den Parameter mit `${parametername}` verwenden.
Zwischen den geschweiften Klammern können wir beliebige Java-Ausdrücke verwenden.

Z. B.
```.html
@param String username

<p> Hallo ${username + ". Wie geht es dir?"} </p>
```




# Templates rendern

Um in einem Programm Templates zu rendern, müssen wir zunächst das Template in einer Datei speichern.
Dafür bietet es sich an, im Ordner `src/main` einen neuen Ordner mit dem Namen `jte` zu erstellen.
<!-- Dort müssen wir eine leere Datei mit dem Namen `.jteroot` anlegen. -->
Dort speichern wir den Template-Code von oben

```.html
@param String username

<p> Hallo ${username + ". Wie geht es dir?"} </p>
```

unter dem Namen `greetUser.jte` ab.


Anschließend importieren wir die Bibliothek `jte`.

Dafür müssen wir die folgende Zeile im Dependency-Block in `build.gradle` einfügen.


```
implementation("gg.jte:jte:3.2.3")
```

oder in `pom.xml` die folgende Abhängigkeit ergänzen:

```
<dependency>
    <groupId>gg.jte</groupId>
    <artifactId>jte</artifactId>
    <version>3.2.3</version>
</dependency>
```


In der Java-Datei, in der wir die `TemplateEngine` nutzen wollen, müssen wir die folgenden Importe einfügen.

```{.java .cb-nb line_numbers=false}
import java.nio.file.Path;
import gg.jte.resolve.DirectoryCodeResolver;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
```

Dadurch werden die Klassen `Path`, `DirectoryCodeResolver`, `ContentType` und `TemplateEngine` importiert.


Jetzt können wir mit dem folgenden Code eine `TemplateEngine` erzeugen.

\small
```{.java .cb-nb line_numbers=false}
DirectoryCodeResolver htmlCodeResolver = new DirectoryCodeResolver(Path.of("src/main/jte"));
TemplateEngine htmlTemplateEngine = TemplateEngine.create(htmlCodeResolver, ContentType.Html);
```

\normalsize

Diese sucht im eben erstellten Ordner `"src/main/jte"` nach `.jte`-Dateien.
Weil wir `ContentType.Html` verwendet haben, können wir mit dieser `TemplateEngine` HTML-Templates rendern.


Um gerenderte Templates speichern zu können, müssen wir die Klasse `StringOutput` importieren und ein Objekt der Klasse erzeugen.

```{.java .cb-nb line_numbers=false}
import gg.jte.output.StringOutput;

StringOutput stringOutput = new StringOutput();
```

Wir können die `TemplateEngine` nun nutzen, um ein Template zu rendern und den Output in einem String zu speichern.
Dafür übergeben wir der `TemplateEngine`-Methode `render` den Namen des Templates, einen Wert für den Parameter und einen
`StringOutput`.

```java
htmlTemplateEngine.render("greetUser.jte", "Alex", stringOutput);
``` 

Den `Output` können wir anschließend in einen String konvertieren.

```java
stringOutput.toString()
```
```html
<p> Hallo Alex. Wie geht es dir? </p>
```


Der gesamte Code ist noch einmal im folgenden Codeblock zu sehen.

\small
```java
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.output.StringOutput;
import gg.jte.resolve.DirectoryCodeResolver;

import java.nio.file.Path;


void main() {
    DirectoryCodeResolver htmlCodeResolver = new DirectoryCodeResolver(Path.of("src/main/jte"));
    TemplateEngine htmlTemplateEngine = TemplateEngine.create(htmlCodeResolver, ContentType.Html);
    StringOutput stringOutput = new StringOutput();
    htmlTemplateEngine.render("greetUser.jte", "Alex", stringOutput);
    System.out.println(stringOutput);
}
```
\normalsize

# Boolesche Attribute


Die meisten Attribute in HTML benötigen einen Wert. Z. B. wird dem Attribut `href` immer ein Link zugewiesen.

```html
<a href = "https://www.ghse.de">
```

Boolesche Attribute werden einfach nur angegeben.

```html
<input type = "checkbox" checked>
```

oder ausgelassen.

```html
<input type = "checkbox">
```


In `jte`-Templates müssen wir booleschen Attributen einen Wahrheitswert zuweisen.

```html
<input type="checkbox" checked = "${true}">
```

Wenn dieser Wert `true` ist, wird das Attribut angegeben. Ansonsten wird es ausgelassen.

# Templates in anderen Templates aufrufen

Mit `@template.templateName(parametername = wert)` können wir ein Template aus einem anderen Template heraus aufrufen.

```html
<div>
    @template.greetUser(username = "Alice")
</div>
```

```html
<div>
    
<p> Hallo Alice. Wie geht es dir? </p>

</div>
```

# Kontrollstrukturen

In `jte`-Templates können die aus Java bekannten Kontrollstrukturen genutzt werden.
Im Folgenden sind Beispiele für die in Templates wichtigsten Kontrollstrukturen zu sehen.

## Zählerschleifen


**Template:**
```html
@for(int i = 1; i <= 3; i++)
    <p>This is paragraph number ${i}.</p>
@endfor
```

**Ausgabe:**

```html
    <p>This is paragraph number 1.</p>

    <p>This is paragraph number 2.</p>

    <p>This is paragraph number 3.</p>
```

## Schleifen über Listen

**Template:**
```html
@import java.util.List

@for(int x: List.of(1, 3))
    <p>Hello User ${x}.</p>
@endfor
```

**Ausgabe:**

```html
    <p>Hello User 1.</p>

    <p>Hello User 3.</p>
```

## if-else-Statements

**Template:**
```html
@if(2 * 2 == 5)
    <p>true</p>
@else
    <p>not true</p>
@endif
```

**Ausgabe:**

```html
<p>not true</p>
```



