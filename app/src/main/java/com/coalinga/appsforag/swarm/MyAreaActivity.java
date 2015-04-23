package com.coalinga.appsforag.swarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by nevena on 4/19/15.
 */
public class MyAreaActivity extends ActionBarActivity{

    private LinkedList<Event> currentEvents;

    LinkedList<Event> myAreaEvents = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myarea);

        currentEvents = new LinkedList<>();

        runParseQuery(7, 1, null);

        Spinner time_spinner = (Spinner) findViewById(R.id.spinner_time);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> time_adapter = ArrayAdapter.createFromResource(this,
                R.array.time_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        time_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        time_spinner.setAdapter(time_adapter);
        time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
            //parent.getItemAtPosition(pos)
                switch (pos){
                    case 0:
                        // query 7 days
                    case 1:

                    case 2:

                    default: // query 7 days
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        Spinner radius_spinner = (Spinner) findViewById(R.id.spinner_radius);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> radius_adapter = ArrayAdapter.createFromResource(this,
                R.array.radius_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        radius_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        radius_spinner.setAdapter(radius_adapter);
        radius_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                //parent.getItemAtPosition(pos)
                switch (pos){
                    case 0:
                        // query 1 mile
                    case 1:
                        // 5

                    case 2:
                        //10

                    case 3:
                        //20

                    default: // query 1
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
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
        int id = item.getItemId();

        switch(item.getItemId()){
            case R.id.report:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.my_area:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void runParseQuery(int days, int radius, ParseGeoPoint point) {
        Log.i(SwarmApplication.TAG, "retrieve events ...");

        myAreaEvents.clear();

        // Time
        Date now = new Date();
        //int time = days*24*3600*1000;
        int time = 3*3600*1000;
        now.setTime(now.getTime()-time);
        if (point == null) {
            Log.i(SwarmApplication.TAG, "point is null.");
            // Coalinga: point = new ParseGeoPoint(36.14414414414414, -120.35280943076845);
            // Santa Barbara, UCSB:
            point = new ParseGeoPoint(34.4138686, -119.8415803);
        }
        // Find the intersection of Time and Radius Events
        ParseQuery<ParseObject> radius_query = ParseQuery.getQuery("Event");
        radius_query.whereWithinMiles("location", point, radius)
                    .whereGreaterThan("updatedAt",now)
                    .whereEqualTo("isYes", true)
                    .setLimit(1000);

        radius_query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public synchronized void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                //currentEvents.clear();
                for (ParseObject event : parseObjects) {
                    //currentEvents.add((Event) event);
                    myAreaEvents.add((Event)event);
                }
                Log.i(SwarmApplication.TAG, "retrieved size final " + myAreaEvents.size());
            }
        });
    }
}
