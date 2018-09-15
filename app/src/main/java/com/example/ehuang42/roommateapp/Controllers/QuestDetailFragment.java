package com.example.ehuang42.roommateapp.Controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ehuang42.roommateapp.Model.Model;
import com.example.ehuang42.roommateapp.Model.Quest;
import com.example.ehuang42.roommateapp.Model.User;
import com.example.ehuang42.roommateapp.R;

import java.util.List;

/**
 * A fragment representing a single Quest detail screen.
 *
 * Basically this displays a list of Users that are in a particular Quest
 * that was selected from the main screen.
 *
 * This fragment is either contained in a {@link QuestListActivity}
 * in two-pane mode (on tablets) or a {@link QuestDetailActivity}
 * on handsets.
 */
public class QuestDetailFragment extends Fragment {
    /**
     * The fragment arguments representing the  ID's that this fragment
     * represents.  Used to pass keys into other activities through Bundle/Intent
     */
    public static final String ARG_Quest_ID = "Quest_id";
    public static final String ARG_User_ID = "User_id";

    /**
     * The Quest that this detail view is for.
     */
    private Quest mQuest;

    /**
     * The adapter for the recycle view list of Users
     */
    private SimpleUserRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuestDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if we got a valid Quest passed to us
        if (getArguments().containsKey(ARG_Quest_ID)) {
            // Get the id from the intent arguments (bundle) and
            //ask the model to give us the Quest object
            Model model = Model.getInstance();
           // mQuest = model.getQuestById(getArguments().getInt(ARG_Quest_ID));
            mQuest = model.getCurrentQuest();
            Log.d("QuestDetailFragment", "Passing over Quest: " + mQuest);
            //Log.d("QuestDetailFragment", "Got Users: " + mQuest.getUsers().size());

            Activity activity = this.getActivity();

            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mQuest.toString());
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.quest_detail, container, false);

        //Step 1.  Setup the recycler view by getting it from our layout in the main window
        View recyclerView = rootView.findViewById(R.id.user_list);
        assert recyclerView != null;
        //Step 2.  Hook up the adapter to the view
        setupRecyclerView((RecyclerView) recyclerView);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    /**
     * Set up an adapter and hook it to the provided view
     *
     * @param recyclerView  the view that needs this adapter
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        adapter = new SimpleUserRecyclerViewAdapter((List<User>) mQuest.getUsers());
        Log.d("Adapter", adapter.toString());
        recyclerView.setAdapter(adapter);
    }

    /**
     * This inner class is our custom adapter.  It takes our basic model information and
     * converts it to the correct layout for this view.
     *
     * In this case, we are just mapping the toString of the User object to a text field.
     */
    public class SimpleUserRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleUserRecyclerViewAdapter.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private final List<User> mValues;

        /**
         * set the items to be used by the adapter
         * @param items the list of items to be displayed in the recycler view
         */
        public SimpleUserRecyclerViewAdapter(List<User> items) {
            mValues = items;
        }

        @Override
        public SimpleUserRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            /*
              This sets up the view for each individual item in the recycler display
              To edit the actual layout, we would look at: res/layout/Quest_list_content.xml
              If you look at the example file, you will see it currently just 2 TextView elements
             */
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_detail, parent, false);
            return new SimpleUserRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final SimpleUserRecyclerViewAdapter.ViewHolder holder, int position) {
            /*
            This is where we have to bind each data element in the list (given by position parameter)
            to an element in the view (which is one of our two TextView widgets
             */
            //start by getting the element at the correct position
            holder.mUser = mValues.get(position);
            Log.d("Adapter", "User: " + holder.mUser);
            /*
              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
              textview and the string rep of a Quest in the other.
             */
            holder.mIdView.setText("" + mValues.get(position).get_ID());
            holder.mContentView.setText(mValues.get(position).toString());

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*
                        //on a phone, we need to change windows to the detail view
                        Context context = v.getContext();
                        //create our new intent with the new screen (activity)
                        Intent intent = new Intent(context, EditUserActivity.class);
                        /*
                            pass along the selected User we can retrieve the correct data in
                            the next window
                         */
                    /*
                        intent.putExtra(QuestDetailFragment.ARG_User_ID, holder.mUser);

                        //now just display the new window
                        context.startActivity(intent);

*/
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
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
            public User mUser;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.user_id);
                Log.d("Holder", mIdView.toString());
                mContentView = (TextView) view.findViewById(R.id.user_details);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
