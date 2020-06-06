package com.mustofakamal.jfood_android.object.type;
/**
 * Enumeration class PaymentType adalah enum class yang berfungsi membuat tipe payment di jFood.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */
public enum PaymentType {
    Cashless("Cashless"), Cash("Cash");

    private String type;

    PaymentType(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }
}
