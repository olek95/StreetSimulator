package streetsimulator;

public class StreetSimulator {
    public static void main(String[] args) {
        GameManager.makeGame();
        Pedestrian[] walkers = GameManager.getWalkers();
        BikeDecorator[] bikes = GameManager.getBikes();
        CarDecorator[] cars = GameManager.getCars();
        GameManager.drawBoard(); 
        boolean done = true;
        do{
            System.out.println("----------------------");
            for(Pedestrian walker : walkers){
                done = walker.move(); 
                if(!done) {
                    System.out.println("PIESZY");
                    break;
                }
            }
            if(!done) break;
            for(BikeDecorator bike : bikes){
                done = bike.move(); 
                if(!done) {
                    System.out.println("ROWER");
                    break;
                }
            }
            if(!done) break;
            for(CarDecorator car : cars){
                car.move();
                if(!done) {
                    System.out.println("AUTO");
                    break;
                }
            }
            if(!done) break;
            GameManager.drawBoard(); 
        }while(true);
        GameManager.drawBoard();
    }
}
