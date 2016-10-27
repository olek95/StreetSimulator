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
        }else{
            if(isAccident(x,y) && isEmptyVehicleNear())
                if(GameManager.getBoard()[y][x] == '>'){
                    System.out.println("WSIADLO");
                    for(BikeDecorator bike : GameManager.getBikes())
                        if(bike.getX() == x && bike.getY() == y){
                            bike.setSymbol('<');
                            GameManager.changeBoardField(x, y, '<');
                        }
                }else{
                    for(CarDecorator car : GameManager.getCars())
                        if(car.getX() == x && car.getY() == y){
                            car.setSymbol('[');
                            GameManager.changeBoardField(x, y, '[');
                        }
                }
            else GameManager.changeBoardField(x, y, SYMBOL);
        }
        return true;
    }
    private boolean isEmptyVehicleNear(){
        char[][] board = GameManager.getBoard();
        if(board[y][x] == '>' || board[y][x] == ']') return true;
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
