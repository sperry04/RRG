package net.doink.rrg.SeasonManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Maintains a list of Sessions
 * Created by spery on 5/23/15.
 */
public class Sessions {

    private static final Logger LOGGER = LoggerFactory.getLogger(Drivers.class);

    private final SortedMap<Integer, Session> sessions = new TreeMap<>();


    /**
     * Encapsulates Session data
     */
    public class Session {

        private Integer number;
        private String name;



    }

}
