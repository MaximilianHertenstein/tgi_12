package org.example;

public record SimpleEntry(String key, int value) {
    SimpleEntry(String key){
        this(key, 0);
    }

    SimpleEntry setValue(int newValue) {
        return new SimpleEntry(key, newValue);
    }
}
