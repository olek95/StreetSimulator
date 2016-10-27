package streetsimulator;

import java.util.Random;

public class StreetSimulator {
    public static void drawBoard(){
        char[][] board = RoadUser.board;
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
        char[][] board = RoadUser.board;
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
    public static void main(String[] args) {
        Pedestrian[] walkers = new Pedestrian[takeNumberElements()];
        int[] xy;
        Random rand = new Random();
        walkers[0] = new Pedestrian(rand.nextInt(120), rand.nextInt(30));
        BikeDecorator[] bikes = new BikeDecorator[takeNumberElements()];
        CarDecorator[] cars = new CarDecorator[takeNumberElements()];
        for(int i = 1; i < walkers.length; i++){
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
        drawBoard(); 
        boolean done = true;
        do{
            System.out.println("----------------------");
            for(Pedestrian walker : walkers){
                done = walker.move(); 
                if(!done) {
                    System.out.println("PIESZY");
                    break;
                }
            }
            if(!done) break;
            for(BikeDecorator bike : bikes){
                done = bike.move(); 
                if(!done) {
                    System.out.println("ROWER");
                    break;
                }
            }
            if(!done) break;
            for(CarDecorator car : cars){
                car.move();
                if(!done) {
                    System.out.println("AUTO");
                    break;
                }
            }
            if(!done) break;
            drawBoard(); 
        }while(true);
        drawBoard();
    }
}
