package ru.javawebinar.topjava.storage;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 2020-02-08
 * Singleton class for MealStorage class
 *
 * @author a.chernyavskiy0n
 */
public class BaseConfig {
    private static final Logger log = getLogger(BaseConfig.class);
    private static final BaseConfig INSTANCE = new BaseConfig();

    private final Storage storage;

    public static BaseConfig get() {
        return INSTANCE;
    }

    private BaseConfig() {
        log.info("New Singleton was constructed");
        storage = new MealStorage();
    }

    public Storage getStorage() {
        return storage;
    }
}
