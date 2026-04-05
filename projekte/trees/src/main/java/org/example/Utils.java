package org.example;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    // === SIZE ===
    public static <T> int sizeHelper(Node<T> node){
        if (node == null){
            return 0;
        }
        return 1 + sizeHelper(node.left()) + sizeHelper(node.right());
    }

    // === COUNT LEAVES ===
    public static <T> int countLeavesHelper(Node<T> node){
        if (node == null){
            return 0;
        }
        if (node.isLeaf()){
            return 1;
        }
        return countLeavesHelper(node.left()) + countLeavesHelper(node.right());
    }

    // === COUNT INNER NODES ===
    public static <T> int countInnerNodesHelper(Node<T> node){
        if (node == null){
            return 0;
        }
        if (node.isLeaf()){
            return 0;
        }
        return 1 + countInnerNodesHelper(node.left()) + countInnerNodesHelper(node.right());
    }

    // === HEIGHT ===
    public static <T> int heightHelper(Node<T> node){
        if (node == null){
            return 0;
        }
        if (node.isLeaf()){
            return 1;
        }
        return 1 + Math.max(heightHelper(node.left()), heightHelper(node.right()));
    }

    // === PRINT IN ORDER ===
    public static <T> void printInOrderHelper(Node<T> node) {
        if (node == null) return;
        printInOrderHelper(node.left());
        System.out.println(node.content());
        printInOrderHelper(node.right());
    }

    // === PRINT PRE ORDER ===
    public static <T> void printPreOrderHelper(Node<T> node) {
        if (node == null) return;
        System.out.println(node.content());
        printPreOrderHelper(node.left());
        printPreOrderHelper(node.right());
    }

    // === PRINT POST ORDER ===
    public static <T> void printPostOrderHelper(Node<T> node) {
        if (node == null) return;
        printPostOrderHelper(node.left());
        printPostOrderHelper(node.right());
        System.out.println(node.content());
    }

    // === CONTAINS ===
    public static <T> boolean containsHelper(Node<T> node, T key){
        if (node == null) {
            return false;
        }
        return node.content().equals(key) || containsHelper(node.left(), key) || containsHelper(node.right(), key);
    }

    // === TO LIST IN ORDER ===
    public static <T> List<T> toListInOrderHelper(Node<T> node) {
        var res = new ArrayList<T>();
        if (node == null) return res;
        res.addAll(toListInOrderHelper(node.left()));
        res.add(node.content());
        res.addAll(toListInOrderHelper(node.right()));
        return res;
    }

    // === TO LIST PRE ORDER ===
    public static <T> List<T> toListPreOrderHelper(Node<T> node) {
        var res = new ArrayList<T>();
        if (node == null) return res;
        res.add(node.content());
        res.addAll(toListPreOrderHelper(node.left()));
        res.addAll(toListPreOrderHelper(node.right()));
        return res;
    }

    // === TO LIST POST ORDER ===
    public static <T> List<T> toListPostOrderHelper(Node<T> node) {
        var res = new ArrayList<T>();
        if (node == null) return res;
        res.addAll(toListPostOrderHelper(node.left()));
        res.addAll(toListPostOrderHelper(node.right()));
        res.add(node.content());
        return res;
    }

    // === IS FULL ===
    public static <T> boolean isFullHelper(Node<T> node){
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

    // === INVERT ===
    public static <T> Node<T> invertHelper(Node<T> node){
        if (node == null){
            return null;
        }
        if (node.isLeaf()){
            return node;
        }
        return new Node<>(node.content(), invertHelper(node.right()), invertHelper(node.left()));
    }
}

