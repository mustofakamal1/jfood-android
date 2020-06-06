package com.mustofakamal.jfood_android.object.type;
/**
 * Enumeration class FoodCategory adalah enum class yang berfungsi membuat kategori food di jFood.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */
public enum FoodCategory {
    Beverages("Beverages"), Coffee("Coffee"), Western("Western"),
    Snacks("Snacks"), Rice("Rice"), Noodles("Noodles"),
    Bakery("Backery"), Japanese("Japanese");

    private String category;

    FoodCategory(String category) {
        this.category = category;
    }

    public String toString() {
        return category;
    }

}
