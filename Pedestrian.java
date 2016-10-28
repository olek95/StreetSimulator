package streetsimulator;

import java.util.ArrayList;

public class Pedestrian extends RoadUser{
    private final static char SYMBOL = '*';
    private final static int SPEED = 1;
    private int x, y;
    public Pedestrian(int x, int y){
        this.x = x;
        this.y = y;
        GameManager.changeBoardField(x, y, SYMBOL);
    }
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
                        System.out.println("WSIADLO ROWER");
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
                        System.out.println("WSIADLO AUTO");
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
        System.out.print("Jestem pieszym");
    }
}
