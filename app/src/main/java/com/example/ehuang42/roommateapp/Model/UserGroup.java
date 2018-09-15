package com.example.ehuang42.roommateapp.Model;

import java.util.List;

public class UserGroup {
    private List<User> _users;

    private List<User> _owners;
    private List<User> _admins;

    private List<User> _viewers;
    // TODO: 9/15/2018 add support for quest objects
    private int _groupID;

    public UserGroup(List<User> _users) {
        this._users = _users;
        this._groupID = hashCode();
    }

    public UserGroup() {
    }

    public List<User> get_users() {
        return _users;
    }

    public void set_users(List<User> _users) {
        this._users = _users;
    }

    public List<User> get_owners() {
        return _owners;
    }

    public void set_owners(List<User> _owners) {
        this._owners = _owners;
    }

    public List<User> get_admins() {
        return _admins;
    }

    public void set_admins(List<User> _admins) {
        this._admins = _admins;
    }

    public List<User> get_viewers() {
        return _viewers;
    }

    public void set_viewers(List<User> _viewers) {
        this._viewers = _viewers;
    }

    public int get_groupID() {
        return _groupID;
    }
}
