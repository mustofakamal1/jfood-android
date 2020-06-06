package com.mustofakamal.jfood_android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mustofakamal.jfood_android.request.LoginRequest;
import com.mustofakamal.jfood_android.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("Session_Key", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("login")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        final TextInputEditText etEmail = findViewById(R.id.email_edit_text);
        final TextInputEditText etPassword = findViewById(R.id.password_edit_text);
        TextInputLayout tvEmail = findViewById(R.id.email_text_input);
        TextInputLayout tvPassword = findViewById(R.id.password_text_input);
        MaterialButton btnLogin = findViewById(R.id.login_button);
        MaterialButton btnRegister = findViewById(R.id.register_button);
//        TextView tvRegister = findViewById(R.id.RegisterNow);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                int currentUserId = jsonObject.getInt("id");
                                String name = jsonObject.getString("name");
                                String email = jsonObject.getString("email");
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("currentUserId", currentUserId);
                                editor.putString("name", name);
                                editor.putString("email", email);
                                editor.putBoolean("login", true);
                                editor.apply();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }catch (JSONException e) {
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("login", false);
                            editor.apply();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        }
        );

        btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            }
        );
    }
}
