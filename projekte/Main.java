
import org.example.ImmutableLinkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.IO.println;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
//        var x = new ArrayList<Integer>();
//        var y = List.of(1,2,3);


        var xs = ImmutableLinkedList.of(1,2,3);
        var x = 1/0;
        println(xs.get(-1));
    }

