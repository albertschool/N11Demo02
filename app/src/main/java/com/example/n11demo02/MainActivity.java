package com.example.n11demo02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * The main activity
 * <p>
 *     A basic demo application to demonstrate:
 *     1. inheriting from Master activity (menu)
 *     2. writing/reading text file from internal memory
 *     3. reading text file from application resource (raw)
 *     4. writing/reading text file from external memory(SD-card)
 * </p>
 *
 * @author		Levy Albert albert.school2015@gmail.com
 * @version     2.0
 * @since		14/6/2023
 */

public class MainActivity extends MasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}