package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

/**
 * 2020-02-11
 *
 * @author a.chernyavskiy0n
 */
public interface CRUDMealStorage {
    void save(Meal meal);

    void delete(int id);

    Meal get(int id);

    void update(Meal meal);

    List<MealTo> getAllFiltered();

}
