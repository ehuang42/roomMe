package com.hackmit.roommie.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.hackmit.roommie.Model.Globals;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hackmit.roommie.Model.Model;
import com.hackmit.roommie.Model.Quest;
//import com.hackmit.roommie.Model.User;

import com.hackmit.roommie.Model.User;
import com.hackmit.roommie.R;


public class CreateQuestActivity extends AppCompatActivity {

    /* ************************
        Widgets we will need for binding and getting information
     */
    private EditText nameField;
    private EditText descriptionField;
    private EditText rewardField;

    //for when we have this part working
    //private User _user = Globals.getUser();
    //private User _user = new User("Boo", "dffd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_quest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * Grab the dialog widgets so we can get info for later
         */
        nameField = (EditText) findViewById(R.id.name_input);
        descriptionField = (EditText) findViewById(R.id.description_input);
        rewardField = (EditText) findViewById(R.id.reward_input);

        final Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseUser currentFBUser = FirebaseAuth.getInstance().getCurrentUser();
                User currentUser = new User(currentFBUser.getDisplayName(), currentFBUser.getUid());
                Model model = Model.getInstance();
                Quest _quest = new Quest("New Quest");
                _quest.setGiver(currentUser);
                _quest.setTitle(nameField.getText().toString());
                _quest.setDetail(descriptionField.getText().toString());
                _quest.setReward(Integer.parseInt(rewardField.getText().toString()));
                model.addQuest(_quest);
                startActivity(new Intent(CreateQuestActivity.this, QuestListActivity.class));
            }
        });
    }
}


