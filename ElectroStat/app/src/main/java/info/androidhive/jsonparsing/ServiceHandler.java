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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ServiceHandler {

    static String response = null;
    private static String LOG_TAG = "ServiceHandler";
    public final static int GET = 1;
    public final static int POST = 2;

    public ServiceHandler() {

    }
    public  String  GetText()  throws  UnsupportedEncodingException
    {
        String Name, Email, Login, Pass;
        // Get user defined values
        Name = "Na";//fname.getText().toString();
        Email   = "E@mail.com";//email.getText().toString();
        Login   = "Lo";//login.getText().toString();
        Pass   = "Pa";//pass.getText().toString();

        String state = "1";

        // Create data variable for sent values to server

        /*String data = URLEncoder.encode("name", "UTF-8")
                + "=" + URLEncoder.encode(Name, "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(Email, "UTF-8");

        data += "&" + URLEncoder.encode("user", "UTF-8")
                + "=" + URLEncoder.encode(Login, "UTF-8");

        data += "&" + URLEncoder.encode("pass", "UTF-8")
                + "=" + URLEncoder.encode(Pass, "UTF-8");
        */

        String data = URLEncoder.encode("value", "UTF-8")
                + "=" + URLEncoder.encode(state, "UTF-8");
        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {

            // Defined URL  where to send data
            //URL url = new URL("http://androidexample.com/media/webservice/httppost.php");
            //URL url = new URL("http://64.233.183.104/robots.txt");
            URL url = new URL("http://192.168.1.34/rest/relay/1");

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();


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
        catch(Exception ex)
        {

        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {}
        }

        // Show response on activity
        return text;

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
