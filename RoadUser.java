package streetsimulator;

import java.util.Random;
/**
 * Klasa <code>RoadUser</code> reprezentuje dowolnego użytkownika drogi - pieszego, 
 * samochód lub rower. Zawiera metody wspólne dla każdego z tych użytkowników. 
 * @author AleksanderSklorz
 */
public abstract class RoadUser {
    protected int x, y, previousDirection = -1;
    public RoadUser(){
    }
    /**
     * Przemieszcza element na inną losową pozycję. Zwraca dwuelementową tablicę, w której 
     * pierwszy element to współrzędna x, drugi element to współrzedna y nowej pozycji. 
     * Metoda zapewnia ochronę przed powrotem na poprzednią pozycję oraz przed wykroczeniem poza planszę gry.
     * @param x aktualna współrzędna w poziomie.
     * @param y aktualna współrzędna w pionie. 
     * @param symbol oznaczenie użytkownika.
     * @param speed prędkość użytkownika. 
     * @return dwuelementowa tablica z nowymi współrzędnymi położenia użytkownika. 
     */
    protected int[] move(int x, int y, char symbol, int speed){
        boolean moved = false;
        GameManager.changeBoardField(x, y, ' ');
        Random ran = new Random();
        int direction; 
        do{
            direction = ran.nextInt(4); 
            if(direction != (previousDirection ^ 2)) // warunek chroniący przed powrotem na poprzednie pole. previousDirection przechowuje poprzedni kierunek a XOR go obraca
                switch(direction){
                    case 0:
                        if(y - speed >= 0){
                            y -= speed; 
                            moved = true;
                        }
                        break;
                    case 1:
                        if(x + speed < GameManager.getBoardWidth() - 1){ // -1 bo na końcu tablicy jest krawędź planszy | 
                            x += speed; 
                            moved = true;
                        }
                        break;
                    case 2:
                        if(y + speed < GameManager.getBoardHeight()){
                            y += speed; 
                            moved = true;
                        }
                        break;
                    default:
                        if(x - speed >= 1){
                            x -= speed;
                            moved = true;
                        }
                }
        }while(!moved);
        previousDirection = direction;
        return new int[] {x,y};
    }
    protected boolean isAccident(int x, int y){
        if(GameManager.getBoard()[y][x] != ' ') return true;
        return false;
    }
    /**
     * Zwraca położenie poziome użytkownika.
     * @return położenie poziome. 
     */
    public int getX(){
        return x; 
    }
    /**
     * Zwraca położenei pionowe użytkownika. 
     * @return położenie pionowe.
     */
    public int getY(){
        return y; 
    }
}
