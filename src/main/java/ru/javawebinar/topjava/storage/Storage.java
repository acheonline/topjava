package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

/**
 * 2020-02-08
 * Interface for Storage implementation of any CRUD storage
 *
 * @author a.chernyavskiy0n
 */
public interface Storage {

    void save(MealTo meal);

    void delete(String uuid);

    MealTo get(String uuid);

    List<MealTo> getAll();

    void clear();

    void update(MealTo meal);

    int size();
}
