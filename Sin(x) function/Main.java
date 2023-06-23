
package com.company;

import static java.lang.Math.*;

public class Main {

    public static int FACTORIAL;
    public static double POWER;

    public static void main(String[] args) {

        System.out.println("Przykład szeregu Taylora: ");

        System.out.println("liczenie kąta wyrażonego w radianach PI/10");
        double radians = PI/10 ;
        opis(radians);

        System.out.println();
        System.out.println("liczenie kąta wyrażonego w stopniach (230 stopni)");
        int degrees = 230 ;
        radians = degrees * (PI/180);
        opis(radians);

    }
    public static void opis(double x){
        boolean cwiartka = true;
        x = x % (2 * PI);
        if (x <= PI && x > PI/2) x = PI - x;
        else if (x <= 1.5 * PI && x > PI) {x = x - PI; cwiartka = false;}
        else if (x <= 2 * PI && x > PI * 1.5) {x =  2 * PI - x; cwiartka = false;}
        if (cwiartka) System.out.println("Wartośc kąta :" + sinTaylor(x));
        else System.out.println("Wartośc kąta :" + (sinTaylor(x) * -1));
    }

    public static double sinTaylor(double x){
        double suma = x;
        boolean minus = true;
        double wynikS = sin(x);
        POWER = pow(x,3);
        FACTORIAL = 6;

        for (int i = 1; i <= 10; i ++) {
            if(i % 2 == 1 && i != 1) {
                if (minus) {
                    suma -= POWER / FACTORIAL;
                    minus = false;
                } else {
                    suma += POWER / FACTORIAL;
                    minus = true;
                }
                POWER *= x;
                FACTORIAL *= (i + 1) * (i + 2);
            }
                System.out.println("Wyliczone z szeregu taylora dla " + i + " wyrazów szeregu :" + suma);
                System.out.println("Wartość prawdziwa: " + wynikS);
                System.out.println("Różnica wartości: " + abs(wynikS - suma));
                System.out.println();

        }
        return suma;
    }
}
