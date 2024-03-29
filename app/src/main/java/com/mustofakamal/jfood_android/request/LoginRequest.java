package com.mustofakamal.jfood_android.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class LoginRequest adalah class yang berfungsi untuk membuat
 * rest requset ke rest server untuk proses login user.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */
public class LoginRequest extends StringRequest {
    private static final String URL = "http://192.168.43.28:8080/customer/login";
    private Map<String, String> params;

    public LoginRequest(String email, String password, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError
    {
        return params;
    }
}
