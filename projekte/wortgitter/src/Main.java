import static java.lang.IO.println;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    var gui = new    GUI();

    var xs = List.of(new Suchwort("Haskell", false),
            new Suchwort("Java", true),new Suchwort("Python", false));
    var xsA = new ArrayList<>(xs);
    var s = new  Steuerung(gui,xsA);
    s.pruefeWort("Java");
    println(s.alleWoerterGefunden());
}
