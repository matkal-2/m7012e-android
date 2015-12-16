package info.androidhive.jsonparsing;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;

import java.io.IOException;
import java.util.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Capsoize on 16.12.2015.
 */
public class GetPowerUsageByDateAsyncTask extends AsyncTask<String, Void, JSONArray> {
    HistoryGrapher historyGrapher;

    public GetPowerUsageByDateAsyncTask(HistoryGrapher historyGrapher){
        super();
        this.historyGrapher = historyGrapher;
    }

    @Override
    protected JSONArray doInBackground(String... params) {
        JSONArray jsonArray = null;
        try{
            jsonArray = historyGrapher.serviceHandler.getPowerUsage(HistoryGrapher.getDateFormatted(), "hour");
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

                historyGrapher.historyDayPlot.removeAllSeries();

                // Debug printout
                powerData = jsonArray.toString().substring(1, jsonArray.toString().length() - 1);
                historyGrapher.dataPrinter.setText(HistoryGrapher.getDateFormatted());

                // Go through all voltage data and add them to DataPoint array
                for (int i = 0; i < jsonArray.length(); i++){
                    voltage = Float.parseFloat(jsonArray.getJSONObject(i).getString("voltage"));
                    date.setTime(format.parse(jsonArray.getJSONObject(i).getString("datetime")));

                    time = date.get(Calendar.HOUR_OF_DAY) + date.get(Calendar.MINUTE)*(0.6);

                    dataPoints[i] = new DataPoint(time, voltage );
                }

                // debug
                historyGrapher.historyDaySeries = new LineGraphSeries<DataPoint>(dataPoints);
                historyGrapher.historyDayPlot.getViewport().setMinX(0);
                historyGrapher.historyDayPlot.getViewport().setMaxX(24);
                historyGrapher.historyDayPlot.addSeries(historyGrapher.historyDaySeries);
            }
            else{
                historyGrapher.content.setText("Null object returned");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
