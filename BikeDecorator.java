package streetsimulator;

public class BikeDecorator extends VehicleDecorator{
    private char symbol = '<';
    private final static int SPEED = 2;
    private int x, y;
    public BikeDecorator(Pedestrian user){
        super(user);
        x = user.getX();
        y = user.getY();
        GameManager.changeBoardField(x, y, symbol);
    }
    public boolean move(){
        int[] newXY = super.move(x, y, symbol, SPEED);
        x = newXY[0];
        y = newXY[1];
        if(isAccident(x,y)){
            GameManager.changeBoardField(x, y, 'W');
            return false;
        }
        GameManager.changeBoardField(x, y, symbol);
        return true;
    }
    public boolean getOff(){
        //using = false;
        boolean done = ((Pedestrian)user).move(); 
        System.out.println("PIESZY");
        return done;
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
    public void setSymbol(char symbol){
        this.symbol = symbol;
    }
    public char getSymbol(){
        return symbol;
    }
    public void przedstawSie(){
        user.przedstawSie();
        System.out.print(" na rowerze.");
    }
}
