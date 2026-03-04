package org.example;

import java.util.ArrayList;
import java.util.List;

import static java.lang.IO.println;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        MutableList<String> list1 = MutableList.of();
        var list2 = MutableList.of("d", "e", "f");
        list1.addAll(list2);
        println(list1.toArrayList());

    }
}
