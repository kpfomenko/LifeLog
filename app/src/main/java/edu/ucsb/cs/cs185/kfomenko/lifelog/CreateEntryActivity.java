package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CreateEntryActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener, CreateCategory.CreateCategoryListener {
    private ArrayList<Entry> entryList;
    private ArrayList<String> categoryArray;
    private ArrayList<String> dropDownArray = new ArrayList<String>();

    //Values being set
    private String startTime;
    private String endTime;
    private String cat;
    private String label;
    private String annotation;
    private Integer color;

    int selectCount = 0;

    ArrayAdapter<String> adapter;
    MySpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null){
            entryList = extras.getParcelableArrayList("entryList");
            categoryArray = extras.getStringArrayList("Categories");
            for(int i=0; i < categoryArray.size(); i++){
                dropDownArray.add(categoryArray.get(i));
            }
            dropDownArray.add("+ Create");
//            Toast.makeText(getApplicationContext(), "Categories Loaded! + "+  categoryArray.get(0), Toast.LENGTH_LONG).show();
        }else{
//            Toast.makeText(getApplicationContext(), "Load-failed", Toast.LENGTH_LONG).show();
            categoryArray = new ArrayList<String>();
        }
        EditText et = (EditText) findViewById(R.id.activity_name_input);
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                EditText et = (EditText) v;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    et.setCursorVisible(false);
                }
                return false;
            }
        });
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) v;
                et.setCursorVisible(true);
            }
        });
        edu.ucsb.cs.cs185.kfomenko.lifelog.MyEditText description = (edu.ucsb.cs.cs185.kfomenko.lifelog.MyEditText) findViewById(R.id.annotationEntry);
//        et.setFocusable(false);
        description.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                EditText et = (EditText) v;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    et.setCursorVisible(false);
                }
                return false;
            }
        });
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) v;
                et.setCursorVisible(true);
            }
        });
        spinner = (MySpinner) findViewById(R.id.cat_spinner);
        if(spinner == null){
            Toast.makeText(getApplicationContext(), "Spinner Is NULL!", Toast.LENGTH_LONG).show();
        }else{
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dropDownArray);
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
        if (id == android.R.id.home) {
            Intent intent = new Intent(this, Home.class);
            intent.putParcelableArrayListExtra("entryList", entryList);
            intent.putStringArrayListExtra("Categories", categoryArray);
            startActivity(intent);

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
//        selectCount = selectCount + 1;
//        if(selectCount > 1){
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
                DialogFragment newFragment = new CreateCategory();
                newFragment.show(getFragmentManager(), "Create New Category:");
//                cat = "+ Create";
                color = R.color.custom;
                break;
            default:
                cat = parent.getItemAtPosition(position).toString();
                color = R.color.custom;
//                Toast.makeText(getApplicationContext(), "Error: Unknown option selected" , Toast.LENGTH_LONG).show();
//                color = R.color.red;
        }
        LinearLayout lLayout = (LinearLayout) findViewById(R.id.nameLabel);
        lLayout.setBackgroundResource(color);
//        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

//    private String startTime;
//    private String endTime;
//    private String cat;
//    private String label;
//    private String annotation;
//    private Integer color;

    public void createNewEntry(View v){
        //creating new entry -- must error check

        EditText nameLabel = (EditText) findViewById(R.id.activity_name_input);

        if(!nameLabel.getText().toString().equals("")){
            label = nameLabel.getText().toString();
        }else{
            Toast.makeText(getApplicationContext(), "Error: Please enter name" , Toast.LENGTH_SHORT).show();
            return;
        }

        Button startBtn = (Button) findViewById(R.id.entry_start_time_btn);
        if(!startBtn.getText().toString().equals("Start")){
            startTime = startBtn.getText().toString();
        }else{
            Toast.makeText(getApplicationContext(), "Error: Please enter Start Time" , Toast.LENGTH_SHORT).show();
            return;
        }

        Button endBtn = (Button) findViewById(R.id.entry_end_time_btn);
        if(!endBtn.getText().toString().equals("Finish")){
            endTime = endBtn.getText().toString();
        }else{
            Toast.makeText(getApplicationContext(), "Error: Please enter Finish Time" , Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isGoodTimeRange(startTime, endTime)){
            Toast.makeText(getApplicationContext(), "Error: Start time must be before the end time." , Toast.LENGTH_SHORT).show();
            return;
        }
        //cat and color already set.

        //Getting Annotation
        MyEditText description = (MyEditText) findViewById(R.id.annotationEntry);
        annotation = description.getText().toString();

        //Now create new entry and add it to entry array

        Entry newEntry = new Entry(startTime, endTime, cat, label, annotation, color);


        if(entryList.size() == 0){
            entryList.add(newEntry);
        }else if( entryList.size() == 1){
            //Only 1 element in the list
            if(isEarlierTimeThan(entryList.get(0).getEndTime(), startTime)){
                //current ends before this one
                entryList.add(newEntry);
            }else if(isEarlierTimeThan(endTime, entryList.get(0).getStartTime())){
                //this entry ends before the first one starts
                entryList.add(0, newEntry);
            }else{
                //does not fit
                Toast.makeText(getApplicationContext(), "Error: Time Conflict with event: "+ entryList.get(0).getLabel() , Toast.LENGTH_SHORT).show();
                return;
            }

        }else{
            boolean hasBeenAdded = false;
            for(int i=0; i< entryList.size()- 1; i++){
                Entry currEntry = entryList.get(i);
                Entry nextEntry = entryList.get(i+1);

                if(isEarlierTimeThan(endTime, currEntry.getStartTime())){
                    // Mine happens before the first element in the list
                    // want to insert before currentEntry
                    entryList.add(i, newEntry);
                    hasBeenAdded = true;
                    break;
                }
                if(isEarlierTimeThan(currEntry.getEndTime(), startTime) && isEarlierTimeThan(endTime, nextEntry.getStartTime())){
                    // means my entry starts after the currEntry ends and mine ends before next one!
                    // means ==> Ideal Spot for Current entry has been found!
                    entryList.add(i+1, newEntry);
                    hasBeenAdded = true;
                    break;
                }
                if(isEarlierTimeThan(nextEntry.getEndTime(), startTime) && i+1 == entryList.size()-1){
                    //Next one is the last one, and since your time is after that, insert to end
                    entryList.add(newEntry);
                    hasBeenAdded = true;
                    break;
                }
            }

            if(hasBeenAdded == false){
                Toast.makeText(getApplicationContext(), "Error: Time Chosen Conflicts with Another Event." , Toast.LENGTH_SHORT).show();
                return;
            }

        }



        Intent intent = new Intent(this, Home.class);
        intent.putParcelableArrayListExtra("entryList", entryList);
        intent.putStringArrayListExtra("Categories", categoryArray);

//        Toast.makeText(getApplicationContext(), "Saved." , Toast.LENGTH_SHORT).show();

        startActivity(intent);

    }



    public boolean isGoodTimeRange(String start, String end){
        //have startTime and endTime --> need to make sure they are realistic
        String startTimeArray[] = start.split(":");
        String endTimeArray[] = end.split(":");

        int startHour = Integer.parseInt(startTimeArray[0]);
        int endHour = Integer.parseInt(endTimeArray[0]);

        String tempStartArr[] = startTimeArray[1].split(" ");
        String tempEndArr[] = endTimeArray[1].split(" ");

        int startMin = Integer.parseInt(tempStartArr[0]);
        int endMin = Integer.parseInt(tempEndArr[0]);

        String startAMPM = tempStartArr[1];
        String endAMPM = tempEndArr[1];


        //Convert to 24 Hour Format
        if(startAMPM.equals("PM") && startHour != 12){
            startHour += 12;
        }
        if(startHour == 12 && startAMPM.equals("AM")){
            startHour = 0;
        }
        if(endAMPM.equals("PM") && endHour != 12){
            endHour += 12;
        }
        if(endHour == 12 && endAMPM.equals("AM")){
            endHour = 0;
        }

//        Toast.makeText(getApplicationContext(), "Comparing: "+ startHour+":"+startMin + " and "+  endHour+":"+endMin  , Toast.LENGTH_SHORT).show();


        //Start Checking
        if(start.equals(end)){
            //Same value--Error
//            Toast.makeText(getApplicationContext(), "Case1" , Toast.LENGTH_SHORT).show();
            return false;
        }

        if(startHour > endHour){
            //start hour > end hour
//            Toast.makeText(getApplicationContext(), "Case2" , Toast.LENGTH_SHORT).show();
            return false;
        }

        if(startHour == endHour && startMin > endMin){
            //if same hour, but later minute
//            Toast.makeText(getApplicationContext(), "Case3" , Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;

    }

    public boolean isEarlierTimeThan(String start, String end){
        //have startTime and endTime --> need to make sure they are realistic
        String startTimeArray[] = start.split(":");
        String endTimeArray[] = end.split(":");

        int startHour = Integer.parseInt(startTimeArray[0]);
        int endHour = Integer.parseInt(endTimeArray[0]);

        String tempStartArr[] = startTimeArray[1].split(" ");
        String tempEndArr[] = endTimeArray[1].split(" ");

        int startMin = Integer.parseInt(tempStartArr[0]);
        int endMin = Integer.parseInt(tempEndArr[0]);

        String startAMPM = tempStartArr[1];
        String endAMPM = tempEndArr[1];


        //Convert to 24 Hour Format
        if(startAMPM.equals("PM") && startHour != 12){
            startHour += 12;
        }
        if(startHour == 12 && startAMPM.equals("AM")){
            startHour = 0;
        }
        if(endAMPM.equals("PM") && endHour != 12){
            endHour += 12;
        }
        if(endHour == 12 && endAMPM.equals("AM")){
            endHour = 0;
        }

//        Toast.makeText(getApplicationContext(), "Comparing: "+ startHour+":"+startMin + " and "+  endHour+":"+endMin  , Toast.LENGTH_SHORT).show();


        //Start Checking
        if(start.equals(end)){
            //Same value--Error
//            Toast.makeText(getApplicationContext(), "Case1" , Toast.LENGTH_SHORT).show();
            return true;
        }

        if(startHour > endHour){
            //start hour > end hour
//            Toast.makeText(getApplicationContext(), "Case2" , Toast.LENGTH_SHORT).show();
            return false;
        }

        if(startHour == endHour && startMin > endMin){
            //if same hour, but later minute
//            Toast.makeText(getApplicationContext(), "Case3" , Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

        Dialog dialogView = dialog.getDialog();
        EditText newCatInput = (EditText) dialogView.findViewById(R.id.create_new_cat);

        cat = newCatInput.getText().toString();
        categoryArray.add(cat);
        int oldIndex = dropDownArray.indexOf("+ Create");
        dropDownArray.add(oldIndex, cat);

//        adapter.add(cat);
        int spinPos = adapter.getPosition(cat);
        spinner.setAdapter(adapter);
        spinner.setSelection(spinPos);

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //Failed creation --> still need to set the cat value
        cat = "Active";
        int spinPos = adapter.getPosition(cat);
        spinner.setSelection(spinPos);
    }
}
