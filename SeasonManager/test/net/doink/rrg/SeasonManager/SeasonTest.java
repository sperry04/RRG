package net.doink.rrg.SeasonManager;

import junit.framework.Assert;
import org.json.JSONObject;
import org.testng.annotations.Test;

/**
 * Tests the Season class
 * Created by sperry on 5/22/15.
 */
public class SeasonTest {

    @Test
    public void instanceTest() throws Exception {
        Season season = new Season("Test", "Constructor Test");
        Assert.assertNotNull(season);
    }

    @Test
    public void factoryTest() throws Exception {
        Season season = Season.createNewSeason("Test", "Factory Test", "factoryTest.season");
        Assert.assertNotNull(season);
    }

    @Test
    public void getJsonTest() throws Exception {
        Season season = new Season("Test", "toString Test");
        Assert.assertNotNull(season);

        JSONObject json = season.getJson();
        Assert.assertNotNull(json);
        // TODO: compare json to expected output
    }
}
