package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TimePicker;

/**
 * Created by Katya on 6/6/2015.
 */
public class CreateEntryFragment extends DialogFragment {

    public interface CreateEntryListener{
        public void onDialogPositiveClick(DialogFragment dialog);
    }

    CreateEntryListener entryListener;
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            entryListener = (CreateEntryListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+"must implement EnterScoresListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate the layout view
        View v = inflater.inflate(R.layout.activity_create_entry_view, null);

        //Add Autocompletion
//        AutoCompleteTextView team1View = (AutoCompleteTextView) v.findViewById(R.id.team1Input);
//        AutoCompleteTextView team2View = (AutoCompleteTextView) v.findViewById(R.id.team2Input);
//        String[] teams = getResources().getStringArray(R.array.teams_array);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, teams);
//        team1View.setAdapter(adapter);
//        team2View .setAdapter(adapter);

        builder.setView(v);

//        builder.setMessage(R.string.enterScoresDialog)
        builder.setTitle(R.string.title_create_entry_dialogue)
                .setPositiveButton(R.string.saveBtnText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        entryListener.onDialogPositiveClick(CreateEntryFragment.this);
//                        Toast.makeText(getActivity().getApplicationContext(), R.string.doneBtnText, Toast.LENGTH_SHORT).show();

                    }
//                })
//                .setNegativeButton(R.string.cancelBtnText, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User cancelled the dialog
//                        Toast.makeText(getActivity().getApplicationContext(),R.string.cancelBtnText, Toast.LENGTH_SHORT).show();
//                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
