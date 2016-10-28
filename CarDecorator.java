package streetsimulator;

public class CarDecorator extends VehicleDecorator{
    private char symbol = '[';
    private final static int SPEED = 3;
    private int x, y, milage;
    public CarDecorator(Pedestrian user){
        super(user);
        x = user.getX(); 
        y = user.getY();
        milage = 0;
        GameManager.changeBoardField(x, y, symbol);
    }
    public boolean move(){
        int[] newXY = super.move(x, y, symbol, SPEED);
        x = newXY[0];
        y = newXY[1];
        if(isAccident(x,y)){
            GameManager.changeBoardField(x, y, 'W');
            return false;
        }
        GameManager.changeBoardField(x, y, symbol);
        return true;
    }
    public boolean getOff(){
        //using = false;
        System.out.println("WYSIADL Z AUTA");
        System.out.println(GameManager.getWalkers().size());
        Pedestrian walker = (Pedestrian)user;
        GameManager.getWalkers().add(walker);
        symbol = ']';
        GameManager.changeBoardField(x, y, symbol);
        System.out.println(GameManager.getWalkers().size());
        return true;
    }
    public void changeMilage(int milage){
        this.milage += milage;
    }
    public int getMilage(){
        return milage;
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
    public void setSymbol(char symbol){
        this.symbol = symbol;
    }
    public char getSymbol(){
        return symbol;
    }
    public void przedstawSie(){
        user.przedstawSie(); 
        System.out.print(" w samochodzie.");
    }
}
