package org.example;

import static java.lang.IO.println;

public class Utils {
    void printLinkedList(ImmutableLinkedList<String> xs){
        var it = xs.iterator();
        while (it.hasNext()){
            println(it.next());
        }
    }
}
