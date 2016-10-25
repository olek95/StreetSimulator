package streetsimulator;

public class Pedestrian extends RoadUser{
    private final static char SYMBOL = '*';
    private final static int speed = 1;
    public Pedestrian(){}
    public void move(){
        System.out.printf("%5c%3c",SYMBOL, SYMBOL);
    }
    public void przedstawSie(){
        System.out.print("Jestem pieszym");
    }
}
