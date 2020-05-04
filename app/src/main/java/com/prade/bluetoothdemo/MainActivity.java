package com.prade.bluetoothdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter baAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(baAdapter.isEnabled()){
            Toast.makeText(getApplicationContext(), "Bluetooth is on", Toast.LENGTH_SHORT).show();
        } else{
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(i);
            if(baAdapter.isEnabled()){
                Toast.makeText(getApplicationContext(), "Bluetooth is on", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void findDevices(View view) {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(i);
    }

    public void turnOnOFFBL(View view) {
        baAdapter.disable();
        if(baAdapter.isEnabled()){
            Toast.makeText(getApplicationContext(), "Bluetooth could not turned off", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth turned off", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewDevices(View view) {
        Set<BluetoothDevice> pairedDevices = baAdapter.getBondedDevices();

        ListView listView = (ListView) findViewById(R.id.lvDevices);

        ArrayList pairedDevicesList = new ArrayList();
        for (BluetoothDevice bluetoothDevice : pairedDevices) {
            pairedDevicesList.add(bluetoothDevice.getName());
        }

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,pairedDevicesList);
        listView.setAdapter(arrayAdapter);
    }

}
