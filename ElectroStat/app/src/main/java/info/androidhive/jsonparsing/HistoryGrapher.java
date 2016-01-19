package info.androidhive.jsonparsing;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

import java.util.Calendar;

public class HistoryGrapher extends AppCompatActivity {


    public static Calendar datePicked;
    public static TextView content;
    public static TextView dataPrinter;
    public static ServiceHandler serviceHandler = new ServiceHandler();
    public static HistoryGrapher historyGrapher;

    public GraphView historyDayPlot;
    public LineGraphSeries<DataPoint> historyDaySeries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_grapher);

        datePicked = Calendar.getInstance();
        content = (TextView) findViewById(R.id.textView5);

        dataPrinter = (TextView) findViewById(R.id.textView6);

        historyDayPlot = (GraphView) findViewById(R.id.historygraphplot);
        historyDayPlot.setTitle("Power usage [kWh]");
        GridLabelRenderer historyGridLabel = historyDayPlot.getGridLabelRenderer();
        historyGridLabel.setHorizontalAxisTitle("Hours");
        this.historyDayPlot.getViewport().setXAxisBoundsManual(true);
        this.historyDayPlot.getViewport().setMinX(0);
        this.historyDayPlot.getViewport().setMaxX(60);

        historyGrapher = this;

        updateDate();
    }


    public static void updateDate(){
        content.setText(getDateFormatted());
        new GetPowerUsageByDateAsyncTask(historyGrapher).execute();
    }

    public static String getDateFormatted(){
        return datePicked.get(Calendar.YEAR)+"-"+(1+datePicked.get(Calendar.MONTH))+"-"+datePicked.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showDatePickerDialog(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }
        else if (id == R.id.action_GUI_example) {
            Intent intent = new Intent(this, GUI_example.class);
            startActivity(intent);
        }
        else if (id == R.id.action_Grapher_Activity) {
            Intent intent = new Intent(this, Grapher.class);
            startActivity(intent);
        }
        else if (id == R.id.action_Grapher_History){
            Intent intent = new Intent(this, HistoryGrapher.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
