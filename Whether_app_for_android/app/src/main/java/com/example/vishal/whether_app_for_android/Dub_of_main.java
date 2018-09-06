package com.example.vishal.whether_app_for_android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.vishal.whether_app_for_android.whether_data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;

public class Dub_of_main extends Activity {

    Toolbar toolbar;
    SharedPreferences sharedPreferences1;
    SearchView searchView;
    Button ahmedabad, surat, rajkot, bangalore, mumbai, delhi, hyderabad, kolkata, newyork, chennai, udaipur, jammu;
    ProgressDialog loadingDialog;
    private Exception error;
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dub_of_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        ahmedabad = (Button) findViewById(R.id.ahmedabad);
        surat = (Button) findViewById(R.id.surat);
        rajkot = (Button) findViewById(R.id.rajkot);
        bangalore = (Button) findViewById(R.id.bangalore);
        mumbai = (Button) findViewById(R.id.mumbai);
        delhi = (Button) findViewById(R.id.delhi);
        hyderabad = (Button) findViewById(R.id.hyderabad);
        kolkata = (Button) findViewById(R.id.kolkata);
        newyork = (Button) findViewById(R.id.newyork);
        chennai =(Button) findViewById(R.id.chennai);
        udaipur = (Button) findViewById(R.id.udaipur);
        jammu = (Button)findViewById(R.id.jammu);

        ahmedabad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = new ProgressDialog(Dub_of_main.this);
                loadingDialog.setMessage("loading...");
                loadingDialog.setCancelable(false);
                loadingDialog.show();
                validcity("ahmedabad");
            }
        });

        surat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = new ProgressDialog(Dub_of_main.this);
                loadingDialog.setMessage("loading...");
                loadingDialog.setCancelable(false);
                loadingDialog.show();
                validcity("surat");
            }
        });

        rajkot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = new ProgressDialog(Dub_of_main.this);
                loadingDialog.setMessage("loading...");
                loadingDialog.setCancelable(false);
                loadingDialog.show();
                validcity("rajkot");
            }
        });

        bangalore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = new ProgressDialog(Dub_of_main.this);
                loadingDialog.setMessage("loading...");
                loadingDialog.setCancelable(false);
                loadingDialog.show();
                validcity("bangalore");
            }
        });

        mumbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = new ProgressDialog(Dub_of_main.this);
                loadingDialog.setMessage("loading...");
                loadingDialog.setCancelable(false);
                loadingDialog.show();
                validcity("mumbai");
            }
        });

        delhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = new ProgressDialog(Dub_of_main.this);
                loadingDialog.setMessage("loading...");
                loadingDialog.setCancelable(false);
                loadingDialog.show();
                validcity("delhi");
            }
        });

        hyderabad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = new ProgressDialog(Dub_of_main.this);
                loadingDialog.setMessage("loading...");
                loadingDialog.setCancelable(false);
                loadingDialog.show();
                validcity("hyderabad");
            }
        });

        kolkata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = new ProgressDialog(Dub_of_main.this);
                loadingDialog.setMessage("loading...");
                loadingDialog.setCancelable(false);
                loadingDialog.show();
                validcity("kolkata");
            }
        });

        newyork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = new ProgressDialog(Dub_of_main.this);
                loadingDialog.setMessage("loading...");
                loadingDialog.setCancelable(false);
                loadingDialog.show();
                validcity("newyork");
            }
        });

        chennai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = new ProgressDialog(Dub_of_main.this);
                loadingDialog.setMessage("loading...");
                loadingDialog.setCancelable(false);
                loadingDialog.show();
                validcity("chennai");
            }
        });

        udaipur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = new ProgressDialog(Dub_of_main.this);
                loadingDialog.setMessage("loading...");
                loadingDialog.setCancelable(false);
                loadingDialog.show();
                validcity("udaipur");
            }
        });

        jammu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = new ProgressDialog(Dub_of_main.this);
                loadingDialog.setMessage("loading...");
                loadingDialog.setCancelable(false);
                loadingDialog.show();
                validcity("jammu");
            }
        });


        searchView = (SearchView)findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() == 0 ){
                    Toast.makeText(Dub_of_main.this,"Enter city",Toast.LENGTH_SHORT).show();
                }
                else if (query.length() == 1){
                    Toast.makeText(Dub_of_main.this,"City not found",Toast.LENGTH_SHORT).show();
                }
                else {
                    loadingDialog = new ProgressDialog(Dub_of_main.this);
                    loadingDialog.setMessage("loading...");
                    loadingDialog.setCancelable(false);
                    loadingDialog.show();
                    validcity(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void sendobjbyserchview(final LinkedList<String> arrayList) {
        Log.d("first activity", "sendobj: arraylist length"+arrayList.size());
        Log.d("fdf", "sendobj: "+arrayList);
        LinkedList<Channel> list = new LinkedList<>();
        new AsyncTask<LinkedList<String>, Void, LinkedList<String>>() {
            @Override
            protected LinkedList<String> doInBackground(LinkedList<String>... arrayLists) {
                LinkedList<String> arrayList1 = new LinkedList<>();
                for (int i = 0;i<arrayList.size();i++){
                    Log.d("doInBackground: ", "doInBackground: "+arrayList.get(i));

                    String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")",arrayList.get(i));

                    String endpoint = String .format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                    try {
                        URL url = new URL(endpoint);

                        URLConnection urlConnection = url.openConnection();

                        InputStream inputStream = urlConnection.getInputStream();

                        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = br.readLine())!=null){
                            result.append(line);
                        }

                        arrayList1.add(result.toString());
                    } catch (Exception e) {
                        error = e;
                    }
                }
                return arrayList1;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(LinkedList<String> strings) {
                super.onPostExecute(strings);

                LinkedList<Channel> arraylistchannel = new LinkedList<>();
                for (int i = 0;i<strings.size();i++){


                    if(strings.get(i) == null && error != null){
                        servicefailure(error);
                        return;
                    }
                    try {
                        JSONObject data = new JSONObject(strings.get(i));

                        JSONObject queryresult = data.optJSONObject("query");

                        int count = queryresult.getInt("count");
                        if (count== 0){
                            servicefailure(new Dub_of_main.locationwhetherexeption("no city found"));
                            return;
                        }

                        Channel channle = new Channel();
                        channle.JSONpopulater(queryresult.optJSONObject("results").optJSONObject("channel"));

                        arraylistchannel.add(channle);

                    } catch (JSONException e) {
                        servicefailure(e);
                    }
                }
                servicesuccess(arraylistchannel);
            }
        }.execute();
    }

    public void savecity(String city){
        sharedPreferences1 = getSharedPreferences("city", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences1.edit();

        int lastkeyH;
        lastkeyH = sharedPreferences1.getInt("lastkeycity",0);
        if (lastkeyH == 0){
            lastkeyH++;
        }
        editor.putString(String.valueOf(lastkeyH), city);
        lastkeyH++;
        editor.putInt("lastkeycity",lastkeyH);
        editor.apply();
    }
    private void servicefailure(Exception error) {
        Toast.makeText(this,error.getMessage(),Toast.LENGTH_SHORT).show();
    }
    private void servicesuccess(LinkedList<Channel> list) {
        Log.d("fdd", "servicesuccess: length"+list.size());
        Intent intent = new Intent(this,Data_display.class);
        intent.putExtra("list", list);
        startActivity(intent);
        finish();
    }
    public LinkedList<String> getsharedpref(){
        LinkedList<String> arrayList = new LinkedList<>();
        int temp = sharedPreferences1.getInt("lastkeycity", 0);
        for (int i = 0; i < temp - 1; i++) {
            arrayList.add(sharedPreferences1.getString(String.valueOf(i + 1), ""));
        }
        return arrayList;
    }

    private void validcity(final String location) {
        city = location;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {


                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")",city);

                String endpoint = String .format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endpoint);

                    URLConnection urlConnection = url.openConnection();

                    InputStream inputStream = urlConnection.getInputStream();

                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = br.readLine())!=null){
                        result.append(line);
                    }

                    return result.toString();
                } catch (Exception e) {
                    error = e;
                }


                return null;
            }

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String s) {
                if(s == null && error != null){
                    Toast.makeText(Dub_of_main.this,"service problem",Toast.LENGTH_SHORT).show();
                    loadingDialog.hide();
                    return;
                }
                else {
                    try {
                        JSONObject data = new JSONObject(s);
                        JSONObject queryresult = data.optJSONObject("query");
                        int count = queryresult.getInt("count");
                        if (count == 0){
                            loadingDialog.hide();
                            Toast.makeText(Dub_of_main.this,"City not found",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            savecity(city);
                            LinkedList<String> array = new LinkedList<>();
                            sharedPreferences1 = getSharedPreferences("city", MODE_PRIVATE);
                            if (sharedPreferences1.getInt("lastkeycity", 0) != 0) {

                                int temp = sharedPreferences1.getInt("lastkeycity", 0);
                                for (int i = 0; i < temp - 1; i++) {
                                    array.add(sharedPreferences1.getString(String.valueOf(i + 1), ""));
                                }
                                sendobjbyserchview(array);
                            }
                            else {
                                LinkedList<String> arrayList = new LinkedList<>();
                                arrayList.add(city);

                                sendobjbyserchview(arrayList);
                            }
                        }
                    } catch (JSONException e) {
                        servicefailure(e);
                    }
                }

            }
        }.execute();


    }

    public class locationwhetherexeption extends Exception{
        public locationwhetherexeption(String message) {
            super(message);
        }
    }
}
