package com.company;
public class Node extends List {
    private int value;
    private List next;

    public Node(int value, List next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        return value + " " + next.toString();
    }

    @Override
    public List add(int x, int i) {
        if(i==0) {
            this.next = new Node(this.value, this.next);
            this.value = x;
        }else{
            this.next = this.next.add(x, i-1);
        }
        return this;
    }

    @Override
    public List remove(int x) {
        if(this.value == x)
            return this.next.remove(x);
        else {
            this.next = this.next.remove(x);
            return this;
        }
    }

    @Override
    public int countOdd() {
        if (this.value % 2 != 0)
            return 1 + next.countOdd();
        else
            return next.countOdd();
    }


    @Override
    public List doubleValue() {
        int doppio = this.value * 2;
        List listaDoppia = this.next.doubleValue();
        return new Node(doppio, listaDoppia);
    }

 @Override
    public boolean equals(Object obj) {
        if(this.value == ((Node)obj).getValue()) {
            return this.next.equals(((Node) obj).getNext());
        }
        else
            return false;
    }

    @Override
    public List getNext(){
        return next;
    }

    public int getValue() {
        return value;
    }

}
