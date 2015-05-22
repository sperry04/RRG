package net.doink.rrg.SeasonManager;

import junit.framework.Assert;
import org.testng.annotations.Test;

/**
 * Tests the SeasonManager class
 * Created by sperry on 5/22/15.
 */
public class SeasonManagerTest {

    @Test
    public void instanceTest() throws Exception {
        SeasonManager seasonManager = new SeasonManager("Test", "Constructor Test");
        Assert.assertNotNull(seasonManager);
    }

    @Test
    public void factoryTest() throws Exception {
        SeasonManager seasonManager = SeasonManager.createNewSeason("Test", "Factory Test", "factoryTest.season");
        Assert.assertNotNull(seasonManager);
    }

    @Test
    public void getJsonTest() throws Exception {
        SeasonManager seasonManager = new SeasonManager("Test", "toString Test");
        Assert.assertNotNull(seasonManager);

        String json = seasonManager.getJson();
        Assert.assertNotNull(json);
        // TODO: compare json to expected output
    }
}
