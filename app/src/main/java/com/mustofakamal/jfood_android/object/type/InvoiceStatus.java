package com.mustofakamal.jfood_android.object.type;
/**
 * Enumeration class InvoiceStatus adalah enum class yang berfungsi membuat tipe status invoice di jFood.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */
public enum InvoiceStatus {
    Ongoing("Ongoing"), Finished("Finished"), Cancelled("Cancelled");

    private String status;

    InvoiceStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return status;
    }

}
