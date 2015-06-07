package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class Home extends ActionBarActivity implements CreateEntryFragment.CreateEntryListener{
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
        DialogFragment newFragment = new CreateEntryFragment();
        newFragment.show(getFragmentManager(), "Create Entry");
    }
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(), "Positive Click! :)", Toast.LENGTH_SHORT).show();
    }
}
