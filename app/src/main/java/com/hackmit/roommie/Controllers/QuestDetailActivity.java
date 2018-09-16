package com.hackmit.roommie.Controllers;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hackmit.roommie.Model.Model;
import com.hackmit.roommie.Model.Quest;
import com.hackmit.roommie.Model.User;
import com.hackmit.roommie.R;


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
    public void onClick(android.view.View view) {
        switch (view.getId()) {
            case R.id.questSignUpButton:
                // TODO: 9/16/2018 Implement this button
                //get the current quest the user is viewing
                Quest currentQuest = Model.getInstance().getCurrentQuest();
                FirebaseUser currentFBUser = FirebaseAuth.getInstance().getCurrentUser();
                User currentUser = new User(currentFBUser.getEmail(), currentFBUser.getUid());
                boolean hasSignedUp = currentQuest.getUsers().contains(currentUser);
                if (!hasSignedUp) {
                    //sign up!
                    currentQuest.getUsers().add(currentUser);
                    Toast.makeText(this, "You have signed up for this Quest!", Toast.LENGTH_LONG);
                    //go back to the list
                    startActivity(new Intent(this, QuestListActivity.class));
                } else {
                    Toast.makeText(this, "You have already signed up for this Quest!", Toast
                            .LENGTH_LONG).show();
                }
                break;
            default:
                //do nothing
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
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