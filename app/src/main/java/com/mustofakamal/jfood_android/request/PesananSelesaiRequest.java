package com.mustofakamal.jfood_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PesananSelesaiRequest extends StringRequest {
    private static final String URL = "http://192.168.43.28:8080/invoice/invoiceStatus/";
    private Map<String, String> params;

    public PesananSelesaiRequest(int invoiceId, Response.Listener<String> listener)
    {
        super(Method.PUT, URL + invoiceId, listener, null);
        params = new HashMap<>();
        params.put("invoiceStatus", "Finished");
    }

    @Override
    protected Map<String, String> getParams()
    {
        return params;
    }
}
