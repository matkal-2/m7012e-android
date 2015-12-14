package info.androidhive.jsonparsing;

import android.os.AsyncTask;
import android.os.Handler;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Capsoize on 14.12.2015.
 */
public class GetPowerUsageAsyncTask extends AsyncTask<String, Void, String> {
    public String analogIO;
    ServiceHandler serviceHandler;
    GraphView graphView;
    LineGraphSeries<DataPoint> series;
    String device;

    public GetPowerUsageAsyncTask(ServiceHandler serviceHandler, GraphView graphView, LineGraphSeries<DataPoint> series, String analogIO, String device){
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
            graphView.getViewport().setMaxX(endX);
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
