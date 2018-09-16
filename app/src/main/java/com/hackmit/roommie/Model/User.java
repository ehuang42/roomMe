package com.hackmit.roommie.Model;

public class User {
    public enum GENDER {
        MALE, FEMALE, NONBINARY;
    }
    private String _name;
    private String _password;
    private int _credit;
    private String _ID;

    //option params
    /*
    private GENDER gender
     */

    // TODO: 9/15/2018 add support for firebase authen

    public User(String _name, String uid) {
        this._name = _name;
        this._credit = 0;
        this._ID = uid;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_credit() {
        return _credit;
    }

    public void set_credit(int _credit) {
        this._credit = _credit;
    }

    public String get_ID() {
        return _ID;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }


    public String toString() {
        return _name;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {return false;}
        if (! (obj instanceof User)) {return false;}

        User other = (User) obj;
        return other.get_ID().equals(get_ID());
    }

    /**
     * Changes the user's credit by the specified amount.
     * Formula: newCredit = this.credit + delta;
     * @param delta the amount of change inflicted on the user's credit.
     */
    public void deltaCredit(int delta) {
        _credit += delta;
    }
}
