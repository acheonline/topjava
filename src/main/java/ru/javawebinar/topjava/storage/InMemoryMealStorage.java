package ru.javawebinar.topjava.storage;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 2020-02-08
 * Controller for all CRUD operations in memory (in List)
 *
 * @author a.chernyavskiy0n
 */
public class InMemoryMealStorage implements CRUDMealStorage {

    private static final Logger log = getLogger(InMemoryMealStorage.class);
    private List<Meal> list = new CopyOnWriteArrayList<>();

    public InMemoryMealStorage() {
        log.info("Storage was created");
    }

    @Override
    public void save(Meal meal) {
        log.info("Save " + meal);
        list.add(meal);
    }

    @Override
    public void delete(int id) {
        log.info("Delete " + id);
        list.remove(id);
    }

    @Override
    public Meal get(int id) {
        log.info("Get " + id);
        return list.get(id);
    }

    @Override
    public void update(Meal meal) {
        list.set(list.indexOf(meal), meal);
    }

    @Override
    public List<MealTo> getAllFiltered() {
        log.info("getAll");
        return MealsUtil.filteredByStreams(list, MealsUtil.MIN, MealsUtil.MAX, 2000);
    }
}
