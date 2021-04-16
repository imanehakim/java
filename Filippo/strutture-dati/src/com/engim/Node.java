package com.engim;

public class Node extends List{
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
    public List addLast(int x) {
        this.next = this.next.addLast(x);
        return this;
    }

    public void addLast2(int x){
        if(this.next instanceof Nil)
            this.next = new Node(x, this.next);
        else
            this.next.addLast2(x);
    }

    @Override

        public List add(int x, int i) {
            if(i == 0) {
                this.next = next.add(this.value, i);
                this.value = x;
            } else {
                this.next = next.add(x, --i);
            }
            return this;
        }


    @Override
    public List remove(int x) {
        if(this.value == x)
            return this.next;
        else
            this.next = next.remove(x);
        return this;
    }

    @Override
    public List removeAtIndex(int i) {
        if (i == 0)
            return this.next;
        else
            this.next = next.removeAtIndex(--i);
        return this;
    }

    @Override
    public List doppio() {
        this.next=next.doppio();
        this.next=
        return this;
    }

    @Override
    public boolean pari() {

        return false;
    }

    @Override
    public boolean ordinata(int next) {


        return true;
    }
}
