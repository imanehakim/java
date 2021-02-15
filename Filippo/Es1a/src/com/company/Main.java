package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        //anno bisestile
        Scanner tastiera = new Scanner(System.in);
        System.out.print("Dammi un anno: ");
        int anno = tastiera.nextInt();
        boolean bisestile = bisestile(anno);
        if (bisestile)
            System.out.println("L'anno " + anno + " e' bisestile");
        else
            System.out.println("L'anno " + anno + " non e' bisestile");

        //array inverso

        System.out.println("Inserisci il numero degli elementi che vuoi inserire nell'Array: ");
        int n = tastiera.nextInt();
        int[] array = new int[n];
        System.out.print("L'array creato è: ");
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = (int) (Math.random() * 100);

            System.out.print(array[i] + " ");
        }
        array = inverse(array);
        System.out.println("L'array inverso è: ");
        for (int i : array) {
            System.out.print(i + " ");
        }


    }

    public static boolean bisestile(int anno) {
        return (anno > 1584 &&
                ((anno % 400 == 0) ||
                        (anno % 4 == 0 && anno % 100 != 0)));
    }


    public static int[] inverse(int[] array) {
        int[] temp = new int[array.length];
        int k = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            temp[k] = array[i];
            System.out.print(temp[k]);
            k++;
        }

        return temp;
    }
}
