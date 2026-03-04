import java.util.ArrayList;
import java.util.List;

public record Node<T>(T content, Node<T> left, Node<T> right) {

    boolean isLeaf(){
        return  left == null && right == null;
    }

    boolean contains(T elem){
        if (left == null && right == null){
            return  content.equals(elem);
        }
        if (left == null){
            return  content.equals(elem) || right.contains(elem);
        }
        if (right == null){
            return  content.equals(elem) || left.contains(elem);
        }
        return  content.equals(elem) || left.contains(elem) || right.contains(elem);
    }

    boolean contains2(T elem) {
        if (content.equals(elem)) {
            return true;
        }
        if (left != null && right != null) {
            return right.contains2(elem) || left.contains2(elem);
        }
        if (right != null) {
            return right.contains2(elem);
        }
        if (left != null) {
            return left.contains2(elem);
        }
        return false;
    }




    int size(){
        if (left == null && right == null){
            return  1;
        }
        if (left == null){
            return  1 +  right.size();
        }
        if (right == null){
            return  1 +  left.size();
        }
        return  1 +  right.size()+ left.size();
    }

    int height(){
        if (left == null && right == null){
            return  1;
        }
        if (left == null){
            return  1 +  right.height();
        }
        if (right == null){
            return  1 +  left.height();
        }
        return  1 +  Math.max(right.height(), left.height());
    }

    int countLeaves(){
        if (left == null && right == null){
            return  1;
        }
        if (left == null){
            return   right.countLeaves();
        }
        if (right == null){
            return  left.countLeaves();
        }
        return  right.countLeaves()+ left.countLeaves();
    }

    int countInnerNodes(){
        if (left == null && right == null){
            return  0;
        }
        if (left == null){
            return   1 + right.countInnerNodes();
        }
        if (right == null){
            return  1+ left.countInnerNodes();
        }
        return  1 + right.countInnerNodes()+ left.countInnerNodes();
    }

    void printInOrder() {
        if (left  != null) left.printInOrder();
        println(content);
        if (right != null) right.printInOrder();
    }

    void printPreOrder() {
        println(content);
        if (left  != null) left.printInOrder();
        if (right != null) right.printInOrder();
    }

    void printInOrder() {
        if (left  != null) left.printInOrder();
        if (right != null) right.printInOrder();
        println(content);
    }




    List<T> toListInOrder() {
        var res = new ArrayList<T>();
        if (left  != null) res.addAll(left.toList());
        res.add(content);
        if (right != null) res.addAll(right.toList());
        return res;
    }

    List<T> toListPreOrder() {
        var res = new ArrayList<T>();
        res.add(content);
        if (left  != null) res.addAll(left.toList());
        if (right != null) res.addAll(right.toList());
        return res;
    }

    List<T> toListPostOrder() {
        var res = new ArrayList<T>();
        if (left  != null) res.addAll(left.toList());
        if (right != null) res.addAll(right.toList());
        res.add(content);
        return res;
    }


    boolean isFull(){
        if (left == null && right == null){
            return  true;
        }
        if (left == null || right == null){
            return  false;
        }
        return  left.isFull() && right.isFull();
    }

    Node<T> invert(){
        if (left == null && right == null){
            return  this;
        }
        if (left == null){
            return  new Node<>(content, right.invert(), null);
        }
        if (right == null){
            return  new Node<>(content, null, left.invert());
        }
        return  new Node<>(content, right.invert(), left.invert());
    }




}
