package ir.koliber.Game;

/**
 * Created by koliber on 3/18/17.
 */
public class GameTool_Roll {
    int game_size;
    int score = 0;

    public GameTool_Roll(int game_size){
        this.game_size = game_size;
    }

    private int[] roll(int row[]){
        int return_row[] = new int[row.length];
        int before_number_position = 0;
        for(int a = 0 ; a < row.length ; a++){
            if(row[a] != 0){
                if(return_row[before_number_position] == row[a]){
                    return_row[before_number_position] *= 2;
                    score += return_row[before_number_position];
                    before_number_position++;
                }else{
                    if(return_row[before_number_position] == 0){
                        return_row[before_number_position] = row[a];
                    }else{
                        before_number_position++;
                        return_row[before_number_position] = row[a];
                    }
                }
            }
        }
        return return_row;
    }

    private int[] get_row(int matrix[][],int i,int type){
        switch (type){
            case 0 : {
                // left to right
                int row[] = new int[game_size];
                int a = 0;
                for(int k = 0 ; k < game_size ; k++){
                    row[a] = matrix[i][k];
                    a++;
                }
                return row;
            }
            case 1 : {
                // top to bottom
                int row[] = new int[game_size];
                int a = 0;
                for(int k = 0 ; k < game_size ; k++){
                    row[a] = matrix[k][i];
                    a++;
                }
                return row;
            }
            case 2 : {
                // right to left
                int row[] = new int[game_size];
                int a = 0;
                for(int k = game_size -1 ; k >= 0 ; k--){
                    row[a] = matrix[i][k];
                    a++;
                }
                return row;
            }
            case 3 : {
                // bottom to top
                int row[] = new int[game_size];
                int a = 0;
                for(int k = game_size -1 ; k >= 0 ; k--){
                    row[a] = matrix[k][i];
                    a++;
                }
                return row;
            }
            default : {
                return null;
            }
        }
    }

    private int[][] set_row(int matrix[][],int row[],int i , int type){
        switch (type){
            case 0 : {
                // left to right
                for(int k = 0 ; k < game_size ; k++){
                    matrix[i][k] = row[k];
                }
                return matrix;
            }
            case 1 : {
                // top to bottom
                for(int k = 0 ; k < game_size ; k++){
                    matrix[k][i] = row[k];
                }
                return matrix;
            }
            case 2 : {
                // right to left
                for(int k = 0 ; k < game_size ; k++){
                    matrix[i][(game_size-1)-k] = row[k];
                }
                return matrix;
            }
            case 3 : {
                // bottom to top
                for(int k = 0 ; k < game_size ; k++){
                    matrix[(game_size-1)-k][i] = row[k];
                }
                return matrix;
            }
            default : {
                return null;
            }
        }
    }

    private void copy_matrix(int input[][],int output[][]){
        for(int a = 0 ; a < input.length ; a++){
            for(int b = 0 ; b < input[a].length ; b++){
                output[a][b] = input[a][b];
            }
        }
    }

    public int[][] get_rolled_matrix(int matrix[][] , int type){
        int result_matrix[][] = new int[game_size][game_size];
        copy_matrix(matrix,result_matrix);
        for(int a = 0 ; a < game_size ; a++){
            int row[] = get_row(result_matrix,a,type);
            int rolled_row[] = roll(row);
            result_matrix = set_row(result_matrix,rolled_row,a,type);
        }
        return result_matrix;
    }
}
