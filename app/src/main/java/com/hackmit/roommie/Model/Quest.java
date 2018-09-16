package com.hackmit.roommie.Model;
import java.util.ArrayList;
import java.util.List;
public class Quest {
    private String name;
    private User giver;
    private UserGroup usersGroup;
    private List<User> users;
    private int reward;
    private String description;
    public Quest(String name, User giver, int reward) {
        this.name = name;
        this.giver = giver;
        this.reward = reward;
        this.usersGroup = new UserGroup();
        this.users = usersGroup.get_users();
        this.description = "This is a quest";
    }
    public Quest(String name) {
        this.name = name;
        this.giver = null;
        this.reward = 0;
        this.usersGroup = new UserGroup();
        this.users = usersGroup.get_users();
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
    public ArrayList<User> getUsers() {
        return usersGroup.get_users();
    }
    public void setUsers(ArrayList<User> users) {
        this.usersGroup.set_users(users);
    }
    public void setUser(User taker) {
        this.usersGroup.add(taker);
    }
    public String toString() {
        return name;
    }
    @Override
    public boolean equals(Object o) {
        Quest c = (Quest) o;
        return (c.getName().equals(name));
    }
}