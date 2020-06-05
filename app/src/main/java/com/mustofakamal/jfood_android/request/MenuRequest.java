package com.mustofakamal.jfood_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class MenuRequest extends StringRequest {
    private static final String URL = "http://192.168.43.28:8080/food/";

    public MenuRequest(Response.Listener<String> listener)
    {
        super(Method.GET, URL, listener, null);
    }
}
