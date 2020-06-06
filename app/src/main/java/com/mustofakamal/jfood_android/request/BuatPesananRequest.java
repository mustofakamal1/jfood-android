package com.mustofakamal.jfood_android.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class BuatPesananRequest adalah class yang berfungsi untuk membuat
 * rest requset ke rest server untuk proses data yang dibutuhkan untuk pembuatan pesanan.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */
public class BuatPesananRequest extends StringRequest {
    private static final String URL_PROMO = "http://192.168.43.28:8080/promo/";
    private static final String URL_CASH = "http://192.168.43.28:8080/invoice/createCashInvoice";
    private static final String URL_CASHLESS = "http://192.168.43.28:8080/invoice/createCashlessInvoice";
    private Map<String, String> params;

    public BuatPesananRequest(String code, Response.Listener<String> listener)
    {
        super(Method.GET, URL_PROMO + code, listener, null);
        params = new HashMap<>();
    }

    public BuatPesananRequest(ArrayList<Integer> foodIdList, int customerId, int deliveryFee, Response.Listener<String> listener)
    {
        super(Method.POST, URL_CASH, listener, null);
        params = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < foodIdList.size(); i++) {
            int id = foodIdList.get(i);
            sb.append(id).append(",");
        }
        sb.setLength(sb.length()-1);
        String idList = sb.toString();

        params.put("foodIdList", idList);
        params.put("customerId", String.valueOf(customerId));
        params.put("deliveryFee", String.valueOf(deliveryFee));
    }

    public BuatPesananRequest(ArrayList<Integer> foodIdList, int customerId, String promoCode, Response.Listener<String> listener)
    {
        super(Method.POST, URL_CASHLESS, listener, null);
        params = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < foodIdList.size(); i++) {
            int id = foodIdList.get(i);
            sb.append(id).append(",");
        }
        sb.setLength(sb.length()-1);
        String idList = sb.toString();

        params.put("foodIdList", idList);
        params.put("customerId", String.valueOf(customerId));
        params.put("promoCode", String.valueOf(promoCode));
    }

    @Override
    protected Map<String, String> getParams()
    {
        return params;
    }
}
