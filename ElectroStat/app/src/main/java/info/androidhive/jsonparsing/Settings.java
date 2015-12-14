package info.androidhive.jsonparsing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final TextView content = (TextView) findViewById(R.id.textView3);
        final EditText addrInputField = (EditText) findViewById(R.id.editText);
        final Button applyButton = (Button) findViewById(R.id.button2);

        content.setText("Server Settings");

        addrInputField.setText(ServiceHandler.SERVER_IP);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceHandler.SERVER_IP = addrInputField.getText().toString();
                Toast.makeText(getApplicationContext(), "New Server IP applied", Toast.LENGTH_LONG).show();
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

        return super.onOptionsItemSelected(item);
    }


}