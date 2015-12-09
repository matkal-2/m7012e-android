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

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ServiceHandler{

    static String response = null;
    private static String LOG_TAG = "ServiceHandler";
    public final static int GET = 1;
    public final static int POST = 2;
    private final static String SERVER_IP = "192.168.1.35";

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


}
