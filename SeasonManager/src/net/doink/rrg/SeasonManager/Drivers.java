package net.doink.rrg.SeasonManager;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Maintains a list of Drivers and Aliases
 * Created by sperry on 5/22/15.
 */
public class Drivers {

    private static final int FUZZY_THRESHOLD_AUTO = 2; // fuzzy matches below this Levenshtein Distance will be auto accepted as the same name
    private static final int FUZZY_THRESHOLD_WARN = 6; // fuzzy matches below this Levenshtein Distance will be logged as possible matches
    private static final Logger LOGGER = LoggerFactory.getLogger(Drivers.class);
    private SortedMap<String, Driver> drivers = new TreeMap<>();

    public Drivers() {

    }

    public Driver addDriver(String name) {
        LOGGER.info("Adding driver named [{}]...", name);
        Driver matchedDriver = null;

        for (Driver driver : drivers.values()) {
            switch (driver.matchesName(name, true)) {
                case Same:
                    LOGGER.info("Driver [{}] already exists [{}]", driver);
                    return driver; // driver already exists

                case FuzzySame:
                    LOGGER.info("Driver [{}] appears to closely match existing driver [{}], assuming they are the same", name, driver);
                    driver.addAlias(name); // make sure the new alias is added on a fuzzy match
                    return driver; // driver already exists

                case FuzzyDifferent:
                    matchedDriver = driver;
                    break;
            }
        }

        if (null != matchedDriver) {
            LOGGER.warn("Driver [{}] appears similar to existing driver [{}], adding new driver, please manually check this is correct", name, matchedDriver);
        }

        Driver newDriver = new Driver(name, drivers.keySet());
        drivers.put(newDriver.getAcronym(), newDriver);
        LOGGER.info("Created new driver: [{}]", newDriver);
        return newDriver;
    }

    public SortedMap<String, Driver> getDrivers() {
        return drivers;
    }

    public int getDriversCount() {
        return drivers.size();
    }

    @Override
    public String toString() {
        StringBuilder rval = new StringBuilder("Drivers:");
        for (Driver driver : drivers.values()) {
            rval.append(System.lineSeparator()).append("  ").append(driver);
        }
        return rval.toString();
    }

    public enum MatchResult {
        Same,
        FuzzySame,
        FuzzyDifferent,
        Different
    }

    public class Driver {
        private String acronym;
        private String displayName;
        private SortedSet<String> aliases = new TreeSet<>();

        public Driver(String name, Set<String> existingAcronyms) {

            // figure out the driver's unique acronym
            int offset = name.lastIndexOf(" ") + 1; // the start of the driver's last name
            int duplicateAvoidanceOffset = 0;       // increases the length of the acronym to avoid duplicates
            int duplicateAvoidancePostfix = 0;      // appends a counter once the last name length is exhausted
            while (acronym == null || existingAcronyms.contains(acronym)) {
                try {
                    acronym = name.substring(offset, offset + 3 + duplicateAvoidanceOffset++).toUpperCase() +
                            (duplicateAvoidancePostfix == 0 ? "" : String.valueOf(duplicateAvoidancePostfix));
                } catch (Exception e) {
                    // duplicateAvoidanceCount got too long and we went out of range on the substring()
                    duplicateAvoidancePostfix++;
                }
            }

            displayName = name;
            aliases.add(name.toUpperCase());
        }

        public String getAcronym() { return acronym; }

        public String getDisplayName() { return displayName; }

        public MatchResult matchesName(String name, boolean fuzzyMatching) {

            if (aliases.contains(name.toUpperCase())) {
                return MatchResult.Same;
            }

            MatchResult rval = MatchResult.Different;
            if (fuzzyMatching) {
                for (String alias : aliases) {
                    int score = StringUtils.getLevenshteinDistance(alias, name.toUpperCase(), FUZZY_THRESHOLD_WARN);
                    if (score < 0) continue; // didn't match at all, keep searching
                    if (score < FUZZY_THRESHOLD_AUTO) return MatchResult.FuzzySame; // close enough to return
                    if (score < FUZZY_THRESHOLD_WARN) rval = MatchResult.FuzzyDifferent; // better than nothing, but keep searching
                }
            }
            return rval;
        }

        public void addAlias(String name) {
            aliases.add(name.toUpperCase());
        }

        @Override
        public String toString() {
            return String.format("(%s) %s, aliases: %s", getAcronym(), getDisplayName(), aliases);
        }
    }

}
