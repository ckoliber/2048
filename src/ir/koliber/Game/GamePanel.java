package ir.koliber.Game;

import ir.koliber.Tool.ToolPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by koliber on 3/18/17.
 */
public class GamePanel extends JPanel{
    GameTool_Roll roller;
    GameTool_Fill filler;
    ToolPanel tool_panel;
    Graphics graphic;
    int size;
    int nums[] = {0,2,4,8,16,32,64,128,256,512,1024,2048};
    int game[][] = new int[size][size];
    int score = 0;
    int move = 0;

    Color[] colors = {
            new Color(255,51,51),
            new Color(255,153,51),
            new Color(255,255,51),
            new Color(153,255,51),
            new Color(51,255,51),
            new Color(51,255,153),
            new Color(51,255,255),
            new Color(51,153,255),
            new Color(51,51,255),
            new Color(153,51,255),
            new Color(255,51,255),
            new Color(255,51,153)
    };

    GamePanel(int size){
        this.size = size;
        roller = new GameTool_Roll(size);
        filler = new GameTool_Fill(size);
        setSize(500,500);
    }

    public void init_tool_panel(ToolPanel tool_panel){
        this.tool_panel = tool_panel;
    }

    public void restart(){
        game = new int[size][size];
        score = 0;
        move = 0;
        roller.score = 0;
        fillGame();
        repaint();
    }

    private int getColor(int num){
        for(int a = 0 ; a < nums.length ; a++){
            if(num == nums[a]){
                return a;
            }
        }
        return 0;
    }

    public void drawGameFrame(){
        graphic.clearRect(0,0,getWidth(),getHeight());
        graphic.setColor(Color.white);
        graphic.fillRect(getWidth()/(size+2) + 10,getHeight()/(size+2) + 10,getWidth()*size/(size+2) - 10,getHeight()*size/(size+2) - 10);
        for(int a = 0 ; a < game.length ; a++){
            for(int b = 0 ; b < game.length ; b++){
                if(game[b][a] != 0){
                    graphic.setColor(colors[getColor(game[b][a])]);
                    graphic.fill3DRect((a+1)*getWidth()/(size+2) + 5,(b+1)*getHeight()/(size+2) + 5,getWidth()/(size+2) - 10,getHeight()/(size+2) - 10,false);
                    Font font = new Font("Arial",Font.BOLD,getHeight()/((size+2)*4));
                    graphic.setFont(font);
                    graphic.setColor(Color.WHITE);
                    graphic.drawString(game[b][a]+"", (a+1)*getWidth()/(size+2) +getWidth()/((size+2)*2) - getFontMetrics(font).stringWidth(game[b][a]+"")/2 , (b+1)*getHeight()/(size+2) + getHeight()/((size+2)*2-1));
                }
            }
        }
    }

    public void initGame(){
        graphic.setColor(Color.BLACK);
        for(int a = 1 ; a <= (size+1) ; a++){
            graphic.drawLine(getWidth()/(size+2)*a,getHeight()/(size+2),getWidth()/(size+2)*a,getHeight()*(size+1)/(size+2));
        }
        for(int b = 1 ; b <= (size+1) ; b++){
            graphic.drawLine(getWidth()/(size+2),getHeight()/(size+2)*b,getWidth()*(size+1)/(size+2),getHeight()/(size+2)*b);
        }
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        this.graphic = graphics;
        graphics.clearRect(0,0,getWidth(),getHeight());
        drawGameFrame();
        initGame();
    }

    public void rollGame(int type){
        if(!matrixEquals(game,roller.get_rolled_matrix(game,type))){
            game = roller.get_rolled_matrix(game,type);
            this.move++;
            this.score = roller.score;
            fillGame();
            checkEnd();
        }
    }

    private void fillGame(){
        game = filler.get_filled_matrix(game);
    }

    private boolean matrixEquals(int matrix1[][],int matrix2[][]){
        for(int a = 0 ; a < matrix1.length ; a++){
            for(int b = 0 ; b < matrix1[a].length ; b++){
                if(matrix1[a][b] != matrix2[a][b]){
                    return false;
                }
            }
        }
        return true;
    }

    public void checkEnd(){
        if(game[0][0] != 0){
            boolean has_more = false;
            for(int a = 0; a < size ; a++){
                if(!matrixEquals(game,roller.get_rolled_matrix(game,a))){
                    has_more = true;
                    break;
                }
            }
            if(!has_more){
                JOptionPane.showMessageDialog(null,"Game Over !\nScore : "+score+"\nMoves : "+move+"\nTime : "+tool_panel.get_time());
                tool_panel.restart();
                restart();
            }
        }
        for(int a = 0 ; a < size ; a++){
            for(int b = 0 ; b < size ; b++){
                if(game[a][b] == 2048){
                    JOptionPane.showMessageDialog(null,"Game END !\nScore : "+score+"\nMoves : "+move+"\nTime : "+tool_panel.get_time());
                    tool_panel.restart();
                    restart();
                }
            }
        }
        // 0 -> not end !
        // 1 -> end ( game over ) !
        // 2 -> end
    }

}
