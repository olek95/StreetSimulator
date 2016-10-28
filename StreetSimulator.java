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
            /*for(BikeDecorator bike : bikes){
                if(bike.getSymbol() != '>'){
                    done = bike.move();
                    if(!done) {
                        System.out.println("ROWER");
                        break;
                    }
                }
            }*/
            if(!done) break;
            for(CarDecorator car : cars){
                if(car.getSymbol() != ']'){
                    if(car.getMilage() != 5){
                        car.move();
                        car.changeMilage(1);
                        if(!done) {
                            System.out.println("AUTO");
                            break;
                        }
                    }else{
                        car.changeMilage(-5);
                        car.getOff();
                    }
                }
            }
            if(!done) break;
            for(int i = 0; i < walkers.size(); i++){
                done = walkers.get(i).move(); 
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

