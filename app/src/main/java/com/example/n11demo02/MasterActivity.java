package com.example.n11demo02;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

/**
 * The Master activity
 * <p>
 *     Master activity to demonstrate inheriting from Master activity (menu)
 * </p>
 *
 * @author		Levy Albert albert.school2015@gmail.com
 * @version     2.0
 * @since		14/6/2023
 */

public class MasterActivity extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_master, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.iF) {
            intent = new Intent(MasterActivity.this, IntActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.rF) {
            intent = new Intent(MasterActivity.this, RawActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.eF) {
            intent = new Intent(MasterActivity.this, ExtActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}