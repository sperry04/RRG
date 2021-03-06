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
public class Season {

    private static final Logger LOGGER = LoggerFactory.getLogger(Season.class);

    private static final String HEADER = "season";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "desc";
    private JSONObject headerData;

    /**
     * Creates a new empty season, writing the empty shell structure to the specified file
     * @param filename The name of the file to write the empty shell to
     * @return a Season object backed by the specified file, or null if the file could not be created
     */
    public static Season createNewSeason(String name, String description, String filename) {
        LOGGER.info("Creating new season named [{}] - [{}] in file [{}]...", name, description, filename);
        Season rval = new Season(name, description);
        return rval.writeToDisk(filename) ? rval : null;
    }

    /**
     * Constructs a new Season
     */
    protected Season(String name, String description) {
        headerData = new JSONObject();
        headerData.put(NAME, name);
        headerData.put(DESCRIPTION, description);
    }

    /**
     * Gets the JSON representation of the Season
     * @return JSON String containing the Season's current state
     */
    public JSONObject getJson() {
        JSONObject rval = new JSONObject();

        // append the season's headerData into the json object
        rval.put(HEADER, headerData);

        return rval;
    }

    /**
     * Writes the current state of the Season to the specified filename
     * @param filename the name of the file to write the season to
     * @return true if the season was written, false otherwise
     */
    public boolean writeToDisk(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(getJson().toString());
            return true;
        } catch (IOException e) {
            LOGGER.error("Failed to create season file", e);
            return false;
        }
    }
}
