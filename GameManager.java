package streetsimulator;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private static char[][] board;
    private static ArrayList<Pedestrian> walkers; 
    private static ArrayList<BikeDecorator> bikes; 
    private static ArrayList<CarDecorator> cars; 
    public static void makeGame(){
        board = new char[30][120];
        for(int i = 0; i < board.length; i++)
            for(int k = 0; k < board[i].length; k++)
                board[i][k] = ' ';
        walkers = new ArrayList();
        bikes = new ArrayList();
        cars = new ArrayList(); 
        int[] xy;
        int size = takeNumberElements(); 
        for(int i = 0; i < size; i++){
            xy = takeXY(Pedestrian.class);
            walkers.add(new Pedestrian(xy[0],xy[1]));
        }
        size = takeNumberElements() + 1; // zakładam że jeden rower będzie bez własciciela 
        for(int i = 0; i < size; i++){
            xy = takeXY(BikeDecorator.class);
            bikes.add(new BikeDecorator(new Pedestrian(xy[0],xy[1])));
            if(i >= size - 1){
                bikes.get(i).setSymbol('>');
                changeBoardField(xy[0], xy[1], '>');
            }
        }
        size = takeNumberElements() + 2; // zakładam że dwa samochody będą bez właściciela
        for(int i = 0; i < size; i++){
            xy = takeXY(CarDecorator.class);
            cars.add(new CarDecorator(new Pedestrian(xy[0], xy[1])));
            if(i >= size - 2){
                cars.get(i).setSymbol(']');
                changeBoardField(xy[0], xy[1], ']');
            }
        }
    }
    public static void drawBoard(){
        for(int i = 0; i < board.length; i++){
            for(int k = 0; k < board[i].length; k++)
                System.out.print(board[i][k]);
            System.out.println(); 
        }
    }
    public static int takeNumberElements(){
        return new Random().nextInt(3) + 1;
    }
    public static int[] takeXY(Class roadUserClass){
        Random rand = new Random();
        int x, y, speed;
        boolean enoughSpace;
        if(roadUserClass.equals(Pedestrian.class)) speed = Pedestrian.getSpeed();
        else if(roadUserClass.equals(BikeDecorator.class)) speed = BikeDecorator.getSpeed();
        else speed = CarDecorator.getSpeed();
        do{
            enoughSpace = true;
            x = rand.nextInt(120); 
            y = rand.nextInt(30);
            int i = x - speed;
            do{
                if(i >= 0 && i < 120 && board[y][i] != ' ') enoughSpace = false;
                i++;
            }while(enoughSpace && i <= x + speed);
            i = y - speed;
            if(enoughSpace)
                do{
                    if(i >= 0 && i < 30 && board[i][x] != ' ') enoughSpace = false;
                    i++;
                }while(enoughSpace && i <= y + speed);
        }while(!enoughSpace);
        return new int[] {x, y};
    }
    public static ArrayList<Pedestrian> getWalkers(){
        return walkers; 
    }
    public static ArrayList<BikeDecorator> getBikes(){
        return bikes; 
    }
    public static ArrayList<CarDecorator> getCars(){
        return cars;
    }
    public static char[][] getBoard(){
        return board;
    }
    public static void changeBoardField(int x, int y, char c){
        board[y][x] = c;
    }
}
