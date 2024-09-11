package com.example.worksheet1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class UnionFindTest {

    @Test
    void find() {
        UnionFind uf = new UnionFind(15);
        // 1 and 2 are separate sets
        uf.union(1, 2);
        // Testing to see if 2's parent is now 1 after union to test find method
        assertEquals(uf.find(1), uf.find(2));
        //Repeating
        uf.union(2, 3);
        assertEquals(uf.find(2), uf.find(3));

    }

    @Test
    void union() {
        UnionFind uf = new UnionFind(15);
        //There starts with 15 sets in the union find
        // Testing to ensure sets union in to one, size should decrease
        uf.union(1, 5);
        uf.union(2, 14);
        uf.union(8,4);

        //Ensuring the size decreases relative to the sets union
        assertEquals(12,uf.size);
    }

}