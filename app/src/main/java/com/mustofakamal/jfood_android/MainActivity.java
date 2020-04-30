package com.mustofakamal.jfood_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Seller> listSeller = new ArrayList<>();
    private ArrayList<Food> foodIdList = new ArrayList<>();
    private HashMap<Seller, ArrayList<Food>> childMapping = new HashMap<>();
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        listAdapter = new MainListAdapter(MainActivity.this, listSeller, childMapping);
        expListView.setAdapter(listAdapter);

        refreshList();

    }

    protected void refreshList() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    if (jsonResponse != null) {
                        for (int i = 0; i < jsonResponse.length(); i++) {
                            JSONObject food = jsonResponse.getJSONObject(i);
                            JSONObject seller = food.getJSONObject("seller");
                            JSONObject location = seller.getJSONObject("location");
                            for(i = 0; i < seller.length(); i++) {
                                Seller s = new Seller(seller.getInt("id"),
                                        seller.getString("name"), seller.getString("email"),
                                        seller.getString("phoneNumber"), new Location
                                        (seller.getString("city"), seller.getString
                                                ("province"), seller.getString("descrption")));
                                listSeller.add(s);
                            }
                            foodIdList.add(i, new Food(food.getInt("id"), food.getString
                                    ("name"), listSeller.get(i), food.getInt("price"),
                                    food.getString("category")));
                            for (Seller s1 : listSeller) {
                                ArrayList<Food> food1 = new ArrayList<>();
                                for (Food food2 : foodIdList) {
                                    if(food2.getSeller().getName().equals(s1.getName()) ||
                                        food2.getSeller().getEmail().equals(s1.getEmail()) ||
                                        food2.getSeller().getPhoneNumber().equals(s1.getPhoneNumber())) {
                                        food1.add(food2);
                                    }
                                }
                                childMapping.put(s1, food1);
                            }
                        }
                        Toast.makeText(MainActivity.this, "Refresh Successful", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Refresh Failed", Toast.LENGTH_LONG).show();
                }
            }
        };
        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);
    }
}
