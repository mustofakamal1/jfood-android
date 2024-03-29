package com.mustofakamal.jfood_android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mustofakamal.jfood_android.R;
import com.mustofakamal.jfood_android.request.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class RegisterActivity adalah class yang berfungsi untuk memproses
 * proses registrasi user baru.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputLayout tvName = findViewById(R.id.name_text_input);
        TextInputLayout tvEmail = findViewById(R.id.email_text_input);
        TextInputLayout tvPassword = findViewById(R.id.password_text_input);
        final TextInputEditText etName = findViewById(R.id.name_edit_text);
        final TextInputEditText etEmail = findViewById(R.id.email_edit_text);
        final TextInputEditText etPassword = findViewById(R.id.password_edit_text);
        Button btnRegister = findViewById(R.id.login_button);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }catch (JSONException e) {
                            Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        }
        );
    }
}