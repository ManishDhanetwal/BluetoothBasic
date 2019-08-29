package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button buttonON,buttonOff ;
    BluetoothAdapter myBluetoothAdapter ;
    Intent btEnablingIntent;
    int requestCodeForEnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonON = findViewById(R.id.BtnON);
        buttonOff= findViewById(R.id.BtnOff);
        myBluetoothAdapter= BluetoothAdapter.getDefaultAdapter() ;
        btEnablingIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        requestCodeForEnable=1;
        bluetoothOnmethod();
        bluetoothOFFmethod();
    }

    private void bluetoothOFFmethod() {
        buttonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myBluetoothAdapter.isEnabled()) {
                    myBluetoothAdapter.disable();
                }
            }


        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==requestCodeForEnable)
        {
           if(resultCode==RESULT_OK){
               Toast.makeText(getApplicationContext(), "Bluetooth is enabled", Toast.LENGTH_LONG).show();
           }else if(resultCode== RESULT_CANCELED)
           {
                Toast.makeText(getApplicationContext(),"Bluetooth Enabling cancelled",Toast.LENGTH_LONG).show();
           }
        }
    }

    private void bluetoothOnmethod() {
        buttonON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myBluetoothAdapter == null){
                    Toast.makeText(getApplicationContext(), "bluetooth does not work on this device", Toast.LENGTH_LONG).show();
            }
            else {
                if(!myBluetoothAdapter.isEnabled())
                {
                    startActivityForResult(btEnablingIntent,requestCodeForEnable);
                }

                }


            }
        });
    }


}
