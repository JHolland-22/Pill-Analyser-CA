package com.example.worksheet1;

public class LinkyNode<C> {
    public LinkyNode<C> next = null;
    public C nodeToRemove;
    private C contents;

    public C getContents() {
        return contents;
    }

    public void setContents(C c) {
        contents = c;
    }

    public LinkyNode<C> getNext() {
        return next;
    }

    public void setNext(LinkyNode<C> next) {
        this.next = next;
    }
}
