package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public List<Meal> getAll(Integer userId) {
        log.info("getAll");
        return service.getAll(userId);
    }

    public Meal get(int id, Integer userId) {
        log.info("get {" + id + "}");
        return service.get(id, userId);
    }

    public Meal create(Meal meal, Integer userId) {
        log.info("create {" + meal + "}");
        checkNew(meal);
        return service.create(meal, userId);
    }

    public void delete(int id, Integer userId) {
        log.info("delete {" + id + "}");
        service.delete(id, userId);
    }

    public void update(Meal meal, int id, Integer userId) {
        log.info("update {" + meal
                + "} with user_id = {" + userId
                + "} of id {" + id);
        assureIdConsistent(meal, id);
        service.update(meal, userId);
    }

    public List<MealTo> getAll(Integer userId, int caloriesPerDay) {
       return service.getTos(userId, caloriesPerDay);
    }
}