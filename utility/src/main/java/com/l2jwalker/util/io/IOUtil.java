package com.l2jwalker.util.io;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class IOUtil {

    public static void moveArrayPart(byte[] src, int from, int to, int len) {
        if (from == to) {
            return;
        }
        if (to < from) {
            System.arraycopy(src, from, src, to, len);
        } else {
            System.arraycopy(src, from, src, to, len - 1 + 1);
        }
    }

    public static int readD(byte[] src, int offset) {//4 bytes int
        int value = 0;
        for (int i = 0; i < 32; i += 8) {
            value += (src[offset++] & 0x000000FF) << i;
        }
        return value;
    }

    public static int readD(InputStream src) throws IOException {//4 bytes int
        byte[] tmp = new byte[4];
        assert 4 == src.read(tmp);
        int value = 0;
        value += (tmp[0] & 0x000000FF);
        value += (tmp[1] & 0x000000FF) << 8;
        value += (tmp[2] & 0x000000FF) << 16;
        value += (tmp[3] & 0x000000FF) << 24;
        return value;
    }

    public static byte readC(byte[] src, int offset) {//1 byte
        return src[offset];
    }

    public static byte readC(InputStream src) throws IOException {//1 byte
        byte[] tmp = new byte[1];
        assert 1 == src.read(tmp);
        return tmp[0];
    }

    public static short readW(byte[] src, int offset) {//2 bytes
        short value = 0;
        for (int i = 0; i < 16; i += 8) {
            value += (src[offset++] & 0x00FF) << i;
        }
        return value;
    }

    public static short readW(InputStream src) throws IOException {//2 bytes
        byte[] tmp = new byte[2];
        assert 2 == src.read(tmp);
        short value = 0;
        value += (tmp[0] & 0x00FF);
        value += (tmp[1] & 0x00FF) << 8;
        return value;
    }

    public static double readF(byte[] src, int offset) {//8 bytes double
        int i;
        int len = 8;
        int cnt = 0;
        byte[] tmp = new byte[len];
        for (i = 0; i < len; i++) {
            tmp[cnt] = src[offset + i];
            cnt++;
        }
        long accum = 0;
        i = 0;
        for (int shiftBy = 0; shiftBy < 0x40; shiftBy += 8) {
            accum |= ((long) (tmp[i] & 0xFF)) << shiftBy;
            i++;
        }
//        offset += 8;
        return Double.longBitsToDouble(accum);
    }

    public static double readF(InputStream src) throws IOException {//8 bytes double
        byte[] tmp = new byte[8];
        assert 8 == src.read(tmp);
        long accum = 0;
        for (int i = 0; i < 8; i++) {
            accum |= ((long) (tmp[i] & 0xFF)) << i * 8;
        }
        return Double.longBitsToDouble(accum);
    }

    public static long readQ(byte[] src, int offset) {//8 bytes long
        long value = src[offset++] & 0xff;
        value |= (src[offset++] & 0xffL) << 8L;
        value |= (src[offset++] & 0xffL) << 16L;
        value |= (src[offset++] & 0xffL) << 24L;
        value |= (src[offset++] & 0xffL) << 32L;
        value |= (src[offset++] & 0xffL) << 40L;
        value |= (src[offset++] & 0xffL) << 48L;
        value |= (src[offset] & 0xffL) << 56L;
        return value;
    }

    public static long readQ(InputStream src) throws IOException {//8 bytes long
        byte[] tmp = new byte[8];
        assert 8 == src.read(tmp);
        long value = tmp[0] & 0xff;
        value |= (tmp[1] & 0xffL) << 8L;
        value |= (tmp[2] & 0xffL) << 16L;
        value |= (tmp[3] & 0xffL) << 24L;
        value |= (tmp[4] & 0xffL) << 32L;
        value |= (tmp[5] & 0xffL) << 40L;
        value |= (tmp[6] & 0xffL) << 48L;
        value |= (tmp[7] & 0xffL) << 56L;
        return value;
    }

    public static String readU(byte[] src, int offset) throws UnsupportedEncodingException {
        int i = 0;
        while (!((byte) 0x00 == src[offset + i] && (byte) 0x00 == src[offset + i + 1])) {
            i += 2;
        }
        if (0 == i) {
            offset += 2;
            return "";
        }
        i--;
        byte[] unicodeBytes = new byte[i + 3];
        System.arraycopy(src, offset, unicodeBytes, 3, i);
        unicodeBytes[0] = (byte) 0xFE;
        unicodeBytes[1] = (byte) 0xFF;
        unicodeBytes[2] = (byte) 0x00;
        offset += i + 3;
        return new String(unicodeBytes, "Unicode");
    }

    private static final byte[] UNICODE_STRING_START = new byte[]{
            (byte) 0xFE,
            (byte) 0xFF,
            (byte) 0x00
    };

    public static String readU(InputStream src) throws IOException {
        ByteArrayOutputStream tmp = new ByteArrayOutputStream();
        tmp.write(UNICODE_STRING_START);
        byte[] pair = new byte[2];
        while (2 == src.read(pair) && !((byte) 0x00 == pair[0] && (byte) 0x00 == pair[1])) {
            tmp.write(pair);
        }
        if (0 == tmp.size()) {
            return "";
        }
        pair = tmp.toByteArray();
        return new String(pair, 0, pair.length - 1, "Unicode");
    }

    public static byte[] readB(byte[] src, int offset, int length) {
        return Arrays.copyOfRange(src, offset, offset + length);
    }

    public static byte[] readB(InputStream src, int len) throws IOException {
        byte[] out = new byte[len];
        assert len == src.read(out);
        return out;
    }

    public static byte[] readB(InputStream src) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.copy(src, out);
        return out.toByteArray();
    }

    public static int readB(InputStream src, byte[] dest) throws IOException {
        return src.read(dest);
    }

    public static int readB(InputStream src, byte[] dest, int offset, int len) throws IOException {
        return src.read(dest, offset, len);
    }

    public static int writeC(byte[] src, int offset, byte value) {//1 byte
        src[offset] = value;
        return 1;
    }

    public static int writeC(OutputStream src, int value) throws IOException {
        src.write(new byte[]{(byte) value});
        return 1;
    }

    public static int writeD(byte[] src, int offset, int value) {//4 bytes int
        for (int i = 0; i < 32; i += 8) {
            src[offset++] = (byte) ((value >> i) & 0xFF);
        }
        return 4;
    }

    public static int writeD(OutputStream src, int value) throws IOException {
        src.write(new byte[]{
                (byte) ((value) & 0xFF),
                (byte) ((value >> 8) & 0xFF),
                (byte) ((value >> 16) & 0xFF),
                (byte) ((value >> 24) & 0xFF)
        });
        return 4;
    }

    public static int writeW(byte[] src, int offset, short value) {//2 bytes short
        src[offset++] = (byte) (value & 0xFF);
        src[offset] = (byte) ((value >> 8) & 0xFF);
        return 2;
    }

    public static int writeW(OutputStream src, int value) throws IOException {
        src.write(new byte[]{
                (byte) (value & 0xFF),
                (byte) ((value >> 8) & 0xFF)
        });
        return 2;
    }

    public static int writeQ(byte[] src, int offset, long value) {//8bytes long
        for (int i = 0; i < 64; i += 8) {
            src[offset++] = (byte) ((value >> i) & 0xFF);
        }
        return 8;
    }

    public static int writeQ(OutputStream src, long value) throws IOException {
        src.write(new byte[]{
                (byte) ((value) & 0xFF),
                (byte) ((value >> 8) & 0xFF),
                (byte) ((value >> 16) & 0xFF),
                (byte) ((value >> 24) & 0xFF),
                (byte) ((value >> 32) & 0xFF),
                (byte) ((value >> 40) & 0xFF),
                (byte) ((value >> 48) & 0xFF),
                (byte) ((value >> 56) & 0xFF)
        });
        return 8;
    }

    public static int writeF(byte[] src, int offset, double value) {//8 bytes double
        writeQ(src, offset, Double.doubleToRawLongBits(value));
        return 8;
    }

    public static int writeF(OutputStream src, double value) throws IOException {//8 bytes double
        writeQ(src, Double.doubleToRawLongBits(value));
        return 8;
    }

    public static int writeB(byte[] src, int offset, byte[] value) {//bytes array
        for (byte b : value) {
            src[offset++] = b;
        }
        return value.length;
    }

    public static int writeB(byte[] src, int offset, int size) {
        for (int i = 0; i < size; i++) {
            src[offset++] = (byte) 0x00;
        }
        return size;
    }

    public static int writeB(OutputStream dest, int len) throws IOException {
        writeB(dest, new byte[len], 0, len);
        return len;
    }

    public static int writeB(OutputStream dest, byte[] src, int offset, int len) throws IOException {
        for (int i = offset; i < offset + len; i++) {
            dest.write(src, offset, len);
        }
        return len;
    }

    public static int writeB(OutputStream dest, byte[] src) throws IOException {
        dest.write(src);
        return src.length;
    }

    public static int writeU(byte[] src, int offset, String text) throws UnsupportedEncodingException {
        final byte[] unicodeBytes = text.getBytes("Unicode");
        for (int i = 3; i < unicodeBytes.length; i++) {
            src[offset++] = unicodeBytes[i];
        }
        IOUtil.writeC(src, offset++, (byte) 0x00);
        IOUtil.writeC(src, offset++, (byte) 0x00);
        IOUtil.writeC(src, offset, (byte) 0x00);
        return unicodeBytes.length;
    }

    private static final byte[] UNICODE_STRING_END = new byte[]{0x00, 0x00, 0x00};

    public static int writeU(OutputStream src, String text) throws IOException {
        final byte[] unicodeBytes = text.getBytes("Unicode");
        src.write(unicodeBytes, 3, unicodeBytes.length - 3);
        src.write(UNICODE_STRING_END);
        return unicodeBytes.length;
    }

    public static int writeS(byte[] src, int offset, String text) {
        for (int i = 0; i < text.length(); i++) {
            src[offset + i] = (byte) text.charAt(i);
        }
        offset += text.length();
        IOUtil.writeC(src, offset++, (byte) 0x00);
        IOUtil.writeC(src, offset, (byte) 0x00);
        return text.length() + 2;
    }

}
