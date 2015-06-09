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
import android.widget.Toast;

/**
 * Created by Katya on 6/7/2015.
 */
public class CreateCategory extends DialogFragment {
    public interface CreateCategoryListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);

    }

    CreateCategoryListener categoryListener;
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            categoryListener = (CreateCategoryListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+"must implement CreateCategoryListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate the layout view
        View v = inflater.inflate(R.layout.create_category, null);

        builder.setView(v);

        builder.setTitle(R.string.title_create_category)
                .setPositiveButton(R.string.doneBtnText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        categoryListener.onDialogPositiveClick(CreateCategory.this);
//                        Toast.makeText(getActivity().getApplicationContext(), R.string.doneBtnText, Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton(R.string.cancelBtnText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        categoryListener.onDialogNegativeClick(CreateCategory.this);
//                        Toast.makeText(getActivity().getApplicationContext(), R.string.cancelBtnText, Toast.LENGTH_SHORT).show();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
