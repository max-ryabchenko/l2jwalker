package com.l2jwalker.packet.io.deserialize;

import com.l2jwalker.packet.io.AbstractIOTest;
import com.l2jwalker.packet.io.Serializer;
import com.l2jwalker.util.io.IOUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PrimitiveDeserializeTest extends AbstractIOTest {

    Serializer serializer;
    ByteArrayInputStream src;
    Map<String, Object> data;

    byte[] buff;

    byte byte1;
    short short1;
    int int1;
    long long1;
    double double1;
    String str1;

    @Before
    public void before() {
        serializer = new Serializer();
        buff = new byte[128];
        src = new ByteArrayInputStream(buff);
        data = new HashMap<String, Object>();

        byte1 = randomByte();
        short1 = randomShort();
        int1 = randomInt();
        long1 = randomLong();
        double1 = randomDouble();
        str1 = randomString();
    }

    @Test
    public void testByte() throws IOException {
        IOUtil.writeC(buff, 0, byte1);
        assertEquals(1, serializer.deserializeArray(src, data, getTemplate("../primitive/byte.js"), 1));
        assertEquals(byte1, data.get("testByte"));
    }

    @Test
    public void testShort() throws IOException {
        IOUtil.writeW(buff, 0, short1);
        assertEquals(2, serializer.deserializeArray(src, data, getTemplate("../primitive/short.js"), 1));
        assertEquals(short1, data.get("testShort"));
    }

    @Test
    public void testInt() throws IOException {
        IOUtil.writeD(buff, 0, int1);
        assertEquals(4, serializer.deserializeArray(src, data, getTemplate("../primitive/int.js"), 1));
        assertEquals(int1, data.get("testInt"));
    }

    @Test
    public void testDouble() throws IOException {
        IOUtil.writeF(buff, 0, double1);
        assertEquals(8, serializer.deserializeArray(src, data, getTemplate("../primitive/double.js"), 1));
        assertEquals(double1, data.get("testDouble"));
    }

    @Test
    public void testString() throws IOException {
        IOUtil.writeU(buff, 0, str1);
        assertEquals(2 + 2 * str1.length(), serializer.deserializeArray(src, data, getTemplate("../primitive/unicode-string.js"), 1));
        assertEquals(str1, data.get("testUnicodeString"));
    }

    @Test
    public void testByteArray() throws IOException {
        byte[] array = randomBytes(17);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtil.writeB(out, array);
        assertEquals(17, serializer.deserializeArray(new ByteArrayInputStream(out.toByteArray()), data, getTemplate("../primitive/byte-array.js"), 1));
        assertArrayEquals(array, (byte[])data.get("testByteArray"));
    }

}
