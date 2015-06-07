package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Katya on 6/6/2015.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener, TimePicker.OnTimeChangedListener {
    int calledBtnVal;
    private static final int TIME_PICKER_INTERVAL=15;
    private boolean mIgnoreEvent=false;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE)/ 15;
        minute = (minute > 3) ? 0 : minute;

        final TimePickerDialog toReturn = new TimePickerDialog(new ContextThemeWrapper(getActivity(), R.style.MyAppThemeWithTimePicker), this, hour, minute,DateFormat.is24HourFormat(getActivity()));
//        final TimePickerDialog toReturn = new TimePickerDialog(getActivity(), this, hour, minute,DateFormat.is24HourFormat(getActivity()));
        //Hopefully works
//        toReturn.setOnShowListener(new DialogInterface.OnShowListener() {
//            @Override
//            public void onShow(DialogInterface dialog) {
//                int tpLayoutId = getResources().getIdentifier("timePickerLayout", "id", "android");
//
//                ViewGroup tpLayout = (ViewGroup) toReturn.findViewById(tpLayoutId);
//                ViewGroup layout = (ViewGroup) tpLayout.getChildAt(0);
//
//                // Customize minute NumberPicker
//                NumberPicker minutePicker = (NumberPicker) layout.getChildAt(2);
//                minutePicker.setDisplayedValues(new String[]{"00", "15", "30", "45", "00"});
//                minutePicker.setMinValue(0);
//                minutePicker.setMaxValue(3);
//            }
//        });

        // Create a new instance of TimePickerDialog and return it
//        return new TimePickerDialog(getActivity(), this, hour, minute,
//                DateFormat.is24HourFormat(getActivity()));
        return toReturn;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String am_pm = "";

        Calendar datetime = Calendar.getInstance();
        datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        datetime.set(Calendar.MINUTE, minute);

        if (datetime.get(Calendar.AM_PM) == Calendar.AM)
            am_pm = "AM";
        else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
            am_pm = "PM";

        String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ?"12":datetime.get(Calendar.HOUR)+"";
//        String[] minVals = new String[]{"00", "15", "30", "45", "00"};
//        String output = String.format("%s:%s %s", strHrsToShow, minVals[minute], am_pm);
        String output = String.format("%s:%02d %s", strHrsToShow, minute, am_pm);

        if(calledBtnVal == 0){
            Button startBtn = (Button) getActivity().findViewById(R.id.entry_start_time_btn);
            startBtn.setText(output);
        }
        if(calledBtnVal == 1){
            Button endBtn = (Button) getActivity().findViewById(R.id.entry_end_time_btn);
            endBtn.setText(output);
        }
    }

    public void setBtnVal(int val){
        calledBtnVal = val;
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        if (mIgnoreEvent)
            return;
        if (minute%TIME_PICKER_INTERVAL!=0){
            int minuteFloor=minute-(minute%TIME_PICKER_INTERVAL);
            minute=minuteFloor + (minute==minuteFloor+1 ? TIME_PICKER_INTERVAL : 0);
            if (minute==60)
                minute=0;
            mIgnoreEvent=true;
            view.setCurrentMinute(minute);
            mIgnoreEvent=false;
        }

    }
}
