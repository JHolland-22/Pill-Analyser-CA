package com.example.worksheet1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisjointSetNodeTest {

    @Test
    void getParent() {
        // Create a node with initial data and check if the parent is null initially
        DisjointSetNode<Integer> node = new DisjointSetNode<>(5);
        assertNull(node.getParent(), "Parent should be null by default");

        // Set a new node as the parent and verify if it is returned correctly
        DisjointSetNode<Integer> parent = new DisjointSetNode<>(10);
        node.setParent(parent);
        assertSame(parent, node.getParent(), "getParent should return the same object set by setParent");
    }

    @Test
    void setParent() {
        // Create a node and a separate parent node
        DisjointSetNode<Integer> node = new DisjointSetNode<>(5);
        DisjointSetNode<Integer> parent = new DisjointSetNode<>(10);

        // Set the parent for the node and verify using getParent
        node.setParent(parent);
        assertSame(parent, node.getParent(), "The parent should be correctly set and retrievable");
    }

    @Test
    void getData() {
        // Create a node with specific data and check if the same data is returned
        DisjointSetNode<String> node = new DisjointSetNode<>("data");
        assertEquals("data", node.getData(), "getData should return the data that was set in constructor");
    }
    @Test
    void setData() {
        // Create a node and set its initial data
        DisjointSetNode<String> node = new DisjointSetNode<>("initial");

        // Update the data and verify the update is correct
        node.setData("updated");
        assertEquals("updated", node.getData(), "setData should update the node's data correctly and be retrievable");
    }
}
