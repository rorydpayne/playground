/**
 * Created by rory.payne on 20/03/14.
 */
public class Main {
    public static void main (String args[]) {
        Car car = new Car(0, "north");
        car.accelerate();
        car.accelerate();
        car.accelerate();
        car.accelerate();
        car.accelerate();
        car.turnLeft();
        car.moveBack();
        System.out.println(car.getSpeed() + " " + car.getDirection());
    }
}
