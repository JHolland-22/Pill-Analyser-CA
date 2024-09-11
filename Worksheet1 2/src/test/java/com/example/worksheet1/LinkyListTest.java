package com.example.worksheet1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkyListTest {

    @Test
    void add() {
        //Testing successful add
        LinkyList list = new LinkyList();
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals(3, list.getSize());
    }

    @Test
    void remove() {
        LinkyList list = new LinkyList();
        list.remove("A");
        list.remove("B");
        list.remove("C");

        assertEquals(0, list.getSize());

    }

}