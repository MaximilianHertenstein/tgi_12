package org.example;

public class Cat {
    String name;

    public Cat(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        //throw new Ar
        this.name = name;
    }
}
