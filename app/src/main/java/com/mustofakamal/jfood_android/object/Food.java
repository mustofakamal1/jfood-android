package com.mustofakamal.jfood_android.object;

import com.mustofakamal.jfood_android.object.type.FoodCategory;


/**
 * Seller adalah class yang berfungsi memproses informasi makanan
 * di jFood.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */
public class Food {
    // instance variables - replace the example below with your own
    private int id;
    private String name;
    private Seller seller;
    private int price;
    private String category;

    /**
     * Constructor for objects of class Food.
     * <p>
     * Mengupdate id, name, seller, price, dan category
     * pada object baru seller dengan nilai yang dimasukkan.
     *
     * @param id       angka unik yang dimiliki setiap makanan
     *                 sebagai identifier
     * @param name     nama dari makanan
     * @param seller   penjual makanan yang diambil dari salah
     *                 satu object dari class Seller
     * @param price    harga makanan
     * @param category kategori atau jenis makanan
     */
    public Food(int id, String name, Seller seller, int price, String category) {
        this.id = id;
        this.name = name;
        this.seller = seller;
        this.price = price;
        this.category = category;
    }

    /**
     * Mengembalikan angka unik dari object Food yang
     * bersangkutan.
     *
     * @return angka unik dari object Food
     */
    public int getId() {
        // put your code here
        return id;
    }

    /**
     * Mengupdate id dari object Food yang
     * bersangkutan.
     *
     * @param id makanan yang akan diupdate ke object
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Mengembalikan nama dari object Food yang
     * bersangkutan.
     *
     * @return nama dari object Food
     */
    public String getName() {
        return name;
    }

    /**
     * Mengupdate nama dari object Food yang
     * bersangkutan.
     *
     * @param name nama makanan yang akan diupdate ke object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mengembalikan seller pada Class Seller dari object Food
     * yang bersangkutan.
     *
     * @return seller pada class Seller dari object Food
     */
    public Seller getSeller() {
        return seller;
    }

    /**
     * Mengupdate seller pada Class Seller dari object Food
     * yang bersangkutan.
     *
     * @param seller pada Class Seller yang akan diupdate ke
     *               object
     */
    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    /**
     * Mengembalikan harga dari object Food yang
     * bersangkutan.
     *
     * @return harga dari object Food
     */
    public int getPrice() {
        return price;
    }

    /**
     * Mengupdate harga dari object Food yang
     * bersangkutan.
     *
     * @param price harga makanan yang akan diupdate ke object
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Mengembalikan kategori dari object Food yang
     * bersangkutan.
     *
     * @return kategori dari object Food
     */
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String toString() {
        return
                "============FOOD============\n" +
                        "ID: " + id + "\n" +
                        "Name: " + name + "\n" +
                        "Seller: " + seller.getName() + "\n" +
                        "City: " + seller.getLocation().getCity() + "\n" +
                        "Price: " + price + "\n" +
                        "Category: " + category;
    }

}
