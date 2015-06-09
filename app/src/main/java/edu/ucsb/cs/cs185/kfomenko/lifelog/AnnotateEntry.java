package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class AnnotateEntry extends ActionBarActivity{
    private ArrayList<Entry> entryList = new ArrayList<Entry>();
    private ArrayList<String> categoryArray = new ArrayList<String>();
    private ArrayList<String> origCategoryArray = new ArrayList<String>();
    private Entry currEntry;
//    private Integer color;
//    private String cat;
//    private String annotation;

    //For creating New Categorys
//    private Spinner catSpin;
//    ArrayAdapter<String> adapter;
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
//        color = currEntry.getColor();
//        cat = currEntry.getCat();
//        annotation = currEntry.getAnnotation();
        header.setBackgroundResource(currEntry.getColor());
        TextView label = (TextView) findViewById(R.id.edit_entry_label);
        label.setText(currEntry.getLabel());
        TextView startTime = (TextView) findViewById(R.id.edit_entry_start_time);
        startTime.setText(currEntry.getStartTime());
        TextView endTime = (TextView) findViewById(R.id.edit_entry_end_time);
        endTime.setText(currEntry.getEndTime());
        TextView category = (TextView) findViewById(R.id.edit_entry_category);
        category.setText(currEntry.getCat());
        TextView anno = (TextView) findViewById(R.id.edit_entry_anno);
        if(currEntry.getAnnotation().equals("")){
            anno.setText("No description set");
        }else {
            anno.setText(currEntry.getAnnotation());
        }

        //Spinner
//        catSpin = (Spinner) findViewById(R.id.edit_entry_category_spinner);
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryArray);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
//        catSpin.setAdapter(adapter);
//
//        int spinPos = adapter.getPosition(currEntry.getCat());
//        catSpin.setSelection(spinPos);
//        catSpin.setOnItemSelectedListener(this);

//        edu.ucsb.cs.cs185.kfomenko.lifelog.MyEditText et = (edu.ucsb.cs.cs185.kfomenko.lifelog.MyEditText) findViewById(R.id.edit_entry_annotation_edit_text);
////        et.setFocusable(false);
//        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                EditText et = (EditText) v;
//                if(actionId == EditorInfo.IME_ACTION_DONE){
//                    et.setCursorVisible(false);
//                }
//                return false;
//            }
//        });
//        et.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText et = (EditText) v;
//                et.setCursorVisible(true);
//            }
//        });
//        if(annotation == null || annotation.equals("")){
//            et.setHint("Add a description");
//        }else{
//            et.setText(annotation);
//        }
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
        if (id == android.R.id.home) {
            Intent intent = new Intent(this, Home.class);
            intent.putParcelableArrayListExtra("entryList", entryList);
            intent.putStringArrayListExtra("Categories", categoryArray);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        //Selecting a Category
//        switch (parent.getItemAtPosition(position).toString() ){
//            case "Work":
//                cat = "Work";
//                color = R.color.work;
//                break;
//            case "Rest":
//                cat = "Rest";
//                color = R.color.rest;
//                break;
//            case "Personal":
//                cat = "Personal";
//                color = R.color.personal;
//                break;
//            case "Active":
//                cat = "Active";
//                color = R.color.active;
//                break;
//            case "+ Create":
//                DialogFragment newFragment = new CreateCategory();
//                newFragment.show(getFragmentManager(), "Create New Category:");
//                color = R.color.custom;
//                break;
//            default:
//                cat = parent.getItemAtPosition(position).toString();
//                color = R.color.custom;
//                break;
//        }
//        LinearLayout header = (LinearLayout) findViewById(R.id.edit_entry_header);
//        header.setBackgroundResource(color);
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }

    public void edit_entry_edit(View v){
//        EditText et = (EditText) findViewById(R.id.edit_entry_annotation_edit_text);
//        annotation = et.getEditableText().toString();
//        for(int i=0; i<entryList.size(); i++){
//            if(currEntry.equals(entryList.get(i))){
//                entryList.get(i).setCat(cat);
//                entryList.get(i).setAnnotation(annotation);
//                entryList.get(i).setColor(color);
//                break;
//            }
//        }
//        Intent intent = new Intent(this, Home.class);
//        intent.putParcelableArrayListExtra("entryList", entryList);
//        intent.putExtra("Categories", origCategoryArray);
//        startActivity(intent);
        Intent intent = new Intent(this, Home.class);
        intent.putParcelableArrayListExtra("entryList", entryList);
        intent.putExtra("Categories", origCategoryArray);
        intent.putExtra("currEntry", currEntry);
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

//    @Override
//    public void onDialogPositiveClick(DialogFragment dialog) {
//
//        Dialog dialogView = dialog.getDialog();
//        EditText newCatInput = (EditText) dialogView.findViewById(R.id.create_new_cat);
////
//        cat = newCatInput.getText().toString();
//
//        origCategoryArray.add(cat);
//        int oldIndex = categoryArray.indexOf("+ Create");
//        categoryArray.add(oldIndex, cat);
////
////        adapter.add(cat);
//        int spinPos = adapter.getPosition(cat);
//        catSpin.setAdapter(adapter);
//        catSpin.setSelection(spinPos);
//
//    }
//
//    @Override
//    public void onDialogNegativeClick(DialogFragment dialog) {
//        //Failed creation --> still need to set the cat value
//        cat = currEntry.getCat();
//        int spinPos = adapter.getPosition(cat);
//        catSpin.setSelection(spinPos);
//    }

}
