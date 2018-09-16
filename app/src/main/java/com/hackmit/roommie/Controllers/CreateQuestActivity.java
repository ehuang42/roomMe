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

import com.hackmit.roommie.Model.Quest;
import com.hackmit.roommie.Model.User;

import cs2340.gatech.edu.lab3.R;
import cs2340.gatech.edu.lab3.model.ClassStanding;
import cs2340.gatech.edu.lab3.model.Model;
import cs2340.gatech.edu.lab3.model.Student;

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
        setContentView(R.layout.activity_edit_student);
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
        nameField = (EditText) findViewById(R.id.student_name_input);
        majorSpinner = (Spinner) findViewById(R.id.spinner);
        standingSpinner = (Spinner) findViewById(R.id.spinner2);
        idField = (TextView) findViewById(R.id.student_id_field);

        /*
          Set up the adapter to display the allowable majors in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Student.legalMajors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        majorSpinner.setAdapter(adapter);
         /*
          Set up the adapter to display the allowable standings in the spinner
         */
        ArrayAdapter<String> standingAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, ClassStanding.values());
        standingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        standingSpinner.setAdapter(standingAdapter);
        /*
           If a student has been passed in, this was an edit, if not, this is a new add
         */
        if (getIntent().hasExtra(CourseDetailFragment.ARG_STUDENT_ID)) {
            _student = (Student) getIntent().getParcelableExtra(CourseDetailFragment.ARG_STUDENT_ID);
            majorSpinner.setSelection(Student.findPosition(_student.getMajor()));
            standingSpinner.setSelection(_student.getStanding().ordinal());
            editing = true;
        } else {
            _student = new Student();
            editing = false;
        }

        nameField.setText(_student.getName());
        idField.setText("" + _student.getId());

    }

    /**
     * Button handler for the add new student button
     * @param view the button
     */
    public void onAddPressed(View view) {
        Log.d("Edit", "Add Student");
        Model model = Model.getInstance();

        _student.setName(nameField.getText().toString());
        _student.setMajor((String) majorSpinner.getSelectedItem());
        _student.setStanding((ClassStanding) standingSpinner.getSelectedItem());

        Log.d("Edit", "Got new student data: " + _student);
        if (!editing) {
            model.addStudent(_student);
        }  else {
            model.replaceStudentData(_student);
        }

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

          _major = parent.getItemAtPosition(position).toString();
        _standing = (ClassStanding) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        _major = "NA";
    }
}
