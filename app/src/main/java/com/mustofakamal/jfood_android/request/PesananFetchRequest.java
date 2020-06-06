package com.mustofakamal.jfood_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class PesananFetchRequest adalah class yang berfungsi untuk membuat
 * rest requset ke rest server untuk menampilkan pesanan yang sedang berjalan.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */
public class PesananFetchRequest extends StringRequest {
    private static final String URL_FETCH_INVOICE = "http://192.168.43.28:8080/invoice/customer/";
    private Map<String, String> params;

    public PesananFetchRequest(int customerId, Response.Listener<String> listener)
    {
        super(Method.GET, URL_FETCH_INVOICE + customerId, listener, null);
        params = new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams()
    {
        return params;
    }
}
