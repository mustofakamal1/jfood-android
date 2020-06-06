package com.mustofakamal.jfood_android.object;
/**
 * Class Promo adalah class yang berfungsi memproses informasi promo di jFood.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */

public class Promo {
    // instance variables - replace the example below with your own
    private int id;
    private String code;
    private int discount;
    private int minPrice;
    private boolean active;

    /**
     * Constructor for objects of class Promo.
     * <p>
     * Mengupdate id, code, discount, minPrice, dan active
     * pada object baru promo dengan nilai yang dimasukkan.
     *
     * @param id        angka unik yang dimiliki setiap seller
     *                  sebagai identifier
     * @param code      kode dari promo
     * @param discount  potongan harga yang diberikan
     * @param minPrice  harga minimal makanan agar promo berlaku
     * @param active    status berlaku promo
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

    /**
     * Mengembalikan angka unik (id) objek promo
     *
     * @return angka unik (id) identitas object promo
     */
    public int getId() {
        // put your code here
        return id;
    }

    /**
     * Mengupdate location seller pada Class Location dari object
     * Seller yang bersangkutan.
     *
     * @param id    angka unik identitas object promo
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Mengembalikan kode unik promo.
     *
     * @return kode unik promo
     */
    public String getCode() {
        // put your code here
        return code;
    }

    /**
     * Mengupdate kode unik promo
     *
     * @param code    kode unik promo
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Mengembalikan nilai potongan harga objek promo.
     *
     * @return nilai potongan harga dari promo
     */
    public int getDiscount() {
        // put your code here
        return discount;
    }

    /**
     * Mengupdate nilai potongan harga objek promo.
     *
     * @param discount  nilai potongan harga dari promo
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * Mengembalikan nilai harga minimal untuk bisa menggunakan promo.
     *
     * @return harga minimal promo
     */
    public int getMinPrice() {
        // put your code here
        return minPrice;
    }

    /**
     * Mengupdate harga minimal promo
     *
     * @param minPrice    harga minimal untuk bisa menggunakan promo.
     */
    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * Mengembalikan status berlaku promo
     *
     * @return boolean status promo
     */
    public boolean getActive() {
        // put your code here
        return active;
    }

    /**
     * Mengupdate location seller pada Class Location dari object
     * Seller yang bersangkutan.
     *
     * @param active    status berlaku promo
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Mengembalikan beberapa informasi Promo yang bersangkutan
     * meliputi id, code, discount, minPrice dan active.
     *
     * @return string informasi promo
     */
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
