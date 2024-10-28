package com.github.xushifustudio.libduckeys.ui.activities;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.github.xushifustudio.libduckeys.R;
import com.github.xushifustudio.libduckeys.api.clients.TaskManager;
import com.github.xushifustudio.libduckeys.context.DuckClient;
import com.github.xushifustudio.libduckeys.context.LifeActivity;
import com.github.xushifustudio.libduckeys.context.LifeManager;
import com.github.xushifustudio.libduckeys.helper.AccelerometerController;
import com.github.xushifustudio.libduckeys.instruments.InstrumentContext;
import com.github.xushifustudio.libduckeys.instruments.control.ChordSelector;
import com.github.xushifustudio.libduckeys.instruments.control.ModeSelector;
import com.github.xushifustudio.libduckeys.instruments.pad2.SimplePad2;
import com.github.xushifustudio.libduckeys.midi.MidiEventRT;
import com.github.xushifustudio.libduckeys.midi.MidiUserAgent;

public class SimplePadActivity extends LifeActivity {

    private MidiUserAgent mMidiUA;
    private InstrumentContext mIC;
    private ModeSelector mModeSel;
    private ChordSelector mChordSel;

    private SurfaceView mSurfaceView;
    private Button mButtonSelectMode;
    private Button mButtonSelectBase;
    private Button mButtonSelectChordRoot;
    private Button mButtonSelectChordType;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_simple_pad);
        mSurfaceView = findViewById(R.id.surface_view_pad);
        mButtonSelectMode = findViewById(R.id.button_select_mode);
        mButtonSelectBase = findViewById(R.id.button_select_base);
        mButtonSelectChordRoot = findViewById(R.id.button_select_chord_root);
        mButtonSelectChordType = findViewById(R.id.button_select_chord_type);

        init();

        setupListener();
    }


    private void init() {

        LifeManager lm = getLifeManager();
        DuckClient client = new DuckClient(this);
        TaskManager tm = new TaskManager(this);
        MidiUserAgent ua = new MidiUserAgent(this, client, tm);
        ModeSelector mode_sel = new ModeSelector(this);
        ChordSelector chord_sel = new ChordSelector();

        InstrumentContext ic = SimplePad2.create(this, mSurfaceView, lm);
        MidiEventRT mert = ic.getMert();
        ua.setRx(mert);
        mert.setTx(ua);
        mode_sel.setIC(ic);
        chord_sel.setIC(ic);

        AccelerometerController acc_sensor = new AccelerometerController(this, ic.getSensorBuffer());

        lm.add(tm);
        lm.add(client);
        lm.add(ua);
        lm.add(ic.getLife());
        lm.add(acc_sensor);

        mChordSel = chord_sel;
        mModeSel = mode_sel;
        mMidiUA = ua;
        mIC = ic;
    }

    private void setupListener() {
        mButtonSelectBase.setOnClickListener((v) -> {
            mModeSel.showBaseDialog();
        });
        mButtonSelectMode.setOnClickListener((v) -> {
            mModeSel.showModeDialog();
        });
        mButtonSelectChordType.setOnClickListener((v) -> {
            mChordSel.showTypeDialog();
        });
        mButtonSelectChordRoot.setOnClickListener((v) -> {
            mChordSel.showRootDialog();
        });
    }
}
