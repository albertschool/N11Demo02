package com.example.n11demo02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class RawActivity extends MasterActivity {
    private TextView tV;
    private final String FILENAME = "rawtest.txt";
    private int resourceId;
    private boolean fileExists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw);

        initAll();
    }

    private void initAll() {
        tV = (TextView) findViewById(R.id.tV);

        String fileName = FILENAME.substring(0, FILENAME.length() - 4);
        resourceId = this.getResources().getIdentifier(fileName, "raw", this.getPackageName());
        if (resourceId != 0) {
            fileExists = true;
            Toast.makeText(this, "raw file exist", Toast.LENGTH_LONG).show();
        } else {
            fileExists = false;
            Toast.makeText(this, "raw file doesn't exist", Toast.LENGTH_LONG).show();
        }
    }

    public void reset(View view) {
        tV.setText("");
    }

    public void read(View view) {
        if (fileExists) {
            InputStream iS = this.getResources().openRawResource(resourceId);
            InputStreamReader iSR = new InputStreamReader(iS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            try {
                String line = bR.readLine();
                while (line != null) {
                    sB.append(line+'\n');
                    line = bR.readLine();
                }
                tV.setText(sB.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bR.close();
                    iSR.close();
                    iS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(this, "raw file doesn't exist", Toast.LENGTH_LONG).show();
        }
    }
}