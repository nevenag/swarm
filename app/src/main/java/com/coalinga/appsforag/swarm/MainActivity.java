package com.coalinga.appsforag.swarm;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.SaveCallback;


public class MainActivity extends ActionBarActivity {

    private int currentId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reset();

        final Button buttonYes = (Button) findViewById(R.id.button_yes);
        buttonYes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveEventWithLocation(true);
            }
        });

        final Button buttonNo = (Button) findViewById(R.id.button_no);
        buttonNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveEventWithLocation(false);
            }
        });
    }

    /**
     *
     */
    private void saveEventWithLocation(final boolean isYes){
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                Event event = new Event();
                event.setPestType(currentId);
                event.putYes(isYes);
                event.setLocation(new ParseGeoPoint(location.getLatitude(), location.getLongitude()));
                event.saveEventually( new SaveCallback() {
                                          @Override
                                          public void done(ParseException e) {
                                              if (e == null) {
                                                  Log.i(SwarmApplication.TAG, "Saved event");
                                                  incrementImage();
                                              } else {
                                                  Toast.makeText(
                                                          getApplicationContext(),
                                                          "Error saving: " + e.getMessage(),
                                                          Toast.LENGTH_SHORT).show();
                                              }
                                          }
                                      }

                );
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
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

    private void reset(){
        currentId = 1;
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
        if (id == R.id.report) {
            return true;
            // return
        } else if(id == R.id.my_area){
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
