package com.company;

import Jama.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        double[][] doubles = new double[1600][1600];
        double[][] v = new double[1600][1];

        for (int i = 0; i < 1600; i++) {
            int ii = i / 40;
            int ij = i % 40;
            if (ij == 39) v[i][0] -= 100;
            if (ii == 39) v[i][0] -= 200;
            if (ij == 0) v[i][0] -= 150;
            if (ii == 0) v[i][0] -= 50;

            for (int j = 0; j < 1600; j++) {
                if (i == j){ doubles[i][j] = -4;}
                int ji = j/40;
                int jj = j%40;
                if (ii+1 == ji && ij == jj) doubles[i][j] = 1;
                if (ii == ji && ij+1 == jj) doubles[i][j] = 1;
                if (ii-1 == ji && ij == jj) doubles[i][j] = 1;
                if (ii == ji && ij-1 == jj) doubles[i][j] = 1;
            }
        }

        Matrix mtablica = new Matrix(doubles);
        Matrix mb = new Matrix(v);
        Matrix result = mtablica.solve(mb);
    }
}

