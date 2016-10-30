package streetsimulator;

import java.util.ArrayList;
/**
 * Obiekt klasy <code>Pedestrian</code> reprezentuje pieszego, który oprócz 
 * poruszania się, posiada również zdolność do wsiadania do pojazdów. 
 * @author AleksanderSklorz
 */
public class Pedestrian extends RoadUser{
    private final static char SYMBOL = '*';
    private final static int SPEED = 1;
    public Pedestrian(int x, int y){
        this.x = x;
        this.y = y;
        GameManager.changeBoardField(x, y, SYMBOL);
    }
    /**
     * Przemieszcza pieszego w losowym kierunku. Posiada zdolność automatycznego 
     * rozpoznawiania czy wystąpił wypadek oraz czy w pobliżu jest wolny pojazd. 
     * @return wartość logiczna informująca czy przemieszczenie się udało (jeśli zmieniono pozycję lub wsiadło się do pojazdu) lub nie udało (gdy doszło do wypadku)
     */
    public boolean move(){
        int[] newXY = super.move(x, y, SYMBOL, SPEED);
        x = newXY[0];
        y = newXY[1];
        if(isAccident(x,y)){
            if(!isEmptyVehicleNear()){
                GameManager.changeBoardField(x, y, 'W');
                return false;
            }else{
                if(isEmptyVehicleNear()){
                    boolean found = false;
                    int i = 0;
                    if(GameManager.getBoard()[y][x] == '>'){
                        ArrayList<BikeDecorator> bikes = GameManager.getBikes();
                        BikeDecorator bike;
                        do{
                            bike = bikes.get(i);
                            if(bike.getX() == x && bike.getY() == y) 
                                found = true;
                            if(!found) i++;
                        }while(!found);
                        bikes.set(i, new BikeDecorator(this));
                        GameManager.getWalkers().remove(this);
                    }else{
                        ArrayList<CarDecorator> cars = GameManager.getCars();
                        CarDecorator car;
                        do{
                            car = cars.get(i);
                            if(car.getX() == x && car.getY() == y)
                                found = true;
                            if(!found) i++;
                        }while(!found);
                        cars.set(i, new CarDecorator(this));
                        GameManager.getWalkers().remove(this);
                    }
                }
            }
        }else GameManager.changeBoardField(x, y, SYMBOL);
        return true;
    }
    private boolean isEmptyVehicleNear(){
        char[][] board = GameManager.getBoard();
        if(board[y][x] == '>' || board[y][x] == ']') return true;
        return false;
    }
    /**
     * Zwraca prędkość pieszego. 
     * @return prędkość pieszego. 
     */
    public static int getSpeed(){
        return SPEED;
    }
}
