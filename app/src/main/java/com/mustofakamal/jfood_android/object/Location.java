package com.mustofakamal.jfood_android.object;

public class Location {
    private String province;
    private String description;
    private String city;

    public Location(String city, String province, String description) {
        // initialise instance variables
        this.city = city;
        this.province = province;
        this.description = description;
    }

    public String getCity() {
        // put your code here
        return city;
    }

    public String getProvince() {
        // put your code here
        return province;
    }

    public String getDescription() {
        // put your code here
        return description;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
