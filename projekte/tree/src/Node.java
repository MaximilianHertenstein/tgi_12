import java.util.ArrayList;
import java.util.List;

public record Node<T>(T content, Node<T> left, Node<T> right) {

    boolean isLeaf(){
        return  left == null && right == null;
    }

}
