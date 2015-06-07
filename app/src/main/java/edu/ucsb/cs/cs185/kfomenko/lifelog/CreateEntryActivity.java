package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class CreateEntryActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
    private ArrayList<Entry> entryList;
    private ArrayList<String> categoryArray;

    //Values being set
    private String startTime;
    private String endTime;
    private String cat;
    private String label;
    private String annotation;
    private Integer color;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null){
            entryList = extras.getParcelableArrayList("entryList");
            categoryArray = extras.getStringArrayList("Categories");
            categoryArray.add("+ Create");
//            Toast.makeText(getApplicationContext(), "Categories Loaded! + "+  categoryArray.get(0), Toast.LENGTH_LONG).show();
        }else{
//            Toast.makeText(getApplicationContext(), "Load-failed", Toast.LENGTH_LONG).show();
            categoryArray = new ArrayList<String>();
        }

        Spinner spinner = (Spinner) findViewById(R.id.cat_spinner);
        if(spinner == null){
            Toast.makeText(getApplicationContext(), "Spinner Is NULL!", Toast.LENGTH_LONG).show();
        }else{
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_entry, menu);
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

    public void setStartTime(View v){
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
        //0 for Start Button
        newFragment.setBtnVal(0);

    }

    public void setEndTime(View v){
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
        //1 for EndTIme
        newFragment.setBtnVal(1);
    }

//    public void selectACategory(View v){
//
//    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getApplicationContext(), "Spinner Value: "+ parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
        //Selecting a Category
        switch (parent.getItemAtPosition(position).toString() ){
            case "Work":
                cat = "Work";
                color = R.color.work;
                break;
            case "Rest":
                cat = "Rest";
                color = R.color.rest;
                break;
            case "Personal":
                cat = "Personal";
                color = R.color.personal;
                break;
            case "Active":
                cat = "Active";
                color = R.color.active;
                break;
            case "+ Create":
                cat = "+ Create";
                color = R.color.custom;
                break;
            default:
                Toast.makeText(getApplicationContext(), "Error: Unknown option selected" , Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
