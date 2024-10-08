package com.github.xushifustudio.libduckeys.conn.bleconn;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;

import com.github.xushifustudio.libduckeys.context.ComponentContext;
import com.github.xushifustudio.libduckeys.context.ComponentLife;
import com.github.xushifustudio.libduckeys.context.ComponentRegistration;
import com.github.xushifustudio.libduckeys.context.ComponentRegistrationBuilder;
import com.github.xushifustudio.libduckeys.midi.MidiEventHandler;
import com.github.xushifustudio.libduckeys.midi.MidiUriConnection;
import com.github.xushifustudio.libduckeys.midi.MidiUriConnector;

import java.io.IOException;
import java.net.URI;

public class BleConnector implements MidiUriConnector, ComponentLife {

    private BluetoothManager mBTM;
    private Context mContext;

    public BleConnector() {
    }

    @Override
    public boolean supports(URI uri) {
        String str = uri.toString();
        return str.startsWith("ble:");
    }

    @Override
    public MidiUriConnection open(URI uri, MidiEventHandler rx) throws IOException {

        String addr = uri.getHost() + "";
        addr = addr.replace('-', ':');
        addr = addr.replace('.', ':');
        addr = addr.toUpperCase();

        BluetoothAdapter adapter = mBTM.getAdapter();
        BluetoothDevice device = adapter.getRemoteDevice(addr);


        BleConnection conn = new BleConnection(mContext, uri, device, rx);
        conn.connect(5000);
        return conn;
    }

    private void onLoad(ComponentContext cc) {
        Context ctx = cc.context;
        mBTM = ctx.getSystemService(BluetoothManager.class);
        mContext = ctx;
    }

    @Override
    public ComponentRegistration init(ComponentContext cc) {
        ComponentRegistrationBuilder b = ComponentRegistrationBuilder.newInstance(cc);
        b.onCreate(() -> {
            onLoad(cc);
        });
        return b.create();
    }
}
