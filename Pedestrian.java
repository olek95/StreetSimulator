package streetsimulator;

public class Pedestrian extends RoadUser{
    private final static char SYMBOL = '*';
    private final static int SPEED = 1;
    private int x, y;
    public Pedestrian(int x, int y){
        super();
        this.x = x;
        this.y = y;
        board[y][x] = SYMBOL;
    }
    public void move(){
        int[] newXY = super.move(x, y, SYMBOL, SPEED);
        x = newXY[0];
        y = newXY[1];
    }
    public void przedstawSie(){
        System.out.print("Jestem pieszym");
    }
}
