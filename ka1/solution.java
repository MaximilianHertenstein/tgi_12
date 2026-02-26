public record SimpleEntry(String key, int value) {      // 0.5 pro Eigenschaft
    
    SimpleEntry(String key){                            // 1    
        this(key, 0);                                   // 2        2 von 3 bei korrektem Class-Konstruktor
    }

    SimpleEntry setValue(int newValue) {                    // 1
        return new SimpleEntry(key, newValue);             // 2
    }
}


public record InvertedEntry(int key, List<String> value) {
}


public class Utils {


    public static List<Integer> values(List<SimpleEntry> entryList) {// 0.5
        var values = new ArrayList<Integer>();                      // 1
        for (var entry : entryList) {                               // 1
            values.add(entry.value());                              // 1   
        }
        return values;                                              // 0.5
    }

0.5 wenn value statt value()

    public static <T> List<T> dedup(List<T> xs) {                   // 0.5
        var acc = new ArrayList<T>();                               // 1
        for (T x : xs) {                                            // 1
            if (!acc.contains(x)) {                                 // 1
                acc.add(x);                                         // 1
            }
        }
        return acc;                                                 // 0.5
    }
    
    //return in Schleife -1.5
    //-1 nicht verallgemeinert

    public static List<String> keySet(List<SimpleEntry> entryList) {     // 0.5
        var keys = new ArrayList<String>();                              // 1
        for (var entry : entryList) {                                    // 1
            keys.add(entry.key());                                       // 1
        }
        return dedup(keys);                                              // 0.5
    }

    public static List<InvertedEntry> invertedEntries(List<SimpleEntry> entryList) { 0.5
        var res = new ArrayList<InvertedEntry>();                            // 1
        for (var value : dedup(values(entryList))) {                         // 1.5
            var acc = new ArrayList<String>();                               // 1
            for (var entry : dedup(entryList)) {                             // 1
                if (entry.value() == value) {                               // 1.5
                    acc.add(entry.key());                                   // 1.5
                }
            }
            res.add(new InvertedEntry(value, acc));                            // 1.5
        }
        return res;                                                         // 0.5
    }
}


public class SimpleMap {                
    public ArrayList<SimpleEntry> entryList;      // 1.5 + 0.5

    SimpleMap() {                                   // 0.5         
        entryList = new ArrayList<SimpleEntry>();   // 1.5
    }
    
    -0.5 void

    SimpleMap(List<SimpleEntry> entryList) {            // 0.5
        this.entryList = new ArrayList<>(entryList);    // 1.5
    }
    
    -1 kein Konvertieren

    public List<SimpleEntry> entryList() {            // 0.25
        return entryList;                               // 0.75
    }
    
    -0.5 aufruf von entrylist

    public int size() {                             // 0.25
        return entryList.size();                   // 0.75
    }

    public boolean isEmpty() {                       // 0.25
        return size() == 0;                    // 0.75
    }

    public void clear() {                          // 0.25
        entryList = new ArrayList<SimpleEntry>();   // 1.75
    }
    
    -0.5 wenn lokale Variable geändert wird
    
    public List<String> keySet() {                  //0.25
        return Utils.keySet(entryList);             //1.75
    }

    public List<Integer> values() {                 //0.25
        return Utils.values(entryList);             //1.75
    }

    public boolean containsKey(String key) {        //0.25
        return keySet().contains(key);              //1.75
    }
    
    nur 0.5 wenn contains direkt auf entryList aufgerufen wird

    public Integer keyToIndex(String key) {                 0
        for (int i = 0; i < entryList.size(); i++) {        1
            if (entryList.get(i).key().equals(key)) {       1
                return i;                                   0.5
            }
        }
        return -1;                                          0.5
    }

    public Integer get(String key) {                        0
        var index = keyToIndex(key);                        0.5
        if (index == -1) {                                  0.5
            return -1;                                      0.5
        }
        return entryList.get(index).value();               0.5
    }

    public List<InvertedEntry> invertedEntries() {          0.5
        return Utils.invertedEntries(entryList);            0.5
    }

}
