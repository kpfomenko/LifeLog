package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class EditEntry extends ActionBarActivity implements AdapterView.OnItemSelectedListener{
    private ArrayList<Entry> entryList;
    private ArrayList<String> categoryArray;
    private Entry currEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            entryList = extras.getParcelableArrayList("entryList");
            currEntry = (Entry) extras.getParcelable("currEntry");
            categoryArray = extras.getStringArrayList("Categories");
            categoryArray.add("+ Create");
        }
        LinearLayout header = (LinearLayout) findViewById(R.id.edit_entry_header);
        header.setBackgroundResource(currEntry.getColor());
        TextView label = (TextView) findViewById(R.id.edit_entry_label);
        label.setText(currEntry.getLabel());
        TextView startTime = (TextView) findViewById(R.id.edit_entry_start_time);
        startTime.setText(currEntry.getStartTime());
        TextView endTime = (TextView) findViewById(R.id.edit_entry_end_time);
        endTime.setText(currEntry.getEndTime());
        Spinner catSpin = (Spinner) findViewById(R.id.edit_entry_category_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
        catSpin.setAdapter(adapter);
        catSpin.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_entry, menu);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Selecting a Category
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void edit_entry_save(View v){

    }

    public void edit_entry_delete(View v){

    }
}
