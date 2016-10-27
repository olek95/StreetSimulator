package streetsimulator;

public class Pedestrian extends RoadUser{
    private final static char SYMBOL = '*';
    private final static int SPEED = 1;
    private int x, y;
    public Pedestrian(int x, int y){
        this.x = x;
        this.y = y;
        board[y][x] = SYMBOL;
    }
    public boolean move(){
        int[] newXY = super.move(x, y, SYMBOL, SPEED);
        x = newXY[0];
        y = newXY[1];
        if(isAccident(x,y)){
            board[y][x] = 'W';
            return false;
        }
        board[y][x] = SYMBOL;
        return true;
    }
    public int getX(){
        return x; 
    }
    public int getY(){
        return y; 
    }
    public static int getSpeed(){
        return SPEED;
    }
    public void przedstawSie(){
        System.out.print("Jestem pieszym");
    }
}
