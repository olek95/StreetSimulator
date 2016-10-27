package streetsimulator;

public class CarDecorator extends VehicleDecorator{
    private boolean using = false;
    private final static char SYMBOL = '$';
    private final static int SPEED = 3;
    private int x, y;
    public CarDecorator(Pedestrian user){
        super(user);
        x = user.getX(); 
        y = user.getY();
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
        user.przedstawSie(); 
        System.out.print(" w samochodzie.");
    }
}
