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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rec = (RecyclerView) findViewById(R.id.recycler);
        rec.setHasFixedSize(true);
        recLayout.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(recLayout);
        recAdapter = new RecAdapter(new ArrayList<String>());
        rec.setAdapter(recAdapter);
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
        startActivity(intent);
    }
//    @Override
//    public void onDialogPositiveClick(DialogFragment dialog) {
//        Toast.makeText(getApplicationContext(), "Positive Click! :)", Toast.LENGTH_SHORT).show();
//    }
//    public void setStartTime(View v){
//        TimePickerFragment newFragment = new TimePickerFragment();
//        newFragment.show(getFragmentManager(), "timePicker");
//        //0 for Start Button
//        newFragment.setBtnVal(0);
//    }
//
//    public void setEndTime(View v){
//        TimePickerFragment newFragment = new TimePickerFragment();
//        newFragment.show(getFragmentManager(), "timePicker");
//        //1 for EndTIme
//        newFragment.setBtnVal(1);
////        Button endBtn = (Button) findViewById(R.id.entry_end_time_btn);
////        endBtn.setText(newFragment.getTimeAsString());
//    }

//    @Override
//    public void onChangeOccured(int hourOfDay, int minute, int calledBtnVal) {
//        if(calledBtnVal == 0){
//           //start Btn
////            Button timePickerCaller = (Button) findViewById(R.id.entry_start_time_btn);
////            timePickerCaller.setText(hourOfDay+":"+minute);
//            Toast.makeText(getApplicationContext(), "Start Time: "+ hourOfDay+":"+minute, Toast.LENGTH_SHORT).show();
//        }else{
////            Button timePickerCaller = (Button) findViewById(R.id.entry_end_time_btn);
////            timePickerCaller.setText(hourOfDay+":"+minute);
//            Toast.makeText(getApplicationContext(), "End Time: "+ hourOfDay+":"+minute, Toast.LENGTH_SHORT).show();
//        }
//
//    }
}
