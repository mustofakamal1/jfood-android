package com.mustofakamal.jfood_android.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mustofakamal.jfood_android.R;
import com.mustofakamal.jfood_android.adapter.ChartAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity implements ChartAdapter.ItemClickListener {

    SharedPreferences sharedPreferences;
    int currentUserId;
    ChartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        sharedPreferences = getSharedPreferences("Session_Key", Context.MODE_PRIVATE);
        currentUserId = sharedPreferences.getInt("currentUserId", 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        displayListView();
        List<String> countryList = new ArrayList<String>();
        countryList.add("Aruba");
        countryList.add("Anguilla");
        countryList.add("Netherlands Antilles");
        countryList.add("Antigua and Barbuda");
        countryList.add("Aruba");
        countryList.add("Anguilla");
        countryList.add("Netherlands Antilles");
        countryList.add("Antigua and Barbuda");
        countryList.add("Aruba");
        countryList.add("Anguilla");
        countryList.add("Netherlands Antilles");
        countryList.add("Antigua and Barbuda");
        countryList.add("Aruba");
        countryList.add("Anguilla");
        countryList.add("Netherlands Antilles");
        countryList.add("Antigua and Barbuda");
        //create an ArrayAdaptar from the String Array
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.chartView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChartAdapter(this, countryList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }

    private void displayListView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        getMenuInflater().inflate(R.menu.toolbar_menu_2, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItem = item.getItemId();
        switch(menuItem) {
            case R.id.search:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure want to logout?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


                break;

            case R.id.filter:
                Toast.makeText(this, "Action Favourites", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
