package com.example.ehuang42.roommateapp.Model;

import java.util.List;

public class Quest {
    private String name;
    private User giver;
    private UserGroup takers;
    private int reward;
    private String description;

    public Quest(String name, User giver, int reward) {
        this.name = name;
        this.giver = giver;
        this.reward = reward;
        this.takers = new UserGroup();
        this.description = "This is a quest";
    }
    public Quest(String name) {
        this.name = name;
        this.giver = null;
        this.reward = 0;
        this.takers = new UserGroup();
        this.description = "This is a quest";
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public User getGiver() {
        return giver;
    }
    public void setGiver(User giver) {
        this.giver = giver;
    }
    public int getReward() {
        return reward;
    }
    public void setReward(int reward) {
        this.reward = reward;
    }

    public UserGroup getUsers() {
        return takers;
    }
    public void setUsers(UserGroup takers) {
        this.takers = takers;
    }
    public String toString() {
        return name + ", a quest by" + giver.toString();
    }

    @Override
    public boolean equals(Object o) {
        Quest c = (Quest) o;
        return (c.getName().equals(name));
    }
}
