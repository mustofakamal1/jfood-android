package com.mustofakamal.jfood_android.object;
import com.mustofakamal.jfood_android.object.type.InvoiceStatus;
import com.mustofakamal.jfood_android.object.type.PaymentType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Seller adalah class yang berfungsi memproses informasi invoice di jFood.
 *
 * @author Mustofa Kamal
 * @version 28-02-2020
 */

public abstract class Invoice {
    protected int totalPrice;
    // instance variables - replace the example below with your own
    private int id;
    private ArrayList<Food> foods;
    private Calendar date;
    private Customer customer;
    private InvoiceStatus invoiceStatus;

    /**
     * Constructor for objects of class Invoice.
     * <p>
     * Mengupdate id, name, email, phoneNumber, dan joinDate
     * pada object baru seller dengan nilai yang dimasukkan.
     *
     * @param id         angka unik untuk setiap transaksi
     *                   sebagai identifier
     * @param idFood     angka unik makanan yang dipesan
     * @param date       tanggal transaksi dilakukan
     * @param customer   customer yang melakukan transaksi,
     *                   diambil dari salah satu object dari
     *                   class Customer
     * @param totalPrice total harga pada transaksi
     */
    public Invoice(int id, ArrayList<Food> foods, Customer customer) {
        // initialise instance variables
        Calendar now = Calendar.getInstance();
        this.id = id;
        this.foods = foods;
        this.customer = customer;
        this.date = now;
        this.invoiceStatus = InvoiceStatus.Ongoing;
    }

    /**
     * Mengembalikan angka unik transaksi dari object Invoice
     * yang bersangkutan.
     *
     * @return angka unik penjual dari object Invoice
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Mengembalikan angka unik makanan dari object Invoice
     * yang bersangkutan.
     *
     * @return angka unik makanan dari object Invoice
     */
    public ArrayList<Food> getFoods() {
        return foods;
    }

    /**
     * Mengembalikan tanggal transaksi dari object Invoice
     * yang bersangkutan.
     *
     * @return tanggal transaksi dari object Invoice
     */
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * Mengembalikan total harga transaksi dari object Invoice
     * yang bersangkutan.
     *
     * @return total harga transaksi dari object Invoice
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * Mengembalikan customer pada Class Customer dari object
     * Invoice yang bersangkutan.
     *
     * @return customer pada Class Customer dari object Invoice
     */
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public abstract PaymentType getPaymentType();

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public void setFood(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public void setDate(int year, int month, int dayOfMonth) {
        this.date = new GregorianCalendar(year, month - 1, dayOfMonth);
//        ;
    }

    public abstract void setTotalPrice();

    public abstract void setTotalPrice(int totalPrice);

    public abstract String toString();
}
