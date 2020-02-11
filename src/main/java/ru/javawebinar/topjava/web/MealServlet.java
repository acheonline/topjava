package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.InMemoryMealStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet implements Serializable {
    private static final Logger log = getLogger(MealServlet.class);

    private static final long serialVersionUID = 2L;
    private Storage storage;

    @Override
    public void init(ServletConfig config) {
        storage = new InMemoryMealStorage();
        MealsUtil.meals.forEach(m->storage.save(m));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        Meal meal;
        if (action == null) {
            request.setAttribute("meals", MealsUtil.filteredStream(storage.getAll(), 2000));
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
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }
}
