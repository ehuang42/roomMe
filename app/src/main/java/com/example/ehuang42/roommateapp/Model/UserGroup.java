package com.example.ehuang42.roommateapp.Model;

import java.util.List;
import java.util.ArrayList;

public class UserGroup {
    private ArrayList<User> _users;

    private ArrayList<User> _owners;
    private ArrayList<User> _admins;

    private ArrayList<User> _viewers;
    // TODO: 9/15/2018 add support for quest objects
    private int _groupID;

    public UserGroup(ArrayList<User> _users) {
        this._users = _users;
        this._groupID = hashCode();
    }

    public UserGroup() {
        this._users = new ArrayList<>();
        this._groupID = hashCode();
    }

    public ArrayList<User> get_users() {
        return _users;
    }

    public void set_users(ArrayList<User> _users) {
        this._users = _users;
    }
    public void add(User _user) { _users.add(_user);
    }

    public ArrayList<User> get_owners() {
        return _owners;
    }

    public void set_owners(ArrayList<User> _owners) {
        this._owners = _owners;
    }

    public ArrayList<User> get_admins() {
        return _admins;
    }

    public void set_admins(ArrayList<User> _admins) {
        this._admins = _admins;
    }

    public ArrayList<User> get_viewers() {
        return _viewers;
    }

    public void set_viewers(ArrayList<User> _viewers) {
        this._viewers = _viewers;
    }

    public int get_groupID() {
        return _groupID;
    }
}
