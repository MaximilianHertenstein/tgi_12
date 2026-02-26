public record Suchwort(String aWort, boolean aGefunden) {

    public Suchwort(String  wort) {
        this(wort, false);
    }

    public boolean pruefeWort(String wort) {
        return wort.equals(aWort);
    }
}
