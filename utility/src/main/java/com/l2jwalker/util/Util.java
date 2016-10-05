package com.l2jwalker.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class Util {

    public static  final String PROTO_ENUM_SPLITTER = "_";

    public static byte[] formattedHexStringToByteArray(String s) {
        return hexStringToByteArray(clearHex(new StringBuilder(s)));
    }

    public static String readFormattedHexFile(File file) {
        return clearHex(getTextFromFile(file));
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static int INT_SQRT = 65536;

    public static StringBuilder sqlToJavaName(StringBuilder value) {
        if (0 == value.indexOf("is_")) {
            value.replace(0, 3, "");
        }
        while (-1 != value.indexOf("_")) {
            int p = value.indexOf("_");
            value.replace(p, p + 1, "");
            value.replace(p, p + 1, value.substring(p, p + 1).toUpperCase());
        }
        return value;
    }

    public static String sqlToJavaName(String value) {
        return sqlToJavaName(new StringBuilder(value)).toString();
    }

    static final String HEXES = "0123456789ABCDEF";

    static boolean isHexChar(char c) {
        for (int i = 0; i < HEXES.length(); i++) {
            if (HEXES.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }

    public static String byteArrayToHexString(byte[] raw, int offset, int len) {
        if (raw == null) {
            return null;
        }
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        int i = 0;
        for (int n = offset; n < offset + len; n++) {
            if (i % 16 == 0 && i != 0) {
                hex.append("\n");
            }
            if (i != 0 && i % 16 != 0) {
                hex.append(" ");
            }
            i++;
            hex.append(HEXES.charAt((raw[n] & 0xF0) >> 4))
                    .append(HEXES.charAt((raw[n] & 0x0F)));

        }
        return hex.toString();
    }

    public static String byteArrayToClearHexString(byte[] raw) {
        return byteArrayToClearHexString(raw, 0, raw.length);
    }

    public static String byteArrayToClearHexString(byte[] raw, int offset, int len) {
        if (raw == null) {
            return null;
        }
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        int i = 0;
        for (int n = offset; n < offset + len; n++) {
            i++;
            hex.append(HEXES.charAt((raw[n] & 0xF0) >> 4))
                    .append(HEXES.charAt((raw[n] & 0x0F)));

        }
        return hex.toString();
    }

    public static String byteToHexString(byte raw) {
        final StringBuilder hex = new StringBuilder(2);
        hex.append(HEXES.charAt((raw & 0xF0) >> 4))
                .append(HEXES.charAt((raw & 0x0F)));
        return hex.toString();
    }

    public static String byteArrayToHexString(byte[] raw) {
        return byteArrayToHexString(raw, 0, raw.length);
    }

    public static int byteArrayToInt(byte[] b, int offset) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i + offset] & 0x000000FF) << shift;
        }
        return value;
    }

    public static int byteArrayToInt(byte[] b) {
        return byteArrayToInt(b, 0);
    }

    public static byte[] revers(byte[] b) {
        return revers(b, 0, b.length);
    }

    public static byte[] revers(byte[] b, int offset, int length) {
        for (int i = 0; i < length / 2; i++) {
            byte t = b[offset + i];
            b[offset + i] = b[offset + length - i - 1];
            b[offset + length - i - 1] = t;
        }
        return b;
    }

    public static void incBFKey(byte[] bfkey) {
        int i = bfkey.length - 1;
        while ((++bfkey[i] == 0x00)) {
            i--;
            if (i == -1) return;
        }
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static StringBuilder getTextFromFile(final File file) {
        final StringBuilder textBuilder = new StringBuilder();
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            return null;
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null) {
                textBuilder.append(text)
                        .append(System.getProperty(
                                "line.separator"));
            }
        } catch (IOException e) {
            return null;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                //ignore
            }
        }
        return textBuilder;
    }

    public static String clearHex(String hexText) {
        StringBuilder clearHex = new StringBuilder(hexText.length());
        for (int i = 0; i < hexText.length(); i++) {
            if (isHexChar(hexText.charAt(i))) {
                clearHex.append(hexText.charAt(i));
            }
        }
        return clearHex.toString();
    }

    public static String clearHex(StringBuilder hexText) {
        StringBuilder clearHex = new StringBuilder(hexText.length());
        for (int i = 0; i < hexText.length(); i++) {
            if (isHexChar(hexText.charAt(i))) {
                clearHex.append(hexText.charAt(i));
            }
        }
        return clearHex.toString();
    }

    public static byte[] readHexFile(File file) {
        return hexStringToByteArray(clearHex(getTextFromFile(file)));
    }

    public static int arrayCompare(byte[] array1, int offset1, byte[] array2, int offset2, int size) {
        for (int i = 0; i < size; i++) {
            if (array1[offset1 + i] != array2[offset2 + i]) {
                return i;
            }
        }
        return -1;
    }

    public static String getLine(StringBuilder text, int line) {
        int firstChar;
        int lastChar = 0;
        int lineNum = 0;
        for (int c = 0; c < text.length(); c++) {
            if (text.charAt(c) == '\n') {
                lineNum++;
                firstChar = lastChar;
                lastChar = c;
                if (line == lineNum) {
                    return text.substring(firstChar + 1, lastChar);
                }
            }
        }
        return null;
    }

}
