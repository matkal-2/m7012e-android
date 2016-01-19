package info.androidhive.jsonparsing;

import android.os.AsyncTask;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Capsoize on 9.12.2015.
 */
public class GetRelayAsyncTask extends AsyncTask<String, Void, String>{

    public String relay;
    ServiceHandler serviceHandler;
    Switch relay_switch;

    public GetRelayAsyncTask(ServiceHandler serviceHandler,Switch relay_switch, String relay){
        super();
        this.relay = relay;
        this.serviceHandler = serviceHandler;
        this.relay_switch = relay_switch;

    }

    @Override
    protected String doInBackground(String... params) {
        try{
            return serviceHandler.getRelay(relay);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "Error!>;)";
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject jObject = new JSONObject(result);
            if(jObject.get("value") == 1){
                relay_switch.setChecked(true);
            }else{
                relay_switch.setChecked(false);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
