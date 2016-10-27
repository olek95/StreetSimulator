package streetsimulator;

import java.util.ArrayList;

public class StreetSimulator {
    public static void main(String[] args) {
        GameManager.makeGame();
        ArrayList<Pedestrian> walkers = GameManager.getWalkers();
        ArrayList<BikeDecorator> bikes = GameManager.getBikes();
        ArrayList<CarDecorator> cars = GameManager.getCars();
        GameManager.drawBoard(); 
        boolean done = true;
        do{
            System.out.println("----------------------");
            for(BikeDecorator bike : bikes){
                if(bike.getSymbol() != '>'){
                    done = bike.move();
                    if(!done) {
                        System.out.println("ROWER");
                        break;
                    }
                }
            }
            if(!done) break;
            for(CarDecorator car : cars){
                if(car.getSymbol() != ']'){
                    car.move();
                    if(!done) {
                        System.out.println("AUTO");
                        break;
                    }
                }
            }
            if(!done) break;
            for(Pedestrian walker : walkers){
                done = walker.move(); 
                if(!done) {
                    System.out.println("PIESZY");
                    break;
                }
            }
            if(!done) break;
            GameManager.drawBoard(); 
        }while(true);
        GameManager.drawBoard();
    }
}
