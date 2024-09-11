package com.example.worksheet1;

import java.util.Iterator;


public class LinkyIterator<C> implements Iterator<C> {
    private LinkyNode<C> pos;


    public LinkyIterator(LinkyNode<C> fnode){pos=fnode;}

    @Override
    public boolean hasNext(){
        return pos!=null;

    }
    @Override
    public C next() {
        LinkyNode<C> temp = pos;
        pos = pos.next;
        return temp.getContents();
    }





}