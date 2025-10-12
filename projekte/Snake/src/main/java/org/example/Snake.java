package org.example;

import java.util.ArrayList;
import java.util.List;

public record Snake(
        V2 head,
        List<V2> tail,
        boolean digesting
) {

    public Snake(V2 head) {
        this(head, List.of(), false);
    }

    public List<V2> getCoordinates() {
        var res = new ArrayList<V2>();
        res.add(head);
        for (var v2 : tail) {
            res.add(v2);
        }
        return res;
    }

    public boolean tailBitten() {
        return tail.contains(head);
    }

    public List<V2> computeNewTail(){
        if (!digesting) {
            return Utils.dropLast(getCoordinates());
        }
        return getCoordinates();
    }
    public Snake move(V2 direction, V2 applePosition) {
        var newSnakeHead = direction.plus(head);
        var newDigesting = newSnakeHead.equals(applePosition);
        var newSnakeBody = computeNewTail();
        return new Snake(newSnakeHead, newSnakeBody, newDigesting);
    }


}