package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.BaseConfig;
import ru.javawebinar.topjava.storage.Storage;

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
    private Storage storage; // = Config.get().getStorage();

    @Override
    public void init(ServletConfig config) {
        storage = BaseConfig.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        MealTo mealTo;
        if (action == null) {
            request.setAttribute("meals", storage.getAll());
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
                mealTo = new MealTo();
                break;
            case "edit":
                mealTo = storage.get(uuid);
                break;
        }
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }
}
