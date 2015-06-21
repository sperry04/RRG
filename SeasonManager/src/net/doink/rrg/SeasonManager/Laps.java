package net.doink.rrg.SeasonManager;

import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Maintains a list of Laps
 * Created by spery on 5/23/15.
 */
public class Laps {

    private static final Logger LOGGER = LoggerFactory.getLogger(Drivers.class);

    private SortedMap<Object, Lap> laps = new TreeMap<>();
    private SortMode sortMode;

    public Laps(SortMode sortMode) {

    }

    public void setSortMode(SortMode sortMode) {
        SortedMap<Object, Lap> newLaps = new TreeMap<>();
        for ()
    }

    /**
     * Enumerates the sorting mode for the list of laps
     */
    public enum SortMode {
        ByNumber,
        ByTime,
        ByDriver
    }

    /**
     * Encapsulates lap data
     */
    public class Lap {

        private Integer lapNum;
        private Integer lapTime;
        private Double lapSpeed;

        public Lap(Integer lapNum, String lapTime, String lapSpeed) {
            this.lapNum = lapNum;
            this.lapTime = (int)Duration.valueOf(lapTime).toMillis();
            this.lapSpeed = Double.valueOf(lapSpeed);
        }

        public Object getIndex(SortMode sortMode) {
            switch (sortMode) {
                case ByNumber:
                    return lapNum;
                case ByTime:
                    return lapTime;
                case ByDriver:
                    return
            }
        }

    }

}
