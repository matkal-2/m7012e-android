package info.androidhive.jsonparsing;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Capsoize on 15.12.2015.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = HistoryGrapher.datePicked;
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day){
        HistoryGrapher.datePicked.set(Calendar.YEAR, year);
        HistoryGrapher.datePicked.set(Calendar.MONTH, month);
        HistoryGrapher.datePicked.set(Calendar.DAY_OF_MONTH, day);

        HistoryGrapher.updateDate();


    }
}
