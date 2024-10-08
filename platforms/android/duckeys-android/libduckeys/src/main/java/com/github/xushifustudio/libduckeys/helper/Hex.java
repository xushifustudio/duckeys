package com.github.xushifustudio.libduckeys.helper;

public final class Hex {

    private Hex() {
    }


    public static void stringify(byte num, StringBuilder b) {
        if (b == null) {
            return;
        }
        for (int i = 0; i < 2; i++) {
            int hex;
            if (i == 0) {
                // high 4-bits
                hex = (num >> 4) & 0x0f;
            } else {
                // low 4-bits
                hex = num & 0x0f;
            }
            char ch;
            if (hex < 0x0a) {
                ch = (char) (hex + '0');
            } else {
                ch = (char) (hex + 'a' - 0x0a);
            }
            b.append(ch);
        }
    }

    public static String stringify(byte num) {
        StringBuilder b = new StringBuilder();
        stringify(num, b);
        return b.toString();
    }

    public static String stringify(byte[] data, int offset, int length) {
        StringBuilder sb = new StringBuilder();
        int end = offset + length;
        for (int i = offset; i < end; i++) {
            stringify(data[i], sb);
        }
        return sb.toString();
    }


    public static String stringify(byte[] data) {
        return stringify(data, 0, data.length);
    }
}
