package info.androidhive.jsonparsing;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class GUI_example extends AppCompatActivity {

    TextView content;
    String response;
    final ServiceHandler serviceHandler = new ServiceHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_example);

        content    =   (TextView)findViewById( R.id.content );
        content.setMovementMethod(new ScrollingMovementMethod());
        content.setText("Request Response:");

        Button postB = (Button) findViewById(R.id.button_post);
        postB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = "";
                    text = "Async Connecting to server.";

                    new AsyncTask<Void, Void, String>()
                    {
                        @Override
                        public String doInBackground (Void... params)
                        {
                           StringBuilder s = new StringBuilder();
                            s.append("Has Connection: ");
                            boolean b = serviceHandler.hasActiveInternetConnection();
                            if(b)
                                s.append("True");
                            else
                                s.append("False");

                            try{
                                s.append("\nContent:\n" + serviceHandler.setRelay("1", "1"));
                           }catch(UnsupportedEncodingException e){
                                e.printStackTrace();
                            }

                            return s.toString();


                        }

                        @Override
                        protected void onPostExecute(String result)
                        {
                            content.append("\nLength: " + result.length());
                            content.append("\n"+result);
                        }

                    }.execute();

                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();
            }
        });

        /*Switch relay1 = (Switch) findViewById(R.id.switch1) ;
        relay1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){


            }
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_GUI_example) {
            Intent intent = new Intent(this, GUI_example.class);
            startActivity(intent);


        }

        return super.onOptionsItemSelected(item);
    }
}
