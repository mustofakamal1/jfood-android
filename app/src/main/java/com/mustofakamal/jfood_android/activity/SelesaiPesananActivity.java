package com.mustofakamal.jfood_android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mustofakamal.jfood_android.R;
import com.mustofakamal.jfood_android.request.PesananBatalRequest;
import com.mustofakamal.jfood_android.request.PesananFetchRequest;
import com.mustofakamal.jfood_android.request.PesananSelesaiRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelesaiPesananActivity extends AppCompatActivity {
    int currentUserId;
    SharedPreferences sharedPreferences;
    String foodName;
    int totalPrice;
    int invoiceId;
    String orderDate;
    String invoiceStatus;
    String paymentType;
    String customerName;

    Button btnBatal;
    Button btnSelesai;
    TextView tvInvoiceDetails;
    TextView tvInvoiceId;
    TextView tvInvoiceStatus;
    TextView tvCustomerName;
    TextView tvFoodName;
    TextView tvOrderDate;
    TextView tvTotalPrice;
    TextView tvInvoiceIdView;
    TextView tvInvoiceStatusView;
    TextView tvCustomerNameView;
    TextView tvFoodNameView;
    TextView tvOrderDateView;
    TextView tvTotalPriceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_pesanan);

        sharedPreferences = getSharedPreferences("Session_Key", Context.MODE_PRIVATE);
        currentUserId = sharedPreferences.getInt("currentUserId", 0);

        btnBatal = findViewById(R.id.batal);
        btnSelesai = findViewById(R.id.selesai);
        tvInvoiceDetails = findViewById(R.id.invoiceDetails);
        tvInvoiceId = findViewById(R.id.invoiceId);
        tvInvoiceStatus = findViewById(R.id.invoiceStatus);
        tvCustomerName = findViewById(R.id.customerName);
        tvFoodName = findViewById(R.id.foodName);
        tvOrderDate = findViewById(R.id.orderDate);
        tvTotalPrice = findViewById(R.id.totalPrice);
        tvInvoiceIdView = findViewById(R.id.invoiceIdView);
        tvInvoiceStatusView = findViewById(R.id.invoiceStatusView);
        tvCustomerNameView = findViewById(R.id.customerNameView);
        tvFoodNameView = findViewById(R.id.foodNameView);
        tvOrderDateView = findViewById(R.id.orderDateView);
        tvTotalPriceView = findViewById(R.id.totalPriceView);
        btnBatal.setVisibility(View.INVISIBLE);
        btnSelesai.setVisibility(View.INVISIBLE);
        tvInvoiceDetails.setVisibility(View.INVISIBLE);
        tvInvoiceId.setVisibility(View.INVISIBLE);
        tvInvoiceStatus.setVisibility(View.INVISIBLE);
        tvCustomerName.setVisibility(View.INVISIBLE);
        tvFoodName.setVisibility(View.INVISIBLE);
        tvOrderDate.setVisibility(View.INVISIBLE);
        tvTotalPrice.setVisibility(View.INVISIBLE);
        tvInvoiceIdView.setVisibility(View.INVISIBLE);
        tvInvoiceStatusView.setVisibility(View.INVISIBLE);
        tvCustomerNameView.setVisibility(View.INVISIBLE);
        tvFoodNameView.setVisibility(View.INVISIBLE);
        tvOrderDateView.setVisibility(View.INVISIBLE);
        tvTotalPriceView.setVisibility(View.INVISIBLE);

        fetchPesanan();

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                fetchPesanan();
                                Toast.makeText(SelesaiPesananActivity.this, "Cancel Successful", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(SelesaiPesananActivity.this, "Cancel Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                int invoiceId = Integer.parseInt(tvInvoiceIdView.getText().toString());
                PesananBatalRequest pesananBatalRequest = new PesananBatalRequest(invoiceId, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(pesananBatalRequest);
            }
        });

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                fetchPesanan();
                                Toast.makeText(SelesaiPesananActivity.this, "Finish Successful", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(SelesaiPesananActivity.this, "Finish Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                int invoiceId = Integer.parseInt(tvInvoiceIdView.getText().toString());
                PesananSelesaiRequest pesananSelesaiRequest = new PesananSelesaiRequest(invoiceId, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(pesananSelesaiRequest);
            }
        });
    }
    protected void fetchPesanan() {
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    if (jsonResponse != null) {
                        for (int i = 0; i < jsonResponse.length(); i++) {
                            JSONObject invoice = jsonResponse.getJSONObject(i);
                            JSONArray foods = invoice.getJSONArray("foods");
                            totalPrice = invoice.getInt("totalPrice");
                            invoiceId = invoice.getInt("id");
                            orderDate = invoice.getString("date");
                            invoiceStatus = invoice.getString("invoiceStatus");
                            paymentType = invoice.getString("paymentType");
                            JSONObject customer = invoice.getJSONObject("customer");
                            customerName = customer.getString("name");
                            for (i = 0; i < foods.length(); i++) {
                                JSONObject food = foods.getJSONObject(i);
                                foodName = food.getString("name");
                            }
                        }
                        orderDate = orderDate.substring(0,10);
                        Toast.makeText(SelesaiPesananActivity.this, "Fetch Successful", Toast.LENGTH_LONG).show();
                        tvTotalPriceView.setText(String.valueOf(totalPrice));
                        tvInvoiceIdView.setText(String.valueOf(invoiceId));
                        tvOrderDateView.setText(orderDate);
                        tvInvoiceStatusView.setText(invoiceStatus);
                        tvCustomerNameView.setText(customerName);
                        tvFoodNameView.setText(foodName);

                        btnBatal.setVisibility(View.VISIBLE);
                        btnSelesai.setVisibility(View.VISIBLE);
                        tvInvoiceDetails.setVisibility(View.VISIBLE);
                        tvInvoiceId.setVisibility(View.VISIBLE);
                        tvInvoiceStatus.setVisibility(View.VISIBLE);
                        tvCustomerName.setVisibility(View.VISIBLE);
                        tvFoodName.setVisibility(View.VISIBLE);
                        tvOrderDate.setVisibility(View.VISIBLE);
                        tvTotalPrice.setVisibility(View.VISIBLE);
                        tvInvoiceIdView.setVisibility(View.VISIBLE);
                        tvInvoiceStatusView.setVisibility(View.VISIBLE);
                        tvCustomerNameView.setVisibility(View.VISIBLE);
                        tvFoodNameView.setVisibility(View.VISIBLE);
                        tvOrderDateView.setVisibility(View.VISIBLE);
                        tvTotalPriceView.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    Toast.makeText( SelesaiPesananActivity.this, String.valueOf(currentUserId), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        PesananFetchRequest pesananFetchRequest = new PesananFetchRequest(currentUserId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
        queue.add(pesananFetchRequest);
    }
}
