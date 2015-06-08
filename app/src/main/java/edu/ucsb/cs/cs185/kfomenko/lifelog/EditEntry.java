package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;


public class EditEntry extends ActionBarActivity implements AdapterView.OnItemSelectedListener, CreateCategory.CreateCategoryListener{
    private ArrayList<Entry> entryList = new ArrayList<Entry>();
    private ArrayList<String> categoryArray = new ArrayList<String>();
    private ArrayList<String> origCategoryArray = new ArrayList<String>();
    private Entry currEntry;
    private Integer color;
    private String cat;
    private String annotation;

    //For creating New Categorys
    private MySpinner catSpin;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            entryList = extras.getParcelableArrayList("entryList");
            currEntry = (Entry) extras.getParcelable("currEntry");
            origCategoryArray = extras.getStringArrayList("Categories");
            for(int i=0; i<origCategoryArray.size(); i++){
                categoryArray.add(origCategoryArray.get(i));
            }
            categoryArray.add("+ Create");
        }
        LinearLayout header = (LinearLayout) findViewById(R.id.edit_entry_header);
        color = currEntry.getColor();
        cat = currEntry.getCat();
        annotation = currEntry.getAnnotation();
        header.setBackgroundResource(currEntry.getColor());
        TextView label = (TextView) findViewById(R.id.edit_entry_label);
        label.setText(currEntry.getLabel());
        TextView startTime = (TextView) findViewById(R.id.edit_entry_start_time);
        startTime.setText(currEntry.getStartTime());
        TextView endTime = (TextView) findViewById(R.id.edit_entry_end_time);
        endTime.setText(currEntry.getEndTime());

        //Spinner
        catSpin = (MySpinner) findViewById(R.id.edit_entry_category_spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
        catSpin.setAdapter(adapter);
        int spinPos = adapter.getPosition(currEntry.getCat());
        catSpin.setSelection(spinPos);
        catSpin.setOnItemSelectedListener(this);
        EditText et = (EditText) findViewById(R.id.edit_entry_annotation_edit_text);
        if(annotation == null || annotation.equals("")){
            et.setHint("Add a description");
        }else{
            et.setText(annotation);
        }
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
            case "+ Create": //TODO: Add to category list
                DialogFragment newFragment = new CreateCategory();
                newFragment.show(getFragmentManager(), "Create New Category:");
                color = R.color.custom;
                break;
            default:
                cat = parent.getItemAtPosition(position).toString();
                color = R.color.custom;
                break;
        }
        LinearLayout header = (LinearLayout) findViewById(R.id.edit_entry_header);
        header.setBackgroundResource(color);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void edit_entry_save(View v){
        EditText et = (EditText) findViewById(R.id.edit_entry_annotation_edit_text);
        annotation = et.getEditableText().toString();
        for(int i=0; i<entryList.size(); i++){
            if(currEntry.equals(entryList.get(i))){
                entryList.get(i).setCat(cat);
                entryList.get(i).setAnnotation(annotation);
                entryList.get(i).setColor(color);
                break;
            }
        }
        Intent intent = new Intent(this, Home.class);
        intent.putParcelableArrayListExtra("entryList", entryList);
        intent.putExtra("Categories", origCategoryArray);
        startActivity(intent);
    }

    public void edit_entry_delete(View v){
        for(int i=0; i<entryList.size(); i++){
            if(currEntry.equals(entryList.get(i))){
                entryList.remove(i);
            }
        }
        Intent intent = new Intent(this, Home.class);
        intent.putParcelableArrayListExtra("entryList", entryList);
        intent.putExtra("Categories", origCategoryArray);
        startActivity(intent);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

        Dialog dialogView = dialog.getDialog();
        EditText newCatInput = (EditText) dialogView.findViewById(R.id.create_new_cat);
//
        cat = newCatInput.getText().toString();

        origCategoryArray.add(cat);
        int oldIndex = categoryArray.indexOf("+ Create");
        categoryArray.add(oldIndex, cat);
//
        adapter.add(cat);
        int spinPos = adapter.getPosition(cat);
        catSpin.setAdapter(adapter);
        catSpin.setSelection(spinPos);


    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //Failed creation --> still need to set the cat value
        cat = currEntry.getCat();
        int spinPos = adapter.getPosition(cat);
        catSpin.setSelection(spinPos);
    }


}
