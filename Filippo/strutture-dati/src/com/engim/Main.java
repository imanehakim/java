package com.engim;
import java.util.Collection;
import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {
        List l = new Node(1,new Node(2,new Node(3,new Nil())));
/*
        Nil nil = new Nil();
        Node n3 = new Node(3, nil);
        Node n2 = new Node(2, n3);
        Node n1 = new Node(1, n2);
        List l1 = n1;*/

        System.out.println(l);
        l = new Node(0, l);

        System.out.println(l);

        l.addLast2(4);
        l.add(3,2);
        System.out.println(l);
        l.remove(3);
        System.out.println(l);
        l.removeAtIndex(2);
        System.out.println(l);
        l.doppio();
        System.out.println(l);


    }
}
