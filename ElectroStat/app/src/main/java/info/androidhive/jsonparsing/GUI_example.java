package info.androidhive.jsonparsing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

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


        Switch relay1 = (Switch) findViewById(R.id.switch1);
        new GetRelayAsyncTask(serviceHandler, relay1, "1").execute();
        relay1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    new ChangeRelayAsyncTask(serviceHandler, content, "1", "1").execute();
                } else {
                    new ChangeRelayAsyncTask(serviceHandler, content, "1", "0").execute();
                }
            }
        });

        Switch relay2 = (Switch) findViewById(R.id.switch2);
        new GetRelayAsyncTask(serviceHandler, relay2, "2").execute();
        relay2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    new ChangeRelayAsyncTask(serviceHandler, content, "2", "1").execute();
                }
                else{
                    new ChangeRelayAsyncTask(serviceHandler, content, "2", "0").execute();
                }
            }
        });

        Switch relay3 = (Switch) findViewById(R.id.switch3);
        new GetRelayAsyncTask(serviceHandler, relay3, "3").execute();
        relay3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    new ChangeRelayAsyncTask(serviceHandler, content, "3", "1").execute();
                }
                else{
                    new ChangeRelayAsyncTask(serviceHandler, content, "3", "0").execute();
                }
            }
        });

        Switch relay4 = (Switch) findViewById(R.id.switch4);
        new GetRelayAsyncTask(serviceHandler, relay4, "4").execute();
        relay4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    new ChangeRelayAsyncTask(serviceHandler, content, "4", "1").execute();
                }
                else{
                    new ChangeRelayAsyncTask(serviceHandler, content, "4", "0").execute();
                }
            }
        });

        Switch relay5 = (Switch) findViewById(R.id.switch5);
        new GetRelayAsyncTask(serviceHandler, relay5, "5").execute();
        relay5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    new ChangeRelayAsyncTask(serviceHandler, content, "5", "1").execute();
                }
                else{
                    new ChangeRelayAsyncTask(serviceHandler, content, "5", "0").execute();
                }
            }
        });

        Switch relay6 = (Switch) findViewById(R.id.switch6);
        new GetRelayAsyncTask(serviceHandler, relay6, "6").execute();
        relay6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    new ChangeRelayAsyncTask(serviceHandler, content, "6", "1").execute();
                } else {
                    new ChangeRelayAsyncTask(serviceHandler, content, "6", "0").execute();
                }
            }
        });

        Switch relay7 = (Switch) findViewById(R.id.switch7);
        new GetRelayAsyncTask(serviceHandler, relay7,"7").execute();
        relay7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    new ChangeRelayAsyncTask(serviceHandler, content, "7", "1").execute();
                } else {
                    new ChangeRelayAsyncTask(serviceHandler, content, "7", "0").execute();
                }
            }
        });

        Switch relay8 = (Switch) findViewById(R.id.switch8);
        new GetRelayAsyncTask(serviceHandler, relay8, "8").execute();
        relay8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    new ChangeRelayAsyncTask(serviceHandler, content, "8", "1").execute();
                }
                else{
                    new ChangeRelayAsyncTask(serviceHandler, content, "8", "0").execute();
                }
            }
        });


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
        if (id == R.id.action_Settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }
        else if (id == R.id.action_GUI_example) {
            Intent intent = new Intent(this, GUI_example.class);
            startActivity(intent);
        }
        else if (id == R.id.action_Grapher_Activity) {
            Intent intent = new Intent(this, Grapher.class);
            startActivity(intent);
        }
        else if (id == R.id.action_Grapher_History){
            Intent intent = new Intent(this, HistoryGrapher.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
