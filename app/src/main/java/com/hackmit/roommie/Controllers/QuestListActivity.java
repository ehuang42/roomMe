package com.hackmit.roommie.Controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import android.support.v7.app.AppCompatActivity;

import com.hackmit.roommie.Model.Model;
import com.hackmit.roommie.Model.Quest;
import com.hackmit.roommie.R;

/**
 * An activity representing a list of Quests.
 */


public class QuestListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        //Step 1.  Setup the recycler view by getting it from our layout in the main window
        View recyclerView = findViewById(R.id.quest_list);
        assert recyclerView != null;
        //Step 2.  Hook up the adapter to the view
        setupRecyclerView((RecyclerView) recyclerView);
    }

    /**
     * Set up an adapter and hook it to the provided view
     *
     * @param recyclerView the view that needs this adapter
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Model model = Model.getInstance();
        recyclerView.setAdapter(new SimpleQuestRecyclerViewAdapter(model.getQuests()));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.questSignUpButton:
                //TODO implement this button

                break;
            default:
                //do nothing
        }
    }

    /**
     * This inner class is our custom adapter.  It takes our basic model information and
     * converts it to the correct layout for this view.
     * <p>
     * In this case, we are just mapping the toString of the Quest object to a text field.
     */
    public class SimpleQuestRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleQuestRecyclerViewAdapter.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private final List<Quest> mQuests;

        /**
         * set the items to be used by the adapter
         *
         * @param items the list of items to be displayed in the recycler view
         */
        public SimpleQuestRecyclerViewAdapter(List<Quest> items) {
            mQuests = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            /*

              This sets up the view for each individual item in the recycler display
              To edit the actual layout, we would look at: res/layout/Quest_list_content.xml
              If you look at the example file, you will see it currently just 2 TextView elements
             */
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.quest_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            final Model model = Model.getInstance();
            /*
            This is where we have to bind each data element in the list (given by position parameter)
            to an element in the view (which is one of our two TextView widgets
             */
            //start by getting the element at the correct position
            holder.mQuest = mQuests.get(position);
            /*
              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
              textview and the string rep of a Quest in the other.
             */
            holder.mIdView.setText(mQuests.get(position).getTitle());
            holder.mContentView.setText(mQuests.get(position).toString());

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //on a phone, we need to change windows to the detail view
                    Context context = v.getContext();
                    //create our new intent with the new screen (activity)
                    Intent intent = new Intent(context, QuestDetailActivity.class);
                        /*
                            pass along the id of the Quest so we can retrieve the correct data in
                            the next window
                         */
                    intent.putExtra(QuestDetailFragment.ARG_Quest_ID, holder.mQuest.getTitle());

                    model.setCurrentQuest(holder.mQuest);

                    //now just display the new window
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mQuests.size();
        }

        /**
         * This inner class represents a ViewHolder which provides us a way to cache information
         * about the binding between the model element (in this case a Quest) and the widgets in
         * the list view (in this case the two TextView)
         */

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Quest mQuest;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}

