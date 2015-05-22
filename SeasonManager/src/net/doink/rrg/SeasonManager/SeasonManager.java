package net.doink.rrg.SeasonManager;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Manages the headerData for a season of results
 * Created by sperry on 5/22/15.
 */
public class SeasonManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeasonManager.class);

    private static final String HEADER = "season";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "desc";
    private JSONObject headerData;

    /**
     * Creates a new empty season, writing the empty shell structure to the specified file
     * @param filename The name of the file to write the empty shell to
     * @return a SeasonManager object backed by the specified file, or null if the file could not be created
     */
    public static SeasonManager createNewSeason(String name, String description, String filename) {
        SeasonManager rval = new SeasonManager(name, description);
        return rval.writeToDisk(filename) ? rval : null;
    }

    /**
     * Constructs a new SeasonManager
     */
    protected SeasonManager(String name, String description) {
        headerData = new JSONObject();
        headerData.put(NAME, name);
        headerData.put(DESCRIPTION, description);
    }

    /**
     * Gets the JSON representation of the Season
     * @return JSON String containing the Season's current state
     */
    public String getJson() {
        JSONObject json = new JSONObject();

        // append the season's headerData into the json object
        json.put(HEADER, headerData);

        return json.toString();
    }

    /**
     * Writes the current state of the Season to the specified filename
     * @param filename the name of the file to write the season to
     * @return true if the season was written, false otherwise
     */
    public boolean writeToDisk(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(getJson());
            return true;
        } catch (IOException e) {
            LOGGER.error("Failed to create season file", e);
            return false;
        }
    }
}
