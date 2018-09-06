package com.example.vishal.whether_app_for_android.whether_data;

import org.json.JSONObject;

import java.io.Serializable;

public class Location implements JSONpopulater,Serializable {

    String city;

    public String getCity() {
        return city;
    }

    @Override
    public void JSONpopulater(JSONObject data) {
        city = data.optString("city");
    }
}
