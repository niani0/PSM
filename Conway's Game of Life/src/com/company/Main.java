package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static JButton[][] buttons = new JButton[15][15];
    static Thread t;
    static List<Integer> stillLiving;
    static List<Integer> willBorn;
    static JTextArea textArea = new JTextArea("23");
    static JTextArea textArea2 = new JTextArea("3");
    static JLabel label1 = new JLabel("Still living");
    static JLabel label2 = new JLabel("Will be born");
    static JButton startButton = new JButton("start");;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Of Life");
        JPanel menuPanel = new JPanel();
        JPanel gamePanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6,1));
        frame.setLayout(new GridLayout(1,2));
        gamePanel.setLayout(new GridLayout(15,15));
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener((o)->{
            for (JButton[] button : buttons) {
                for (JButton jButton : button) {
                    jButton.setBackground(Color.white);
                    jButton.setName("white");
                }
            }
            startButton.setText("start");
            stop();
        });
        menuPanel.add(label1);
        menuPanel.add(textArea);
        menuPanel.add(label2);
        menuPanel.add(textArea2);
        customizeMenu(menuPanel);
        addButtons(gamePanel);
        menuPanel.add(clearButton);

        frame.setSize(900,600);
        frame.add(menuPanel);
        frame.add(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void addButtons(JPanel gamePanel){
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.white);
                buttons[i][j].setName("white");
                buttons[i][j].addActionListener(o -> {
                    if (((JButton)o.getSource()).getBackground().equals(Color.white)){
                        ((JButton)o.getSource()).setBackground(Color.black);
                        ((JButton)o.getSource()).setName("black");
                    }
                    else {
                        ((JButton)o.getSource()).setBackground(Color.white);
                        ((JButton)o.getSource()).setName("white");
                    }
                });
                gamePanel.add(buttons[i][j]);
            }
        }
    }
    public static void customizeMenu(JPanel menu){
        startButton.addActionListener(o -> {
            if (((JButton)o.getSource()).getText().equals("start")){
                ((JButton)o.getSource()).setText("stop");
                start();
            }
            else{
                ((JButton)o.getSource()).setText("start");
                stop();
            }
        });
        menu.add(startButton);
    }
    public static void start(){
        stillLiving = new ArrayList<>();
        int text = Integer.parseInt(textArea.getText());
        while (text > 0){
            stillLiving.add(text % 10);
            text /= 10;
        }
        willBorn = new ArrayList<>();
        text = Integer.parseInt(textArea2.getText());
        while (text > 0){
            willBorn.add(text % 10);
            text /= 10;
        }
        t = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                try {
                    Thread.sleep(200);
                    change(stillLiving,willBorn);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        t.start();
    }
    public static void stop(){
        t.interrupt();
    }
    public static void change(List<Integer> stillLiving, List<Integer> willBorn) throws InterruptedException {
        int suma;
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                suma = 0;
                if((i != 0 && j != 0) && (i != buttons.length - 1 && j != buttons.length - 1)) {
                    suma += colorCheck(buttons[i - 1][j - 1]);
                    suma += colorCheck(buttons[i][j - 1]);
                    suma += colorCheck(buttons[i - 1][j]);
                    suma += colorCheck(buttons[i + 1][j - 1]);
                    suma += colorCheck(buttons[i - 1][j + 1]);
                    suma += colorCheck(buttons[i + 1][j]);
                    suma += colorCheck(buttons[i][j + 1]);
                    suma += colorCheck(buttons[i + 1][j + 1]);
                }
                else if(i == 0 && j == 0 ){
                    suma += colorCheck(buttons[buttons.length-1][buttons.length-1]);
                    suma += colorCheck(buttons[i][buttons.length-1]);
                    suma += colorCheck(buttons[buttons.length-1][j]);
                    suma += colorCheck(buttons[i + 1][buttons.length-1]);
                    suma += colorCheck(buttons[buttons.length-1][j + 1]);
                    suma += colorCheck(buttons[i + 1][j]);
                    suma += colorCheck(buttons[i][j + 1]);
                    suma += colorCheck(buttons[i + 1][j + 1]);
                }
                else if(i == buttons.length - 1 && j == buttons[i].length - 1){
                    suma += colorCheck(buttons[i - 1][j - 1]);
                    suma += colorCheck(buttons[i][j - 1]);
                    suma += colorCheck(buttons[i - 1][j]);
                    suma += colorCheck(buttons[0][j - 1]);
                    suma += colorCheck(buttons[i - 1][0]);
                    suma += colorCheck(buttons[0][j]);
                    suma += colorCheck(buttons[i][0]);
                    suma += colorCheck(buttons[0][0]);
                }
                else if(i == 0 && j == buttons[i].length - 1){
                    suma += colorCheck(buttons[buttons.length-1][j - 1]);
                    suma += colorCheck(buttons[i][j - 1]);
                    suma += colorCheck(buttons[buttons.length-1][j]);
                    suma += colorCheck(buttons[i + 1][j - 1]);
                    suma += colorCheck(buttons[buttons.length-1][0]);
                    suma += colorCheck(buttons[i + 1][j]);
                    suma += colorCheck(buttons[i][0]);
                    suma += colorCheck(buttons[i + 1][0]);
                }
                else if(i == buttons.length - 1 && j == 0){
                    suma += colorCheck(buttons[i - 1][buttons.length-1]);
                    suma += colorCheck(buttons[i][buttons.length-1]);
                    suma += colorCheck(buttons[i - 1][j]);
                    suma += colorCheck(buttons[0][buttons.length-1]);
                    suma += colorCheck(buttons[i - 1][j + 1]);
                    suma += colorCheck(buttons[0][j]);
                    suma += colorCheck(buttons[i][j + 1]);
                    suma += colorCheck(buttons[0][j + 1]);
                }
                else if(i == 0) {
                    suma += colorCheck(buttons[buttons.length-1][j - 1]);
                    suma += colorCheck(buttons[i][j - 1]);
                    suma += colorCheck(buttons[buttons.length-1][j]);
                    suma += colorCheck(buttons[i + 1][j - 1]);
                    suma += colorCheck(buttons[buttons.length-1][j + 1]);
                    suma += colorCheck(buttons[i + 1][j]);
                    suma += colorCheck(buttons[i][j + 1]);
                    suma += colorCheck(buttons[i + 1][j + 1]);
                }
                else if(j == 0) {
                    suma += colorCheck(buttons[i - 1][buttons.length-1]);
                    suma += colorCheck(buttons[i][buttons.length-1]);
                    suma += colorCheck(buttons[i - 1][j]);
                    suma += colorCheck(buttons[i + 1][buttons.length-1]);
                    suma += colorCheck(buttons[i - 1][j + 1]);
                    suma += colorCheck(buttons[i + 1][j]);
                    suma += colorCheck(buttons[i][j + 1]);
                    suma += colorCheck(buttons[i + 1][j + 1]);
                }
                else if(i == buttons.length - 1) {
                    suma += colorCheck(buttons[i - 1][j - 1]);
                    suma += colorCheck(buttons[i][j - 1]);
                    suma += colorCheck(buttons[i - 1][j]);
                    suma += colorCheck(buttons[0][j - 1]);
                    suma += colorCheck(buttons[i - 1][j + 1]);
                    suma += colorCheck(buttons[0][j]);
                    suma += colorCheck(buttons[i][j + 1]);
                    suma += colorCheck(buttons[0][j + 1]);
                }
                else if(j == buttons.length - 1) {
                    suma += colorCheck(buttons[i - 1][j - 1]);
                    suma += colorCheck(buttons[i][j - 1]);
                    suma += colorCheck(buttons[i - 1][j]);
                    suma += colorCheck(buttons[i + 1][j - 1]);
                    suma += colorCheck(buttons[i - 1][0]);
                    suma += colorCheck(buttons[i + 1][j]);
                    suma += colorCheck(buttons[i][0]);
                    suma += colorCheck(buttons[i + 1][0]);
                }
                if (willBorn.contains(suma)) if (buttons[i][j].getName().equals("white")) buttons[i][j].setName("black");
                if (!stillLiving.contains(suma)) if (buttons[i][j].getName().equals("black")) buttons[i][j].setName("white");
            }
        }
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (buttons[i][j].getName().equals("black")) buttons[i][j].setBackground(Color.black);
                else buttons[i][j].setBackground(Color.white);

            }
        }
    }
    public static int colorCheck(JButton button){
        if(button.getBackground().equals(Color.black)) return 1;
        else return 0;
    }

}
