import java.util.ArrayList;
import java.util.List;

class Tree<T>{
    Node<T> root;

    public Tree() {
        this.root = null;
    }

    public Tree(Node<T> root) {
        this.root = root;
    }

    // ===== LEVEL 1: VERY EASY =====
    // === IS EMPTY ===
    public boolean isEmpty(){
        return root == null;
    }

    // ===== LEVEL 2: EASY =====
    // === SIZE ===
    public int size(){
        return Utils.sizeHelper(root);
    }

    // === COUNT LEAVES ===
    public int countLeaves(){
        return Utils.countLeavesHelper(root);
    }

    // === COUNT INNER NODES ===
    public int countInnerNodes(){
        return Utils.countInnerNodesHelper(root);
    }

    // ===== LEVEL 3: MEDIUM =====
    // === HEIGHT ===
    public int height(){
        return Utils.heightHelper(root);
    }

    // === PRINT IN ORDER ===
    public void printInOrder() {
        Utils.printInOrderHelper(root);
    }

    // === PRINT PRE ORDER ===
    public void printPreOrder() {
        Utils.printPreOrderHelper(root);
    }

    // === PRINT POST ORDER ===
    public void printPostOrder() {
        Utils.printPostOrderHelper(root);
    }

    // ===== LEVEL 4: MEDIUM-HARD =====
    // === CONTAINS ===
    public boolean contains(T key){
        if (root == null) {
            return false;
        }
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        return Utils.containsHelper(root, key);
    }

    // === TO LIST IN ORDER ===
    public List<T> toListInOrder() {
        return Utils.toListInOrderHelper(root);
    }

    // === TO LIST PRE ORDER ===
    public List<T> toListPreOrder() {
        return Utils.toListPreOrderHelper(root);
    }

    // === TO LIST POST ORDER ===
    public List<T> toListPostOrder() {
        return Utils.toListPostOrderHelper(root);
    }

    // ===== LEVEL 5: HARD =====
    // === IS FULL ===
    public boolean isFull(){
        return Utils.isFullHelper(root);
    }

    // === INVERT ===
    public Tree<T> invert(){
        return new Tree<>(Utils.invertHelper(root));
    }
}