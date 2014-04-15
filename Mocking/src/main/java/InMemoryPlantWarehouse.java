import java.util.ArrayList;
import java.util.List;

/**
 * Created by rory.payne on 20/03/14.
 */
public class InMemoryPlantWarehouse implements PlantWarehouse {

    public List<Plant> plants;

    public InMemoryPlantWarehouse() {
        plants = new ArrayList<Plant>();
    }

    public void addPlantToWarehouse(Plant plant) {
        plants.add(plant);
    }



    public Plant findPlantWithName(String nameOfPlant) {
        for (Plant plant : plants) {
            if (plant.getName().equals(nameOfPlant)) {
                return plant;
            }
        }

        return null;
    }

    @Override
    public boolean buyPlantWithName(String nameOfPlant) {
        boolean success = false;
        for (int i = 0; i<plants.size(); i++) {
            if (plants.get(i).getName().equals(nameOfPlant)) {
                plants.remove(i);
                success = true;
            }
        }
        return success;
    }

    @Override
    public void buyPlantNoBoolean(String nameOfPlant) {
        for (int i = 0; i<plants.size(); i++) {
            if (plants.get(i).getName().equals(nameOfPlant)) {
                plants.remove(i);
            }
        }
    }
}
