package com.coalinga.appsforag.swarm;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kylejorgensen on 4/18/15.
 */
@ParseClassName("Event")
public class Event extends ParseObject {

    public static final Map<String, Integer> pests;
    static {
        pests = new HashMap<String, Integer>();
        // Our pest/farm item database
        pests.put("Beet Armyworm",1);
        pests.put("Beet Leafhopper",2);
        pests.put("Botrytis",3);
        pests.put("Navel Orange Worm",4);
        pests.put("Pacific Spider Mite",5);
        pests.put("Peach Twig Borer",6);
        pests.put("Stink Bugs",7);
        pests.put("Thirp",8);
        pests.put("Whitefly",9);
        pests.put("London Rocket",10);
        pests.put("Powdery Mildew",11);
        pests.put("Russian Thistle",12);
        pests.put("Aluminum Pipe Theft",13);
        pests.put("Copper Wire Theft",14);
        pests.put("Honey Bee",15);
    }

    public Event() {
        // a default constructor is required
    }

    public Date getTimestamp() {
        return getCreatedAt();
    }

    public void setLocation(ParseGeoPoint point){
        put("location",point);
    }

    public ParseGeoPoint getLocation(){
        return (ParseGeoPoint) get("location");
    }

    public void setPestType(int id){
        put("type", id);
    }

    public Integer getPestType(){
        return getInt("type");
    }

    public static Map<String, Integer> getPests(){
        return pests;
    }
}
