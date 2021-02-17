package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner tastiera=new Scanner(System.in);
        //inizializzazione pin
        int[] pin={2,8,5,6,3,6};
        //inizializzazione array di supporto
        int[] array={0,1,2,3,4,5,6,7,8,9};
        //diachiarazione array random
        int x[] = new int[10];
        int[] y;
        int codifica[] = new int[6];

        y = new int[6];
        for(int i=0;i<x.length;i++){
            System.out.print(array[i]);
        }
        System.out.println();
        //inizializzo array random con cifre da 0 a 3
        for(int i=0;i<x.length;i++){
            x[i]=(int) (Math.random() * 3)+1;
            System.out.print(x[i]);
        }
        //inizializzo array con codifica corretta
        for(int j=0;j<pin.length;j++) {
            codifica[j]=x[pin[j]];
        }
        System.out.println();
	//utente inserisce codifica pin
        for(int i=0;i<pin.length;i++){
            System.out.print("Inserisci cifra "+(i+1)+" del pin");
            y[i]= tastiera.nextInt();
        }

	//stampacodifica corretta solo per varificare che sia effettivamente corretta
        System.out.println();
        for(int i=0;i<pin.length;i++){
            System.out.print(codifica[i]);
        }
        System.out.println();
	//confronta array inserito con array codifica corretta tramite funzione
        boolean verifica1=uguale(y,codifica);
        if (verifica1){
            System.out.print("Il pin è corretto");
        }else{
            System.out.print("Il pin non è corretto");
        }

    }
    public static boolean uguale(int[] a, int[] b){
        if (a.length != b.length) return false;
        for (int i=0; i<a.length; i++){
            if (a[i] != b[i]) return false;
        }
        return true;
    }
}
