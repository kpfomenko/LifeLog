package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Katya on 6/6/2015.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
//    OnChangeListener mCallback;
    int calledBtnVal;
//    public interface OnChangeListener{
//        public void onChangeOccured(int hourOfDay, int minute, int calledBtnVal);
//    }
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mCallback = (OnChangeListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnHeadlineSelectedListener");
//        }
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
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
}

//public class TimePickerFragment extends TimePickerDialog{
//
//    public TimePickerFragment(Context arg0, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView) {
//        super(arg0, callBack, hourOfDay, minute, is24HourView);
//        // TODO Auto-generated constructor stub
//    }
//
//    @Override
//    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//        // TODO Auto-generated method stub
//        //super.onTimeChanged(arg0, arg1, arg2);
//        if (mIgnoreEvent)
//            return;
//        if (minute%TIME_PICKER_INTERVAL!=0){
//            int minuteFloor=minute-(minute%TIME_PICKER_INTERVAL);
//            minute=minuteFloor + (minute==minuteFloor+1 ? TIME_PICKER_INTERVAL : 0);
//            if (minute==60)
//                minute=0;
//            mIgnoreEvent=true;
//            view.setCurrentMinute(minute);
//            mIgnoreEvent=false;
//        }
//    }
//
//    private final int TIME_PICKER_INTERVAL=15;
//    private boolean mIgnoreEvent=false;
//
//}