package streetsimulator;

import java.util.Random;
/**
 * Klasa <code>RoadUser</code> reprezentuje dowolnego użytkownika drogi - pieszego, 
 * samochód lub rower. Zawiera metody wspólne dla każdego z tych użytkowników. 
 * @author AleksanderSklorz
 */
public abstract class RoadUser {
    protected int x, y, previousDirection = -1, speed;
    protected char symbol;
    public RoadUser(){
    }
    /**
     * Przemieszcza element na inną losową pozycję. Metoda zapewnia ochronę przed 
     * powrotem na poprzednią pozycję oraz przed wykroczeniem poza planszę gry.
     * Dzięki temu zawsze będzie zwracała true, jednak mimo tego aby nie było dwóch 
     * tych samych metod, gdzie w klasie RoadUser ta metoda zwracałaby nic, a w podklasach
     * zwracałaby typ boolean, postanowiłem dla jednoznaczności zwracać tu też typ boolean i nadpisać tą metodę w podklasach.
     * @return zawsze true (typ boolean aby można było nadpisać w podklasach)
     */
    protected boolean move(){
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
        return true;
    }
    protected boolean isAccident(int x, int y){
        return GameManager.getBoard()[y][x] != ' ';
    }
}
