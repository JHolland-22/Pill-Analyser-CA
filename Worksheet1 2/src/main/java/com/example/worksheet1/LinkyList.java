package com.example.worksheet1;

import java.util.Iterator;

public class LinkyList<C> implements Iterable<C> {
    public LinkyNode<C> head = null;

    private int size = 0;


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean add(C contents) {
        LinkyNode<C> BinkyNode = new LinkyNode<C>();// Create a new node called binkynode
        BinkyNode.setContents(contents);// Set the contents of the binkynode
        BinkyNode.next=head;
        head = BinkyNode; // Update the head to be the binkynode
        size++;
        return true;
    }


    /* public boolean remove(C nodeToRemove) {
         LinkyNode<C> temp=head, prev=null;// Start at the head of the linked list
         while (temp.next != null) { // Travels through the linked list
             if (temp.next == nodeToRemove) {
                 temp.next = temp.next.next;
                 size --;// If the next node is the one to remove, update the "next" pointer to skip it
                 return true;// Return the removed node
             }
             temp = temp.next;// Move to the next node
         }
         return false; // Node not found
     }


     */
    public boolean remove(C nodeToRemove) {
        LinkyNode<C> temp=head, prev=null;
        //if (temp.nodeToRemove!=null)
        while(temp!=null &&
                !temp.getContents().equals(nodeToRemove)) {
            prev=temp;
            temp=temp.next;
        }
        if(temp!=null) {
            if(prev==null) head=temp.next;
            else prev.next=temp.next;
            size --;
            return true;
        }
        return false;
    }





    public boolean isValidIndex(LinkyList<C> list,int index) {
        return (index >= 0) && (index < list.getSize());
    }


    public boolean isValidToAdd(LinkyList<C> list,int index){
        if (list == null) {
            return false; // List is null, can't add.
        }
        if (index < 0 || index > list.getSize()) {
            return false; // Index is out of bounds.
        }
        return true;
    }


    public boolean isValidToRemove(LinkyList<C> list, int index) {
        LinkyNode<C> pos = head;
        if (list == null) {
            return false;
        }
        if (index < 0 || index >= list.getSize()) {
            return false;
        }
        return true;
    }
    public boolean search(C contents) {
        LinkyNode<C> pos = head;
        while (pos != null) { // Travels through the linked list
            if (pos == search()) ;
            {
                if (search().getContents().equals(contents)) ;
                return true;
            }
        }
        return false;
    }

    public LinkyNode<C> search() {
        return null;
    }

    @Override
    public Iterator<C> iterator() {
        return new com.example.worksheet1.LinkyIterator<>(head);
    }


    public void clear() {

    }
}


