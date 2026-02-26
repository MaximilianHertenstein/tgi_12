package org.example;

import java.util.ArrayList;
import java.util.List;

public class Utils {


    public static List<Integer> values(List<SimpleEntry> entryList) {
        var values = new ArrayList<Integer>();
        for (var entry : entryList) {
            values.add(entry.value());
        }
        return values;
    }

    public static <T> List<T> dedup(List<T> xs) {
        var acc = new ArrayList<T>();
        for (T x : xs) {
            if (!acc.contains(x)) {
                acc.add(x);
            }
        }
        return acc;
    }

    public static List<String> keySet(List<SimpleEntry> entryList) {
        var keys = new ArrayList<String>();
        for (var entry : entryList) {
            keys.add(entry.key());
        }
        return dedup(keys);
    }

//    public static ArrayList<SimpleEntry> remove(List<SimpleEntry> entryList, String key) {
//        var res = new ArrayList<SimpleEntry>();
//        for (var entry : entryList) {
//            if (!entry.key().equals(key)) {
//                res.add(entry);
//            }
//        }
//        return res;
//    }
//
//    public static ArrayList<SimpleEntry> put(List<SimpleEntry> entryList, String key, int value) {
//        if (!keySet(entryList).contains(key)) {
//            var newEntryList = new ArrayList<SimpleEntry>(entryList);
//            newEntryList.add(new SimpleEntry(key, value));
//            return newEntryList;
//        }
//        var newEntryList = new ArrayList<SimpleEntry>();
//        for (var entry : entryList) {
//            if (entry.key().equals(key)) {
//                newEntryList.add(entry.setValue(value));
//            } else {
//                newEntryList.add(entry);
//            }
//        }
//        return newEntryList;
//    }
//

    private static ArrayList<String> getKeys(List<SimpleEntry> entryList, Integer value) {
        var acc = new ArrayList<String>();
        for (var entry : entryList) {
            if (entry.value() == value) {
                acc.add(entry.key());
            }
        }
        return acc;
    }

    public static List<InvertedEntry> invertedEntries(List<SimpleEntry> entryList) {
        var res = new ArrayList<InvertedEntry>();
        for (var value : dedup(values(entryList))) {
            var keys = getKeys(entryList, value);
            res.add(new InvertedEntry(value, keys));
        }
        return res;
    }


    public static Histogram stringListToHistogram(List<String> list){
        Histogram histogram = new Histogram();
        for (String s : list){
            histogram.count(s);
        }
        return histogram;
    }

}
