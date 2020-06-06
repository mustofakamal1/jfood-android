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
 * @version 07-06-2020
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
     * @param id        angka unik untuk setiap invoice
     *                  sebagai identifier
     * @param foods     daftar objek Food
     * @param customer  customer yang melakukan transaksi,
     *                  diambil dari salah satu object dari
     *                  class Customer
     * @see Food
     * @see Customer
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
     * @return angka unik dari object Invoice
     */
    public int getId() {
        return id;
    }

    /**
     * Mengupdate id invoice pada dari object
     * Invoice yang bersangkutan.
     *
     * @param id    angka unik transaksi object Invoice
     */
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

    /**
     * Mengupdate tanggal invoice pada dari object
     * Invoice yang bersangkutan.
     *
     * @param date    tanggal transaksi dilakukan
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * Mengembalikan total harga pada transaksi dari object Invoice
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
     * @see Customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Mengupdate customer pembuat invoice.
     *
     * @param customer    objek Customer
     * @see Customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Mengembalikan tipe transaksi pada Enum PaymnetType dari object
     * Invoice yang bersangkutan.
     *
     * @return enum PaymentType untuk tipe transaksi
     * @see PaymentType
     */
    public abstract PaymentType getPaymentType();


    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    /**
     * Mengupdate status invoice pada dari object
     * Invoice yang bersangkutan.
     *
     * @param invoiceStatus    status invoice
     */
    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    /**
     * Mengupdate daftar makanan pada invoice.
     *
     * @param foods    daftar object makanan
     */
    public void setFood(ArrayList<Food> foods) {
        this.foods = foods;
    }

    /**
     * Mengupdate tanggal invoice pada dari object
     * Invoice yang bersangkutan.
     *
     * @param year          tahun transaksi
     * @param month         bulan transaksi
     * @param dayOfMonth    hari transaksi
     */
    public void setDate(int year, int month, int dayOfMonth) {
        this.date = new GregorianCalendar(year, month - 1, dayOfMonth);
    }

    /**
     * Mengupdate harga total invoice pada dari object
     * Invoice yang bersangkutan.
     *
     */
    public abstract void setTotalPrice();

    /**
     * Mengupdate harga total invoice pada dari object
     * Invoice yang bersangkutan dengan input yang dimasukkan.
     *
     * @param totalPrice    harga total
     */
    public abstract void setTotalPrice(int totalPrice);

    /**
     * Mengembalikan beberapa informasi invoice.
     *
     * @return string informasi invoice
     *
     */
    public abstract String toString();
}
