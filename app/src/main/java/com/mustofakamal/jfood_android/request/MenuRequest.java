package com.mustofakamal.jfood_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Class MenuRequest adalah class yang berfungsi untuk membuat
 * rest requset ke rest server untuk proses data daftar makanan.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */
public class MenuRequest extends StringRequest {
    private static final String URL = "http://192.168.43.28:8080/food/";

    public MenuRequest(Response.Listener<String> listener)
    {
        super(Method.GET, URL, listener, null);
    }
}
