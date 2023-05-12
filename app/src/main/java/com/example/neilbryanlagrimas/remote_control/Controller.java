package com.example.neilbryanlagrimas.remote_control;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class Controller extends AppCompatActivity {

    ImageView imageView;

    private ProgressDialog progress;
    String address = null;
    Button up, down, up_left, up_right, down_left, down_right, brake, led;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        Intent newint = getIntent();
        address = newint.getStringExtra(Bluetooth_info.EXTRA_ADDRESS); //receive the address of the bluetooth device
        imageView = findViewById(R.id.controller);

        up = findViewById(R.id.up);
        down = findViewById(R.id.down);

        up_left = findViewById(R.id.up_left);
        up_right = findViewById(R.id.up_right);
        down_left = findViewById(R.id.bot_left);
        down_right = findViewById(R.id.bot_right);

        brake = findViewById(R.id.brake);

        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        up();
                        return true;
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        brakes();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        down();
                        return true;
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        brakes();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        up_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        up_left();
                        return true;
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        brakes();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        up_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        up_right();
                        return true;
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        brakes();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        down_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        down_left();
                        return true;
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        brakes();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        down_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        down_right();
                        return true;
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        brakes();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        brake.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        brakes();
                        return true;
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        brakes();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });


//        down.setOnClickListener(this);
//
//        up_left.setOnClickListener(this);
//        up_right.setOnClickListener(this);
//        down_left.setOnClickListener(this);
//        down_right.setOnClickListener(this);
//
//        brake.setOnClickListener(this);

        connection();
    }

    private void up() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write('f');
            } catch (IOException e) {
                Toast.makeText(this, "Error cant sent data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void down() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write('b');
            } catch (IOException e) {
                Toast.makeText(this, "Error cant sent data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void up_left() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write('l');
            } catch (IOException e) {
                Toast.makeText(this, "Error cant sent data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void up_right() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write('r');
            } catch (IOException e) {
                Toast.makeText(this, "Error cant sent data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void down_left() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write('l');
            } catch (IOException e) {
                Toast.makeText(this, "Error cant sent data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void down_right() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write('r');
            } catch (IOException e) {
                Toast.makeText(this, "Error cant sent data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void brakes() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write('s');
            } catch (IOException e) {
                Toast.makeText(this, "Error cant sent data", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void connection() {
        try {
            if (btSocket == null) {
                myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                btSocket.connect();//start connection

                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error btsocket failed", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(this, "Error not connected", Toast.LENGTH_SHORT).show();
        }
    }
}