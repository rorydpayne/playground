/**
 * Created by rory.payne on 20/03/14.
 */

/**
 * An interface is a 100% abstract class. It contains only constants
 * and method signatures. It can be implemented by a class or
 * extended by another interface.
 *
 * The methods declared in an interface don't have method bodies. By
 * default all methods are public abstract. Similarly, all variables
 * we define in an interface are essentially constants because they
 * are implicitly public static final.
 */

public interface DriveCar{
    void turnLeft();
    void turnRight();
    void moveBack();
    void accelerate();
}
