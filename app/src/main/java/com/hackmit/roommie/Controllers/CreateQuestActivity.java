package com.hackmit.roommie.Controllers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.hackmit.roommie.Model.Model;
import com.hackmit.roommie.Model.Quest;
import com.hackmit.roommie.Model.User;

import com.hackmit.roommie.R;


public class CreateQuestActivity extends AppCompatActivity {

    /* ************************
        Widgets we will need for binding and getting information
     */
    private EditText nameField;
    private EditText descriptionField;
    private EditText rewardField;

    private User _user = Globals.getUser();
    private Quest _quest = new Quest("New Quest", _user, 0);

    /* ***********************
       flag for whether this is a new student being added or an existing student being edited;
     */
    private boolean editing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_quest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TBD", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /**
         * Grab the dialog widgets so we can get info for later
         */
        nameField = (EditText) findViewById(R.id.name_input);
        descriptionField = (EditText) findViewById(R.id.description_input);
        rewardField = (EditText) findViewById(R.id.reward_input);
    }

    /**
     * Button handler for the add new student button
     * @param view the button
     */
    public void onAddPressed(View view) {
        Log.d("Edit", "Add Student");
        Model model = Model.getInstance();

        _quest.setName(nameField.getText().toString());
        _quest.setDescription(descriptionField.getText().toString());
        _quest.setReward(Integer.parseInt(rewardField.getText().toString()););
        model.addQuest(_quest);
        finish();
    }

    /**
     * Button handler for cancel
     *
     * @param view the button pressed
     */
    public void onCancelPressed(View view) {
        Log.d("Edit", "Cancel Student");
        finish();
    }
}
