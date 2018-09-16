package com.hackmit.roommie.Model;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class Globals extends Application {
    private List<User> allUsers = new ArrayList<>();
    private User currentUser;

}
