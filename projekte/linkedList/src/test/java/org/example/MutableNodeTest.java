package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MutableNodeTest {

    @Test
    void content() {
        MutableNode<String> node = new MutableNode<>("hello");
        assertEquals("hello", node.content());
    }

    @Test
    void setContent() {
        MutableNode<String> node = new MutableNode<>("initial");
        node.setContent("updated");
        assertEquals("updated", node.content());
    }

    @Test
    void nextNode() {
        MutableNode<String> next = new MutableNode<>("next");
        MutableNode<String> node = new MutableNode<>("current", next);
        assertSame(next, node.nextNode());
    }

    @Test
    void setNextNode() {
        MutableNode<String> node = new MutableNode<>("current");
        MutableNode<String> next = new MutableNode<>("next");
        node.setNextNode(next);
        assertSame(next, node.nextNode());
    }
}