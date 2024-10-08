package com.github.xushifustudio.libduckeys.conn;

import com.github.xushifustudio.libduckeys.midi.MidiUriConnection;
import com.github.xushifustudio.libduckeys.midi.MidiUriConnector;

import java.io.IOException;
import java.net.URI;

public class WifiMidiConnector implements MidiUriConnector {
    @Override
    public boolean supports(URI uri) {
        String str = uri.toString();
        return str.startsWith("wifi:");
    }

    @Override
    public MidiUriConnection open(URI uri) throws IOException {
        return null;
    }
}