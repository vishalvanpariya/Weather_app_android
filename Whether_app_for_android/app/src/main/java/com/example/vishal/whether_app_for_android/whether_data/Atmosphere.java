package com.example.vishal.whether_app_for_android.whether_data;

import org.json.JSONObject;

import java.io.Serializable;

public class Atmosphere implements JSONpopulater,Serializable {

    float humidity,presuure,visiblity;

    public float getHumidity() {
        return humidity;
    }

    public float getPresuure() {
        return presuure;
    }

    public float getVisiblity() {
        return visiblity;
    }

    @Override
    public void JSONpopulater(JSONObject data) {
        humidity = data.optInt("humidity");
        presuure = data.optInt("pressure");
        visiblity = data.optInt("visibility");
    }
}
