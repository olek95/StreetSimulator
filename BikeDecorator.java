package streetsimulator;

/**
 * Obiekt klasy <code>BikeDecorator</code> reprezentuje rower. 
 * @author AleksanderSklorz
 */
public class BikeDecorator extends VehicleDecorator{
    private final static int SPEED = 2;
    public BikeDecorator(Pedestrian user){
        super(user);
        x = user.getX();
        y = user.getY();
        symbol = '<';
        GameManager.changeBoardField(x, y, symbol);
    }
    /**
     * Przemieszcza rower w losowym kierunku. Posiada możliwość sprawdzenia czy nastąpił wypadek. 
     * @return true jeśli przemieszczenie się udało, false jeśli doszło do wypadku. 
     */
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
    /**
     * Pozwala na opuszczenie rowera. 
     * @return true jeśli po opuszczeniu rowera pieszy nie wpada na inny element, false w przeciwnym przypadku.
     */
    public boolean getOff(){
        System.out.println("WYSIADL Z ROWERA");
        Pedestrian walker = new Pedestrian(x,y);
        boolean done = walker.move();
        GameManager.getWalkers().add(walker);
        symbol = '>';
        GameManager.changeBoardField(x, y, symbol);
        return done;
    }
    /**
     * Zwraca prędkość rowera. 
     * @return prędkość rowera. 
     */
    public static int getSpeed(){
        return SPEED;
    }
}
