package com.example.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice bluetoothDevice;
    ListView listView;
    Button on, off, show;
    Intent enableBtIntent;
    Set<BluetoothDevice> pairedDevices;
    ArrayList<BluetoothDevice> arrayListBluetoothDevices = new ArrayList<BluetoothDevice>();
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;

    String[] Location = {Manifest.permission.ACCESS_FINE_LOCATION};

    final UUID MY_UUID = UUID.fromString("d1b77be6-7649-11e9-8f9e-2a86e4085a59");
    private static final int REQUEST_ENABLE_BT = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        on = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView);
        off = (Button) findViewById(R.id.button3);
        show = (Button) findViewById(R.id.button4);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //permissions
        int permissionGrand = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionGrand != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, Location, REQUEST_ENABLE_BT);
        }
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "device not supported", Toast.LENGTH_SHORT).show();
        }

        //bluetooth on
        on.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!mBluetoothAdapter.isEnabled()) {
                    enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);


                }
            }
        });

        //show list deices
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBluetoothAdapter.isEnabled()) {
                    enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);


                    pairedDevice();

                    if (mBluetoothAdapter.isDiscovering()) {
                        mBluetoothAdapter.cancelDiscovery();
                    } else {

                        mBluetoothAdapter.startDiscovery();

                        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);

                        registerReceiver(broadcastReceiver, intentFilter);

                    }

                } else {

                    Toast.makeText(MainActivity.this, "Turn on Bt", Toast.LENGTH_SHORT).show();
                }

            }
        });

        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mBluetoothAdapter.cancelDiscovery();

                bluetoothDevice = arrayListBluetoothDevices.get(position);
                bluetoothDevice.createBond();

                Toast.makeText(getApplicationContext(),"bonded",Toast.LENGTH_SHORT).show();

            }
        });

//bluetooth off
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.disable();
                    Toast.makeText(getApplicationContext(), "TURNING_OFF BLUETOOTH", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Already_OFF BLUETOOTH", Toast.LENGTH_LONG).show();

                }
            }
        });
    }


    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (!arrayList.contains(device.getName())) {
                    arrayList.add(device.getName());
                    arrayListBluetoothDevices.add(device);

                }

                if (arrayAdapter != null)
                    arrayAdapter.notifyDataSetChanged();
            }
        }
    };

    //paired devices
    private void pairedDevice() {
        pairedDevices = mBluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {

                if (!arrayList.contains(device.getName())) {
                    arrayList.add(device.getName());
                    arrayListBluetoothDevices.add(device);
                }
            }
        }

    }

}

    /*public class Serverside extends Thread {
        BluetoothServerSocket bluetoothServerSocket;

        public Serverside() {
            try {

                bluetoothServerSocket = mBluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord("Bluetooth", MY_UUID);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }

        public void run() {
            BluetoothSocket bluetoothSocket = null;
            while (bluetoothSocket == null) {
                try {
                    bluetoothSocket = bluetoothServerSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (bluetoothSocket != null) {

            }

        }
    }

    public class clientside extends Thread {
        BluetoothSocket bluetoothSocket;
        BluetoothDevice bluetoothDevice;

        public clientside(BluetoothDevice device) {
            this.bluetoothDevice = device;

            try {
                bluetoothSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void run() {
            mBluetoothAdapter.cancelDiscovery();
            try {
                bluetoothSocket.connect();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
*/


/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (requestCode == RESULT_OK) {
                Toast.makeText(this, "Bt is Enabled", Toast.LENGTH_SHORT).show();

            } else if (requestCode == RESULT_CANCELED) {
                Toast.makeText(this, "Bt Enabling canceled", Toast.LENGTH_SHORT).show();

            }

        }
    }









     BroadcastReceiver broadcastReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (!arrayList.contains(device.getName())) {
                    arrayList.add(device.getName());


                }

                if (arrayAdapter != null)
                    arrayAdapter.notifyDataSetChanged();
            }
        }
    };


    */



