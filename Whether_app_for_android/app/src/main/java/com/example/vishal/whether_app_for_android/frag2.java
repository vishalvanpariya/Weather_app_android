package com.example.vishal.whether_app_for_android;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vishal.whether_app_for_android.whether_data.Channel;

import java.util.LinkedList;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class frag2 extends Fragment {

    TextView city,todaytemp,todayhigh,todaylow,condition,lastbuild,sunrise,sunset,winddirection,windspeed,pressure,visiblity,humiditiy;
    WebView mWebView;
    frag2 context;
    RecyclerView recyclerView;
    Forcast_adapter adapter;
    ImageView logo;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_frag2, container, false);


        city = (TextView) view.findViewById(R.id.city);
        logo = (ImageView)view.findViewById(R.id.logo);
        todaytemp = (TextView)view.findViewById(R.id.temp);
        todayhigh = (TextView)view.findViewById(R.id.hightemp);
        todaylow = (TextView)view.findViewById(R.id.lowtemp);
        condition = (TextView)view.findViewById(R.id.condition);
        lastbuild =(TextView)view.findViewById(R.id.lastupdate);
        sunrise = (TextView)view.findViewById(R.id.sunrise);
        sunset = (TextView)view.findViewById(R.id.sunset);
        winddirection = (TextView)view.findViewById(R.id.winddirection);
        windspeed = (TextView)view.findViewById(R.id.windspeed);
        pressure = (TextView)view.findViewById(R.id.pressure);
        visiblity = (TextView)view.findViewById(R.id.visibility);
        humiditiy = (TextView)view.findViewById(R.id.humidity);
        mWebView = (WebView) view.findViewById(R.id.web);


        Bundle bundle = getArguments();
        Channel channel = (Channel) bundle.getSerializable("data");
        String tempuni = bundle.getString("temp");
        String winduni = bundle.getString("wind");
        String visiunit = bundle.getString("visi");
        String pressuni = bundle.getString("press");

        city.setText(channel.getLocation().getCity());

        int mainlogo = getResources().getIdentifier("icon_" + channel.getItem().getCondition().getCode(), "drawable",getContext().getPackageName());
        logo.setImageResource(mainlogo);

        if (tempuni.equals("fan")) {
            todaytemp.setText(String.valueOf(channel.getItem().getCondition().getTemprature()) + "\u00B0");
            todayhigh.setText(String.valueOf(channel.getItem().getForcast(0).getHighTemperature()) + "\u00B0");
            todaylow.setText(String.valueOf(channel.getItem().getForcast(0).getLowTemperature()) + "\u00B0");
        }
        if (tempuni.equals("cel")){
            double a,b,c;
            Log.d(TAG, "onCreateView: "+channel.getItem().getCondition().getTemprature());
            a =(channel.getItem().getCondition().getTemprature()-32)*5/9;
            b = (channel.getItem().getForcast(0).getHighTemperature()-32)*5/9;
            c = (channel.getItem().getForcast(0).getLowTemperature()-32)*5/9;
            Log.d(TAG, "onCreateView: "+a+" "+b+" "+c);
            todaytemp.setText(a+ "\u00B0");
            todayhigh.setText(b+ "\u00B0");
            todaylow.setText(c+ "\u00B0");
        }

        condition.setText(channel.getItem().getCondition().getDescription());

        lastbuild.setText(channel.getLastbuilddate());

        sunrise.setText(channel.getAstronomy().getSunrise());
        sunset.setText(channel.getAstronomy().getSunset());

        winddirection.setText(String.valueOf(channel.getWind().getDirection())+"\u00B0");

        if(winduni.equals("Mph")){
            int a = (int) (channel.getWind().getSpeed()/1.60934);
            windspeed.setText(a+"Mph");
        }
        if (winduni.equals("Km")) {
            windspeed.setText(String.valueOf(channel.getWind().getSpeed())+"Km/h");
        }


        if (visiunit.equals("Km")) {
            visiblity.setText(String.valueOf(channel.getAtmosphere().getVisiblity())+"Km/h");
        }
        if (visiunit.equals("Mph")){
            int a = (int)channel.getAtmosphere().getVisiblity();
            visiblity.setText(a+"Mph");
        }

        if (pressuni.equals("HPa")) {
            pressure.setText(String.valueOf(channel.getAtmosphere().getPresuure())+"Hpa");
        }
        if (pressuni.equals("Mb")){
            pressure.setText(String.valueOf(channel.getAtmosphere().getPresuure())+"Mb");
        }
        if (pressuni.equals("mmHg")){
            int a = (int) ( channel.getAtmosphere().getPresuure()*0.75);
            pressure.setText(a+"mmHg");
        }

        LinkedList<String> day = new LinkedList<String>();
        LinkedList<String> cond = new LinkedList<String>();
        LinkedList<String> date = new LinkedList<String>();
        LinkedList<Integer> condlogo = new LinkedList<>();
        LinkedList<Integer> down = new LinkedList<Integer>();
        LinkedList<Integer> up = new LinkedList<Integer>();

        for (int i = 0;i<10;i++){
            if (i == 0){
                day.add("Today");
            }else {
                day.add(channel.getItem().getForcast(i).getDay());
            }
            date.add(channel.getItem().getForcast(i).getDate().substring(0,6));
            if (tempuni.equals("fan")) {
                down.add(channel.getItem().getForcast(i).getLowTemperature());
                up.add(channel.getItem().getForcast(i).getHighTemperature());
            }
            if (tempuni.equals("cel")){
                double a,b;
                a = (channel.getItem().getForcast(i).getLowTemperature()-32)*5/9;
                b = (channel.getItem().getForcast(i).getHighTemperature()-32)*5/9;
                down.add((int) a);
                up.add((int) b);
            }
            cond.add(channel.getItem().getForcast(i).getDescription());
            int weatherIconImageResource = getResources().getIdentifier("icon_" + channel.getItem().getForcast(i).getCode(), "drawable",getContext().getPackageName());
            condlogo.add(weatherIconImageResource);
        }



        recyclerView = (RecyclerView)view.findViewById(R.id.abc);
        adapter = new Forcast_adapter(day,date,cond,condlogo,up,down);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setFocusable(false);

        humiditiy.setText(String.valueOf(channel.getAtmosphere().getHumidity())+"%");


        mWebView.loadUrl("https://windy.com");

        // Enable Javascript
        //WebSettings webSettings = mWebView.getSettings();
        //webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());
        context = this;


        return view;
    }
}
