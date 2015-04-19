package com.coalinga.appsforag.swarm;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private int currentId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button buttonYes = (Button) findViewById(R.id.button_yes);
        buttonYes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });
        final Button buttonNo = (Button) findViewById(R.id.button_no);
        buttonNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                incrementImage();
            }
        });
    }

    private void incrementImage() {
        currentId++;
        if(currentId > Event.pests.size()){
//                    Intent intent = new Intent(this, DisplayMessageActivity.class);
//                    startActivity(intent);
            Toast.makeText(getApplicationContext(), "All reports completed", Toast.LENGTH_SHORT).show();
        } else {
            //updateImage();
            ImageView image = (ImageView) findViewById(R.id.image_view);
            String mDrawableName = Event.pests.get(currentId);
            int resID = getResources().getIdentifier(mDrawableName , "mipmap", getPackageName());
            image.setImageResource(resID);
            // TODO catch null
            //updateText();
            TextView textView = (TextView) findViewById(R.id.image_title);
            int stringID = getResources().getIdentifier(mDrawableName , "string", getPackageName());
            textView.setText(stringID);
        }
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

        switch(item.getItemId()){
            case R.id.report:
                return true;
            case R.id.my_area:
                Intent intent = new Intent(this, MyAreaActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
