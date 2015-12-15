package info.androidhive.jsonparsing;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.UnsupportedEncodingException;

public class Grapher extends AppCompatActivity {

    final ServiceHandler serviceHandler = new ServiceHandler();
    private final Handler mHandler = new Handler();

    public GraphView powerGraph, hourGraph;
    public TextView content;
    private double powerGraphLastValue;
    private Runnable mPowerTimer;
    public LineGraphSeries<DataPoint> powerSeries, hourSeries;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grapher_);

        content    =   (TextView)findViewById( R.id.textView4 );
        content.setMovementMethod(new ScrollingMovementMethod());
        content.setText("Request Response:");

        this.powerGraph = (GraphView) findViewById(R.id.graph);
        this.powerGraph.setTitle("Power usage");
        GridLabelRenderer gridLabel = this.powerGraph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Hours");
        gridLabel.setVerticalAxisTitle("kWh");
        this.powerSeries = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        this.powerGraph.getViewport().setYAxisBoundsManual(true);
        this.powerGraph.getViewport().setMaxY(10.5);
        this.powerGraph.getViewport().setMinY(0);
        powerGraph.getViewport().setXAxisBoundsManual(true);
        this.powerGraphLastValue = 0;
        this.powerGraph.addSeries(this.powerSeries);

       // series.appendData(new DataPoint(5,7),false, 12);

        // Hour graph initializiationze
        this.hourGraph = (GraphView)findViewById(R.id.graph_hour);
        this.hourGraph.setTitle("Power used this hour");
        GridLabelRenderer gridLabelHour = this.hourGraph.getGridLabelRenderer();
        gridLabelHour.setHorizontalAxisTitle("Minutes");
        gridLabelHour.setVerticalAxisTitle("kWh");
        this.hourGraph.getViewport().setYAxisBoundsManual(true);
        this.hourGraph.getViewport().setMaxY(10.5);
        this.hourGraph.getViewport().setMinY(0);
        this.hourGraph.getViewport().setXAxisBoundsManual(true);
        this.hourGraph.getViewport().setMinX(0);
        this.hourGraph.getViewport().setMaxX(60);


        new GetAnalogAsyncTask(serviceHandler, powerGraph, powerSeries, "1", ServiceHandler.ANALOG_INPUT).execute();
        new GetPowerUsageAsyncTask(this).execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();
    }

}
