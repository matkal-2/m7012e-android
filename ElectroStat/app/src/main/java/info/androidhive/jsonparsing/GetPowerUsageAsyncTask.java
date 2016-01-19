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
        Calendar date = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(date.getTime());
        try{
            jsonArray = grapher.serviceHandler.getPowerUsage(dateStr, "minute");
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
                double time;
                String powerData, dateString;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                // Initilize Variables
                Calendar date = Calendar.getInstance();
                DataPoint[] dataPoints = new DataPoint[jsonArray.length()];

                // Debug printout
                powerData = jsonArray.toString().substring(1, jsonArray.toString().length() - 1);

                // Go through all voltage data and add them to DataPoint array
                for (int i = 0; i < jsonArray.length(); i++){
                    voltage = Float.parseFloat(jsonArray.getJSONObject(i).getString("voltage"));

                    //time = date.get(Calendar.MINUTE);
                    dataPoints[i] = new DataPoint(i, voltage );
                }

                // debug
                grapher.hourSeries = new LineGraphSeries<DataPoint>(dataPoints);
                grapher.hourGraph.addSeries(grapher.hourSeries);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//commet