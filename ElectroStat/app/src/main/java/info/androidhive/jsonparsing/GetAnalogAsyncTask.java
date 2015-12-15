package info.androidhive.jsonparsing;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Switch;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * Created by Capsoize on 14.12.2015.
 */
public class GetAnalogAsyncTask extends AsyncTask<String, Void, String>{
    public String analogIO;
    ServiceHandler serviceHandler;
    GraphView graphView;
    LineGraphSeries<DataPoint> series;
    String device;

    public GetAnalogAsyncTask(ServiceHandler serviceHandler, GraphView graphView, LineGraphSeries<DataPoint> series, String analogIO, String device){
        super();
        this.analogIO = analogIO;
        this.serviceHandler = serviceHandler;
        this.graphView = graphView;
        this.series = series;
        this.device = device;
    }

    @Override
    protected String doInBackground(String... params) {

        try{
            return serviceHandler.getAnalog(analogIO, device);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "Error!>;)2";
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject jObject = new JSONObject(result);
            Double valueY = Double.parseDouble(jObject.get("value").toString());

            Double endX = series.getHighestValueX() + 1;
            Double startX = series.getLowestValueX();
            graphView.getViewport().setMinX(startX);
            graphView.getViewport().setMaxX(endX+6);
            series.appendData(new DataPoint(endX, valueY), false, 30);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    new GetAnalogAsyncTask(serviceHandler, graphView, series, analogIO, device).execute();
                }
            }, 1000);


        }catch (JSONException e) {
            e.printStackTrace();
        }


    }

}