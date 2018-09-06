package com.example.vishal.whether_app_for_android.whether_data;

import org.json.JSONObject;

import java.io.Serializable;

public class Condition implements JSONpopulater,Serializable {

    private int code,temprature;
    private String description;

    public int getCode() {
        return code;
    }

    public int getTemprature() {
        return temprature;
    }

    public String getDescription() {
        return description;
    }



    @Override
    public void JSONpopulater(JSONObject data) {
        code = data.optInt("code");
        temprature = data.optInt("temp");
        description = data.optString("text");
    }
}
