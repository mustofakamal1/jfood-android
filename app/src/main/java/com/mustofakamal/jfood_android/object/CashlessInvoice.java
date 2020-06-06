package com.mustofakamal.jfood_android.object;
import com.mustofakamal.jfood_android.object.type.PaymentType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class CashlessInvoice adalah class yang berfungsi memproses informasi cashless invoice di jFood.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */
public class CashlessInvoice extends Invoice {
    // instance variables - replace the example below with your own
    private static final PaymentType PAYMENT_TYPE = PaymentType.Cashless;
    private Promo promo;

    /**
     * Constructor for objects of class CashlessInvoice
     */
    public CashlessInvoice(int id, ArrayList<Food> foods, Customer customer) {
        super(id, foods, customer);
    }

    public CashlessInvoice(int id, ArrayList<Food> foods, Customer customer,
                           Promo promo) {
        super(id, foods, customer);
        this.promo = promo;
    }

    public PaymentType getPaymentType() {
        return PAYMENT_TYPE;
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }

    public void setTotalPrice() {
        for (Food foods : getFoods()) {
            if (promo != null && promo.getActive() && foods.getPrice() > promo.getMinPrice()) {
                super.totalPrice = totalPrice + foods.getPrice() - promo.getDiscount();
            } else {
                super.totalPrice = totalPrice + foods.getPrice();
            }
        }
    }

    public void setTotalPrice(int totalPrice) {
        super.totalPrice = totalPrice;
    }

    public String toString() {
        for (Food foods : getFoods()) {
            if (!(getPromo() == null || !promo.getActive() || foods.getPrice() < promo.getMinPrice())) {
                Date date = getDate().getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMMMM yyyy");
                String formatted = formatter.format(date);
                return
                        "============INVOICE============\n" +
                                "ID: " + getId() + "\n" +
                                "Food: " + foods.getName() + "\n" +
                                "Date:" + formatted + "\n" +
                                "Customer :" + getCustomer().getName() + "\n" +
                                "Promo: " + promo.getCode() + "\n" +
                                "Total Price: " + totalPrice + "\n" +
                                "PaymentType: " + getPaymentType();

            } else {
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
        }
        return "";
    }
}