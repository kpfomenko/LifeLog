package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class Home extends ActionBarActivity{
    private RecyclerView rec;
    private RecAdapter recAdapter;
    private LinearLayoutManager recLayout = new LinearLayoutManager(this);
    private View noEntries;
    private ArrayList<Entry> data;
    private ArrayList<String> categoryArray = new ArrayList<String>() {{
        add("Work");
        add("Rest");
        add("Personal");
        add("Active");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        noEntries = findViewById(R.id.no_entries);
        rec = (RecyclerView) findViewById(R.id.recycler);
        rec.setHasFixedSize(true);
        recLayout.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(recLayout);
        data = new ArrayList<Entry>();
        Entry tempEntry = new Entry("8:00", "9:00", 1, "Morning Run", "jog around lagoon", R.color.actionbar);
        Entry tempEntry2 = new Entry("9:00", "10:00", 2, "Morning Run", "jog around lagoon", R.color.blue);
        data.add(tempEntry);
        data.add(tempEntry2);
        data.add(tempEntry);
        data.add(tempEntry2);
        recAdapter = new RecAdapter(data);
        rec.setAdapter(recAdapter);
        if(recAdapter.getItemCount()!=0){
            noEntries.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onTestFragment(View v){
//        DialogFragment newFragment = new CreateEntryFragment();
//        newFragment.show(getFragmentManager(), "Create Entry");
        //Call new Activity
        Intent intent = new Intent(this, CreateEntryActivity.class);
//        intent.putExtra("Categories", categoryArray);
//        Toast.makeText(getApplicationContext(), "Categories 1: "+ categoryArray.get(0), Toast.LENGTH_LONG).show();
        intent.putStringArrayListExtra("Categories", (ArrayList<String>) categoryArray);
        startActivity(intent);
    }

    public void home_card_click(View v){
        HomeCard cast = (edu.ucsb.cs.cs185.kfomenko.lifelog.HomeCard) v;
//        IMPORTANT USAGE FOR PASSING ARRAYLIST<ENTRY> BETWEEN ACTIVITIES
//
//        TO PUT:
//        Intent i = new Intent(...);
//        i.putParcelableArrayList("data", yourArrayList<T extends Parcelable>);
//
//        TO GET:
//        ArrayList<Entry> yourArrayList = getIntent.getParcelableArrayList("data");
        if(data == null){
            data = new ArrayList<Entry>();
        }
        Intent intent = new Intent(this, EditEntryActivity.class);
        intent.putParcelableArrayListExtra("entryList", data);
        intent.putExtra("currEntry", cast.getEntry());
        startActivity(intent);
    }
}
