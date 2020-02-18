package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = getLogger(InMemoryMealRepository.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Meal save(Meal meal, Integer userId) {
        log.info("Save " + meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, Integer userId) {
        if (repository.get(id).getUserId() == userId) {
            log.info("Delete " + id);
            return repository.remove(id) != null;
        } else {
            throw new NotFoundException("Not that user Id");
        }
    }

    @Override
    public Meal getByUserId(int id, Integer userId) {
        log.info("Get by UserId " + id);
        Meal meal = repository.get(id);
        return meal.getUserId() == userId ? meal : null;
    }

    @Override
    public Meal get(int id, Integer userId) {
        if (repository.get(id).getUserId() == userId) {
            log.info("Get " + id);
            return repository.get(id);
        } else {
            throw new NotFoundException("Not that user Id");
        }
    }

    @Override
    public List<Meal> getAllByUserId(Integer userId) {
        log.info("Get All By User ID");
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAll() {
        log.info("getAll");
        return new ArrayList<>(repository.values());
    }
}

