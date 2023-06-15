package com.example.n11demo02;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * The Ext activity
 * <p>
 *     Activity to demonstrate writing/reading text file from external memory(SD-card)
 * </p>
 *
 * @author		Levy Albert albert.school2015@gmail.com
 * @version     3.0
 * @since		14/6/2023
 */
public class ExtActivity extends MasterActivity {
    private static final int REQUEST_CODE_PERMISSION = 1;
    private EditText eT;
    private TextView tV;
    private boolean extExist, permExist;
    private final String FILENAME = "exttest.txt";
    private String strwr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ext);

        initAll();
    }

    /**
     * initAll method
     * <p>
     *     Init views, check if permission WRITE_EXTERNAL_STORAGE granted & check if file exist in
     * external memory
     * </p>
     */
    private void initAll() {
        eT=(EditText)findViewById(R.id.eT);
        tV=(TextView)findViewById(R.id.tV);
    }

    /**
     * isExternalStorageAvailable
     * <p>
     *     Checking if external sd-card is installed & functioning
     * </p>
     *
     * @return true if the sd-card is installed
     */
    public boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /**
     * checkPermission()
     * <p>
     *     Checking if permission is granted
     * </p>
     *
     * @return true if permission is granted
     */
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * requestPermission()
     * <p>
     *     Requesting permission to write to external storage
     * </p>
     */
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
    }

    /**
     * onRequestPermissionsResult method
     * <p>
     *     Result method of request permission accessing external memory
     * </p>
     *
     * @param requestCode the int request code of the permission asked
     * @param permissions String array of permissions
     * @param grantResults int array of grant results
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission to access external storage granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Permission to access external storage NOT granted", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * onResume method
     * <p>
     *     Checking if external sd-card is installed & functioning & if permission is granted
     * </p>
     */
    @Override
    protected void onResume() {
        super.onResume();
        extExist = isExternalStorageAvailable();
        permExist = checkPermission();

        if (!extExist) {
            Toast.makeText(this, "External memory not installed", Toast.LENGTH_SHORT).show();
        }

        if (permExist) {
            Toast.makeText(this, "External memory access permission granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
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
        if (extExist && permExist) {
            try {
                strwr=eT.getText().toString();
                File externalDir = Environment.getExternalStorageDirectory();
                File file = new File(externalDir, FILENAME);
                file.getParentFile().mkdirs();
                FileWriter writer = new FileWriter(file);
                writer.write(strwr);
                writer.close();
                Toast.makeText(this, "Text file saved successfully", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to save text file", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * isFilePresent method
     * <p> Checking if fileName exist in the external memory
     * </p>
     *
     * @param context the context object
     * @param fileName the file name to be checked
     * @return true if the file is present
     */
    public boolean isFilePresent(Context context, String fileName) {
        File externalDir = Environment.getExternalStorageDirectory();
        File[] filesArray = externalDir.listFiles();
        for (File file : filesArray) {
            if (file.getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * reset method
     * <p> Reset & clear the text file
     * </p>
     *
     * @param view the view that triggered the method
     */
    public void reset(View view) {
        if (extExist && permExist) {
            if (isFilePresent(this,FILENAME)) {
                try {
                    File externalDir = Environment.getExternalStorageDirectory();
                    File file = new File(externalDir, FILENAME);
                    file.getParentFile().mkdirs();
                    FileWriter writer = new FileWriter(file);
                    writer.write("");
                    writer.close();
                    Toast.makeText(this, "Text file cleared successfully", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to clear text file", Toast.LENGTH_SHORT).show();
                }
                eT.setText("");
                tV.setText("");
            } else {
                Toast.makeText(this, "File doesn't Exist", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "External memory or permission problem", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * read method
     * <p> Reading the text from the text file & display
     * </p>
     *
     * @param view the view that triggered the method
     */
    public void read(View view) {
        if (extExist && permExist) {
            if (isFilePresent(this,FILENAME)) {
                try {
                    File externalDir = Environment.getExternalStorageDirectory();
                    File file = new File(externalDir, FILENAME);
                    file.getParentFile().mkdirs();
                    FileReader reader = new FileReader(file);
                    BufferedReader bR = new BufferedReader(reader);
                    StringBuilder sB = new StringBuilder();
                    String line = bR.readLine();
                    while (line != null) {
                        sB.append(line+'\n');
                        line = bR.readLine();
                    }
                    tV.setText(sB.toString());
                    bR.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to read text file", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "File doesn't Exist", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "External memory or permission problem", Toast.LENGTH_SHORT).show();
        }
    }

}
