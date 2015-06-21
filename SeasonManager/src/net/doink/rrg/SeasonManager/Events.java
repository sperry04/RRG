package net.doink.rrg.SeasonManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Maintains a list of Events
 * Created by spery on 5/23/15.
 */
public class Events {

    private static final Logger LOGGER = LoggerFactory.getLogger(Drivers.class);

    private final SortedMap<Date, Event> events = new TreeMap<>();


    /**
     * Encapsulates Event data
     */
    public class Event {

        private Date date;
        private String name;

        private Sessions sessions = new Sessions();


    }

}
