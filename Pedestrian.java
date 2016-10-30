package streetsimulator;

import java.util.ArrayList;
/**
 * Obiekt klasy <code>Pedestrian</code> reprezentuje pieszego, który oprócz 
 * poruszania się, posiada również zdolność do wsiadania do pojazdów. 
 * @author AleksanderSklorz
 */
public class Pedestrian extends RoadUser{
    public Pedestrian(int x, int y){
        this.x = x;
        this.y = y;
        symbol = '*';
        speed = 1;
        GameManager.changeBoardField(x, y, symbol);
    }
    /**
     * Przemieszcza pieszego w losowym kierunku. Posiada zdolność automatycznego 
     * rozpoznawiania czy wystąpił wypadek oraz czy w pobliżu jest wolny pojazd. 
     * @return wartość logiczna informująca czy przemieszczenie się udało (jeśli zmieniono pozycję lub wsiadło się do pojazdu) lub nie udało (gdy doszło do wypadku)
     */
    public boolean move(){
        super.move();
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
                            if(bike.x == x && bike.y == y) 
                                found = true;
                            if(!found) i++;
                        }while(!found);
                        System.out.println("ROWER");
                        bikes.set(i, new BikeDecorator(this));
                        GameManager.getWalkers().remove(this);
                    }else{
                        ArrayList<CarDecorator> cars = GameManager.getCars();
                        CarDecorator car;
                        do{
                            car = cars.get(i);
                            if(car.x == x && car.y == y)
                                found = true;
                            if(!found) i++;
                        }while(!found);
                        System.out.println("AUTO");
                        cars.set(i, new CarDecorator(this));
                        GameManager.getWalkers().remove(this);
                    }
                }
            }
        }else GameManager.changeBoardField(x, y, symbol);
        return true;
    }
    private boolean isEmptyVehicleNear(){
        char[][] board = GameManager.getBoard();
        return board[y][x] == '>' || board[y][x] == ']';
    }
}
