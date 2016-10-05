package com.l2jwalker.encrypt;

import java.io.IOException;

public class NewEncrypt {

    private BlowfishEngine encrypt;
    private BlowfishEngine decrypt;

    /**
     * @param blowfishKey
     */
    public NewEncrypt(byte[] blowfishKey) {
        encrypt = new BlowfishEngine();
        encrypt.init(true, blowfishKey);
        decrypt = new BlowfishEngine();
        decrypt.init(false, blowfishKey);
    }

    public NewEncrypt(String key) {
        this(key.getBytes());
    }

    public static boolean verifyChecksum(byte[] raw) {
        return NewEncrypt.verifyChecksum(raw, 0, raw.length);
    }

    public static boolean verifyChecksum(byte[] raw, final int offset, final int size) {
        // check if size is multiple of 4 and if there is more then only the checksum
        if ((size & 3) != 0 || size <= 4) {
            return false;
        }

        long chksum = 0;
        int count = size - 4;
        long check = -1;
        int i;

        for (i = offset; i < count; i += 4) {
            check = raw[i] & 0xff;
            check |= raw[i + 1] << 8 & 0xff00;
            check |= raw[i + 2] << 0x10 & 0xff0000;
            check |= raw[i + 3] << 0x18 & 0xff000000;

            chksum ^= check;
        }

        check = raw[i] & 0xff;
        check |= raw[i + 1] << 8 & 0xff00;
        check |= raw[i + 2] << 0x10 & 0xff0000;
        check |= raw[i + 3] << 0x18 & 0xff000000;

        return check == chksum;
    }

    public static void appendChecksum(byte[] raw) {
        NewEncrypt.appendChecksum(raw, 0, raw.length);
    }

    public static void appendChecksum(byte[] raw, final int offset, final int size) {
        long chksum = 0;
        int count = size - 4;
        long ecx;
        int i;

        for (i = offset; i < count; i += 4) {
            ecx = raw[i] & 0xff;
            ecx |= raw[i + 1] << 8 & 0xff00;
            ecx |= raw[i + 2] << 0x10 & 0xff0000;
            ecx |= raw[i + 3] << 0x18 & 0xff000000;

            chksum ^= ecx;
        }

        ecx = raw[i] & 0xff;
        ecx |= raw[i + 1] << 8 & 0xff00;
        ecx |= raw[i + 2] << 0x10 & 0xff0000;
        ecx |= raw[i + 3] << 0x18 & 0xff000000;

        raw[i] = (byte) (chksum & 0xff);
        raw[i + 1] = (byte) (chksum >> 0x08 & 0xff);
        raw[i + 2] = (byte) (chksum >> 0x10 & 0xff);
        raw[i + 3] = (byte) (chksum >> 0x18 & 0xff);
    }

    /**
     * Packet is first XOR encoded with <code>key</code>
     * Then, the last 4 bytes are overwritten with the the XOR "key".
     * Thus this assume that there is enough room for the key to fit without overwriting data.
     *
     * @param raw The raw bytes to be encrypted
     * @param key The 4 bytes (int) XOR key
     */
    public static void encXORPass(byte[] raw, int key) {
        NewEncrypt.encXORPass(raw, 0, raw.length, key);
    }

    public static int byteArrayToInt(byte[] b, int offset) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i + offset] & 0x000000FF) << shift;
        }
        return value;
    }

    public static void encXORPass(byte[] raw, final int offset, final int size, byte[] key) {
        encXORPass(raw, offset, size, byteArrayToInt(key, 0));
    }

    public static void encXORPass(byte[] raw, final int offset, final int size, int key) {
        int stop = size - 8;
        int pos = 4 + offset;
        int edx;
        int ecx = key; // Initial xor key
//        _log.info("#Initial xor key=" + key);
        while (pos < stop) {
            edx = (raw[pos] & 0xFF);
            edx |= (raw[pos + 1] & 0xFF) << 8;
            edx |= (raw[pos + 2] & 0xFF) << 16;
            edx |= (raw[pos + 3] & 0xFF) << 24;

            ecx += edx;

            edx ^= ecx;

            raw[pos++] = (byte) (edx & 0xFF);
            raw[pos++] = (byte) (edx >> 8 & 0xFF);
            raw[pos++] = (byte) (edx >> 16 & 0xFF);
            raw[pos++] = (byte) (edx >> 24 & 0xFF);
        }

        raw[pos++] = (byte) (ecx & 0xFF);
        raw[pos++] = (byte) (ecx >> 8 & 0xFF);
        raw[pos++] = (byte) (ecx >> 16 & 0xFF);
        raw[pos++] = (byte) (ecx >> 24 & 0xFF);
    }

    public static void decXORPass(byte[] raw, int offset, int size, int key) {
        int stop = 4 + offset;
        int pos = size - 12;
        int edx;
        int ecx = key; // Initial xor key

        while (stop <= pos) {
            edx = (raw[pos] & 0xFF);
            edx |= (raw[pos + 1] & 0xFF) << 8;
            edx |= (raw[pos + 2] & 0xFF) << 16;
            edx |= (raw[pos + 3] & 0xFF) << 24;

            edx ^= ecx;

            ecx -= edx;

            raw[pos] = (byte) (edx & 0xFF);
            raw[pos + 1] = (byte) (edx >> 8 & 0xFF);
            raw[pos + 2] = (byte) (edx >> 16 & 0xFF);
            raw[pos + 3] = (byte) (edx >> 24 & 0xFF);
            pos -= 4;
        }
    }

    public byte[] decrypt(byte[] raw) throws IOException {
        byte[] result = new byte[raw.length];
        int count = raw.length / 8;

        for (int i = 0; i < count; i++) {
            decrypt.processBlock(raw, i * 8, result, i * 8);
        }

        return result;
    }

    public void decrypt(byte[] raw, final int offset, final int size) throws IOException {
        byte[] result = new byte[size];
        int count = size / 8;

        for (int i = 0; i < count; i++) {
            decrypt.processBlock(raw, offset + i * 8, result, i * 8);
        }
        // TODO can the encrypt and decrypt go direct to the array
        System.arraycopy(result, 0, raw, offset, size);
    }

    public byte[] encrypt(byte[] raw) throws IOException {
        int count = raw.length / 8;
        byte[] result = new byte[raw.length];
        for (int i = 0; i < count; i++) {
            encrypt.processBlock(raw, i * 8, result, i * 8);
        }

        return result;
    }

    public void encrypt(byte[] raw, final int offset, final int size) throws IOException {
        int count = size / 8;
        byte[] result = new byte[size];
        for (int i = 0; i < count; i++) {
            encrypt.processBlock(raw, offset + i * 8, result, i * 8);
        }
        // TODO can the encrypt and decrypt go direct to the array
        System.arraycopy(result, 0, raw, offset, size);
    }
}
