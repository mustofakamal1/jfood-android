package com.mustofakamal.jfood_android.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsPromptResult;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.mustofakamal.jfood_android.R;
import com.mustofakamal.jfood_android.adapter.ChartAdapter;
import com.mustofakamal.jfood_android.object.CashInvoice;
import com.mustofakamal.jfood_android.object.CashlessInvoice;
import com.mustofakamal.jfood_android.object.Food;
import com.mustofakamal.jfood_android.object.Invoice;
import com.mustofakamal.jfood_android.object.Promo;
import com.mustofakamal.jfood_android.object.type.FoodCategory;
import com.mustofakamal.jfood_android.request.BuatPesananRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class ChartActivity adalah class yang berfungsi untuk memproses
 * chart berupa daftar food yang dipilih untuk selanjutnya diorder dalam satu invoice.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */
public class ChartActivity extends AppCompatActivity implements ChartAdapter.ItemClickListener {

    SharedPreferences sharedPreferences;
    int currentUserId;
    int food_id;
    String food_name;
    int food_price;
    String food_category;
    String food_payment;
    String promo_code;
    int total_price;
    List<Food> foods;
    ChartAdapter adapter;
    RecyclerView recyclerView;
    String json_invoices;
    JSONArray jsonArray;
    Invoice invoice;
    Gson gson;
    MaterialButton btn_checkout;
    TextView tvTotalPrice;
    LinearLayout checkout_view;
    RelativeLayout checkout_box;
    MaterialButton pesan;
    MaterialButton hitung;
    TextView total;
    TextView tvCode;
    EditText etPromoCode;
    RadioGroup rgRadioGroup;
    int deliveryFee;
    ArrayList<Integer> foodIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences("Session_Key", Context.MODE_PRIVATE);
        currentUserId = sharedPreferences.getInt("currentUserId", 0);
        json_invoices = sharedPreferences.getString("food_chart", "");

        total_price = 0;
        deliveryFee = 10000;
        foods = new ArrayList<>();
        foodIdList = new ArrayList<>();
        if(!json_invoices.isEmpty()) {
            try {
                JSONArray jsonFood = new JSONArray(json_invoices);
                for (int i = 0; i < jsonFood.length(); i++) {
                    JSONObject food = jsonFood.getJSONObject(i);
                    foods.add(new Food(food.getInt("id"), food.getString
                            ("name"), null, food.getInt("price"),
                            food.getString("category")));
                    total_price = total_price + food.getInt("price");
                    foodIdList.add(food.getInt("id"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        tvTotalPrice = findViewById(R.id.totalCharPrice);
        btn_checkout = findViewById(R.id.checkout);

        tvTotalPrice.setText(String.valueOf(total_price));
        checkout_view = findViewById(R.id.llcheckout);
        checkout_view.setVisibility(View.GONE);

        checkout_box = (RelativeLayout) findViewById(R.id.checkout_box);
        pesan = findViewById(R.id.pesan);
        hitung = findViewById(R.id.hitung);
        total = findViewById(R.id.total_price);
        etPromoCode = findViewById(R.id.promo_code);
        rgRadioGroup = findViewById(R.id.radioGroup);
        tvCode = findViewById(R.id.textCode);

//        LinearLayout item = (LinearLayout)findViewById(R.id.llchart);
//        View child = getLayoutInflater().inflate(R.layout.checkout_chart, null);
//        item.addView(child);
//        TextView total = findViewById(total_price);
//        total.setVisibility(View.GONE);

//        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LinearLayout myRoot = new LinearLayout(this);
//        view = (LinearLayout) findViewById(R.id.llcheckout);
//        View itemView = inflater.inflate(R.layout.checkout_chart, myRoot);

//        view.setVisibility(View.GONE);

        recyclerView = findViewById(R.id.chartView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.divider)));
        recyclerView.addItemDecoration(itemDecorator);



//        foods = new ArrayList<>();
//        int j = 1;
//        try {
//            jsonArray = new JSONArray(json_invoices);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject inv = jsonArray.getJSONObject(i);
//                food_id = inv.getInt("food_id");
//                food_name = inv.getString("food_name");
//                food_price = inv.getInt("food_price");
//                food_category = inv.getString("food_category");
//                food_payment = inv.getString("food_payment");
//                promo_code = inv.getString("promo_code");
//                total_price = inv.getInt("total_price");
//                Food food = new Food(food_id, food_name, null, food_price, food_category);
//                foods.add(food);
//            }
//        } catch (JSONException e) {
//            e.getMessage();
//        }


        displayListView();

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout_view.setVisibility(View.VISIBLE);
                pesan.setVisibility(View.GONE);
                checkout_box.setVisibility(View.INVISIBLE);
                total.setText(String.valueOf(total_price));
                etPromoCode.setVisibility(View.INVISIBLE);
                tvCode.setVisibility(View.INVISIBLE);
            }
        });

        rgRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                pesan.setVisibility(View.INVISIBLE);
                hitung.setVisibility(View.VISIBLE);
                total.setText(String.valueOf(total_price));
                switch (checkedId) {
                    case R.id.cashless:
                        tvCode.setVisibility(View.VISIBLE);
                        etPromoCode.setVisibility(View.VISIBLE);
                        break;
                    default:
                        tvCode.setVisibility(View.INVISIBLE);
                        etPromoCode.setVisibility(View.INVISIBLE);
                }
            }
        });

        etPromoCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pesan.setVisibility(View.INVISIBLE);
                hitung.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checked = rgRadioGroup.getCheckedRadioButtonId();
                pesan.setVisibility(View.VISIBLE);
                hitung.setVisibility(View.INVISIBLE);
                switch (checked) {
                    case R.id.cash:
                        total.setText(String.valueOf(total_price+deliveryFee));
                        break;
                    case R.id.cashless:
                        String promoCode = etPromoCode.getText().toString();
                        if(!promoCode.matches("")) {
                            Response.Listener<String> responseListener = new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        if (jsonObject != null) {
                                            int promoMin = jsonObject.getInt("minPrice");
                                            boolean promoStatus = jsonObject.getBoolean("active");
                                            if(promoStatus && total_price >= promoMin) {
                                                int promoDiscount = jsonObject.getInt("discount");
                                                total.setText(String.valueOf(total_price-promoDiscount));
                                            }
                                            else {
                                                Toast.makeText(ChartActivity.this, "Promo can't be used", Toast.LENGTH_LONG).show();
                                                total.setText(String.valueOf(total_price));
                                            }
                                        }
                                    }catch (JSONException e) {
                                        Toast.makeText(ChartActivity.this, "Promo Not Found", Toast.LENGTH_LONG).show();
                                    }
                                }
                            };
                            BuatPesananRequest buatPesananRequest = new BuatPesananRequest(promoCode, responseListener);
                            RequestQueue queue = Volley.newRequestQueue(ChartActivity.this);
                            queue.add(buatPesananRequest);
                        }
                        else {
                            Toast.makeText(ChartActivity.this, "No Promo Used", Toast.LENGTH_LONG).show();
                            total.setText(String.valueOf(total_price));
                        }
                        break;
                    default:
                        Toast.makeText(ChartActivity.this, "Choose the payment method", Toast.LENGTH_LONG).show();
                }
            }
        });

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                Toast.makeText(ChartActivity.this, String.valueOf(jsonObject), Toast.LENGTH_LONG).show();
                                sharedPreferences.edit().remove("food_chart").apply();
                                Intent intent = new Intent(ChartActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }catch (JSONException e) {
                            Toast.makeText(ChartActivity.this, "Ongoing Invoice Already Exist", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                int checked = rgRadioGroup.getCheckedRadioButtonId();
                BuatPesananRequest buatPesananRequest;
                RequestQueue queue;
                switch (checked) {
                    case R.id.cash:
                        buatPesananRequest = new BuatPesananRequest(foodIdList, currentUserId, deliveryFee, responseListener);
                        queue = Volley.newRequestQueue(ChartActivity.this);
                        queue.add(buatPesananRequest);
                        Toast.makeText(ChartActivity.this, "Cash Checked", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.cashless:
                        String promoCode = etPromoCode.getText().toString();
                        buatPesananRequest = new BuatPesananRequest(foodIdList, currentUserId, promoCode, responseListener);
                        queue = Volley.newRequestQueue(ChartActivity.this);
                        queue.add(buatPesananRequest);
                        Toast.makeText(ChartActivity.this, "Cashless Checked", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(ChartActivity.this, "???", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void displayListView() {
//        List<String> countryList = new ArrayList<String>();
//        List<Invoice> invoices = new ArrayList<>();
//
//        countryList.add("Aruba");
//        countryList.add("Anguilla");
//        countryList.add("Netherlands Antilles");
//        countryList.add("Antigua and Barbuda");
//        countryList.add("Aruba");
//        countryList.add("Anguilla");
//        countryList.add("Netherlands Antilles");
//        countryList.add("Antigua and Barbuda");
//        countryList.add("Aruba");
//        countryList.add("Anguilla");
//        countryList.add("Netherlands Antilles");
//        countryList.add("Antigua and Barbuda");
//        countryList.add("Aruba");
//        countryList.add("Anguilla");
//        countryList.add("Netherlands Antilles");
//        countryList.add("Antigua and Barbuda");
//        //create an ArrayAdaptar from the String Array
//        // set up the RecyclerView
//
//        List<Food> fods = new ArrayList<>();
//        fods.add(new Food(1, "test", null, 10000, null));
        adapter = new ChartAdapter(this, foods);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
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
                                Toast.makeText(ChartActivity.this, "Action Search", Toast.LENGTH_SHORT).show();
                                Intent logout = new Intent(ChartActivity.this, LoginActivity.class);
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
                Toast.makeText(ChartActivity.this, "Chart", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChartActivity.this, ChartActivity.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You removed " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        foods.remove(position);
        sharedPreferences.edit().remove("food_chart").apply();
        sharedPreferences.edit().putString("food_chart", new Gson().toJson(foods)).apply();
        Intent intent = new Intent(ChartActivity.this, ChartActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChartActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
