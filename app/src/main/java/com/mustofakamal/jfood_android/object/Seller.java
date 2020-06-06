package com.mustofakamal.jfood_android.object;
/**
 * Class Seller adalah class yang berfungsi memproses informasi seller di jFood.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */
public class Seller {
    // instance variables - replace the example below with your own
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;

    /**
     * Constructor for objects of class Seller.
     * <p>
     * Mengupdate id, name, email, phoneNumber, dan object location
     * pada object baru seller dengan nilai yang dimasukkan.
     *
     * @param id          angka unik yang dimiliki setiap seller
     *                    sebagai identifier
     * @param name        nama dari seller
     * @param email       alamat email dari seller
     * @param phoneNumber nomor telepon yang dimiliki seller
     * @param location    lokasi dari seller yang diambil dari
     *                    salah satu object dari class locations
     * @see Location
     */
    public Seller(int id, String name, String email, String phoneNumber, Location location) {
        // initialise instance variables
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    /**
     * Mengembalikan angka unik penjual dari object Seller yang
     * bersangkutan.
     *
     * @return angka unik penjual dari object Seller
     */
    public int getId() {
        // put your code here
        return id;
    }

    /**
     * Mengupdate angka unik seller dari object Seller yang
     * bersangkutan.
     *
     * @param id angka unik seller yang akan diupdate ke
     *           object seller
     *
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Mengembalikan nama seller dari object Seller yang
     * bersangkutan.
     *
     * @return nama seller dari object Seller
     */
    public String getName() {
        return name;
    }

    /**
     * Mengupdate nama seller dari object Seller yang
     * bersangkutan.
     *
     * @param name nama seller yang akan diupdate ke object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mengembalikan email seller dari object Seller yang
     * bersangkutan.
     *
     * @return email seller dari object Seller
     */
    public String getEmail() {
        return email;
    }

    /**
     * Mengupdate email seller dari object Seller yang
     * bersangkutan.
     *
     * @param email alamat email seller yang akan diupdate ke
     *              object
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Mengembalikan nomor telepon seller dari object Seller yang
     * bersangkutan.
     *
     * @return nomor telepon seller dari object Seller
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Mengupdate nomor telepon seller dari object Seller yang
     * bersangkutan.
     *
     * @param phoneNumber alamat email seller yang akan
     *                    diupdate ke object
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Mengembalikan location seller pada Class Location dari
     * object Seller yang bersangkutan.
     *
     * @return location seller pada Class Location dari object
     * Seller
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Mengupdate location seller pada Class Location dari object
     * Seller yang bersangkutan.
     *
     * @param location location seller Class Location yang akan
     *                 diupdate ke object
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Mengembalikan beberapa informasi Seller yang bersangkutan
     * meliputi id, nama, no telepon, dan object lokasi.
     *
     * @return string informasi seller
     *
     */

    public String toString() {
        return
                "============SELLER============\n" +
                        "ID: " + id + "\n" +
                        "Name: " + name + "\n" +
                        "Seller: " + phoneNumber + "\n" +
                        "Category: " + location;
    }

}
