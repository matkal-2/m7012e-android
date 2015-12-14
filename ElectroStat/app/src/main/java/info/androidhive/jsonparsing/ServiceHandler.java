package info.androidhive.jsonparsing;

/**
 * Created by Capsoize on 7.12.2015.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceHandler{

    static String response = null;
    private static String LOG_TAG = "ServiceHandler";
    public final static int GET = 1;
    public final static int POST = 2;
    public final static String ANALOG_INPUT = "ai";
    public final static String ANALOG_OUTPUT = "ao";
    public static String SERVER_IP = "192.168.1.35";

    public ServiceHandler() {

    }

    private String sendRequest(String url_s, String data, int method) throws  UnsupportedEncodingException{
        String text = "";
        BufferedReader reader=null;
        try
        {
            // Defined URL  where to send data
            URL url = new URL(url_s);
            URLConnection conn = url.openConnection();

            // Send POST data request
            if(method == POST){
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write( data );
                wr.flush();
            }

            // Get the server response
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }

            text = sb.toString();
        }
        catch(Exception ex) {
        }
        finally {
            try{reader.close();}
            catch(Exception ex) {}
        }

        // Show response on activity
        return text;
    }

    public String setRelay(String relay, String state) throws UnsupportedEncodingException{
        String data = URLEncoder.encode("value", "UTF-8")
                + "=" + URLEncoder.encode(state, "UTF-8");
        return sendRequest("http://"+SERVER_IP+"/rest/relay/"+relay, data, POST);
    }


    public boolean hasActiveInternetConnection() {
       // if (isNetworkAvailable(context)) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error checking internet connection", e);
            }
      //  } else {
      //      Log.d(LOG_TAG, "No network available!");
       // }
        return false;
    }


    public String getRelay(String relay) throws UnsupportedEncodingException {
        String data = "";
        return sendRequest("http://"+SERVER_IP+"/rest/relay/"+relay, data, GET);
    }

    public String getAnalog(String analogIO, String device) throws UnsupportedEncodingException {
        String data = "";
        return sendRequest("http://"+SERVER_IP+"/rest/"+device+"/"+analogIO, data, GET);
    }

    public JSONObject getPowerUsage(String analogIO, String device) throws UnsupportedEncodingException {
        String data = "";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonContent = new JSONObject();
        JSONObject jsonObj = new JSONObject();
        Random rand = new Random();
        int  n;
        for (int i = 0; i<100;i++){
            try {
                jsonContent.put("id",i);
                jsonContent.put("datetime",(i/3.14));
                n = rand.nextInt(10);
                jsonContent.put("voltage",n);
                jsonArray.put(jsonContent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            jsonObj.put("rows", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
        //return sendRequest("http://"+SERVER_IP+"/rest/"+device+"/"+analogIO, data, GET);
    }
}
