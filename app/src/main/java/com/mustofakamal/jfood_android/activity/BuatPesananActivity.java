package com.mustofakamal.jfood_android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.toolbox.Volley;
import com.mustofakamal.jfood_android.request.BuatPesananRequest;
import com.mustofakamal.jfood_android.R;

import org.json.JSONException;
        import org.json.JSONObject;

import java.util.ArrayList;

public class BuatPesananActivity extends AppCompatActivity {
    private int currentUserId;
    private int id_food;
    private String foodName;
    private String foodCategory;
    private double foodPrice;
    private String promoCode;
    private String selectedPayment;
    SharedPreferences sharedPreferences;

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
        Bundle data = getIntent().getExtras();
        id_food = data.getInt("foodId");;
        foodName = data.getString("foodName");;
        foodCategory = data.getString("foodCategory");;
        foodPrice = data.getInt("foodPrice");;

        final TextView tvCode = findViewById(R.id.textCode);
        TextView tvFoodName = findViewById(R.id.food_name);
        TextView tvFoodCategory = findViewById(R.id.food_category);
        TextView tvFoodPrice = findViewById(R.id.food_price);
        final TextView tvTotalPrice = findViewById(R.id.total_price);
        final Button btnPesan = findViewById(R.id.pesan);
        final Button btnHitung = findViewById(R.id.hitung);
        TextView tvPesanan = findViewById(R.id.pesanan);
        final EditText etPromoCode = findViewById(R.id.promo_code);
        final RadioGroup rgRadioGroup = findViewById(R.id.radioGroup);

        btnPesan.setVisibility(View.INVISIBLE);
        tvCode.setVisibility(View.INVISIBLE);
        etPromoCode.setVisibility(View.INVISIBLE);

        tvFoodName.setText(foodName);
        tvFoodCategory.setText(foodCategory);
        tvFoodPrice.setText(Double.toString(foodPrice));
        tvTotalPrice.setText("0");

        rgRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
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

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checked = rgRadioGroup.getCheckedRadioButtonId();

                switch (checked) {
                    case R.id.cash:
                        tvTotalPrice.setText(Double.toString(foodPrice));
                        btnHitung.setVisibility(View.INVISIBLE);
                        btnPesan.setVisibility(View.VISIBLE);
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
                        btnHitung.setVisibility(View.GONE);
                        btnPesan.setVisibility(View.VISIBLE);
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
                ArrayList<Integer> foodIdList = new ArrayList<>();
                foodIdList.add(id_food);
                BuatPesananRequest buatPesananRequest;
                RequestQueue queue;
                switch (checked) {
                    case R.id.cash:
                        buatPesananRequest = new BuatPesananRequest(foodIdList, currentUserId, 10000, responseListener);
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
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
