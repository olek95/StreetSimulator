package streetsimulator;

public abstract class VehicleDecorator extends RoadUser{
    protected Pedestrian user;
    protected int milage;
    protected char symbol;
    public VehicleDecorator(RoadUser user){
        this.user = (Pedestrian)user;
    }
    public int[] move(int x, int y, char symbol, int speed){
        int[] newXY = super.move(x, y, symbol, speed);
        return newXY;
    } 
    public void changeMilage(int milage){
        this.milage += milage;
    }
    public int getMilage(){
        return milage;
    }
}
