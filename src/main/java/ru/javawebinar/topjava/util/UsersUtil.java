package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * 2020-02-17
 *
 * @author a.chernyavskiy0n
 */
public class UsersUtil {

    public static final List<User> USERS = Arrays.asList(
            new User(0, "Name 0", "email0@mail.com", "password0", Role.ROLE_USER),
            new User(1, "Name 1", "email1@mail.com", "password1", Role.ROLE_USER),
            new User(2, "Name 2", "email2@mail.com", "password2", Role.ROLE_USER),
            new User(3, "Name 3", "email3@mail.com", "password3", Role.ROLE_USER, Role.ROLE_ADMIN));
}
