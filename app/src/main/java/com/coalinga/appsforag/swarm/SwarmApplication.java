package com.coalinga.appsforag.swarm;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

/**
 * Created by kylejorgensen on 4/18/15.
 */
public class SwarmApplication extends Application {

    static final String TAG = "SWARM";

    @Override
    public void onCreate(){
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        ParseObject.registerSubclass(Event.class);
        Parse.initialize(this, "YOUR PARSE APP ID", "YOUR PARSE CLIENT KEY");

        // Save the current Installation to Parse. (useful for Push Notifications)
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

}
