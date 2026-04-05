import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.output.StringOutput;
import gg.jte.resolve.DirectoryCodeResolver;

import static java.lang.IO.println;

void main() {

    var name = "Alex";

    DirectoryCodeResolver htmlCodeResolver = new DirectoryCodeResolver(Path.of("src/main/jte/web"));
    TemplateEngine htmlTemplateEngine = TemplateEngine.create(htmlCodeResolver, ContentType.Html);
    StringOutput stringOutput = new StringOutput();
    htmlTemplateEngine.render("test.jte", name, stringOutput);
    println(stringOutput);
}


