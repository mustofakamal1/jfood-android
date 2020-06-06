package com.mustofakamal.jfood_android.object.type;
/**
 * Enumeration class FoodCategory - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
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
