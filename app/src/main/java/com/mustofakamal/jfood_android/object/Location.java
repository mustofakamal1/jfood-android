package com.mustofakamal.jfood_android.object;
/**
 * Seller adalah class yang berfungsi memproses informasi location di jFood.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */
public class Location {
    // instance variables - replace the example below with your own
    private int id;
    private String province;
    private String description;
    private String city;

    /**
     * Constructor for objects of class Location.
     * <p>
     * Mengupdate id, city, province, dan description
     * pada object baru Location dengan nilai yang dimasukkan.
     *
     * @param id            angka unik yang dimiliki setiap seller
     *                      sebagai identifier
     * @param city          nama dari kota
     * @param province      nama dari provinsi
     * @param description   deskripsi mengenai lokasi
     */
    public Location(int id, String city, String province, String description) {
        // initialise instance variables
        this.id = id;
        this.city = city;
        this.province = province;
        this.description = description;
    }

    public Location(String city, String province, String description) {
        // initialise instance variables
        this.city = city;
        this.province = province;
        this.description = description;
    }

    /**
     * Mengembalikan angka unik (id) objek Location
     *
     * @return angka unik (id) identitas object location
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Mengembalikan provinsi dari object Location yang
     * bersangkutan.
     *
     * @return provinsi dari object Location
     */
    public String getProvince() {
        // put your code here
        return province;
    }

    /**
     * Mengupdate provinsi dari object Location yang
     * bersangkutan.
     *
     * @param province provinsi yang akan diupdate ke object
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Mengembalikan kota dari object Location yang bersangkutan.
     *
     * @return provinsi dari object Location
     */
    public String getCity() {
        // put your code here
        return city;
    }

    /**
     * Mengupdate kota dari object Location yang bersangkutan.
     *
     * @param city kota yang akan diupdate ke object
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Mengembalikan deskripsi dari object Location yang
     * bersangkutan.
     *
     * @return deskripsi dari object Location
     */
    public String getDescription() {
        // put your code here
        return description;
    }

    /**
     * Mengupdate deskripsi dari object Location yang
     * bersangkutan.
     *
     * @param description deskripsi yang akan diupdate ke
     *                    object
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Mengembalikan beberapa informasi Location yang bersangkutan
     * meliputi id, province, city, dan description.
     *
     * @return string informasi promo
     *
     */
    public String toString() {
        return
                "===========LOCATION============\n" +
                        "id : " + id + "\n" +
                        "Province: " + province + "\n" +
                        "City: " + city + "\n" +
                        "Description: " + description;
    }

}
