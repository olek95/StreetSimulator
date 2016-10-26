package streetsimulator;

public class BikeDecorator extends VehicleDecorator{
    private boolean using = false;
    private final static char SYMBOL = '^';
    private final static int SPEED = 2;
    private int x, y;
    public BikeDecorator(RoadUser user){
        super(user);
        x = 0;
        y = 0;
       // board[y][x] = SYMBOL;
    }
    public void move(){
        int[] newXY = super.move(x, y, SYMBOL, SPEED);
        x = newXY[0];
        y = newXY[1];
    }
    public void przedstawSie(){
        user.przedstawSie();
        System.out.print(" na rowerze.");
    }
}
