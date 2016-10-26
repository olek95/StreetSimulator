package streetsimulator;

public abstract class VehicleDecorator extends RoadUser{
    protected RoadUser user;
    public VehicleDecorator(RoadUser user){
        this.user = user;
    }
    public int[] move(int x, int y, char symbol, int speed){
        int[] newXY = super.move(x, y, symbol, speed);
        return newXY;
    } 
}
