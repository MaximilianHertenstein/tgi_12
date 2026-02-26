import java.util.ArrayList;

public record Steuerung (GUI gui,
    ArrayList<Suchwort> dasSuchwort )
    {

    public void pruefeWort(String wort){
        boolean istSuchWort = false;
        for (var sw: dasSuchwort){
            istSuchWort = istSuchWort || sw.pruefeWort(wort);
        }
        var aWG = alleWoerterGefunden();
        if (istSuchWort && aWG){
            gui.zeigeHinweis("Alle Wörter gefungen");
        }
        if (istSuchWort &&!aWG){
            gui.zeigeHinweis("Richtiges Wort");
        }
        else {
            gui.zeigeHinweis("Wort nicht vorhanden");
        }

    }

    public boolean alleWoerterGefunden() {
        for (var sw: dasSuchwort){
            if (!sw.aGefunden()){
                return false;
            }
        }
        return true;
    }
}
