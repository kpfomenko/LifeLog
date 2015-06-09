package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;


public class FilterByCategoryFragment extends DialogFragment {
    private ArrayList<String> showCatArray;
    public interface FilterCategoryListener{
        public void onDialogPositiveClick(DialogFragment dialog);
//        public void onDialogNegativeClick(DialogFragment dialog);

    }

    FilterCategoryListener filterCategoryListener;
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            filterCategoryListener = (FilterCategoryListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+"must implement CreateCategoryListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate the layout view
        View v = inflater.inflate(R.layout.filter_category, null);

        CheckBox ch_active = (CheckBox) v.findViewById(R.id.checkbox_active);
        CheckBox ch_personal = (CheckBox) v.findViewById(R.id.checkbox_personal);
        CheckBox ch_rest = (CheckBox) v.findViewById(R.id.checkbox_rest);
        CheckBox ch_work = (CheckBox) v.findViewById(R.id.checkbox_work);
        CheckBox ch_custom = (CheckBox) v.findViewById(R.id.checkbox_custom);

//        showCatArray
        for(int i =0; i < showCatArray.size(); i++){
            switch (showCatArray.get(i)) {
                case "Active":
                    ch_active.setChecked(true);
                    break;
                case "Personal":
                    ch_personal.setChecked(true);
                    break;
                case "Rest":
                    ch_rest.setChecked(true);
                    break;
                case "Work":
                    ch_work.setChecked(true);
                    break;
                case "Custom":
                    ch_custom.setChecked(true);
                    break;
            }
        }

        builder.setView(v);

        builder.setTitle(R.string.filter_title)
                .setPositiveButton(R.string.doneBtnText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        filterCategoryListener.onDialogPositiveClick(FilterByCategoryFragment.this);
//                        Toast.makeText(getActivity().getApplicationContext(), R.string.doneBtnText, Toast.LENGTH_SHORT).show();

                    }
//                })
//                .setNegativeButton(R.string.cancelBtnText, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User cancelled the dialog
//                        filterCategoryListener.onDialogNegativeClick(FilterByCategoryFragment.this);
//                        Toast.makeText(getActivity().getApplicationContext(), R.string.cancelBtnText, Toast.LENGTH_SHORT).show();
//                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void selectSavedCategories(ArrayList<String> catArray){
        showCatArray = catArray;
    }
}
