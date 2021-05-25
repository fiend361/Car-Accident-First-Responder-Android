package com.jsoftware.capstone;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class Bluetooth extends AppCompatActivity {
    public Button button;
    public BluetoothAdapter bluetoothAdapter;
    public BluetoothDevice btDevice;
    public BluetoothSocket btSocket;

    public static final String SERVICE_ID = "00001101-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_ADDRESS = "98:D3:32:30:49:5A";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {

        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 3);
        }

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
            }
        }

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);

        Intent discoverableIntent =
                new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);


        /*


        button = findViewById(R.id.button);

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        btDevice = btAdapter.getRemoteDevice(SERVICE_ADDRESS);

        if(btAdapter == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth not available", Toast.LENGTH_LONG).show();
        } else {
            if(!btAdapter.isEnabled()) {
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableIntent, 3);
            } else {
                ConnectThread connectThread = new ConnectThread(btDevice);
                connectThread.start();
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(btSocket != null) {

                try{
                    OutputStream out = btSocket.getOutputStream();
                    out.write("4".getBytes());
                }catch(IOException e) {

                }

            }
            }
        });*/


    }


    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {

                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }

    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            BluetoothSocket tmp = null;
            mmDevice = device;

            try {
                tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(SERVICE_ID));
            } catch (IOException e) {

            }
            mmSocket = tmp;
        }

        public void run() {
            bluetoothAdapter.cancelDiscovery();

            try {
                mmSocket.connect();
            } catch (IOException connectException) {

                try {
                    mmSocket.close();
                } catch (IOException closeException) {

                }
                return;
            }

        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {

            }
        }
    }

    public static class MyBluetoothService {
        private static final String TAG = "MY_APP_DEBUG_TAG";
        private Handler handler;

        private interface MessageConstants {
            int MESSAGE_READ = 0;
            int MESSAGE_WRITE = 1;
            int MESSAGE_TOAST = 2;

        }

        private class ConnectedThread extends Thread {
            private final BluetoothSocket mmSocket;
            private final InputStream mmInStream;
            private final OutputStream mmOutStream;
            private byte[] mmBuffer;

            public ConnectedThread(BluetoothSocket socket) {
                mmSocket = socket;
                InputStream tmpIn = null;
                OutputStream tmpOut = null;

                try {
                    tmpIn = socket.getInputStream();
                } catch (IOException e) {
                    Log.e(TAG, "Error occurred when creating input stream", e);
                }
                try {
                    tmpOut = socket.getOutputStream();
                } catch (IOException e) {
                    Log.e(TAG, "Error occurred when creating output stream", e);
                }

                mmInStream = tmpIn;
                mmOutStream = tmpOut;
            }

            public void run() {
                mmBuffer = new byte[1024];
                int numBytes; // bytes returned from read()

                while (true) {
                    try {

                        numBytes = mmInStream.read(mmBuffer);
                        Message readMsg = handler.obtainMessage(
                                MessageConstants.MESSAGE_READ, numBytes, -1,
                                mmBuffer);
                        readMsg.sendToTarget();
                    } catch (IOException e) {
                        Log.d(TAG, "Input stream was disconnected", e);
                        break;
                    }
                }
            }

            public void write(byte[] bytes) {
                try {
                    mmOutStream.write(bytes);

                    Message writtenMsg = handler.obtainMessage(
                            MessageConstants.MESSAGE_WRITE, -1, -1, mmBuffer);
                    writtenMsg.sendToTarget();
                } catch (IOException e) {
                    Log.e(TAG, "Error occurred when sending data", e);

                    Message writeErrorMsg =
                            handler.obtainMessage(MessageConstants.MESSAGE_TOAST);
                    Bundle bundle = new Bundle();
                    bundle.putString("toast",
                            "Couldn't send data to the other device");
                    writeErrorMsg.setData(bundle);
                    handler.sendMessage(writeErrorMsg);
                }
            }

            public void cancel() {
                try {
                    mmSocket.close();
                } catch (IOException e) {
                    Log.e(TAG, "Could not close the connect socket", e);
                }
            }
        }
    }

                                                                                                                                                                /*private class ConnectThread extends Thread {
                                                                                                                                                                    private final BluetoothSocket thisSocket;
                                                                                                                                                                    private final BluetoothDevice thisDevice;

                                                                                                                                                                    public ConnectThread(BluetoothDevice device) {
                                                                                                                                                                        BluetoothSocket tmp = null;
                                                                                                                                                                        thisDevice = device;

                                                                                                                                                                        try {
                                                                                                                                                                            tmp = thisDevice.createRfcommSocketToServiceRecord(UUID.fromString(SERVICE_ID));
                                                                                                                                                                        } catch (IOException e) {
                                                                                                                                                                            Log.e("TEST", "Can't connect to service");
                                                                                                                                                                        }
                                                                                                                                                                        thisSocket = tmp;
                                                                                                                                                                    }

                                                                                                                                                                    public void run() {
                                                                                                                                                                        // Cancel discovery because it otherwise slows down the connection.
                                                                                                                                                                        btAdapter.cancelDiscovery();

                                                                                                                                                                        try {
                                                                                                                                                                            thisSocket.connect();
                                                                                                                                                                            Log.d("TESTING", "Connected to shit");
                                                                                                                                                                        } catch (IOException connectException) {
                                                                                                                                                                            try {
                                                                                                                                                                                thisSocket.close();
                                                                                                                                                                            } catch (IOException closeException) {
                                                                                                                                                                                Log.e("TEST", "Can't close socket");
                                                                                                                                                                            }
                                                                                                                                                                            return;
                                                                                                                                                                        }

                                                                                                                                                                        btSocket = thisSocket;

                                                                                                                                                                    }
                                                                                                                                                                    public void cancel() {
                                                                                                                                                                        try {
                                                                                                                                                                            thisSocket.close();
                                                                                                                                                                        } catch (IOException e) {
                                                                                                                                                                            Log.e("TEST", "Can't close socket");
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }*/

}