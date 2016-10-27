package streetsimulator;

import java.util.Random;

public abstract class RoadUser {
    protected static char[][] board;
    public RoadUser(){
        if(board == null){
            board = new char[30][120];
            for(int i = 0; i < board.length; i++)
                for(int k = 0; k < board[i].length; k++)
                    board[i][k] = ' ';
        }
    }
    public int[] move(int x, int y, char symbol, int speed){
        boolean moved = false;
        board[y][x] = ' ';
        Random ran = new Random();
        int direction; 
        do{
            direction = ran.nextInt(4);
            if(direction == 0 && y - speed >= 0){
                y -= speed;
                //board[y][x] = symbol;
                moved = true;
            }else{
                if(direction == 1 && x + speed < 120){
                    x += speed;
                    //board[y][x] = symbol;
                    moved = true;
                }else{
                    if(direction == 2 && y + speed < 30){
                        y += speed;
                        //board[y][x] = symbol;
                        moved = true;
                    }else{
                        if(x - speed >= 0){
                            x -= speed;
                            //board[y][x] = symbol;
                            moved = true;
                        }
                    }
                }
            }
        }while(!moved);
        return new int[] {x,y};
    }
    protected boolean isAccident(int x, int y){
        if(board[y][x] != ' ') return true;
        return false;
    }
    public void przedstawSie(){}
    public char[][] getBoard(){
        return board;
    }
    public void setBoard(char[][] board){
        this.board = board;
    }
}
