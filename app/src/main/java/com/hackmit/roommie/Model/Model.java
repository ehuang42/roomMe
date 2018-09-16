package com.hackmit.roommie.Model;
//import android.support.annotation.NonNull;

import com.hackmit.roommie.Model.Quest;
import java.util.ArrayList;
import java.util.List;


/**
 * Repurposed by Alexa Flesch for a hackMIT project but originally created by robertwaters on 1/5/17.
 *
 * This is our facade to the Model.  We are using a Singleton design pattern to allow
 * access to the model from each controller.
 *
 *
 */

    public class Model {
        /** Singleton instance */
        private static final Model _instance = new Model();
        public static Model getInstance() { return _instance; }

        /** holds the list of all quests */
        private List<Quest> _quests;

        /** the currently selected quest, defaults to first quest */
        private Quest _currentQuest;

        /** Null Object pattern, returned when no quest is found */
        private final Quest nullQuest = new Quest("Null");


        /**
         * make a new model
         */
        private Model() {
            _quests = new ArrayList<>();

            //comment this out after full app developed -- for homework leave in
            loadDummyData();

        }

        /**
         * populate the model with some dummy data.  The full app would not require this.
         * comment out when adding new quests functionality is present.
         */
        private void loadDummyData() {
            _quests.add(new Quest("Do the dishes"));
            _quests.add(new Quest( "Take out trash"));
            _quests.add(new Quest("Get groceries"));
            _quests.get(0).getUsers().add(new User("Bob", 0 + ""));
            _quests.get(0).getUsers().add(new User("Sally", 2 + ""));
            _quests.get(1).getUsers().add(new User("Fred", 4 + ""));
            _quests.get(1).getUsers().add(new User("Edith", 6 + ""));
            _currentQuest = _quests.get(0);
        }

        /**
         * get the quests
         * @return a list of the courses in the app
         */
        public List<Quest> getQuests() { return _quests; }

        /**
         * add a course to the app.  checks if the course is already entered
         *
         * uses O(n) linear search for course
         *
         * @param quest  the course to be added
         * @return true if added, false if a duplicate
         */
            public boolean addCourse(Quest quest) {
            for (Quest c : _quests) {
                if (c.equals(quest)) return false;
            }
            _quests.add(quest);
            return true;
        }

        /**
         *
         * @return  the currently selected course
         */
        public Quest getCurrentQuest() { return _currentQuest;}

        public void setCurrentQuest(Quest quest) { _currentQuest = quest; }



         /*
        /**
         * add a user to the current quest
         *
         * @param User the student to add
         * @return true if student added, false if not added
         */
        /*
        public boolean addUser(User user) {
            return _currentQuest != null && _currentQuest.addUser(user);
        }

        /**
         * Replace an existing students data with new data
         *
         * @param user the student being edited
         */
        /*
        public void replaceUserData(User user) {
            User existing = _currentQuest.findUserById(user.get_name());

            //if existing comes back null, something is seriously wrong
            if (BuildConfig.DEBUG && (existing == null)) { throw new AssertionError(); }

            //update the name
            existing.set_name(user.get_credit());

            //update the major
            existing.set_credit(user.get_credit());

        }
        */
    //}

}
