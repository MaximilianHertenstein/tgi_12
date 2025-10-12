package org.example;

import java.util.*;

public class SimpleMap {
    ArrayList<SimpleEntry> entryList;

    SimpleMap() {
        entryList = new ArrayList<SimpleEntry>();
    }

    SimpleMap(List<SimpleEntry> entryList) {
        this.entryList = new ArrayList<>(entryList);
    }

    public List<SimpleEntry> entryList() {
        return entryList;
    }

    public void clear() {
        entryList = new ArrayList<SimpleEntry>();
    }



    public int size() {
        return entryList.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public List<String> keySet() {
        var keys = new ArrayList<String>();
        for (var entry : entryList) {
            keys.add(entry.key());
        }
        return keys;

    }

    public List<Integer> values() {
        var values = new ArrayList<Integer>();
        for (var entry : entryList) {
            if (!values.contains(entry.value())) {
                values.add(entry.value());
            }

        }
        return values;
    }

    public Integer get(String key) {
        for (var entry : entryList) {
            if (entry.key().equals(key)) {
                return entry.value();
            }
        }
        return -1;
    }

    public boolean containsKey(String key) {
        return keySet().contains(key);
    }

    public boolean containsValue(int value) {
        return values().contains(value);
    }




    public int put(String key, int value) {
        var res = get(key);
        if (res == -1) {
            entryList.add(new SimpleEntry(key, value));
        }
        else {
            entryList = Utils.updateEntry(entryList, key, value);
        }
        return res;

    }


    public Integer remove(String key) {
        var res = get(key);
        entryList = Utils.remove(entryList, key);
        return res;
    }

    public void putAll(SimpleMap other) {
        for (var entry : other.entryList) {
            put(entry.key(), entry.value());
        }
    }

    public List<InvertedEntry> invertedEntries() {
        return Utils.invertedEntries(entryList);
    }

}
