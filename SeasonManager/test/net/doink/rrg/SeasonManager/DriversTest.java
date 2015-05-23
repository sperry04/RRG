package net.doink.rrg.SeasonManager;

import junit.framework.Assert;
import org.testng.annotations.Test;

/**
 * Tests the Drivers class
 * Created by sperry on 5/22/15.
 */
public class DriversTest {

    @Test
    public void instanceTest() throws Exception {
        Drivers drivers = new Drivers();
        Assert.assertNotNull(drivers);
    }

    @Test
    public void addDriversTest() throws Exception {
        Drivers drivers = new Drivers();
        Assert.assertNotNull(drivers);

        drivers.addDriver("Scott Perry");
        drivers.addDriver("Cory Davis");
        drivers.addDriver("Bob Davis");
        drivers.addDriver("Bob Danger Davis");
        drivers.addDriver("Robert Davis");
        drivers.addDriver("Scott Perry2");

        Assert.assertEquals(drivers.getDriversCount(), 3);
    }
}
