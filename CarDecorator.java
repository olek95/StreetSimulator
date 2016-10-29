package streetsimulator;

public class CarDecorator extends VehicleDecorator{
    private final static int SPEED = 3;
    public CarDecorator(Pedestrian user){
        super(user);
        x = user.getX(); 
        y = user.getY();
        symbol = '[';
        milage = 0;
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
        System.out.println("WYSIADL Z AUTA");
        Pedestrian walker = new Pedestrian(x,y);
        boolean done = walker.move();
        GameManager.getWalkers().add(walker);
        symbol = ']';
        GameManager.changeBoardField(x, y, symbol);
        return done;
    }
    public static int getSpeed(){
        return SPEED;
    }
}
