package ru.javawebinar.topjava.storage;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 2020-02-08
 * Controller for all CRUD operations in memory (in List)
 *
 * @author a.chernyavskiy0n
 */
public class InMemoryMealStorage implements Storage {

    private static final Logger log = getLogger(InMemoryMealStorage.class);

    private Map<Integer, Meal> map = new ConcurrentHashMap<>();
    private int counter;

    public InMemoryMealStorage() {
        log.info("Storage was created");
        //this.map = MealsUtil.filteredStream(MealsUtil.meals, 2000);
    }

    @Override
    public void save(Meal meal) {
        log.info("Save " + meal);
        map.put(getSearchKey(meal), meal);
    }

    @Override
    public void delete(String id) {
        log.info("Delete " + id);
        map.remove(Integer.parseInt(id));
    }

    @Override
    public Meal get(String id) {
        log.info("Get " + id);
        return map.get(Integer.parseInt(id));
    }

    @Override
    public List<Meal> getAll() {
        log.info("getAll");
        return new ArrayList<>(map.values());
    }

    private int getSearchKey(Meal meal) {
        if (map.containsValue(meal)) {
            return counter;
        } else {
            return counter++;
        }
    }
}
