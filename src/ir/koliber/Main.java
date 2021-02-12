package ir.koliber;

import ir.koliber.Game.Game;
import ir.koliber.Start.Start;

import javax.swing.*;


public class Main {

    static Start starter;

    public static void main(String[] args) {
        starter = new Start();
        start();
    }

    private static void start(){
        String size_text = starter.get_game_size();
        if(size_text != null){
            try {
                int size = Integer.parseInt(size_text);
                new Game(size);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Game size is integer number !");
                start();
            }
        }
    }
}
