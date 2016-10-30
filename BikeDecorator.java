package streetsimulator;

/**
 * Obiekt klasy <code>BikeDecorator</code> reprezentuje rower. 
 * @author AleksanderSklorz
 */
public class BikeDecorator extends VehicleDecorator{
    public BikeDecorator(Pedestrian user){
        super(user);
        x = user.x;
        y = user.y;
        symbol = '<';
        speed = 2;
        milage = 0;
        GameManager.changeBoardField(x, y, symbol);
    }
    /**
     * Przemieszcza rower w losowym kierunku. Posiada możliwość sprawdzenia czy nastąpił wypadek. 
     * @return true jeśli przemieszczenie się udało, false jeśli doszło do wypadku. 
     */
    public boolean move(){
        super.move();
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
        Pedestrian walker = new Pedestrian(x,y);
        boolean done = walker.move();
        GameManager.getWalkers().add(walker);
        symbol = '>';
        GameManager.changeBoardField(x, y, symbol);
        return done;
    }
}
