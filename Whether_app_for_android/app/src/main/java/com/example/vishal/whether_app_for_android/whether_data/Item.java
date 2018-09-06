package com.example.vishal.whether_app_for_android.whether_data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Item implements JSONpopulater,Serializable {

    Condition condition;
    Forcast[] forcast;

    public Condition getCondition() {
        return condition;
    }

    public Forcast getForcast(int i) {
        return forcast[i];
    }



    @Override

    public void JSONpopulater(JSONObject data) {
        condition = new Condition();
        condition.JSONpopulater(data.optJSONObject("condition"));


        JSONArray forcastarray = data.optJSONArray("forecast");

        forcast = new Forcast[10];

        for(int i = 0;i<=9;i++){
            forcast[i] = new Forcast();
            try {
                forcast[i].JSONpopulater(forcastarray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
