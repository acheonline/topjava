package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * 2020-02-08
 * Interface for Storage implementation of any CRUD storage
 *
 * @author a.chernyavskiy0n
 */
public interface Storage {

    void save(Meal meal);

    void delete(String id);

    Meal get(String id);

    List<Meal> getAll();
}
