package info.androidhive.jsonparsing;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;

import java.io.IOException;
import java.util.Calendar;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

/**
 * Created by Capsoize on 14.12.2015.
 */
public class GetPowerUsageAsyncTask extends AsyncTask<String, Void, JSONArray> {
    Grapher grapher;

    public GetPowerUsageAsyncTask(Grapher grapher){
        super();
        this.grapher = grapher;
    }

    @Override
    protected JSONArray doInBackground(String... params) {
        JSONArray jsonArray = null;
        int i = 0;
        try{
            jsonArray = grapher.serviceHandler.getPowerUsage();
        }catch (IOException e){
            e.printStackTrace();
        }
        return jsonArray;


}

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        try {
            if(jsonArray != null){
                // Declare Variables
                int initialHour, initialMin, currentHour, currentMin, minutePos;
                long epoch;
                float voltage;
                String powerData, dateString;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                // Initilize Variables
                Calendar date = Calendar.getInstance();
                DataPoint[] dataPoints = new DataPoint[jsonArray.length()];

                // Debug printout
                powerData = jsonArray.toString().substring(1, jsonArray.toString().length() - 1);
                grapher.content.setText(powerData);

                // set inital time values
                dateString = jsonArray.getJSONObject(0).getString("datetime");
                date.setTime(format.parse(dateString, new ParsePosition(0)));
                initialHour = date.get(Calendar.HOUR);
                initialMin = date.get(Calendar.MINUTE);

                // Go through all voltage data and add them to DataPoint array
                for (int i = 0; i < jsonArray.length(); i++){
                    voltage = Float.parseFloat(jsonArray.getJSONObject(i).getString("voltage"));

                    dataPoints[i] = new DataPoint(i, voltage );
                }

                // debug
                grapher.content.setText("bye for loop");
                grapher.hourSeries = new LineGraphSeries<DataPoint>(dataPoints);
                grapher.hourGraph.addSeries(grapher.hourSeries);
            }
            else{
                grapher.content.setText("Null object returned");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
