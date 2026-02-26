package org.example;

import java.util.ArrayList;
import java.util.List;

import static java.lang.IO.println;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        var xs = ImmutableLinkedList.of(1,2,3);
        //println(xs.getNode(3));

        var ys = new ArrayList<>();//List.of(1,2,3));
        ys.add(0,1);


    }
}
