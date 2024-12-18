package com.github.xushifustudio.libduckeys.midi;

import androidx.annotation.NonNull;

public class MidiEventRT implements MidiEventDispatcher, MidiEventHandler, MERT {

    private MidiEventHandler mRx;
    private MidiEventDispatcher mTx;
    private final NopMERT nop = new NopMERT();
    private final String mTag;

    public MidiEventRT(String tag) {
        mTx = nop;
        mRx = nop;
        mTag = tag;
    }


    @NonNull
    @Override
    public String toString() {
        return super.toString() + " tag:" + mTag;
    }

    @Override
    public MidiEventDispatcher getTx() {
        return this;
    }

    public final void setRx(MidiEventHandler rx) {
        if (rx == null) rx = nop;
        mRx = rx;
    }

    public final void setTx(MidiEventDispatcher tx) {
        if (tx == null) tx = nop;
        mTx = tx;
    }

    @Override
    public void dispatch(MidiEvent me) {
        mTx.dispatch(me);
    }

    @Override
    public void handle(MidiEvent me) {
        mRx.handle(me);
    }
}
