package streetsimulator;
/**
 * Klasa <code>VehicleDecorator</code> reprezentuje dowolny pojazd na drodze. 
 * @author AleksanderSklorz
 */
public abstract class VehicleDecorator extends RoadUser{
    protected Pedestrian user;
    protected int milage;
    protected char symbol;
    public VehicleDecorator(Pedestrian user){
        this.user = user;
    }
    /**
     * Pozwala na przemieszczenie pojazdu. 
     * @param x aktualna współrzędna pozioma. 
     * @param y aktualna współrzędna pionowa. 
     * @param symbol oznaczenie pojazdu. 
     * @param speed prędkość pojazdu.
     * @return tablica dwuelementowa, gdzie pierwszy element to nowa współrzędna x, a drugi nowa współrzędna y.
     */
    public int[] move(int x, int y, char symbol, int speed){
        int[] newXY = super.move(x, y, symbol, speed);
        return newXY;
    } 
    /**
     * Pozwala na zmianę przebytej odległości. 
     * @param milage przebyta odległość w danej turze (może być ujemny, np. gdy chce się wyzerować stan przebiegu)
     */
    public void changeMilage(int milage){
        this.milage += milage;
    }
    /**
     * Zwraca przebytą do tej pory odległość. 
     * @return przebyta odległość. 
     */
    public int getMilage(){
        return milage;
    }
}
