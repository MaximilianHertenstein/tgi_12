package org.example;

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

    int sumHelper(Node<Integer> node){
        if (node == null){
            return 0;
        }
        return node.content() + sumHelper(node.left()) + sumHelper(node.right());
    }

    int productHelper(Node<Integer> node){
        if (node == null){
            return 1;
        }
        return node.content() * productHelper(node.left()) * productHelper(node.right());
    }


    private int sizeHelper(Node<T> node){
        if (node == null){
            return 0;
        }
        return 1 + sizeHelper(node.left()) + sizeHelper(node.right());
    }

    private int sizeHelper2(Node<T> node){
       return switch (node) {
           case null -> 0;
           case Node(_, var  left, var right) -> 1 + sizeHelper2(left) + sizeHelper2(right);
       };
    }


    // === SIZE ===
    public int size(){
        return sizeHelper(root);
    }

    private int countLeavesHelper(Node<T> node){
        if (node == null){
            return 0;
        }
        if (node.isLeaf()){
            return 1;
        }
        return countLeavesHelper(node.left()) + countLeavesHelper(node.right());
    }

    // === COUNT LEAVES ===
    public int countLeaves(){
        return countLeavesHelper(root);
    }

    private int countInnerNodesHelper(Node<T> node){
        if (node == null){
            return 0;
        }
        if (node.isLeaf()){
            return 0;
        }
        return 1 + countInnerNodesHelper(node.left()) + countInnerNodesHelper(node.right());
    }

    // === COUNT INNER NODES ===
    public int countInnerNodes(){
        return countInnerNodesHelper(root);
    }

    // ===== LEVEL 3: MEDIUM =====
    private int heightHelper(Node<T> node){
        if (node == null){
            return 0;
        }
        if (node.isLeaf()){
            return 1;
        }
        return 1 + Math.max(heightHelper(node.left()), heightHelper(node.right()));
    }

    // === HEIGHT ===
    public int height(){
        return heightHelper(root);
    }

    private void printInOrderHelper(Node<T> node) {
        if (node == null) return;
        printInOrderHelper(node.left());
        System.out.println(node.content());
        printInOrderHelper(node.right());
    }

    // === PRINT IN ORDER ===
    public void printInOrder() {
        printInOrderHelper(root);
    }

    private void printPreOrderHelper(Node<T> node) {
        if (node == null) return;
        System.out.println(node.content());
        printPreOrderHelper(node.left());
        printPreOrderHelper(node.right());
    }

    // === PRINT PRE ORDER ===
    public void printPreOrder() {
        printPreOrderHelper(root);
    }

    private void printPostOrderHelper(Node<T> node) {
        if (node == null) return;
        printPostOrderHelper(node.left());
        printPostOrderHelper(node.right());
        System.out.println(node.content());
    }

    // === PRINT POST ORDER ===
    public void printPostOrder() {
        printPostOrderHelper(root);
    }

    // ===== LEVEL 4: MEDIUM-HARD =====
    private boolean containsHelper(Node<T> node, T key){
        if (node == null) {
            return false;
        }
        return node.content().equals(key) || containsHelper(node.left(), key) || containsHelper(node.right(), key);
    }

    // === CONTAINS ===
    public boolean contains(T key){
        return containsHelper(root, key);
    }

    // ===== LEVEL 5: HARD =====
    private boolean isFullHelper(Node<T> node){
        if (node == null){
            return true;
        }
        if (node.isLeaf()){
            return true;
        }
        if (node.left() == null || node.right() == null){
            return false;
        }
        return isFullHelper(node.left()) && isFullHelper(node.right());
    }

    // === IS FULL ===
    public boolean isFull(){
        return isFullHelper(root);
    }

    private Node<T> invertHelper(Node<T> node){
        if (node == null){
            return null;
        }
        if (node.isLeaf()){
            return node;
        }
        return new Node<>(node.content(), invertHelper(node.right()), invertHelper(node.left()));
    }

    // === INVERT ===
    public Tree<T> invert(){
        return new Tree<>(invertHelper(root));
    }
}