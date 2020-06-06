package com.mustofakamal.jfood_android.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mustofakamal.jfood_android.object.Food;
import com.mustofakamal.jfood_android.object.Location;
import com.mustofakamal.jfood_android.adapter.MainListAdapter;
import com.mustofakamal.jfood_android.request.MenuRequest;
import com.mustofakamal.jfood_android.R;
import com.mustofakamal.jfood_android.object.Seller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Seller> listSeller = new ArrayList<>();
    private ArrayList<Food> foodIdList = new ArrayList<>();
    private HashMap<Seller, ArrayList<Food>> childMapping = new HashMap<>();
    int currentUserId;
    SharedPreferences sharedPreferences;
    Gson gson;

    MainListAdapter listAdapter;
    ExpandableListView expListView;
    JSONArray jsonResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getSharedPreferences("Session_Key", Context.MODE_PRIVATE);
        currentUserId = sharedPreferences.getInt("currentUserId", 0);
        Button btnPesanan = findViewById(R.id.pesanan);
        expListView = findViewById(R.id.lvExp);
        refreshList();

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Food selected = childMapping.get(listSeller.get(groupPosition)).get(childPosition);
                Intent buatPesanan = new Intent(MainActivity.this, BuatPesananActivity.class);
                gson = new Gson();
                String food_json = gson.toJson(selected);
                buatPesanan.putExtra("food_json", food_json);
//                Bundle data = new Bundle();
//                data.putInt("foodId", selected.getId());
//                data.putString("foodName", selected.getName());
//                data.putString("foodCategory", selected.getCategory());
//                data.putInt("foodPrice", selected.getPrice());
//                buatPesanan.putExtras(data);
                startActivity(buatPesanan);
                return false;
            }
        });

        btnPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pesanan = new Intent(MainActivity.this, SelesaiPesananActivity.class);
                startActivity(pesanan);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItem = item.getItemId();
        switch(menuItem) {
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure want to logout?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.clear();
                                editor.apply();
                                Toast.makeText(MainActivity.this, "Action Search", Toast.LENGTH_SHORT).show();
                                Intent logout = new Intent(MainActivity.this, LoginActivity.class);
                                logout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(logout);
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

            case R.id.chart:
                Toast.makeText(MainActivity.this, "Chart", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ChartActivity.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (isLogin()) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private boolean isLogin() {
        if (sharedPreferences.contains("login")) {
            return true;
        }
        return false;
    }

    protected void refreshList() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonResponse = new JSONArray(response);
                    if (jsonResponse != null) {
                        for (int i = 0; i < jsonResponse.length(); i++) {
                            JSONObject food = jsonResponse.getJSONObject(i);
                            JSONObject seller = food.getJSONObject("seller");
                            JSONObject location = seller.getJSONObject("location");
//                            for(int j = 0; j < seller.length(); j++) {
                            Seller s = new Seller(seller.getInt("id"),
                                    seller.getString("name"), seller.getString("email"),
                                    seller.getString("phoneNumber"), new Location
                                    (location.getString("city"), location.getString
                                            ("province"), location.getString("description"))
                            );
//
//                            if(!listSeller.contains(s)) {
//                                listSeller.add(s);
//                            }
                            if(listSeller.size() == 0){
                                listSeller.add(s);
                            }
                            for (int j = 0; j < listSeller.size(); j++) {
                                if (listSeller.get(j).getId() == s.getId()) {
                                    break;
                                }
                                if(j == listSeller.size() - 1) {
                                    listSeller.add(s);
                                }
                            }

                            foodIdList.add(i, new Food(food.getInt("id"), food.getString
                                    ("name"), s, food.getInt("price"),
                                    food.getString("category")));
                        }
                        for (Seller s1 : listSeller) {
                            ArrayList<Food> food1 = new ArrayList<>();
                            for (Food food2 : foodIdList) {
                                if(food2.getSeller().getName().equals(s1.getName())) {
                                    food1.add(food2);
                                }
                                childMapping.put(s1, food1);
                            }
                        }
                        Toast.makeText(MainActivity.this, "Refresh Successful", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Refresh Failed", Toast.LENGTH_LONG).show();
                }
                listAdapter = new MainListAdapter(MainActivity.this, listSeller, childMapping);
                expListView.setAdapter(listAdapter);
            }
        };
        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);
    }
}
