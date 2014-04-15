/**
 * Created by rory.payne on 20/03/14.
 */
public class GardenCentre {

    private PlantWarehouse plantWarehouse;

    public GardenCentre(PlantWarehouse plantWarehouse) {
        this.plantWarehouse = plantWarehouse;
    }

    public Plant findPlantWithName(String nameOfPlant) {
        Plant plant = plantWarehouse.findPlantWithName(nameOfPlant);
        return plant;
    }

    public boolean buyPlant(String nameOfPlant) {
        boolean success = plantWarehouse.buyPlantWithName(nameOfPlant);
        return success;
    }

    public void buyPlantNoSuccessBoolean(String nameOfPlant) {
        plantWarehouse.buyPlantNoBoolean(nameOfPlant);
    }
}
