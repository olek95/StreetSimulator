package streetsimulator;

import java.util.Random;

public abstract class RoadUser {
    public RoadUser(){
    }
    public int[] move(int x, int y, char symbol, int speed){
        boolean moved = false;
        GameManager.changeBoardField(x, y, ' ');
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
        if(GameManager.getBoard()[y][x] != ' ') return true;
        return false;
    }
    public void przedstawSie(){}
}
