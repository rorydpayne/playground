/**
 * Created by rory.payne on 20/03/14.
 */
public interface PlantWarehouse {

    Plant findPlantWithName(String nameOfPlant);

    boolean buyPlantWithName(String nameOfPlant);

    void buyPlantNoBoolean(String nameOfPlant);
}
