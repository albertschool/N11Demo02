package com.example.n11demo02;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * The Int activity
 * <p>
 *     Activity to demonstrate writing/reading text file from internal memory
 * </p>
 *
 * @author		Levy Albert albert.school2015@gmail.com
 * @version     2.0
 * @since		14/6/2023
 */
public class IntActivity extends MasterActivity {
    private EditText eT;
    private TextView tV;
    private final String FILENAME = "inttest.txt";
    private String strwr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_int);

        initAll();
    }

    /**
     * initAll method
     * <p> Init views & check if file exist in internal memory
     * </p>
     */
    private void initAll() {
        eT=(EditText)findViewById(R.id.eT);
        tV=(TextView)findViewById(R.id.tV);
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            read(null);
        } else {
            reset(null);
        }
    }

    /**
     * write method
     * <p> Writing the text input to the text file
     * </p>
     *
     * @param view the view that triggered the method
     */
    public void write(View view) {
        strwr=eT.getText().toString();
        try {
            FileOutputStream fos = openFileOutput(FILENAME,MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(strwr);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reset method
     * <p> Reset & clear the text file
     * </p>
     *
     * @param view the view that triggered the method
     */
    public void reset(View view) {
        try {
            FileOutputStream fOS = openFileOutput(FILENAME,MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter(oSW);
            bW.write("");
            bW.close();
            oSW.close();
            fOS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        eT.setText("");
        tV.setText("");
    }

    /**
     * read method
     * <p> Reading the text from the text file & display
     * </p>
     *
     * @param view the view that triggered the method
     */
    public void read(View view) {
        try {
            FileInputStream fIS= openFileInput(FILENAME);
            InputStreamReader iSR = new InputStreamReader(fIS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null) {
                sB.append(line+'\n');
                line = bR.readLine();
            }
            tV.setText(sB.toString());
            bR.close();
            iSR.close();
            fIS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}