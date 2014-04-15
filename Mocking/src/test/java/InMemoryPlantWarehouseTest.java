import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by rory.payne on 20/03/14.
 */
public class InMemoryPlantWarehouseTest {

    private InMemoryPlantWarehouse plantWarehouse;

    @Before
    public void before() {
        plantWarehouse = new InMemoryPlantWarehouse();
        plantWarehouse.addPlantToWarehouse(new Plant("tulip"));
        plantWarehouse.addPlantToWarehouse(new Plant("daffodil"));
    }

    @Test
    public void testFindPlantWithNameTulip() {
        Plant plant = plantWarehouse.findPlantWithName("tulip");
        Assert.assertEquals("tulip", plant.getName());
    }

    @Test
    public void testFindPlantWithNameDaffodil() {
        Plant plant = plantWarehouse.findPlantWithName("daffodil");
        Assert.assertEquals("daffodil", plant.getName());
    }

    @Test
    public void testBuyPlantWithNameTulip() {
        Boolean success = plantWarehouse.buyPlantWithName("tulip");
        Assert.assertTrue(success);
    }

    @Test
    public void testBuyPlantWithNameDaffodil() {
        Boolean success = plantWarehouse.buyPlantWithName("daffodil");
        Assert.assertTrue(success);
    }

    @Test
    public void testBuyPlantWithNameRose() {
        Boolean success = plantWarehouse.buyPlantWithName("rose");
        Assert.assertFalse(success);
    }

    @Test
    public void testBuyPlantWithNameTulipRemovesTulipFromPlantWarehouse() {
        Boolean success = plantWarehouse.buyPlantWithName("tulip");
        Assert.assertNull(plantWarehouse.findPlantWithName("tulip"));
    }

    @Test
    public void testBuyPlantWithNameDaffodilRemovesTulipFromPlantWarehouse() {
        Boolean success = plantWarehouse.buyPlantWithName("Daffodil");
        Assert.assertNull(plantWarehouse.findPlantWithName("Daffodil"));
    }

    @Test
    public void testBuyPlantNoBooleanWithNameTulip() {
        plantWarehouse.buyPlantNoBoolean("tulip");
        Assert.assertNull(plantWarehouse.findPlantWithName("tulip"));
    }
}
