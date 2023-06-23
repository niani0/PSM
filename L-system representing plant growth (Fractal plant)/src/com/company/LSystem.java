package com.company;

import processing.core.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Scanner;

public class LSystem extends PApplet {
    static String sentance = "X";
    static Rule r1;
    static Rule r2;
    double len = 400;

    public void settings(){
        size(800, 800);
    }
    public void setup(){
        background(51);
        color(0);
        turtle();
        for (int i = 0; i < 6; i++) {
            generate();
        }
    }
    public void draw(){

    }
    public void generate() {
        len *= 0.5;
        String next = "";
        for (int i = 0; i < sentance.length(); i++) {
            char curr = sentance.charAt(i);
            if (curr == r1.axiom) {
                next += r1.rule;
            } else if (curr == r2.axiom) {
                next += r2.rule;
            } else {
                next += curr;
            }
        }
        sentance = next;
        turtle();
    }
    public void turtle() {
        background(51);
        resetMatrix();
        translate(width/2,height);
        stroke(255);
        Random random = new Random();
        for (int i = 0; i < sentance.length(); i++) {
            char curr = sentance.charAt(i);
            if (curr == 'F') {
                double rand = random.nextDouble();
                line(0, 0, 0, (float) (-len +(len) * rand));
                translate(0, (float) (-len + (len * rand)));
            } else if (curr == '+') {
                rotate(25);
            } else if (curr == '-') {
                rotate(-25);
            } else if (curr == '[') {
                pushMatrix();
            } else if (curr == ']') {
                popMatrix();
            }
        }
    }
    public static void main(String[] args) {
        PApplet.main(LSystem.class);

        r1 = new Rule('X',"F+[[X]-X]-F[-FX]+X");
        r2 = new Rule('F',"FF");
        System.out.println(sentance);
    }
}
