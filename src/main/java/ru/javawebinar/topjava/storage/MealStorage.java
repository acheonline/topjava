package ru.javawebinar.topjava.storage;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 2020-02-08
 * Controller for all CRUD operations in memory (in List)
 * @author a.chernyavskiy0n
 */
public class MealStorage implements Storage {

    private static final Logger log = getLogger(MealStorage.class);

    private List<MealTo> arrayList;

    public MealStorage() {
        this.arrayList = MealsUtil.filteredStream(MealsUtil.meals, 2000);
    }

    @Override
    public void save(MealTo meal) {
        log.info("Save " + meal);
        arrayList.add(meal);
    }

    @Override
    public void delete(String uuid) {
        log.info("Delete " + uuid);
        arrayList.remove(getSearchKey(uuid));
    }

    @Override
    public MealTo get(String uuid) {
        log.info("Get " + uuid);
        return arrayList.get(getSearchKey(uuid));
    }

    @Override
    public List<MealTo> getAll() {
        log.info("getAll");
        return arrayList;
    }

    @Override
    public void clear() {
        arrayList.clear();
    }

    @Override
    public void update(MealTo meal) {
        log.info("Update " + meal);
        arrayList.set(getSearchKey(meal.getUuid()), meal);
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    private Integer getSearchKey(String uuid) {
        int pos = -1;
        for (int i = 0; i < size(); i++) {
            if (uuid.equals(arrayList.get(i).getUuid())) {
                pos = i;
                break;
            }
        }
        return pos;
    }
}
