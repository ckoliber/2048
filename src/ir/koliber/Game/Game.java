package ir.koliber.Game;

import ir.koliber.Tool.ToolPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.URL;

/**
 * Created by koliber on 3/18/17.
 */
public class Game extends JFrame implements KeyListener{
    GamePanel game_panel;
    ToolPanel tool_panel;

    public Game(int size){
        addKeyListener(this);
        game_panel = new GamePanel(size);
        tool_panel = new ToolPanel();
        game_panel.init_tool_panel(tool_panel);
        tool_panel.init_game_panel(game_panel);
        JPanel full_panel = new JPanel();
        full_panel.add(tool_panel);
        full_panel.add(game_panel);
        game_panel.setSize(new Dimension(getWidth(),getHeight()-30));
        tool_panel.setSize(new Dimension(getWidth(),30));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setContentPane(full_panel);
        setVisible(true);
        setResizable(false);
        game_panel.setPreferredSize(new Dimension(getWidth(),getHeight()*7/8));
        tool_panel.setPreferredSize(new Dimension(getWidth(),getHeight()/8));
        tool_panel.restart();
        game_panel.restart();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case 37:
                // left
                game_panel.rollGame(0);
                game_panel.repaint();
                break;
            case 38:
                // up
                game_panel.rollGame(1);
                game_panel.repaint();
                break;
            case 39:
                // right
                game_panel.rollGame(2);
                game_panel.repaint();
                break;
            case 40:
                // down
                game_panel.rollGame(3);
                game_panel.repaint();
                break;
        }
        tool_panel.on_game_rolled(game_panel.score,game_panel.move);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

}
