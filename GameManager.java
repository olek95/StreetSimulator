package streetsimulator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa <code>GameManager</code> reprezentuje zarządcę gry. Zawiera ona metody 
 * służące do zarządzaniem i sterowaniem grą. 
 * @author AleksanderSklorz
 */
public class GameManager {
    private static char[][] board;
    private static ArrayList<Pedestrian> walkers; 
    private static ArrayList<BikeDecorator> bikes; 
    private static ArrayList<CarDecorator> cars; 
    private static int boardWidth, boardHeight;
    /**
     * Tworzy grę - planszę do gry oraz użytkowników drogi (pieszych, rowerów i samochody). 
     * Liczba użytkowników drogi jest dobierana losowo z pewnego zakresu. Również położenie 
     * użytkowników drogi jest wybierane losowo (przy takim założeniu, iż pomiędzy 
     * początkowym położeniem obiektów ma być pewna odległość wolna aby zaraz na początku się nie zderzyły). 
     * @param boardWidth szerokość planszy. 
     * @param boardHeight wysokość planszy. 
     */
    public static void makeGame(int boardWidth, int boardHeight){
        GameManager.boardWidth = boardWidth;
        GameManager.boardHeight = boardHeight;
        board = new char[boardHeight][boardWidth];
        for(int i = 0; i < board.length; i++)
            for(int k = 0; k < board[i].length; k++)
                board[i][k] = (k == 0 || k == boardWidth - 1) ? '|' : ' ';
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
                bikes.get(i).symbol = '>';
                changeBoardField(xy[0], xy[1], '>');
            }
        }
        size = takeNumberElements() + 2; // zakładam że dwa samochody będą bez właściciela
        for(int i = 0; i < size; i++){
            xy = takeXY(CarDecorator.class);
            cars.add(new CarDecorator(new Pedestrian(xy[0], xy[1])));
            if(i >= size - 2){
                cars.get(i).symbol = ']';
                changeBoardField(xy[0], xy[1], ']');
            }
        }
    }
    /**
     * Rysuje planszę do gry wraz z zawartością. 
     */
    public static void drawBoard(){
        for(int i = 0; i < board.length; i++){
            for(int k = 0; k < board[i].length; k++)
                System.out.print(board[i][k]);
            System.out.println(); 
        }
    }
    private static int takeNumberElements(){
        return new Random().nextInt(3) + 1; // zakładam że danego rodzaju użytkownika drogi może być początkwo od 1 do 3
    }
    private static int[] takeXY(Class roadUserClass){
        Random rand = new Random();
        int x, y, speed;
        boolean enoughSpace;
        if(roadUserClass.equals(Pedestrian.class)) speed = 1;
        else if(roadUserClass.equals(BikeDecorator.class)) speed = 2;
        else speed = 3;
        do{
            enoughSpace = true;
            x = rand.nextInt(boardWidth - 2) + 1; // +1 i -2 aby nie umieścić na krawędzi planszy | 
            y = rand.nextInt(boardHeight);
            int i = x - speed;
            do{
                if(i >= 1 && i < boardWidth - 1 && board[y][i] != ' ') enoughSpace = false;
                i++;
            }while(enoughSpace && i <= x + speed);
            i = y - speed;
            if(enoughSpace)
                do{
                    if(i >= 0 && i < boardHeight && board[i][x] != ' ') enoughSpace = false;
                    i++;
                }while(enoughSpace && i <= y + speed);
        }while(!enoughSpace);
        return new int[] {x, y};
    }
    /**
     * Zwraca listę pieszych. 
     * @return lista pieszych. 
     */
    public static ArrayList<Pedestrian> getWalkers(){
        return walkers; 
    }
    /**
     * Zwraca listę rowerów. 
     * @return lista rowerów. 
     */
    public static ArrayList<BikeDecorator> getBikes(){
        return bikes; 
    }
    /**
     * Zwraca listę samochodów. 
     * @return lista samochodów. 
     */
    public static ArrayList<CarDecorator> getCars(){
        return cars;
    }
    /**
     * Zwraca planszę do gry. 
     * @return plansza do gry..
     */
    public static char[][] getBoard(){
        return board;
    }
    /**
     * Zmienia zawartość danego pola planszy do gry. 
     * @param x współrzędna pozioma.
     * @param y współrzędna pionowa. 
     * @param c nowy znak który ma zostać wstawiony na podanej pozycji. 
     */
    public static void changeBoardField(int x, int y, char c){
        board[y][x] = c;
    }
    /**
     * Zwraca szerokość planszy do gry. 
     * @return szerokość planszy. 
     */
    public static int getBoardWidth(){
        return boardWidth;
    }
    /**
     * Zwraca wysokość planszy do gry. 
     * @return wysokość planszy.
     */
    public static int getBoardHeight(){
        return boardHeight;
    }
}
