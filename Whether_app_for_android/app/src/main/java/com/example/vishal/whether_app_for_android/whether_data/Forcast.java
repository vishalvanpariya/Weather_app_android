package com.example.vishal.whether_app_for_android.whether_data;


import org.json.JSONObject;

import java.io.Serializable;

public class Forcast implements JSONpopulater,Serializable {
    private int code;
    private  int highTemperature;
    private int lowTemperature;
    private String description;
    private String day;
    private String date;

    public int getCode() {
        return code;
    }

    public int getHighTemperature() {
        return highTemperature;
    }

    public int getLowTemperature() {
        return lowTemperature;
    }

    public String getDescription() {
        return description;
    }

    public String getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    @Override
    public void JSONpopulater(JSONObject data) {
        code = data.optInt("code");
        highTemperature = data.optInt("high");
        lowTemperature = data.optInt("low");
        description = data.optString("text");
        day = data.optString("day");
        date = data.optString("date");
    }
}
