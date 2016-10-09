package com.l2jwalker.packet.io.serialize;

import com.l2jwalker.packet.io.AbstractIOTest;
import com.l2jwalker.packet.io.PacketIO;
import com.l2jwalker.util.Util;
import com.l2jwalker.util.io.IOUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CombinedPrimitiveSerializeTest extends AbstractIOTest {

    PacketIO packetIO;
    ByteArrayOutputStream out;
    Map<String, Object> data;

    @Before
    public void before() {
        packetIO = new PacketIO();
        out = new ByteArrayOutputStream();
        data = new HashMap<String, Object>() {{
            put("testByte1", randomByte());
            put("testByte2", randomByte());
            put("testByte3", randomByte());
            put("testString1", randomString());
            put("testString2", randomString());
            put("testString3", randomString());
            put("testInt1", randomInt());
            put("testInt2", randomInt());
            put("testInt3", randomInt());
            put("testShort1", randomShort());
            put("testShort2", randomShort());
            put("testShort3", randomShort());
            put("testLong1", randomLong());
            put("testLong2", randomLong());
            put("testLong3", randomLong());
            put("testDouble1", randomDouble());
            put("testDouble2", randomDouble());
            put("testDouble3", randomDouble());
        }};
    }

    @Test
    public void test1() throws IOException {

        assertEquals(25 + 2 * ((String) data.get("testString1")).length(), packetIO.writeArray(out, data, getTemplate("../combined-primitive/test1.json"), 1));

        InputStream result = new ByteArrayInputStream(out.toByteArray());
        log.info(Util.byteArrayToHexString(out.toByteArray()));
        assertEquals(data.get("testByte1"), IOUtil.readC(result));
        assertEquals(data.get("testString1"), IOUtil.readU(result));
        assertEquals(data.get("testInt1"), IOUtil.readD(result));
        assertEquals(data.get("testShort1"), IOUtil.readW(result));
        assertEquals(data.get("testDouble1"), IOUtil.readF(result));
        assertEquals(data.get("testLong1"), IOUtil.readQ(result));

    }

    @Test
    public void test2() throws IOException {

        assertEquals(
                75 + 2 * (((String) data.get("testString1")).length() + ((String) data.get("testString2")).length() + ((String) data.get("testString3")).length()),
                packetIO.writeArray(out, data, getTemplate("../combined-primitive/test2.json"), 1));

        InputStream result = new ByteArrayInputStream(out.toByteArray());
        log.info(Util.byteArrayToHexString(out.toByteArray()));

        assertEquals(data.get("testString1"), IOUtil.readU(result));
        assertEquals(data.get("testByte1"), IOUtil.readC(result));
        assertEquals(data.get("testByte2"), IOUtil.readC(result));
        assertEquals(data.get("testShort2"), IOUtil.readW(result));
        assertEquals(data.get("testString2"), IOUtil.readU(result));
        assertEquals(data.get("testDouble1"), IOUtil.readF(result));
        assertEquals(data.get("testInt1"), IOUtil.readD(result));
        assertEquals(data.get("testShort1"), IOUtil.readW(result));
        assertEquals(data.get("testLong1"), IOUtil.readQ(result));
        assertEquals(data.get("testDouble2"), IOUtil.readF(result));
        assertEquals(data.get("testInt2"), IOUtil.readD(result));
        assertEquals(data.get("testByte3"), IOUtil.readC(result));
        assertEquals(data.get("testLong2"), IOUtil.readQ(result));
        assertEquals(data.get("testDouble3"), IOUtil.readF(result));
        assertEquals(data.get("testInt3"), IOUtil.readD(result));
        assertEquals(data.get("testLong3"), IOUtil.readQ(result));
        assertEquals(data.get("testShort3"), IOUtil.readW(result));
        assertEquals(data.get("testString3"), IOUtil.readU(result));

    }

}
