package streetsimulator;

/**
 * Obiekt klasy <code>CarDecorator</code> reprezentuje samochód.
 * @author AleksanderSklorz
 */
public class CarDecorator extends VehicleDecorator{
    public CarDecorator(Pedestrian user){
        super(user);
        x = user.x; 
        y = user.y;
        symbol = '[';
        speed = 3;
        milage = 0;
        GameManager.changeBoardField(x, y, symbol);
    }
    /**
     * Przemieszcza samochód w losowym kierunku. Posiada możliwość sprawdzenia czy nastąpił wypadek. 
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
     * Pozwala na opuszczenie samochodu. 
     * @return true jeśli po opuszczeniu samochodu pieszy nie wpada na inny element, false w przeciwnym przypadku.
     */
    public boolean getOff(){
        Pedestrian walker = new Pedestrian(x,y);
        boolean done = walker.move();
        GameManager.getWalkers().add(walker);
        symbol = ']';
        GameManager.changeBoardField(x, y, symbol);
        return done;
    }
}
