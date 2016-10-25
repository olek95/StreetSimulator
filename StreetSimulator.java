package streetsimulator;

public class StreetSimulator {
    public static void main(String[] args) {
        Pedestrian pieszy = new Pedestrian(); 
        pieszy.przedstawSie();
        System.out.println(); 
        BikeDecorator rower = new BikeDecorator(new Pedestrian());
        rower.przedstawSie();
        System.out.println();
        CarDecorator auto = new CarDecorator(new Pedestrian());
        auto.przedstawSie();
        System.out.println(); 
        pieszy.move();
    }
}
