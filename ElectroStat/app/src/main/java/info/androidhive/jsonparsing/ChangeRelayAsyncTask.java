package info.androidhive.jsonparsing;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOError;
import java.io.IOException;

/**
 * Created by Capsoize on 9.12.2015.
 */
public class ChangeRelayAsyncTask extends AsyncTask<String, Void, String> {
    public String relay, state;
    ServiceHandler serviceHandler;
    TextView content;

    public ChangeRelayAsyncTask(ServiceHandler serviceHandler,TextView content, String relay, String state){
        super();
        this.relay = relay;
        this.state = state;
        this.serviceHandler = serviceHandler;
        this.content = content;

    }

    @Override
    protected String doInBackground(String... params) {
        try{
            return serviceHandler.setRelay(relay, state);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "Error!>;)";
    }

    @Override
    protected void onPostExecute(String result) {
        content.setText(result);
    }
}
