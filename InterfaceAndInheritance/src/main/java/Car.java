/**
 * Created by rory.payne on 20/03/14.
 */

/**
 * An interface defines a contract which an implementing class must adhere
 * to. The DriveCar interface defines a set of operations that must be
 * supported by a Car. So a class that actually implements the interface
 * should implement all the methods declared in the interface.
 */

class Car implements DriveCar {
    private int speed;
    private String Direction;
    String N = "north";
    String E = "east";
    String S = "south";
    String W = "west";

    public Car(int speed, String Direction) {
        this.speed = speed;
        this.Direction = Direction;
    }

    public int getSpeed() {
        return speed;
    }

    public String getDirection() {
        return Direction;
    }

    public void turnRight() {
        if (Direction == N) {
            Direction = E;
        } else if (Direction == E) {
            Direction = S;
        } else if (Direction == S) {
            Direction = W;
        } else {
            Direction = N;
        }
    }

    public void turnLeft() {
        if (Direction == N) {
            Direction = W;
        } else if (Direction == E) {
            Direction = N;
        } else if (Direction == S) {
            Direction = W;
        } else {
            Direction = S;
        }
    }

    public void moveBack() {
        if (speed != 0) {
            speed --;
        }
    }

    public void accelerate() {
        speed ++;
    }
}
