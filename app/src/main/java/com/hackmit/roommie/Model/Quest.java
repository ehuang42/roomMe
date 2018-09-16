package com.hackmit.roommie.Model;

import java.util.ArrayList;
import java.util.List;

public class Quest {
    public static enum ROLE{
        OWNER, ADMIN, PARTICIPANT;
    }
    private List<User> admin;
    private List<User> users;
    private UserGroup usersGroup;
    private User giver;
    private String title;
    private String detail;
    private int reward;
    private int questID;

    public Quest() {
    }

    public Quest(String title) {
        this.users = new ArrayList<>();
        this.usersGroup = new UserGroup();
        this.giver = null;
        this.title = title;
        this.detail = "";
        this.reward = 0;
        this.questID = hashCode();
    }

    public Quest(List<User> users, UserGroup usersGroup, User giver, String title, String detail, int reward) {
        this.users = users;
        this.usersGroup = usersGroup;
        this.giver = giver;
        this.title = title;
        this.detail = detail;
        this.reward = reward;
        this.questID = hashCode();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UserGroup getUsersGroup() {
        return usersGroup;
    }

    public void setUsersGroup(UserGroup usersGroup) {
        this.usersGroup = usersGroup;
    }

    public User getGiver() {
        return giver;
    }

    public void setGiver(User giver) {
        this.giver = giver;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        return title;
    }

    /**
     * Quests are considered the same when they have the same title and giver.
     * @param obj
     * @return if two quests have the same title and giver.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Quest) {
            return false;
        }
        Quest other = (Quest) obj;
        return other.title.equals(title) && other.giver.equals(giver);
    }
}
