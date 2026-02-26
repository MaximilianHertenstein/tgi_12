package org.example;

import java.util.ArrayList;
import java.util.List;

public class Histogram {
    SimpleMap map = new SimpleMap();

    List<SimpleEntry> getEntries() {
        return map.entryList;
    }

    public void count(String s){
        if (map.containsKey(s)){
            map.put(s, map.get(s)+1);
        }
        else {
            map.put(s, 1);
        }
    }

    public static Histogram of(List<String> list){
        Histogram histogram = new Histogram();
        for (String s : list){
            histogram.count(s);
        }
        return histogram;
    }
}
