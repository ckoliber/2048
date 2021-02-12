package ir.koliber.Game;

import java.util.Random;

/**
 * Created by koliber on 3/18/17.
 */
public class GameTool_Fill {
    int game_size;

    int nums[] = {0,2,4,8,16,32,64,128,256,512,1024,2048};

    public GameTool_Fill(int game_size){
        this.game_size = game_size;
    }

    private boolean is_matrix_fillable(int matrix[][],int a, int b){
        boolean fillable = true;
        for(int i = a-1 ; i <= a+1 ; i++){
            for(int j = b-1 ; j <= b+1 ; j++){
                try{
                    if(matrix[i][j] != 0){
                        fillable = false;
                        break;
                    }
                }catch (Exception ignored){}
            }
        }
        return fillable;
    }

    private boolean is_matrix_necessary_fillable(int matrix[][],int a, int b){
        return matrix[a][b] == 0;
    }

    private void copy_matrix(int input[][],int output[][]){
        for(int a = 0 ; a < input.length ; a++){
            for(int b = 0 ; b < input[a].length ; b++){
                output[a][b] = input[a][b];
            }
        }
    }

    public int[][] get_filled_matrix(int matrix[][]){
        int result_matrix[][] = new int[game_size][game_size];
        copy_matrix(matrix,result_matrix);
        int filled_num = 0;
        for(int a = 0 ; a < game_size ; a++){
            for(int b = 0 ; b < game_size ; b++){
                if(is_matrix_fillable(result_matrix,a,b)){
                    filled_num++;
                    Random r = new Random();
                    int rnd = r.nextInt(5);
                    if(rnd == 0 || rnd == 1){
                        result_matrix[a][b] = nums[0];
                    }else if(rnd == 2 || rnd == 3){
                        result_matrix[a][b] = nums[1];
                    }else{
                        result_matrix[a][b] = nums[2];
                    }
                }
            }
        }
        if(filled_num == 0){
            for(int a = 0 ; a < game_size ; a++){
                for(int b = 0 ; b < game_size ; b++){
                    if(is_matrix_necessary_fillable(result_matrix,a,b)){
                        Random r = new Random();
                        result_matrix[a][b] = nums[r.nextInt(2)];
                    }
                }
            }
        }
        return result_matrix;
    }

}
