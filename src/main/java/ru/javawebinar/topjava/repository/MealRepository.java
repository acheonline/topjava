package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {
    // null if not found, when updated
    Meal save(Meal meal, Integer userId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Meal get(int id);

    Meal getByUserId(int id, int userId);

    List<Meal> getAll();

    List<Meal> getAllByUserId(int userId);

}