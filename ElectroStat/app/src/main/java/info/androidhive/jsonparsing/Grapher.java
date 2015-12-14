package info.androidhive.jsonparsing;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Grapher extends AppCompatActivity {

    final ServiceHandler serviceHandler = new ServiceHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grapher_);

        GraphView graphView = (GraphView) findViewById(R.id.graph);
        graphView.setTitle("Power usage");
        GridLabelRenderer gridLabel = graphView.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Hours");
        gridLabel.setVerticalAxisTitle("kWh");
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMaxY(10.5);
        graphView.getViewport().setMinY(0);
       /* graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMaxX(24);
        graphView.getViewport().setMinX(0);*/
        graphView.addSeries(series);

       // series.appendData(new DataPoint(5,7),false, 12);

        new GetAnalogAsyncTask(serviceHandler, graphView, series, "1", ServiceHandler.ANALOG_INPUT).execute();
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

}
