package info.androidhive.jsonparsing;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by Capsoize on 9.12.2015.
 */
public class GetRelayAsyncTask extends AsyncTask<String, Void, String>{

    public String relay;
    ServiceHandler serviceHandler;
    TextView content;

    public GetRelayAsyncTask(ServiceHandler serviceHandler,TextView content, String relaye){
        super();
        this.relay = relay;
        this.serviceHandler = serviceHandler;
        this.content = content;

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
        content.setText(result);
    }

}
