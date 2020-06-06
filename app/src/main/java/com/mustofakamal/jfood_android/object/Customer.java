package com.mustofakamal.jfood_android.object;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 * Customer adalah class yang berfungsi memproses informasi
 * customer di jFood.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */
public class Customer {
    // instance variables
    private int id;
    private String name;
    private String email;
    private String password;
    private Calendar joinDate;

    /**
     * Constructor for objects of class Customer.
     * <p>
     * Mengupdate id, nama, alamat email, password, dan tanggal
     * bergabung pada object baru Customer dengan nilai yang
     * dimasukkan.
     *
     * @param id       angka unik yang dimiliki setiap customer
     *                 sebagai identifier
     * @param name     nama dari customer
     * @param email    alamat email dari customer
     * @param password password yang digunakan customer untuk
     *                 login
     * @param joinDate tanggal mulai menjadi customer atau
     *                 membuat akun
     */
    public Customer(int id, String name, String email, String password,
                    Calendar joinDate) {
        // initialise instance variables
        this.id = id;
        this.name = name;
        this.setEmail(email);
        this.setPassword(password);
        this.joinDate = joinDate;
    }

    public Customer(int id, String name, String email, String password,
                    int year, int month, int dayOfMonth) {
        // initialise instance variables
        this.id = id;
        this.name = name;
        this.setEmail(email);
        this.setPassword(password);
        this.joinDate = new GregorianCalendar(year, month - 1, dayOfMonth);
    }

    public Customer(int id, String name, String email, String password) {
        // initialise instance variables
        Calendar now = Calendar.getInstance();
        this.id = id;
        this.name = name;
        this.setEmail(email);
        this.setPassword(password);
        this.joinDate = now;
    }

    /**
     * Mengembalikan id dari object Customer yang
     * bersangkutan.
     *
     * @return angka unik dari object customer
     */
    public int getId() {
        return id;
    }

    /**
     * Mengupdate angka unik dari object Customer yang
     * bersangkutan.
     *
     * @param id angka unik customer yang akan diupdate ke object
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Mengembalikan nama dari object Customer yang
     * bersangkutan.
     *
     * @return nama dari object customer
     */
    public String getName() {
        return name;
    }

    /**
     * Mengupdate nama dari object Customer yang
     * bersangkutan.
     *
     * @param name nama customer yang akan diupdate ke object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mengembalikan email dari object Customer yang
     * bersangkutan.
     *
     * @return email dari object customer
     */
    public String getEmail() {
        return email;
    }

    /**
     * Mengupdate email dari object Customer yang
     * bersangkutan.
     *
     * @param email email customer yang akan diupdate ke object
     */
    public void setEmail(String email) {
        boolean checke = Pattern.matches("^[\\w%&_*~]+(?:\\.[\\w&_'*~]+)*@(?!-)(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", email);

        if (checke) {
            this.email = email;
        } else {
            this.email = "";
        }
    }

    /**
     * Mengembalikan password dari object Customer yang
     * bersangkutan.
     *
     * @return password dari object customer
     */
    public String getPassword() {
        return password;
    }

    /**
     * Mengupdate password dari object Customer yang
     * bersangkutan.
     *
     * @param password password customer yang akan diupdate ke
     *                 object
     */
    public void setPassword(String password) {
        boolean checkp = Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$", password);

        if (checkp) {
            this.password = password;
        } else {
            this.password = "";
        }
    }

    /**
     * Mengembalikan tanggal bergabungs dari object Customer yang
     * bersangkutan.
     *
     * @return tanggal bergabung dari object customer
     */
    public Calendar getJoinDate() {
        return joinDate;
    }

    /**
     * Mengupdate tanggal bergabung dari object Customer yang
     * bersangkutan.
     *
     * @param joinDate tanggal bergabung customer yang akan diupdate
     *                 ke object
     */
    public void setJoinDate(Calendar joinDate) {
        this.joinDate = joinDate;
    }

    public void setJoinDate(int year, int month, int dayOfMonth) {
        this.joinDate = new GregorianCalendar(year, month - 1, dayOfMonth);
    }

    /**
     * Menampilkan nama customer dari object customer yang
     * bersangkutan.
     */
    public String toString() {
        String string = "";

        if (joinDate != null) {
            Date date = joinDate.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMMMM yyyy");
            String formatted = formatter.format(date);
            string =
                    "============CUSTOMER============\n" +
                            "ID: " + id + "\n" +
                            "Name: " + name + "\n" +
                            "Email: " + email + "\n" +
                            "Password: " + password + "\n" +
                            "joinDate: " + formatted;
        } else {
            string =
                    "============CUSTOMER============\n" +
                            "ID: " + id + "\n" +
                            "Name: " + name + "\n" +
                            "Email: " + email + "\n" +
                            "Password: " + password;
        }
        return string;
    }
}
