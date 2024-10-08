package com.github.xushifustudio.libduckeys.conn;

import com.github.xushifustudio.libduckeys.midi.MidiEventHandler;
import com.github.xushifustudio.libduckeys.midi.MidiUriConnection;
import com.github.xushifustudio.libduckeys.midi.MidiUriConnector;

import java.io.IOException;
import java.net.URI;

public class UsbMidiConnector implements MidiUriConnector {
    @Override
    public boolean supports(URI uri) {
        String str = uri.toString();
        return str.startsWith("usb:");
    }

    @Override
    public MidiUriConnection open(URI uri, MidiEventHandler h) throws IOException {
        return null;
    }
}
