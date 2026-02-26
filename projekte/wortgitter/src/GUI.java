import static java.lang.IO.println;

public record GUI() {
    void zeigeHinweis(String hinweis){
        println(hinweis);
    }
}
