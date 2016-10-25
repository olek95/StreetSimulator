package streetsimulator;

public class BikeDecorator extends VehicleDecorator{
    private boolean using = false;
    public BikeDecorator(RoadUser user){
        super(user);
    }
    public void move(){
        
    }
    public void przedstawSie(){
        user.przedstawSie();
        System.out.print(" na rowerze.");
    }
}
