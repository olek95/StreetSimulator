package streetsimulator;
/**
 * Klasa <code>VehicleDecorator</code> reprezentuje dowolny pojazd na drodze. 
 * @author AleksanderSklorz
 */
public abstract class VehicleDecorator extends RoadUser{
    protected Pedestrian user;
    protected int milage;
    public VehicleDecorator(Pedestrian user){
        this.user = user;
    }
    /**
     * Pozwala na przemieszczenie pojazdu. 
     * @return zawsze true (typ boolean aby można było nadpisać w podklasach)
     */
    public boolean move(){
        return super.move();
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
