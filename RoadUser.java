package streetsimulator;

import java.util.Arrays;
import java.util.Random;

public abstract class RoadUser {
    protected char[][] board = new char[30][120];
    public RoadUser(){
        for(int i = 0; i < board.length; i++)
            for(int k = 0; k < board[i].length; k++)
                board[i][k] = ' ';
    }
    public void move(int x, int y, char symbol, int speed){
        boolean moved = false;
        board[y][x] = ' ';
        Random ran = new Random();
        int direction; 
        do{
            direction = ran.nextInt(4);
            if(direction == 0 && y - 1 >= 0){
                y -= speed;
                board[y][x] = symbol;
                moved = true;
            }else{
                if(direction == 1 && x + 1 <= 120){
                    x += speed;
                    board[y][x] = symbol;
                    moved = true;
                }else{
                    if(direction == 2 && y + 1 <= 30){
                        y += speed;
                        board[y][x] = symbol;
                        moved = true;
                    }else{
                        if(x - 1 >= 0){
                            x += speed;
                            board[y][x] = symbol;
                            moved = true;
                        }
                    }
                }
            }
        }while(!moved);
    }
    public void przedstawSie(){}
    public char[][] getBoard(){
        return board;
    }
}

