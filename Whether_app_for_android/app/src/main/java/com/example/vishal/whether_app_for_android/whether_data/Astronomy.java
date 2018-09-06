package com.example.vishal.whether_app_for_android.whether_data;

import org.json.JSONObject;

import java.io.Serializable;

public class Astronomy implements JSONpopulater,Serializable {

    String sunrise,sunset;

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    @Override
    public void JSONpopulater(JSONObject data) {
        sunrise = data.optString("sunrise");
        sunset = data.optString("sunset");
    }
}
