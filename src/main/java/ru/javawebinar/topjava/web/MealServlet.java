package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.CRUDMealStorage;
import ru.javawebinar.topjava.storage.InMemoryMealStorage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet implements Serializable {
    private static final Logger log = getLogger(MealServlet.class);

    private static final long serialVersionUID = 2L;
    private static CRUDMealStorage storage;
    private int counter = 0;

    static {
        storage = new InMemoryMealStorage();
        MealsUtil.meals.forEach(m -> storage.save(m));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        Meal meal = storage.get(uuid);
        String description = request.getParameter("description");
        String datetime = request.getParameter("datetime");
        String calories = request.getParameter("calories");
        meal.setDescription(description);
        meal.setDateTime(LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        meal.setCalories(Integer.parseInt(calories));
        storage.save(meal);
        request.setAttribute("mapTo", processTo(storage.getAll()));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        Meal meal = null;
        if (action == null) {
            request.setAttribute("mapTo", processTo(storage.getAll()));
            request.setAttribute("meal", new MealTo());
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
            return;
        }
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("meals");
                return;
            case "add":
                meal = new Meal();
                break;
            case "edit":
                meal = storage.get(uuid);
                break;
        }
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    private Map<Integer, MealTo> processTo(List<Meal> list) {
        Map<Integer, MealTo> mapTo = new ConcurrentHashMap<>();
        List<MealTo> listTo = MealsUtil.filteredStream(list, 2000);
        mapTo.clear();
        listTo.forEach(a -> mapTo.put(counter++, a));
        return mapTo;
    }
}
