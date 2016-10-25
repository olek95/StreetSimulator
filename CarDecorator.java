package streetsimulator;

public class CarDecorator extends VehicleDecorator{
    private boolean using = false;
    public CarDecorator(RoadUser user){
        super(user);
    }
    public void move(){
        
    }
    public void przedstawSie(){
        user.przedstawSie(); 
        System.out.print(" w samochodzie.");
    }
}
