package com.example.vishal.whether_app_for_android.whether_data;

import org.json.JSONObject;

import java.io.Serializable;

public class Wind implements JSONpopulater,Serializable {

    int direction,speed;

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void JSONpopulater(JSONObject data) {
        direction = data.optInt("direction");
        speed = data.optInt("speed");
    }
}
