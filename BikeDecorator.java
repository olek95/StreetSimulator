package streetsimulator;

public class BikeDecorator extends VehicleDecorator{
    private final static int SPEED = 2;
    public BikeDecorator(Pedestrian user){
        super(user);
        x = user.getX();
        y = user.getY();
        symbol = '<';
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
        System.out.println("WYSIADL Z ROWERA");
        Pedestrian walker = new Pedestrian(x,y);
        walker.move();
        GameManager.getWalkers().add(walker);
        symbol = '>';
        GameManager.changeBoardField(x, y, symbol);
        return true;
    }
    public static int getSpeed(){
        return SPEED;
    }
}
