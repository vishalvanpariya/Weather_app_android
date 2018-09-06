package com.example.vishal.whether_app_for_android;

import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vishal.whether_app_for_android.whether_data.Channel;
import com.example.vishal.whether_app_for_android.whether_data.Condition;
import com.example.vishal.whether_app_for_android.whether_data.Item;
import com.example.vishal.whether_app_for_android.whether_data.WhetherServiceCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;

public class Data_display extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    frag1 f,f1;
    SharedPreferences sharedPreferences,sharedPreferences1,sharedPreferences2;
    RecyclerView recyclerView;
    Drawer_recycler_Adapter adapter;
    ArrayList<Channel> arrayList;
    LinkedList<Channel> linkedList;
    ArrayList<Integer> condlogo;
    Drawer_recycler_delet_Adapter adapter1;
    TextView edit_drawer;
    ImageView logo_drawer;

    @SuppressLint("ObjectAnimatorBinding")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences("unit", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences2.edit();
        if (sharedPreferences2.getString("temp","abc") == "abc"){
            editor.putString("temp","fan");
            editor.apply();
        }
        if (sharedPreferences2.getString("wind","abc") == "abc"){
            editor.putString("wind","Km");
            editor.apply();
        }
        if (sharedPreferences2.getString("visi","abc") == "abc"){
            editor.putString("visi","Km");
            editor.apply();
        }
        if (sharedPreferences2.getString("press","abc") == "abc"){
            editor.putString("press","HPa");
            editor.apply();
        }



        f = (frag1)this.getSupportFragmentManager().findFragmentById(R.id.fragment);
        f1 = (frag1)this.getSupportFragmentManager().findFragmentById(R.id.fragment);
        linkedList = new LinkedList<Channel>();
        arrayList = (ArrayList<Channel>) getIntent().getSerializableExtra("list");
        for (int i = 0;i<arrayList.size();i++){
            linkedList.add(arrayList.get(i));
        }

        Log.d("second activity", "onCreate:data display "+arrayList.size());
        if (linkedList.size() == 0){
            Toast.makeText(this,"You are Offline",Toast.LENGTH_LONG).show();
            int temp = sharedPreferences.getInt("lastkeyJSON",0);
            if (temp != 0){
                for (int i = 0 ;i<temp-1;i++){
                    Gson gson = new Gson();
                    Type type = new TypeToken<Channel>() {}.getType();
                    Channel channel = gson.fromJson(sharedPreferences.getString(String.valueOf(i+1),""),type);
                    linkedList.add(channel);
                }
                f.addPage(linkedList,
                        sharedPreferences2.getString("temp","f")
                        ,sharedPreferences2.getString("wind","Mph")
                        ,sharedPreferences2.getString("visi","Km")
                        ,sharedPreferences2.getString("press","in") );
            }
        }
        else {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.clear();
            edit.apply();
            for (int i = 0; i < linkedList.size(); i++) {
                Gson gson = new Gson();
                String s = gson.toJson(linkedList.get(i));
                saveJSONobj(s);
            }
            f.addPage(linkedList,
                    sharedPreferences2.getString("temp","f")
                    ,sharedPreferences2.getString("wind","Mph")
                    ,sharedPreferences2.getString("visi","Km")
                    ,sharedPreferences2.getString("press","in"));
        }

        condlogo = new ArrayList<>();
        for (int i = 0;i<linkedList.size();i++){
            int weatherIconImageResource = getResources().getIdentifier("icon_" + linkedList.get(i).getItem().getCondition().getCode(), "drawable",getPackageName());
            condlogo.add(weatherIconImageResource);
        }



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        recyclerView = (RecyclerView)findViewById(R.id.drawerrecycler);
        adapter = new Drawer_recycler_Adapter(linkedList,condlogo);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.VISIBLE);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            this.finishAffinity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.data_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_city) {
            Intent intent = new Intent(this,Dub_of_main.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void saveJSONobj(String JSONobj){
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int lastkeyH;
        lastkeyH = sharedPreferences.getInt("lastkeyJSON",0);
        if (lastkeyH == 0){
            lastkeyH++;
        }
        editor.putString(String.valueOf(lastkeyH), JSONobj);
        lastkeyH++;
        editor.putInt("lastkeyJSON",lastkeyH);
        editor.apply();
    }

    public void edit(final View view) {
        edit_drawer = (TextView)findViewById(R.id.edit_drawer);
        logo_drawer = (ImageView)findViewById(R.id.logo_drawer);
        Log.d("fdj", "edit: "+edit_drawer.getText());
        if (edit_drawer.getText() == "Complete") {
            edit_drawer.setText("edit");
            logo_drawer.setImageResource(R.drawable.location);
            recyclerView = (RecyclerView)findViewById(R.id.drawerrecycler);
            adapter = new Drawer_recycler_Adapter(linkedList,condlogo);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setVisibility(View.VISIBLE);
        }
        else {
            if (linkedList.size() == 1){
                Toast.makeText(this,"Can't edit",Toast.LENGTH_LONG).show();
            }else {
                sharedPreferences1 = getSharedPreferences("city", MODE_PRIVATE);
                edit_drawer.setText("Complete");
                logo_drawer.setImageResource(R.drawable.sign);
                recyclerView = (RecyclerView) findViewById(R.id.drawerrecycler);
                adapter1 = new Drawer_recycler_delet_Adapter(linkedList, condlogo);
                adapter1.setCallback(new Drawer_recycler_delet_Adapter.Callback() {
                    @Override
                    public void onClicklistner(int position) {
                        if (linkedList.size() == 1 && position == 0) {
                            Toast.makeText(Data_display.this, "Cant edit", Toast.LENGTH_SHORT).show();
                        } else {
                            linkedList.remove(position);
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.clear();
                            edit.apply();
                            SharedPreferences.Editor editor = sharedPreferences1.edit();
                            editor.clear();
                            editor.apply();
                            for (int i = 0; i < linkedList.size(); i++) {
                                savecity(linkedList.get(i).getLocation().getCity().toString());
                                Gson gson = new Gson();
                                String s = gson.toJson(linkedList.get(i));
                                saveJSONobj(s);
                            }
                            adapter1 = new Drawer_recycler_delet_Adapter(linkedList, condlogo);
                            recyclerView.setAdapter(adapter1);
                            recyclerView.setLayoutManager(new LinearLayoutManager(Data_display.this));
                            recyclerView.setVisibility(View.VISIBLE);
                            edit(view);
                            f.removepage(linkedList,
                                    sharedPreferences2.getString("temp","f")
                                    ,sharedPreferences2.getString("wind","Mph")
                                    ,sharedPreferences2.getString("visi","Km")
                                    ,sharedPreferences2.getString("press","in"));
                        }
                    }
                });
                recyclerView.setAdapter(adapter1);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setVisibility(View.VISIBLE);
            }
        }
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

    public void Addcity(View view) {
        Intent intent = new Intent(this,Dub_of_main.class);
        startActivity(intent);
    }

    public void tempupdate(View view) {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.temp_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);
        deleteDialog.setTitle("Temperature");
        deleteDialog.show();
        RadioButton r1 = (RadioButton)deleteDialogView.findViewById(R.id.celsius);
        RadioButton r2 = (RadioButton)deleteDialogView.findViewById(R.id.fahrenheit);

        if (sharedPreferences2.getString("temp","abc").equals("cel")){
            r1.setChecked(true);
        }
        if (sharedPreferences2.getString("temp","abc").equals("fan")){
            r2.setChecked(true);
        }
        r1.setText("C"+"\u00B0");
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences2 = getSharedPreferences("unit", MODE_PRIVATE);
                if (sharedPreferences2.getString("temp","abc").equals("cel")){

                }
                if (sharedPreferences2.getString("temp","abc").equals("fan")){
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.putString("temp","cel");
                    editor.apply();
                    f.removepage(linkedList,
                            sharedPreferences2.getString("temp","fan")
                            ,sharedPreferences2.getString("wind","Km")
                            ,sharedPreferences2.getString("visi","Km")
                            ,sharedPreferences2.getString("press","in"));
                }
                deleteDialog.dismiss();
            }
        });
        r2.setText("F"+"\u00B0");
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences2 = getSharedPreferences("unit", MODE_PRIVATE);
                if (sharedPreferences2.getString("temp","abc").equals("fan")){

                }
                if (sharedPreferences2.getString("temp","abc").equals("cel")){
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.putString("temp","fan");
                    editor.apply();
                    f.removepage(linkedList,
                            sharedPreferences2.getString("temp","fan")
                            ,sharedPreferences2.getString("wind","Km")
                            ,sharedPreferences2.getString("visi","Km")
                            ,sharedPreferences2.getString("press","in"));
                }
                deleteDialog.dismiss();

            }
        });
    }

    public void windupdate(View view) {
        sharedPreferences2 = getSharedPreferences("unit", MODE_PRIVATE);
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.temp_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);
        deleteDialog.setTitle("Wind");
        deleteDialog.show();

        RadioButton r1 = (RadioButton)deleteDialogView.findViewById(R.id.celsius);
        RadioButton r2 = (RadioButton)deleteDialogView.findViewById(R.id.fahrenheit);
        if (sharedPreferences2.getString("wind","abc").equals("Km")){
            r1.setChecked(true);
        }
        if (sharedPreferences2.getString("wind","abc").equals("Mph")){
            r2.setChecked(true);
        }
        r1.setText("Km/hour");
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences2 = getSharedPreferences("unit", MODE_PRIVATE);
                if (sharedPreferences2.getString("wind","abc").equals("Km")){

                }
                if (sharedPreferences2.getString("wind","abc").equals("Mph")){
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.putString("wind","Km");
                    editor.apply();
                    f.removepage(linkedList,
                            sharedPreferences2.getString("temp","fan")
                            ,sharedPreferences2.getString("wind","Km")
                            ,sharedPreferences2.getString("visi","Km")
                            ,sharedPreferences2.getString("press","in"));
                }
                deleteDialog.dismiss();
            }
        });
        r2.setText("Mph");
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences2 = getSharedPreferences("unit", MODE_PRIVATE);
                if (sharedPreferences2.getString("wind","abc").equals("Mph")){

                }
                if (sharedPreferences2.getString("wind","abc").equals("Km")){
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.putString("wind","Mph");
                    editor.apply();
                    f.removepage(linkedList,
                            sharedPreferences2.getString("temp","fan")
                            ,sharedPreferences2.getString("wind","Km")
                            ,sharedPreferences2.getString("visi","Km")
                            ,sharedPreferences2.getString("press","in"));
                }
                deleteDialog.dismiss();

            }
        });

    }

    public void visibilityupdate(View view) {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.temp_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);
        deleteDialog.setTitle("Visibility unit");
        deleteDialog.show();

        RadioButton r1 = (RadioButton)deleteDialogView.findViewById(R.id.celsius);
        RadioButton r2 = (RadioButton)deleteDialogView.findViewById(R.id.fahrenheit);
        if (sharedPreferences2.getString("visi","abc").equals("Km")){
            r1.setChecked(true);
        }
        if (sharedPreferences2.getString("visi","abc").equals("Mph")){
            r2.setChecked(true);
        }
        r1.setText("Km/hour");
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences2.getString("visi","abc").equals("Km")){

                }
                if (sharedPreferences2.getString("visi","abc").equals("Mph")){
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.putString("visi","Km");
                    editor.apply();
                    f.removepage(linkedList,
                            sharedPreferences2.getString("temp","fan")
                            ,sharedPreferences2.getString("wind","Km")
                            ,sharedPreferences2.getString("visi","Km")
                            ,sharedPreferences2.getString("press","in"));
                }
                deleteDialog.dismiss();
            }
        });
        r2.setText("Mph");
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences2.getString("visi","abc").equals("Mph")){

                }
                if (sharedPreferences2.getString("visi","abc").equals("Km")){
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.putString("visi","Mph");
                    editor.apply();
                    f.removepage(linkedList,
                            sharedPreferences2.getString("temp","fan")
                            ,sharedPreferences2.getString("wind","Km")
                            ,sharedPreferences2.getString("visi","Km")
                            ,sharedPreferences2.getString("press","in"));
                }
                deleteDialog.dismiss();

            }
        });

    }

    public void pressureupdate(View view) {

        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.pressure_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);
        deleteDialog.setTitle("Pressure");
        deleteDialog.show();

        RadioButton r1 = (RadioButton)deleteDialogView.findViewById(R.id.r1);
        RadioButton r2 = (RadioButton)deleteDialogView.findViewById(R.id.r2);
        RadioButton r3 = (RadioButton)deleteDialogView.findViewById(R.id.r3);

        if (sharedPreferences2.getString("press","abc").equals("HPa")){
            r1.setChecked(true);
        }
        if (sharedPreferences2.getString("press","abc").equals("Mb")){
            r2.setChecked(true);
        }
        if (sharedPreferences2.getString("press","abc").equals("mmHg")){
            r3.setChecked(true);
        }

        r1.setText("HPa");
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences2.getString("press","abc").equals("HPa")){

                }
                if (sharedPreferences2.getString("press","abc").equals("Mb")){
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.putString("press","HPa");
                    editor.apply();
                    f.removepage(linkedList,
                            sharedPreferences2.getString("temp","fan")
                            ,sharedPreferences2.getString("wind","Km")
                            ,sharedPreferences2.getString("visi","Km")
                            ,sharedPreferences2.getString("press","HPa"));
                }
                if (sharedPreferences2.getString("press","abc").equals("mmHg")){
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.putString("press","HPa");
                    editor.apply();
                    f.removepage(linkedList,
                            sharedPreferences2.getString("temp","fan")
                            ,sharedPreferences2.getString("wind","Km")
                            ,sharedPreferences2.getString("visi","Km")
                            ,sharedPreferences2.getString("press","Hpa"));
                }

                deleteDialog.dismiss();
            }
        });


        r2.setText("Mb");
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences2.getString("press","abc").equals("Mb")){

                }
                if (sharedPreferences2.getString("press","abc").equals("Hpa")){
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.putString("press","Mb");
                    editor.apply();
                    f.removepage(linkedList,
                            sharedPreferences2.getString("temp","fan")
                            ,sharedPreferences2.getString("wind","Km")
                            ,sharedPreferences2.getString("visi","Km")
                            ,sharedPreferences2.getString("press","Hpa"));
                }
                if (sharedPreferences2.getString("press","abc").equals("mmHg")){
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.putString("press","Mb");
                    editor.apply();
                    f.removepage(linkedList,
                            sharedPreferences2.getString("temp","fan")
                            ,sharedPreferences2.getString("wind","Km")
                            ,sharedPreferences2.getString("visi","Km")
                            ,sharedPreferences2.getString("press","Hpa"));
                }

                deleteDialog.dismiss();
            }
        });


        r3.setText("mmHg");
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences2.getString("press","abc").equals("mmHg")){

                }
                if (sharedPreferences2.getString("press","abc").equals("HPa")){
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.putString("press","mmHg");
                    editor.apply();
                    f.removepage(linkedList,
                            sharedPreferences2.getString("temp","fan")
                            ,sharedPreferences2.getString("wind","Km")
                            ,sharedPreferences2.getString("visi","Km")
                            ,sharedPreferences2.getString("press","Hpa"));
                }
                if (sharedPreferences2.getString("press","abc").equals("Mb")){
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.putString("press","mmHg");
                    editor.apply();
                    f.removepage(linkedList,
                            sharedPreferences2.getString("temp","fan")
                            ,sharedPreferences2.getString("wind","Km")
                            ,sharedPreferences2.getString("visi","Km")
                            ,sharedPreferences2.getString("press","Hpa"));
                }

                deleteDialog.dismiss();

            }
        });

    }

}
