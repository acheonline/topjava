package ru.javawebinar.topjava.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MealTo implements Serializable {
    private static final long serialVersionUID = 2L;

    private LocalDateTime dateTime;

    private String description;

    private int calories;

    private boolean excess;

    private String uuid;

    public MealTo(String uuid, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
        this.uuid = uuid;
    }

    public MealTo(LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public MealTo() {
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public String getUuid() {
        return uuid;
    }

    public boolean isExcess() {
        return excess;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }
}
