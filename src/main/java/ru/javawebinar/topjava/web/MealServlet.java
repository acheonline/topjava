package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.CRUDMealStorage;
import ru.javawebinar.topjava.storage.InMemoryMealStorage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet implements Serializable {
    private static final Logger log = getLogger(MealServlet.class);

    private static final long serialVersionUID = 2L;
    private static CRUDMealStorage storage;

    @Override
    public void init(ServletConfig config) {
        storage = new InMemoryMealStorage();
        MealsUtil.meals.forEach(m -> storage.save(m));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String description = request.getParameter("description");
        String datetime = request.getParameter("datetime");
        String calories = request.getParameter("calories");
        if (!request.getParameter("id").isEmpty()) {
            int id = Integer.parseInt(request.getParameter("id"));
            storage.delete(id);
            Meal meal = new Meal(LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")),
                    description, Integer.parseInt(calories));
            storage.save(meal);
        } else {
            storage.save(new Meal(LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")),
                    description, Integer.parseInt(calories)));
        }
        request.setAttribute("listTo", storage.getAllFiltered());
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("listTo", storage.getAllFiltered());
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
            return;
        } else {
            Meal meal = null;
            int id = 0;
            switch (action) {
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    storage.delete(id);
                    response.sendRedirect("meals");
                    return;
                case "add":
                    meal = new Meal();
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    meal = storage.get(id);
                    break;
            }
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/edit.jsp").forward(request, response);
        }
    }
}
