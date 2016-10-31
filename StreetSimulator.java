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
                + "na to samo pole - dochodzi do wypadku i końca gry. Wypadek oznaczony jest przez W. Wyjątkiem jest sytuacja gdy pieszy "
                + "wchodzi na pole z wolnym rowerem lub samochodem - wtedy do niego wchodzi. "
                + "Jest jeden wyjątek od powyższych reguł - jeśli pieszy wyjdzie z pojazdu, może wykonać "
                + "jeden dodatkowy ruch aby w razie potrzeby uciec od pobliskich elementów, oznacza to że może też z powrotem wejść do pojazdu. "
                + "Klawisz Enter przechodzi do następnego kroku gry. Aby wyłaczyć pracę krokową podczas gry wpisz spację i wciśnij Enter. Wciśnij Enter aby rozpocząć...");
        Scanner in = new Scanner(System.in);
        while(!in.nextLine().equals("")){ // czeka na wciśnięcie entera 
        }
        System.out.println("Domyślne rozmiary planszy do gry 79x21. Jeśli chcesz je zmienić wpisz T, aby zastosować domyślne rozmiary "
                + "wpisz N (należy zmienić rozmiar szczególnie wtedy, jeśli wymiary konsoli uniemożliwią wyświetlenie całej planszy).");
        char change;
        RoadUser lost = null;
        do{
            change = Character.toUpperCase(in.next().charAt(0));
        }while(change != 'T' && change != 'N');
        if(change == 'N') GameManager.makeGame(79, 21);
        else{
            System.out.print("Podaj szerokość: ");
            int width = in.nextInt();
            System.out.print("Podaj wysokość: ");
            int height = in.nextInt();
            GameManager.makeGame(width, height);
        }
        ArrayList<Pedestrian> walkers = GameManager.getWalkers();
        ArrayList<BikeDecorator> bikes = GameManager.getBikes();
        ArrayList<CarDecorator> cars = GameManager.getCars();
        String option;
        int round = 0;
        boolean done = true;
        Random rand = new Random();
        do{ 
            option = in.nextLine();
        }while(!option.equals(" ") && !option.equals("")); // czeka na wciśnięcie entera lub spacji
        do{
            round++;
            int i = 0;
            System.out.println("----------------------Runda " + round + "----------------------");
            GameManager.drawBoard(); 
            BikeDecorator bike;
            do{
                bike = bikes.get(i);
                if(bike.symbol != '>'){
                    if(bike.getMilage() != 5){ // zakładam że po takim dystansie, element losuje czy chce wyjść z pojazdu czy nie
                        done = bike.move();
                        bike.changeMilage(1); // zakładam że każda tura będzie zwiększać przebieg o 1
                        if(!done) lost = bike;
                    }else{
                        bike.changeMilage(-5);
                        if(rand.nextBoolean()) done = bike.getOff();
                    }
                }
                i++;
            }while(i < bikes.size() && done);
            i = 0;
            CarDecorator car;
            if(done)
                do{
                    car = cars.get(i);
                    if(car.symbol != ']'){
                        if(car.getMilage() != 5){
                            done = car.move();
                            car.changeMilage(1);
                            if(!done) lost = car;
                        }else{
                            car.changeMilage(-5);
                            if(rand.nextBoolean()) done = car.getOff();
                        }
                    }
                    i++;
                }while(i < cars.size() && done);
            i = 0;
            if(done)
                do{
                    done = walkers.get(i).move(); 
                    if(!done) lost = walkers.get(i);
                    i++;
                }while(i < walkers.size() && done);
            if(!option.equals(" "))
                do{ 
                    option = in.nextLine();
                }while(!option.equals(" ") && !option.equals("")); // czeka na wciśnięcie entera lub spacji
        }while(done);
        System.out.println("GAME OVER");
        if(lost instanceof Pedestrian) System.out.println("Pieszy spowodował wypadek.");
        else if(lost instanceof CarDecorator) System.out.println("Samochód spowodował wypadek.");
        else if(lost instanceof BikeDecorator) System.out.println("Rower spowodował wypadek.");
        GameManager.drawBoard();
    }
}
