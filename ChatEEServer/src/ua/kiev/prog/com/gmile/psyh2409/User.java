package ua.kiev.prog.com.gmile.psyh2409;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.Message;

import java.util.*;

public class User {
    private String login;
    private String password;
    private final List<Message> mesList = new ArrayList<>();
    private boolean isOnline;
    private final Map<String, Room> rooms = new HashMap<>();

    public User() {
        super();
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public Map<String, Room> getRooms() {
        return rooms;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Message> getMesList() {
        return mesList;
    }

    public List<User> showAllUsers() {
        return new ArrayList<>();
    }

    public List<Message> getHistory() {
        return new ArrayList<>();
    }

    private String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static User fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, User.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", mesList=" + mesList +
                ", isOnline=" + isOnline +
                ", rooms=" + rooms +
                '}';
    }
}
