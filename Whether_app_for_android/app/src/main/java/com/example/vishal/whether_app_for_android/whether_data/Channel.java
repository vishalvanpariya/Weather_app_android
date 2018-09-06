package com.example.vishal.whether_app_for_android.whether_data;

import android.os.Parcelable;

import org.json.JSONObject;

import java.io.Serializable;

public class Channel implements JSONpopulater,Serializable {
    Item item;
    Location location;
    String lastbuilddate;
    Astronomy astronomy;
    Wind wind;
    Atmosphere atmosphere;



    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public Item getItem() {
        return item;
    }

    public Location getLocation() {
        return location;
    }

    public String getLastbuilddate() {
        return lastbuilddate;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public Wind getWind() {
        return wind;
    }

    @Override

    public void JSONpopulater(JSONObject data) {
        item = new Item();
        item.JSONpopulater(data.optJSONObject("item"));

        location = new Location();
        location.JSONpopulater(data.optJSONObject("location"));

        lastbuilddate = data.optString("lastBuildDate");

        astronomy = new Astronomy();
        astronomy.JSONpopulater(data.optJSONObject("astronomy"));

        wind = new Wind();
        wind.JSONpopulater(data.optJSONObject("wind"));

        atmosphere = new Atmosphere();
        atmosphere.JSONpopulater(data.optJSONObject("atmosphere"));
    }
}
