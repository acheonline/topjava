package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Map;

/**
 * 2020-02-11
 *
 * @author a.chernyavskiy0n
 */
public interface CRUDMealStorage {
    void save(Meal meal);

    void delete(String id);

    Meal get(String id);

    List<Meal> getAll();

    Map<Integer, Meal> getMap();

    int getMealById(Meal meal);
}
