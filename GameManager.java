package streetsimulator;

import java.util.Random;

public class GameManager {
    private static char[][] board;
    private static Pedestrian[] walkers; 
    private static BikeDecorator[] bikes; 
    private static CarDecorator[] cars; 
    public static void makeGame(){
        board = new char[30][120];
        for(int i = 0; i < board.length; i++)
            for(int k = 0; k < board[i].length; k++)
                board[i][k] = ' ';
        walkers = new Pedestrian[takeNumberElements()];
        bikes = new BikeDecorator[takeNumberElements()];
        cars = new CarDecorator[takeNumberElements()];
        int[] xy;
        for(int i = 0; i < walkers.length; i++){
            xy = takeXY(Pedestrian.class);
            walkers[i] = new Pedestrian(xy[0],xy[1]);
        }
        for(int i = 0; i < bikes.length; i++){
            xy = takeXY(BikeDecorator.class);
            bikes[i] = new BikeDecorator(new Pedestrian(xy[0],xy[1]));
        }
        for(int i = 0; i < cars.length; i++){
            xy = takeXY(CarDecorator.class);
            cars[i] = new CarDecorator(new Pedestrian(xy[0], xy[1]));
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
    public static Pedestrian[] getWalkers(){
        return walkers; 
    }
    public static BikeDecorator[] getBikes(){
        return bikes; 
    }
    public static CarDecorator[] getCars(){
        return cars;
    }
    public static char[][] getBoard(){
        return board;
    }
    public static void changeBoardField(int x, int y, char c){
        board[y][x] = c;
    }
}
