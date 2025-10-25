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

    public int size() {
        return entryList.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        entryList = new ArrayList<SimpleEntry>();
    }
    public List<String> keySet() {
        return Utils.keySet(entryList);
    }

    public List<Integer> values() {
        return Utils.values(entryList);
    }

    public boolean containsKey(String key) {
        return keySet().contains(key);
    }

    public boolean containsValue(int value) {
        return values().contains(value);
    }
    public Integer keyToIndex(String key) {
        for (int i = 0; i < entryList.size(); i++) {
            if (entryList.get(i).key().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public Integer get(String key) {
        var index = keyToIndex(key);
        if (index == -1) {
            return -1;
        }
        return entryList.get(index).value();
    }

    public Integer remove(String key) {
        var res = get(key);
        entryList = Utils.remove(entryList, key);
        return res;
    }

    public int put(String key, int value) {
        var res = get(key);
        entryList = Utils.put(entryList, key, value);
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
