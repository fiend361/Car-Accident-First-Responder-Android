package com.jsoftware.capstone;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class SearchRecyclerViewActivity extends AppCompatActivity {

    private TextView textView;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_recycler_view);
        getSupportActionBar().setElevation(0);
        FirebaseMessaging.getInstance().subscribeToTopic("NEWS");

        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.case0, "Heart Attack", "sudden dizziness, tightness or squeezing in chest and back, cold sweat, fatigue, and shortness of breath"));
        exampleList.add(new ExampleItem(R.drawable.case1, "Anaphylaxis", "allergic reaction that causes itchy rash, throat or tongue swelling, shortness of breath, vomiting, lightheadedness, and low blood pressure."));
        exampleList.add(new ExampleItem(R.drawable.case2, "Stroke", "sudden confusion, trouble speaking, difficulty understanding speech, trouble walking, dizziness, and loss of balance"));
        exampleList.add(new ExampleItem(R.drawable.case3, "Diabetes", "unconsciousness, irritability, confusion, weakness, and drowsiness"));
        exampleList.add(new ExampleItem(R.drawable.case4, "Seizures", "change in your behavior, staring spell, uncontrollable jerking movements of the arms and legs."));
        exampleList.add(new ExampleItem(R.drawable.case5, "Asthma", "shortness of breath, chest tightness, trouble sleeping, and coughing"));
        exampleList.add(new ExampleItem(R.drawable.case6, "Fractures", "swelling or bruising over a bone, deformity of an arm or leg, and extreme pain"));
        exampleList.add(new ExampleItem(R.drawable.case7, "Cuts and grazes", "skin opening or abrasion that results in blood"));
        exampleList.add(new ExampleItem(R.drawable.case8, "Dog bites", "a bite that result in punctures, crushes, lacerations and avulsion of flaps."));
        exampleList.add(new ExampleItem(R.drawable.case9, "Fainting", "blurred vision, ringing in the ears, dizziness, and loss of consciousness"));
        exampleList.add(new ExampleItem(R.drawable.case10, "Nosebleed", "if someone is bleeding from the nose"));
        exampleList.add(new ExampleItem(R.drawable.case11, "Wasp stings", "severe swelling, gasping, and extreme pain"));
        exampleList.add(new ExampleItem(R.drawable.case12, "Major Bleeding", "a deep wound that results in major bleeding"));
        exampleList.add(new ExampleItem(R.drawable.case13, "Puncture Wounds", "a knife stab or a nail penetration"));
        exampleList.add(new ExampleItem(R.drawable.case14, "Shock", "cool, pale or ashen skin, bluish tinge to lips or fingernails, and enlarged pupils"));
        exampleList.add(new ExampleItem(R.drawable.case15, "Minor Wounds", "like scratch wounds"));
        exampleList.add(new ExampleItem(R.drawable.case16, "Burns", "red or white and peeling skin, and swelling"));

        Toast.makeText(this, "emergency added", Toast.LENGTH_SHORT).show();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 2);
        mAdapter = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                View v = mLayoutManager.findViewByPosition(position);
                textView = v.findViewById(R.id.textView);
                Intent intent = new Intent(
                  SearchRecyclerViewActivity.this,
                   FirstAidActivity.class);
                intent.putExtra("name", textView.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }
}
