package org.example;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Integer> values(List<SimpleEntry> entryList) {
        var values = new ArrayList<Integer>();
        for (var entry : entryList) {
            if (!values.contains(entry.value())) {
                values.add(entry.value());
            }
        }
        return values;
    }

    public static ArrayList<SimpleEntry> updateEntry(List<SimpleEntry> entryList, String key, int value) {
        var newEntryList = new ArrayList<SimpleEntry>();
        for (var entry : entryList) {
            if (entry.key().equals(key)) {
                newEntryList.add(entry.setValue(value));
            }
            else {
                newEntryList.add(entry);
            }
        }
        return newEntryList;
    }

    public static ArrayList<SimpleEntry> remove(List<SimpleEntry> entryList, String key) {
        var res = new ArrayList<SimpleEntry>();
        for (var entry : entryList) {
            if (!entry.key().equals(key)) {
                res.add(entry);
            }
        }
        return res;
    }


    public static List<InvertedEntry> invertedEntries(List<SimpleEntry> entryList) {
        var res = new ArrayList<InvertedEntry>();
        for (var value : values(entryList)) {
            var acc = new ArrayList<String>();
            for (var entry : entryList) {
                if (entry.value() == value) {
                    acc.add(entry.key());
                }
            }
            res.add(new InvertedEntry(value, acc));
        }
        return res;
    }


}
