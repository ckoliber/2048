package ir.koliber.Tool;

import ir.koliber.Game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by koliber on 3/18/17.
 */
public class ToolPanel extends JPanel{
    GamePanel game_panel;
    JButton restart_button;
    JLabel score_lable;
    JLabel moves_lable;
    JLabel timer_lable;
    JLabel highscore_lable;
    Thread timer_thread;
    int second = 0;
    int highscore = 0;

    public ToolPanel(){
        highscore = get_highscore();
        restart_button = new JButton("Restart");
        restart_button.addActionListener(actionEvent -> {
            game_panel.restart();
            on_game_rolled(0,0);
            second = 0;
        });
        score_lable = new JLabel("SCORE : 0",SwingConstants.CENTER);
        moves_lable = new JLabel("MOVES : 0",SwingConstants.CENTER);
        timer_lable = new JLabel("TIME : 00:00:00",SwingConstants.CENTER);
        highscore_lable = new JLabel("HighScore : "+highscore,SwingConstants.CENTER);
        setLayout(new GridLayout(3,2));
        restart_button.setFocusable(false);
        add(restart_button);
        add(score_lable);
        add(moves_lable);
        add(timer_lable);
        add(highscore_lable);
        timer_thread = new Thread(() -> {
            while(true){
                try {
                    Thread.sleep(1000);
                    second ++;
                    int hour = second%(60*60*24) / (60*60);
                    int min = second%(60*60) / (60);
                    int sec = second%(60);
                    String h,m,s;
                    if(hour < 10){
                        h = "0"+hour;
                    }else{
                        h = hour +"";
                    }
                    if(min < 10){
                        m = "0"+min;
                    }else{
                        m = min +"";
                    }
                    if(sec < 10){
                        s = "0"+sec;
                    }else{
                        s = sec +"";
                    }
                    timer_lable.setText("Time : "+h+":"+m+":"+s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timer_thread.start();
    }

    public void init_game_panel(GamePanel game_panel){
        this.game_panel = game_panel;
    }

    public void on_game_rolled(int score,int move){
        if(score > highscore){
            highscore = score;
            set_highscore(highscore);
            highscore_lable.setText("HighScore : "+highscore);
        }
        score_lable.setText("SCORE : "+score);
        moves_lable.setText("MOVES : "+move);
    }

    private int get_highscore(){
        try{
            String file = "score.txt";
            FileInputStream reader = new FileInputStream(file);
            String page = "";
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = reader.read(buffer)) >= 0){
                page += new String(buffer,0,len);
            }
            return Integer.parseInt(page);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    private void set_highscore(int sc){
        try{
            String file = "score.txt";
            FileOutputStream writer = new FileOutputStream(file);
            writer.write((sc+"").getBytes());
        }catch (Exception e){}
    }

    public String get_time(){
        int hour = second%(60*60*24) / (60*60);
        int min = second%(60*60) / (60);
        int sec = second%(60);
        String h,m,s;
        if(hour < 10){
            h = "0"+hour;
        }else{
            h = hour +"";
        }
        if(min < 10){
            m = "0"+min;
        }else{
            m = min +"";
        }
        if(sec < 10){
            s = "0"+sec;
        }else{
            s = sec +"";
        }
        return h+":"+m+":"+s;
    }

    public void restart(){
        second = 0;
        game_panel.restart();
        on_game_rolled(0,0);
    }

}
