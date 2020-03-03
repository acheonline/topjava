package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(userId);
            em.persist(meal);
            return meal;
        } else {
            if (meal.getId() == userId) {
               em.merge(meal);
            }
            return null;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE, Meal.class)
                .setParameter(1, id)
                .setParameter(2, userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return em.createNamedQuery(Meal.BY_USER, Meal.class)
                .setParameter(1, id)
                .setParameter(2, userId)
                .getSingleResult();
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL, Meal.class)
                .setParameter(1, userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.BETWEEN, Meal.class)
                .setParameter(1, userId)
                .setParameter(2, startDate)
                .setParameter(3, endDate)
                .getResultList();
    }
}