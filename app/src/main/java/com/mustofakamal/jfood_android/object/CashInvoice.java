package com.mustofakamal.jfood_android.object;

import com.mustofakamal.jfood_android.object.type.PaymentType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Write a description of class CashInvoice here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CashInvoice extends Invoice {
    // instance variables - replace the example below with your own
    private static final PaymentType PAYMENT_TYPE = PaymentType.Cash;
    private int deliveryFee;

    /**
     * Constructor for objects of class CashInvoice
     */
    public CashInvoice(int id, ArrayList<Food> foods, Customer customer) {
        super(id, foods, customer);
    }

    public CashInvoice(int id, ArrayList<Food> foods, Customer customer,
                       int deliveryFee) {
        super(id, foods, customer);
        this.deliveryFee = deliveryFee;
    }

    public PaymentType getPaymentType() {
        return PAYMENT_TYPE;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public void setTotalPrice() {
        for (Food foods : getFoods()) {
            if (deliveryFee > 0) {
                super.totalPrice = foods.getPrice() + deliveryFee;
            } else {
                super.totalPrice = foods.getPrice();
            }
        }
    }

    public void setTotalPrice(int totalPrice) {
        super.totalPrice = totalPrice;
    }

    public String toString() {
        for (Food foods : getFoods()) {
            Date date = getDate().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMMMM yyyy");
            String formatted = formatter.format(date);
            return
                    "============INVOICE============\n" +
                            "ID: " + getId() + "\n" +
                            "Food: " + foods.getName() + "\n" +
                            "Date:" + formatted + "\n" +
                            "Customer :" + getCustomer().getName() + "\n" +
                            "Total Price: " + totalPrice + "\n" +
                            "PaymentType: " + getPaymentType();
        }
        return null;
    }
}
