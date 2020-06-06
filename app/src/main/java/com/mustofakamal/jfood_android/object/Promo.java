package com.mustofakamal.jfood_android.object;
/**
 * Write a description of class Promo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Promo {
    // instance variables - replace the example below with your own
    private int id;
    private String code;
    private int discount;
    private int minPrice;
    private boolean active;

    /**
     * Constructor for objects of class Promo
     */
    public Promo(int id, String code, int discount, int minPrice, boolean
            active) {
        // initialise instance variables
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.minPrice = minPrice;
        this.active = active;
    }

    public int getId() {
        // put your code here
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        // put your code here
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDiscount() {
        // put your code here
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getMinPrice() {
        // put your code here
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public boolean getActive() {
        // put your code here
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String toString() {
        return
                "============PROMO============\n" +
                        "ID: " + id + "\n" +
                        "Code: " + code + "\n" +
                        "Discount: " + discount + "\n" +
                        "MinPrice: " + minPrice + "\n" +
                        "Active Status: " + active;
    }

}
