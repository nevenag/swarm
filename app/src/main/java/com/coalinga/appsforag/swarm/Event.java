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

    public static final Map<Integer, String> pests;
    static {
        pests = new HashMap<Integer, String>();
        // Our pest/farm item database
        pests.put(1,"beet_armyworm");
        pests.put(2,"beet_leafhopper");
        pests.put(3,"botrytis");
        pests.put(4,"navel_orange_worm");
        pests.put(5,"pacific_spider_mite");
        pests.put(6,"peach_twig_borer");
        pests.put(7,"stink_bugs");
        pests.put(8,"thrip");
        pests.put(9,"whitefly");
        pests.put(10,"london_rocket");
        pests.put(11,"powdery_mildew");
        pests.put(12,"russian_thistle");
        pests.put(13,"aluminum_pipe_theft");
        pests.put(14,"copper_wire_theft");
        pests.put(15,"honey_bee");
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

    public void putYes(boolean isYes){
        put("isYes", isYes);
    }

    public boolean isYes() {
        return getBoolean("isYes");
    }

    public void setPestType(int id){
        put("type", id);
    }

    public Integer getPestType(){
        return getInt("type");
    }

    public static Map<Integer, String> getPests(){
        return pests;
    }
}
