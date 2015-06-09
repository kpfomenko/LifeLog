package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Home extends ActionBarActivity implements FilterByCategoryFragment.FilterCategoryListener {
    private RecyclerView rec;
    private RecAdapter recAdapter;
    private LinearLayoutManager recLayout = new LinearLayoutManager(this);
    private View noEntries;
    private ArrayList<Entry> data = new ArrayList<Entry>();
    private ArrayList<Entry> showEntries = new ArrayList<Entry>();
    private ArrayList<String> categoryArray;
    private ArrayList<String> showCategoryArray;
    private ArrayList<String> defaultCategoryArray;

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("data", data);
        outState.putStringArrayList("categories", categoryArray);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        noEntries = findViewById(R.id.no_entries);
        rec = (RecyclerView) findViewById(R.id.recycler);
        rec.setHasFixedSize(true);
        recLayout.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(recLayout);
        if(savedInstanceState != null){
            data = savedInstanceState.getParcelableArrayList("data");
            categoryArray = savedInstanceState.getStringArrayList("categories");
        }
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            data = extras.getParcelableArrayList("entryList");
            categoryArray = extras.getStringArrayList("Categories");
        }
        if(categoryArray == null){
            categoryArray = new ArrayList<String>();
            categoryArray.add("Active");
            categoryArray.add("Personal");
            categoryArray.add("Rest");
            categoryArray.add("Work");
        }

        defaultCategoryArray = new ArrayList<String>();
        defaultCategoryArray.add("Active");
        defaultCategoryArray.add("Personal");
        defaultCategoryArray.add("Rest");
        defaultCategoryArray.add("Work");

        recAdapter = new RecAdapter(data);
        rec.setAdapter(recAdapter);
        if(recAdapter.getItemCount()!=0){
            noEntries.setVisibility(View.INVISIBLE);
        }

        showCategoryArray = new ArrayList<String>();
        showCategoryArray.add("Active");
        showCategoryArray.add("Personal");
        showCategoryArray.add("Rest");
        showCategoryArray.add("Work");
        showCategoryArray.add("Custom");


        updateDisplayedCards();
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
        if(id == R.id.action_filter){
            //Filtering CardView!
            FilterByCategoryFragment newFragment = new FilterByCategoryFragment();
            newFragment.selectSavedCategories(showCategoryArray);
            newFragment.show(getFragmentManager(), "Filter");
        }
        if(id == R.id.action_clear) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//            alertDialogBuilder.setTitle("Your Title");
            alertDialogBuilder
                    .setMessage("Are you sure you want to delete all log entries?")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close
                            // current activity
                            data.clear();
                            updateDisplayedCards();
                            noEntries.setVisibility(View.VISIBLE);
//                            Toast.makeText(getApplicationContext(), "Deleted.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();


//            data.clear();
//            updateDisplayedCards();
//            noEntries.setVisibility(View.VISIBLE);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onTestFragment(View v){
//        DialogFragment newFragment = new CreateEntryFragment();
//        newFragment.show(getFragmentManager(), "Create Entry");
        //Call new Activity
        Intent intent = new Intent(this, CreateEntryActivity.class);
        intent.putExtra("Categories", categoryArray);
       intent.putParcelableArrayListExtra("entryList", data);
//        Toast.makeText(getApplicationContext(), "Categories 1: "+ categoryArray.get(0), Toast.LENGTH_LONG).show();
//        intent.putStringArrayListExtra("Categories", (ArrayList<String>) categoryArray);
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
        Intent intent = new Intent(this, ShowEntry.class);
        intent.putParcelableArrayListExtra("entryList", data);
        intent.putExtra("currEntry", cast.getEntry());
        intent.putExtra("Categories", categoryArray);
        startActivity(intent);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
//        Toast.makeText(getApplicationContext(), "PostiveClick ", Toast.LENGTH_SHORT).show();
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_active:
                if(checked && !showCategoryArray.contains("Active")){
//                    Toast.makeText(getApplicationContext(), "Active Added!", Toast.LENGTH_SHORT).show();
                    showCategoryArray.add("Active");
                }else if(!checked){
                    showCategoryArray.remove("Active");
//                    Toast.makeText(getApplicationContext(), "Active Removed!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.checkbox_personal:
                if(checked && !showCategoryArray.contains("Personal")){
                    showCategoryArray.add("Personal");
                }else if(!checked){
                    showCategoryArray.remove("Personal");
                }
                break;
            case R.id.checkbox_rest:
                if(checked && !showCategoryArray.contains("Rest")){
                    showCategoryArray.add("Rest");
                }else if(!checked){
                    showCategoryArray.remove("Rest");
                }
                break;
            case R.id.checkbox_work:
                if(checked && !showCategoryArray.contains("Work")){
                    showCategoryArray.add("Work");
                }else if(!checked){
                    showCategoryArray.remove("Work");
                }
                break;
            case R.id.checkbox_custom:
                if(checked && !showCategoryArray.contains("Custom")){
                    showCategoryArray.add("Custom");
                }else if(!checked){
                    showCategoryArray.remove("Custom");
                }
                break;
        }

        //update the cards with the show array
        updateDisplayedCards();
    }

    public void updateDisplayedCards(){

        //Check if Custom Allowed
        boolean customAllowed = false;
        for(int i=0; i< showCategoryArray.size(); i++){
            if(!defaultCategoryArray.contains(showCategoryArray.get(i))){
                customAllowed = true;
                break;
            }
        }

        showEntries.clear();
        for(int i=0; i< data.size(); i++){
            if(customAllowed && !defaultCategoryArray.contains(data.get(i).getCat())){
                //if custom is allowed, and the category is not a default one, then add it
                showEntries.add(data.get(i));
            }
            if(showCategoryArray.contains(data.get(i).getCat())){
                showEntries.add(data.get(i));
            }
        }
        recAdapter.update(showEntries);

        TextView filterNotification = (TextView) findViewById(R.id.filter_notification);
        if(showCategoryArray.size()!= 5){
            //Filters Are On!
            filterNotification.setVisibility(View.VISIBLE);
        }else{
            filterNotification.setVisibility(View.INVISIBLE);
        }
        if(recAdapter.getItemCount()!=0){
            noEntries.setVisibility(View.INVISIBLE);
        }

    }
}
