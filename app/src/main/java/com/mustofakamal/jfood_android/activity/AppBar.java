package com.mustofakamal.jfood_android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mustofakamal.jfood_android.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Class AppBar adalah class yang berfungsi untuk membuat
 * app bar pada apliakasi.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 *
 * @deprecated yang digunakan hanya layout xml nya saja
 */
public class AppBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItem = item.getItemId();

//        switch(menuItem) {
//            case R.id.action_search:
//                Toast.makeText(MainActivity.this, "Action Search", Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.action_favourite:
//                Toast.makeText(MainActivity.this, "Action Favourites", Toast.LENGTH_SHORT).show();
//        }

        return super.onOptionsItemSelected(item);
    }
}
