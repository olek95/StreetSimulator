package streetsimulator;

import java.util.Random;

public class Pedestrian extends RoadUser{
    private final static char SYMBOL = '*';
    private final static int speed = 1;
    public int x, y;
    public Pedestrian(){
        x = 0;
        y = 0;
        board[y][x] = SYMBOL;
    }
    public void move(){
        //System.out.printf("%5c%3c",SYMBOL, SYMBOL);
        super.move(x, y, SYMBOL, speed);
    }
    public void przedstawSie(){
        System.out.print("Jestem pieszym");
    }
}
