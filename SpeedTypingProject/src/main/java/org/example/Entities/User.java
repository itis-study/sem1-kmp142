package org.example.Entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {
    private boolean isAdmin;
    private String login;
    private ArrayList<TypingProcess> typingHistory;
    private Integer score;
    private String password;

    public User() {
    }

    public User(boolean isAdmin, String login, ArrayList<TypingProcess> typingHistory, Integer score, String password) {
        this.isAdmin = isAdmin;
        this.login = login;
        this.typingHistory = typingHistory;
        this.score = score;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getLogin() {
        return login;
    }

    public ArrayList<TypingProcess> getTypingHistory() {
        return typingHistory;
    }

    public Integer getScore() {
        return score;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setTypingHistory(ArrayList<TypingProcess> typingHistory) {
        this.typingHistory = typingHistory;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
