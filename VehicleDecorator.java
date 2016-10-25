package streetsimulator;

public abstract class VehicleDecorator extends RoadUser{
    protected RoadUser user;
    public VehicleDecorator(RoadUser user){
        this.user = user;
    }
    /*public void move(){
        user.przedstawSie();
    }*/ // ta metoda nie jest tu konieczna bo pole typu RoadUser jest protected więc można w klasach dziedziczących użyć metod pola user
    // jeśli pole RoadUser byłoby prywatne, wtedy można napisać tu w ten sposób metodę move, ale user trzeba rzutować na Pedestriana
}
