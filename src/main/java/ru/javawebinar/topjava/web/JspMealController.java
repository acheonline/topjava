package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static java.util.Objects.requireNonNull;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;

@Controller()
public class JspMealController {
    @Autowired
    private MealService service;

    @GetMapping("/meals")
    public String getMeals(Model model) {
        String action = (String) model.getAttribute("action");
        int id = getId(model, "id");
        int userId = getId(model, "userId");
        switch (action == null ? "all" : action) {
            case "delete":
                service.delete(id, userId);
                return "redirect:meals";
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        service.get(id, userId);
                model.addAttribute("meal", meal);
                return "forward:mealForm";
            case "filter":
                LocalDate startDate = parseLocalDate((String) model.getAttribute("startDate"));
                LocalDate endDate = parseLocalDate((String) model.getAttribute("endDate"));
                model.addAttribute("meals", service.getBetweenInclusive(startDate, endDate, userId));
                return "forward:meals";
            case "all":
            default:
                model.addAttribute("meals", service.getAll(userId));
                return "forward:meals";
        }
    }

    @PostMapping("/meals")
    public String setMeals(Model model) {
        Meal meal = new Meal(
                LocalDateTime.parse((CharSequence) requireNonNull(model.getAttribute("dateTime"))),
                (String) model.getAttribute("description"),
                Integer.parseInt((String) requireNonNull(model.getAttribute("calories"))));
        int userId = getId(model, "userId");
        if (StringUtils.isEmpty(userId)) {
            service.create(meal, userId);
        } else {
            service.update(meal, userId);
        }
        return "redirect:meals";
    }

    private int getId(Model model, String param) {
        String paramId = (String) model.getAttribute(param);
        assert paramId != null;
        return Integer.parseInt(paramId);
    }
}
