package streetsimulator;

public class StreetSimulator {
    public static void drawBoard(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int k = 0; k < board[i].length; k++)
                System.out.print(board[i][k]);
            System.out.println(); 
        }
    }
    public static void main(String[] args) {
        /*Pedestrian pieszy = new Pedestrian(); 
        pieszy.przedstawSie();
        System.out.println(); 
        BikeDecorator rower = new BikeDecorator(new Pedestrian());
        rower.przedstawSie();
        System.out.println();
        CarDecorator auto = new CarDecorator(new Pedestrian());
        auto.przedstawSie();
        System.out.println(); 
        pieszy.move();*/
        Pedestrian walker = new Pedestrian(); 
        System.out.println("RYS1");
        drawBoard(walker.getBoard());
        System.out.println("RYS2");
        walker.move();
        drawBoard(walker.getBoard());
        System.out.println("RYS3");
        walker.move();
        drawBoard(walker.getBoard());
        System.out.println("RYS4");
        walker.move();
        drawBoard(walker.getBoard());
        System.out.println("RYS5");
        walker.move();
        drawBoard(walker.getBoard());
    }
}
