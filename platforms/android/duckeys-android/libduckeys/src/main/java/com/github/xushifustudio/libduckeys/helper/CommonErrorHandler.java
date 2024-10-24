package com.github.xushifustudio.libduckeys.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public final class CommonErrorHandler {

    public static final int FLAG_TOAST = 0x01;
    public static final int FLAG_ALERT = 0x02;
    public static final int FLAG_LOG = 0x04;

    public static void handle(Context ctx, Throwable e) {
        handle(ctx, e, FLAG_TOAST);
    }

    public static void handle(Context ctx, Throwable e, int flags) {

        if (ctx == null || e == null) {
            return;
        }
        boolean done = false;

        if ((flags & FLAG_TOAST) != 0) {
            handle_toast(ctx, e);
            done = true;
        }
        if ((flags & FLAG_ALERT) != 0) {
            handle_alert(ctx, e);
            done = true;
        }
        if ((flags & FLAG_LOG) != 0) {
            handle_log(ctx, e);
            done = true;
        }

        if (!done) {
            e.printStackTrace();
        }
    }


    private static void handle_log(Context ctx, Throwable e) {
        Log.e(DuckLogger.TAG, e.getMessage());
    }


    private static void handle_toast(Context ctx, Throwable e) {
        String msg = e.getClass().getName() + ":" + e.getMessage();
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

    private static void handle_alert(Context ctx, Throwable e) {
        String msg = e.getClass().getName() + ":" + e.getMessage();
        AlertDialog.Builder b = new AlertDialog.Builder(ctx);
        b.setMessage(msg).setTitle(e.getClass().getSimpleName());
        b.setPositiveButton("OK", (v1, v2) -> {
        });
        b.show();
    }

}
