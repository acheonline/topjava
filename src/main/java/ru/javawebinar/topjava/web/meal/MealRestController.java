package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    {
        for (Meal meal : MealsUtil.MEALS) {
            create(meal, 1);
        }
    }

    @Autowired
    private MealService service;

    public List<Meal> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Meal get(int id) {
        log.info("get {" + id + "}");
        return service.get(id);
    }

    public Meal create(Meal meal, Integer userId) {
        log.info("create {" + meal.getDescription() + "}");
        checkNew(meal);
        return service.create(meal, userId);
    }

    public void delete(int id) {
        log.info("delete {" + id + "}");
        service.delete(id);
    }

    public void update(Meal meal, int id) {
        log.info("update {" + meal.getDescription()
                + "} with user_id = {" + meal.getUserId()
                + "} of id {" + id);
        assureIdConsistent(meal, id);
        service.update(meal, meal.getUserId());
    }

    public List<MealTo> convertTo(List<Meal> repo, Integer userId, int caloriesPerDay) {
       return service.getTos(repo, userId, caloriesPerDay);
    }
}