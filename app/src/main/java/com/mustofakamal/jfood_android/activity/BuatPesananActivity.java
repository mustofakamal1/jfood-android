package com.mustofakamal.jfood_android.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mustofakamal.jfood_android.object.Food;
import com.mustofakamal.jfood_android.object.Location;
import com.mustofakamal.jfood_android.object.Seller;
import com.mustofakamal.jfood_android.request.BuatPesananRequest;
import com.mustofakamal.jfood_android.R;

import org.json.JSONArray;
import org.json.JSONException;
        import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BuatPesananActivity extends AppCompatActivity {
    private int currentUserId;
    private int id_food;
    private String foodName;
    private String foodCategory;
    private double foodPrice;
    private String promoCode;
    private String selectedPayment;
    private int deliveryFee;
    ArrayList<Integer> foodIdList = new ArrayList<>();
    SharedPreferences sharedPreferences;
    Gson gson;
    StringBuilder str;
    String food_json;
    Food food;
    List<Food> foods;

//    private TextView tvCode;
//    private TextView tvFoodName;
//    private TextView tvFoodCategory;
//    private TextView tvFoodPrice;
//    private TextView tvTotalPrice;
//    private Button btnPesan;
//    private Button btnHitung;
//    private TextView tvPesanan;
//    private EditText etPromoCode;
//    private RadioGroup rgRadioGroup;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences("Session_Key", Context.MODE_PRIVATE);
        currentUserId = sharedPreferences.getInt("currentUserId", 0);
        food_json = getIntent().getStringExtra("food_json");
        gson = new Gson();
        food = gson.fromJson(food_json, Food.class);
        //        Bundle data = getIntent().getExtras();
//        id_food = data.getInt("foodId", 0);
//        foodName = data.getString("foodName");
//        foodCategory = data.getString("foodCategory");
//        foodPrice = data.getInt("foodPrice");
        id_food = food.getId();
        foodName = food.getName();
        foodCategory = food.getCategory();
        foodPrice = food.getPrice();

        final TextView tvCode = findViewById(R.id.textCode);
        TextView tvFoodName = findViewById(R.id.food_name);
        TextView tvFoodCategory = findViewById(R.id.food_category);
        TextView tvFoodPrice = findViewById(R.id.food_price);
        final TextView tvTotalPrice = findViewById(R.id.total_price);
        final Button btnPesan = findViewById(R.id.pesan);
        final Button btnHitung = findViewById(R.id.hitung);
        final Button btnChart = findViewById(R.id.addToChart);
        TextView tvPesanan = findViewById(R.id.pesanan);
        final EditText etPromoCode = findViewById(R.id.promo_code);
        final RadioGroup rgRadioGroup = findViewById(R.id.radioGroup);

        btnPesan.setVisibility(View.INVISIBLE);
        btnChart.setVisibility(View.INVISIBLE);
        tvCode.setVisibility(View.INVISIBLE);
        etPromoCode.setVisibility(View.INVISIBLE);

        tvFoodName.setText(foodName);
        tvFoodCategory.setText(foodCategory);
        tvFoodPrice.setText(Double.toString(foodPrice));
        tvTotalPrice.setText("0");

        deliveryFee = 10000;

        rgRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                btnChart.setVisibility(View.INVISIBLE);
                btnPesan.setVisibility(View.INVISIBLE);
                btnHitung.setVisibility(View.VISIBLE);
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
                btnPesan.setVisibility(View.INVISIBLE);
                btnChart.setVisibility(View.INVISIBLE);
                btnHitung.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checked = rgRadioGroup.getCheckedRadioButtonId();
                btnPesan.setVisibility(View.VISIBLE);
                btnChart.setVisibility(View.VISIBLE);
                btnHitung.setVisibility(View.INVISIBLE);
                switch (checked) {
                    case R.id.cash:
                        tvTotalPrice.setText(Double.toString(foodPrice+deliveryFee));
                        break;
                    case R.id.cashless:
                        promoCode = etPromoCode.getText().toString();
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
                                            if(promoStatus && foodPrice >= promoMin) {
                                                int promoDiscount = jsonObject.getInt("discount");
                                                tvTotalPrice.setText(Double.toString(foodPrice-promoDiscount));
                                            }
                                            else {
                                                Toast.makeText(BuatPesananActivity.this, "Promo can't be used", Toast.LENGTH_LONG).show();
                                                tvTotalPrice.setText(Double.toString(foodPrice));
                                            }
                                        }
                                    }catch (JSONException e) {
                                        Toast.makeText(BuatPesananActivity.this, "Promo Not Found", Toast.LENGTH_LONG).show();
                                    }
                                }
                            };
                            BuatPesananRequest buatPesananRequest = new BuatPesananRequest(promoCode, responseListener);
                            RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                            queue.add(buatPesananRequest);
                        }
                        else {
                            Toast.makeText(BuatPesananActivity.this, "No Promo Used", Toast.LENGTH_LONG).show();
                            tvTotalPrice.setText(Double.toString(foodPrice));
                        }
                        break;
                    default:
                        Toast.makeText(BuatPesananActivity.this, "Choose the payment method", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                Toast.makeText(BuatPesananActivity.this, "Invoice Created Succesfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(BuatPesananActivity.this, MainActivity.class);
                                intent.putExtra("currentUserId", currentUserId);
                                startActivity(intent);
                            }
                        }catch (JSONException e) {
                            Toast.makeText(BuatPesananActivity.this, "Ongoing Invoice Already Exist", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                int checked = rgRadioGroup.getCheckedRadioButtonId();
                foodIdList.add(id_food);
                BuatPesananRequest buatPesananRequest;
                RequestQueue queue;
                switch (checked) {
                    case R.id.cash:
                        buatPesananRequest = new BuatPesananRequest(foodIdList, currentUserId, deliveryFee, responseListener);
                        queue = Volley.newRequestQueue(BuatPesananActivity.this);
                        queue.add(buatPesananRequest);
                        Toast.makeText(BuatPesananActivity.this, "Cash Checked", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.cashless:
                        promoCode = etPromoCode.getText().toString();
                        buatPesananRequest = new BuatPesananRequest(foodIdList, currentUserId, promoCode, responseListener);
                        queue = Volley.newRequestQueue(BuatPesananActivity.this);
                        queue.add(buatPesananRequest);
                        Toast.makeText(BuatPesananActivity.this, "Cashless Checked", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(BuatPesananActivity.this, "???", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checked = rgRadioGroup.getCheckedRadioButtonId();
                switch (checked) {
                    case R.id.cash:
                        selectedPayment = "Cash";
                        Toast.makeText(BuatPesananActivity.this, "Cash Checked", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.cashless:
                        selectedPayment = "Cashless";
                        Toast.makeText(BuatPesananActivity.this, "Cashless Checked", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(BuatPesananActivity.this, "???", Toast.LENGTH_LONG).show();
                }
//                str = new StringBuilder();
                String json_invoices = sharedPreferences.getString("food_chart", "");
//                str.append(json_invoices);
//                if(!json_invoices.isEmpty()) {
//                    str.setLength(str.length() - 1);
//                    str.append(",");
//                }
//                else {
//                    str.append("[");
//                }
//                str.append("{'food_id").append("' : '").append(id_food).append("',");
//                str.append("'food_name").append("' : '").append(foodName).append("',");
//                str.append("'food_price").append("' : '").append(foodPrice).append("',");
//                str.append("'food_category").append("' : '").append(foodCategory).append("',");
//                str.append("'food_payment").append("' : '").append(selectedPayment).append("',");
//                str.append("'promo_code").append("' : '").append(promoCode).append("',");
//                str.append("'total_price").append("' : '").append(tvTotalPrice.getText().toString()).append("'}]");
                List<Food> food_list = new ArrayList<>();
                if(!json_invoices.isEmpty()) {
                    food_list = gson.fromJson(json_invoices, new TypeToken<List<Food>>(){}.getType());
//                    try {
//                        JSONArray jsonFood = new JSONArray(json_invoices);
//                        for (int i = 0; i < jsonFood.length(); i++) {
//                            JSONObject food = jsonFood.getJSONObject(i);
//                            JSONObject seller = food.getJSONObject("seller");
//                            JSONObject location = seller.getJSONObject("location");
////                            for(int j = 0; j < seller.length(); j++) {
//                            Seller s = new Seller(seller.getInt("id"),
//                                    seller.getString("name"), seller.getString("email"),
//                                    seller.getString("phoneNumber"), new Location
//                                    (location.getString("city"), location.getString
//                                            ("province"), location.getString("description"))
//                            );
//                            foods.add(new Food(food.getInt("id"), food.getString
//                                    ("name"), s, food.getInt("price"),
//                                    food.getString("category")));
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                }
                food_list.add(food);
                sharedPreferences.edit().remove("food_chart").apply();
                sharedPreferences.edit().putString("food_chart", new Gson().toJson(food_list)).apply();
                Intent addChart = new Intent(BuatPesananActivity.this, ChartActivity.class);
                Toast.makeText(BuatPesananActivity.this, json_invoices, Toast.LENGTH_LONG).show();
                startActivity(addChart);
            }
        });
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
                                Toast.makeText(BuatPesananActivity.this, "Action Search", Toast.LENGTH_SHORT).show();
                                Intent logout = new Intent(BuatPesananActivity.this, LoginActivity.class);
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
                Toast.makeText(BuatPesananActivity.this, "Chart", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BuatPesananActivity.this, ChartActivity.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent( BuatPesananActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
