package ua.kiev.prog.com.gmile.psyh2409;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashSet;
import java.util.Set;

public class UserSet {
    private static final UserSet USER_SET = new UserSet();

    private final Gson gson;
    private final Set<User> userSet = new HashSet<>();

    public static UserSet getInstance() {
        return USER_SET;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    private UserSet() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(User u) {
        userSet.add(u);
    }

    public synchronized String toJSON() {
        return gson.toJson(getInstance());
    }
}
