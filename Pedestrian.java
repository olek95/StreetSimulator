package streetsimulator;

public class Pedestrian extends RoadUser{
    private final static char SYMBOL = '*';
    private final static int SPEED = 1;
    private int x, y;
    public Pedestrian(int x, int y){
        this.x = x;
        this.y = y;
        GameManager.changeBoardField(x, y, SYMBOL);
    }
    public boolean move(){
        int[] newXY = super.move(x, y, SYMBOL, SPEED);
        x = newXY[0];
        y = newXY[1];
        if(isAccident(x,y) && !isEmptyVehicleNear()){
            GameManager.changeBoardField(x, y, 'W');
            return false;
        }
        if(isEmptyVehicleNear())
            if(GameManager.getBoard()[y][x] == '^') GameManager.makeBike();
            else GameManager.makeCar();
        GameManager.changeBoardField(x, y, SYMBOL);
        return true;
    }
    private boolean isEmptyVehicleNear(){
        char[][] board = GameManager.getBoard();
        if(board[y][x] == '^'){
            BikeDecorator[] bikes = GameManager.getBikes();
            for(int i = 0; i < bikes.length; i++)
                if(bikes[i].getX() == x && bikes[i].getY() == y && !bikes[i].isUsing())
                    return true;
        }
        if(board[y][x] == '$'){
            CarDecorator[] cars = GameManager.getCars(); 
            for(int i = 0; i < cars.length; i++)
                if(cars[i].getX() == x && cars[i].getY() == y && !cars[i].isUsing())
                    return true;
        }
        return false;
    }
    public int getX(){
        return x; 
    }
    public int getY(){
        return y; 
    }
    public static int getSpeed(){
        return SPEED;
    }
    public void przedstawSie(){
        System.out.print("Jestem pieszym");
    }
}
