package streetsimulator;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class StreetSimulator {
    public static void main(String[] args) {
        System.out.println("W tej grze elementy poruszają się w losowych kierunkach, z tym wyjątkiem "
                + "iż po wykonaniu ruchu nie mogą cofnąć się na wcześniejszą pozycję. "
                + "Piesi są oznaczeni znakiem *, natomiast pojazdy są podzielone na używane i nieużywane, "
                + "gdzie rower używany to <, nieużywany > oraz samochód używany to [ i nieużywany ]. "
                + "W każdej turze element ma możliwość wykonania jednego ruchu - pieszy 1 jednostka, "
                + "rower 2 jednostki, samochod 3 jednostki. Jeśli dowolne dwa użytkownicy drogi wpadną "
                + "na to samo pole - dochodzi do wypadku i końca gry. Wyjątkiem jest sytuacja gdy pieszy "
                + "wchodzi na pole z wolnym rowerem lub samochodem - wtedy do niego wchodzi. "
                + "Jest jeden wyjątek od powyższych reguł - jeśli pieszy wyjdzie z pojazdu, może wykonać "
                + "jeden dodatkowy ruch aby w razie potrzeby uciec od pobliskich elementów, oznacza to że może też z powrotem wejść do pojazdu. "
                + "Klawisz Enter przechodzi do następnego kroku gry. Wciśnij Enter aby rozpocząć...");
        Scanner in = new Scanner(System.in);
        while(!in.nextLine().equals("")){ // czeka na wciśnięcie entera 
        }
        GameManager.makeGame();
        ArrayList<Pedestrian> walkers = GameManager.getWalkers();
        ArrayList<BikeDecorator> bikes = GameManager.getBikes();
        ArrayList<CarDecorator> cars = GameManager.getCars();
        boolean done = true;
        Random rand = new Random();
        do{
            GameManager.drawBoard(); 
            System.out.println("----------------------");
            for(BikeDecorator bike : bikes){
                if(bike.symbol != '>'){
                    if(bike.getMilage() != 5){ // zakładam że po takim dystansie, element losuje czy chce wyjść z pojazdu czy nie
                        done = bike.move();
                        bike.changeMilage(1);
                        if(!done) {
                            System.out.println("ROWER");
                            break;
                        }
                    }else{
                        bike.changeMilage(-5);
                        if(rand.nextBoolean())
                            done = bike.getOff();
                    }
                }
            }
            if(!done) break;
            for(CarDecorator car : cars){
                if(car.symbol != ']'){
                    if(car.getMilage() != 5){
                        done = car.move();
                        car.changeMilage(1);
                        if(!done) {
                            System.out.println("AUTO");
                            break;
                        }
                    }else{
                        car.changeMilage(-5);
                        if(rand.nextBoolean())
                            done = car.getOff();
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
        }while(in.nextLine().equals(""));
        GameManager.drawBoard();
    }
}
