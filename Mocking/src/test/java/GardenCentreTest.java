
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.times;

/**
 * Created by rory.payne on 20/03/14.
 */
public class GardenCentreTest {

    private GardenCentre gc;
    private PlantWarehouse plantWarehouse;
    private InMemoryPlantWarehouse inMem = new InMemoryPlantWarehouse();

    @Before
    public void before() {
        plantWarehouse = Mockito.mock(PlantWarehouse.class);
        gc = new GardenCentre(plantWarehouse);
    }

    @Test
    public void testFindPlantWithNameTulip() {
        Mockito.when(plantWarehouse.findPlantWithName("tulip")).thenReturn(new Plant("tulip"));

        Plant plant = gc.findPlantWithName("tulip");
        Assert.assertEquals("tulip", plant.getName());
    }

    @Test
    public void testFindPlantWithNameDaffodil() {
        Mockito.when(plantWarehouse.findPlantWithName("daffodil")).thenReturn(new Plant("daffodil"));

        Plant plant = gc.findPlantWithName("daffodil");
        Assert.assertEquals("daffodil", plant.getName());
    }

    @Test
    public void testIfPlantDoesNotExistIShouldGetNull() {
        Mockito.when(plantWarehouse.findPlantWithName("rose")).thenReturn(null);

        Plant plant = gc.findPlantWithName("rose");
        Assert.assertNull(plant);
    }

    @Test
    public void testBuyPlantWithNameTulip() {
        Mockito.when(plantWarehouse.buyPlantWithName("tulip")).thenReturn(true);
        boolean success = gc.buyPlant("tulip");
        Assert.assertTrue(success);
    }

    @Test
    public void testBuyPlantWithNameTulipButIsOutOfStock() {
        Mockito.when(plantWarehouse.buyPlantWithName("tulip")).thenReturn(false);
        boolean success = gc.buyPlant("tulip");
        Assert.assertFalse(success);
    }

    @Test
     public void testBuyPlantsNoSuccessBooleanWithNameTulipWhenInStock() {
        gc.buyPlantNoSuccessBoolean("tulip");
        Mockito.verify(plantWarehouse, times(1)).buyPlantNoBoolean("tulip");
    }
}
