package org.example;


record   Node<T> (T content, Node<T> nextNode) {}
