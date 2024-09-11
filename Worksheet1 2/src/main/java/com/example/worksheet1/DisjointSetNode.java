package com.example.worksheet1;

public class DisjointSetNode<T> {
    private DisjointSetNode<T> parent;
    private T data;

    public DisjointSetNode(T data) {
        this.data = data;
        this.parent = null; // Initialize parent as null
    }

    public DisjointSetNode<T> getParent() {
        return parent;
    }

    public void setParent(DisjointSetNode<T> parent) {
        this.parent = parent;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public double getX() {
        return 0;
    }

    public double getY() {
        return 0;
    }

    public double getWidth() {
        return 0;
    }

    public double getHeight() {
        return 0;
    }

    public Object getColor() {
        return null;
    }
}

//from notes