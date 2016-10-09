package com.l2jwalker.packet.io.serialize;

import com.l2jwalker.packet.io.AbstractIOTest;
import com.l2jwalker.packet.io.PacketIO;
import com.l2jwalker.util.Util;
import com.l2jwalker.util.io.IOUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PrimitiveSerializeTest extends AbstractIOTest {

    PacketIO packetIO;
    ByteArrayOutputStream out;
    Map<String, Object> data;
    byte testByte;
    short testShort;
    int testInt;
    long testLong;
    double testDouble;
    String testUnicodeString;
    Map<String, Object> singleMap;

    @Before
    public void before() {
        packetIO = new PacketIO();
        out = new ByteArrayOutputStream();
        testByte = randomByte();
        testShort = randomShort();
        testInt = randomInt();
        testLong = randomLong();
        testUnicodeString = randomString();
        testDouble = randomDouble();
        data = new HashMap<String, Object>() {{
            put("testByte", testByte);
            put("testShort", testShort);
            put("testInt", testInt);
            put("testLong", testLong);
            put("testUnicodeString", testUnicodeString);
            put("testDouble", testDouble);
        }};
        singleMap = new HashMap<String, Object>();
    }

    @Test
    public void testByte() throws IOException {
        assertEquals(1, packetIO.writeArray(out, data, getTemplate("../primitive/byte.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(testByte, IOUtil.readC(result, 0));

    }

    @Test
    public void testByteNull() throws IOException {
        singleMap.put("testByte", null);

        assertEquals(1, packetIO.writeArray(out, singleMap, getTemplate("../primitive/byte.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x00, IOUtil.readC(result, 0));

    }

    @Test
    public void testByteMiss() throws IOException {

        assertEquals(1, packetIO.writeArray(out, singleMap, getTemplate("../primitive/byte.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x00, IOUtil.readC(result, 0));

    }

    @Test
    public void testShort() throws IOException {

        assertEquals(2, packetIO.writeArray(out, data, getTemplate("../primitive/short.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(testShort, IOUtil.readW(result, 0));

    }

    @Test
    public void testShortNull() throws IOException {
        singleMap.put("testShort", null);

        assertEquals(2, packetIO.writeArray(out, singleMap, getTemplate("../primitive/short.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x0000, IOUtil.readW(result, 0));

    }

    @Test
    public void testShortMiss() throws IOException {

        assertEquals(2, packetIO.writeArray(out, singleMap, getTemplate("../primitive/short.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x0000, IOUtil.readW(result, 0));

    }

    @Test
    public void testInt() throws IOException {

        assertEquals(4, packetIO.writeArray(out, data, getTemplate("../primitive/int.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(testInt, IOUtil.readD(result, 0));

    }

    @Test
    public void testIntNull() throws IOException {
        singleMap.put("testInt", null);

        assertEquals(4, packetIO.writeArray(out, singleMap, getTemplate("../primitive/int.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x000000, IOUtil.readD(result, 0));

    }

    @Test
    public void testIntMiss() throws IOException {

        assertEquals(4, packetIO.writeArray(out, singleMap, getTemplate("../primitive/int.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x000000, IOUtil.readD(result, 0));

    }

    @Test
    public void testLong() throws IOException {

        assertEquals(8, packetIO.writeArray(out, data, getTemplate("../primitive/long.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(testLong, IOUtil.readQ(result, 0));

    }

    @Test
    public void testLongNull() throws IOException {
        singleMap.put("testLong", null);

        assertEquals(8, packetIO.writeArray(out, singleMap, getTemplate("../primitive/long.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x0000000000000000, IOUtil.readQ(result, 0));

    }

    @Test
    public void testLongMiss() throws IOException {

        assertEquals(8, packetIO.writeArray(out, singleMap, getTemplate("../primitive/long.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x0000000000000000, IOUtil.readQ(result, 0));

    }

    @Test
    public void testUnicodeString() throws IOException {

        assertEquals(2 + 2 * testUnicodeString.length(), packetIO.writeArray(out, data, getTemplate("../primitive/unicode-string.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(testUnicodeString, IOUtil.readU(result, 0));

    }

    @Test
    public void testDouble() throws IOException {

        assertEquals(8, packetIO.writeArray(out, data, getTemplate("../primitive/double.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(Double.doubleToRawLongBits(testDouble), Double.doubleToRawLongBits(IOUtil.readF(result, 0)));

    }

    @Test
    public void testDoubleNull() throws IOException {
        singleMap.put("testDouble", null);

        assertEquals(8, packetIO.writeArray(out, singleMap, getTemplate("../primitive/double.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(Double.doubleToRawLongBits(0d), Double.doubleToRawLongBits(IOUtil.readF(result, 0)));

    }

    @Test
    public void testDoubleMiss() throws IOException {

        assertEquals(8, packetIO.writeArray(out, singleMap, getTemplate("../primitive/double.js"), 1));

        byte[] result = out.toByteArray();
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(Double.doubleToRawLongBits(0d), Double.doubleToRawLongBits(IOUtil.readF(result, 0)));

    }

    @Test
    public void testBool1True() throws IOException {
        singleMap.put("testBool", true);

        assertEquals(1, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool1.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(1, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x01, IOUtil.readC(result, 0));
    }

    @Test
    public void testBool1False() throws IOException {
        singleMap.put("testBool", false);

        assertEquals(1, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool1.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(1, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x00, IOUtil.readC(result, 0));
    }

    @Test
    public void testBool1Miss() throws IOException {

        assertEquals(1, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool1.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(1, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x00, IOUtil.readC(result, 0));
    }

    @Test
    public void testBool1Null() throws IOException {
        singleMap.put("testBool", null);

        assertEquals(1, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool1.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(1, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x00, IOUtil.readC(result, 0));
    }

    @Test
    public void testBool2True() throws IOException {
        singleMap.put("testBool", true);

        assertEquals(2, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool2.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(2, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x0001, IOUtil.readC(result, 0));
    }

    @Test
    public void testBool2False() throws IOException {
        singleMap.put("testBool", false);

        assertEquals(2, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool2.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(2, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x0000, IOUtil.readC(result, 0));
    }

    @Test
    public void testBool2Miss() throws IOException {

        assertEquals(2, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool2.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(2, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x0000, IOUtil.readC(result, 0));
    }

    @Test
    public void testBool2Null() throws IOException {
        singleMap.put("testBool", null);

        assertEquals(2, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool2.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(2, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x0000, IOUtil.readC(result, 0));
    }

    @Test
    public void testBool4True() throws IOException {
        singleMap.put("testBool", true);

        assertEquals(4, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool4.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(4, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x00000001, IOUtil.readC(result, 0));
    }

    @Test
    public void testBool4False() throws IOException {
        singleMap.put("testBool", false);

        assertEquals(4, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool4.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(4, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x00000000, IOUtil.readC(result, 0));
    }

    @Test
    public void testBool4Miss() throws IOException {

        assertEquals(4, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool4.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(4, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x00000000, IOUtil.readC(result, 0));
    }

    @Test
    public void testBool4Null() throws IOException {
        singleMap.put("testBool", null);

        assertEquals(4, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool4.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(4, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x00000000, IOUtil.readC(result, 0));
    }

    @Test
    public void testBool8True() throws IOException {
        singleMap.put("testBool", true);

        assertEquals(8, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool8.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(8, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x0000000000000001, IOUtil.readC(result, 0));
    }

    @Test
    public void testBool8False() throws IOException {
        singleMap.put("testBool", false);

        assertEquals(8, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool8.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(8, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x0000000000000000, IOUtil.readC(result, 0));
    }

    @Test
    public void testBool8Miss() throws IOException {

        assertEquals(8, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool8.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(8, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x0000000000000000, IOUtil.readC(result, 0));
    }

    @Test
    public void testBool8Null() throws IOException {
        singleMap.put("testBool", null);

        assertEquals(8, packetIO.writeArray(out, singleMap, getTemplate("../primitive/bool8.js"), 1));

        byte[] result = out.toByteArray();
        assertEquals(8, result.length);
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(0x0000000000000000, IOUtil.readC(result, 0));
    }

    @Test
    public void testByteArray() throws IOException {
        byte[] array = randomBytes(17);
        singleMap.put("testByteArray", array);

        assertEquals(17, packetIO.writeArray(out, singleMap, getTemplate("../primitive/byte-array.js"),1));
        assertArrayEquals(array, out.toByteArray());
    }

}
