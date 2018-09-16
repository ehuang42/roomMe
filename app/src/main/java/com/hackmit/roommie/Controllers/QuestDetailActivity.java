package com.hackmit.roommie.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hackmit.roommie.Model.Model;
import com.hackmit.roommie.Model.Quest;
import com.hackmit.roommie.Model.User;
import com.hackmit.roommie.R;

import com.google.firebase.auth.FirebaseAuth;

//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;


/**
 * An activity representing a single Quest detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link QuestListActivity}.
 *
 * Here we need to display a list of students.  Our view will be pretty similar
 * since we are displaying a list of students in the Quest.  We are going to use a
 * recycler view again.
 */
public class QuestDetailActivity extends AppCompatActivity {

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.questSignUpButton:
                //grab the current quest
                Quest currentQuest = Model.getInstance().getCurrentQuest();
                User currentUser =  new User(FirebaseAuth.getInstance().getCurrentUser()
                        .getDisplayName(), FirebaseAuth.getInstance().getCurrentUser().getUid());
                if (currentQuest.getUsers().contains(currentUser)) {
                    Toast.makeText(this, "You have already signed up for this quest.", Toast
                            .LENGTH_LONG).show();
                } else {
                    currentQuest.getUsers().add(currentUser);
                    Toast.makeText(this, "You have successfully signed up for this quest!",
                            Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this, QuestListActivity.class));
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Creating a new Student", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(getBaseContext(), EditStudentActivity.class);
                startActivity(intent);
                */
        /*
            }
        });
        */

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.  Pass the Quest info to
            //the fragment
            Bundle arguments = new Bundle();
            arguments.putInt(QuestDetailFragment.ARG_Quest_ID,
                    getIntent().getIntExtra(QuestDetailFragment.ARG_Quest_ID, 0));

            QuestDetailFragment fragment = new QuestDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.quest_detail_container, fragment)
                    .commit();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, QuestListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
