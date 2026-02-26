package org.example;

import java.util.ArrayList;

public class SimpleMap {
    ArrayList<SimpleEntry> entryList;

    SimpleMap() {
        entryList = new ArrayList<SimpleEntry>();
    }


    public int keyToIndex(String key) {
        for (int i = 0; i < entryList.size(); i++) {
            if (entryList.get(i).key().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public void put(String key, int value) {
        var index = keyToIndex(key);
        SimpleEntry newEntry = new SimpleEntry(key, value);
        if (index != -1) {
            entryList.set(index, newEntry);
        } else {
            entryList.add(newEntry);
        }
    }

    public Integer get(String key) {
        return  0;


    }
}
