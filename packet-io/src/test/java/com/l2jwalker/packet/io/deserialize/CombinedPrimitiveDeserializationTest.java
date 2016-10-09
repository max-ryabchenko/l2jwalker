package com.l2jwalker.packet.io.deserialize;

import com.l2jwalker.packet.io.AbstractIOTest;
import com.l2jwalker.packet.io.PacketIO;
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

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CombinedPrimitiveDeserializationTest extends AbstractIOTest {

    PacketIO packetIO;
    ByteArrayInputStream src;
    Map<String, Object> data;

    byte[] buff;

    @Before
    public void before(){
        packetIO = new PacketIO();
        buff = new byte[1024];
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

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int offset = 0;

        offset += IOUtil.writeC(out,(Byte)data.get("testByte1"));
        offset += IOUtil.writeU(out,(String)data.get("testString1"));
        offset += IOUtil.writeD(out,(Integer)data.get("testInt1"));
        offset += IOUtil.writeW(out,(Short)data.get("testShort1"));
        offset += IOUtil.writeF(out,(Double)data.get("testDouble1"));
        offset += IOUtil.writeQ(out,(Long)data.get("testLong1"));

        src = new ByteArrayInputStream(out.toByteArray());
        Map<String, Object> result = new HashMap<String, Object>();
        assertEquals(offset, packetIO.readArray(src, result, getTemplate("../combined-primitive/test1.json"), 1));

        assertEquals(data.get("testByte1"), result.get("testByte1"));
        assertEquals(data.get("testShort1"), result.get("testShort1"));
        assertEquals(data.get("testInt1"), result.get("testInt1"));
        assertEquals(data.get("testLong1"), result.get("testLong1"));
        assertEquals(data.get("testDouble1"), result.get("testDouble1"));
        assertEquals(data.get("testString1"), result.get("testString1"));

    }

    @Test
    public void test2() throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int offset = 0;

        offset += IOUtil.writeU(out,(String)data.get("testString1"));
        offset += IOUtil.writeC(out,(Byte)data.get("testByte1"));
        offset += IOUtil.writeC(out,(Byte)data.get("testByte2"));
        offset += IOUtil.writeW(out,(Short)data.get("testShort2"));
        offset += IOUtil.writeU(out,(String)data.get("testString2"));
        offset += IOUtil.writeF(out,(Double)data.get("testDouble1"));
        offset += IOUtil.writeD(out,(Integer)data.get("testInt1"));
        offset += IOUtil.writeW(out,(Short)data.get("testShort1"));
        offset += IOUtil.writeQ(out,(Long)data.get("testLong1"));
        offset += IOUtil.writeF(out,(Double)data.get("testDouble2"));
        offset += IOUtil.writeD(out,(Integer)data.get("testInt2"));
        offset += IOUtil.writeC(out,(Byte)data.get("testByte3"));
        offset += IOUtil.writeQ(out,(Long)data.get("testLong2"));
        offset += IOUtil.writeF(out,(Double)data.get("testDouble3"));
        offset += IOUtil.writeD(out,(Integer)data.get("testInt3"));
        offset += IOUtil.writeQ(out,(Long)data.get("testLong3"));
        offset += IOUtil.writeW(out,(Short)data.get("testShort3"));
        offset += IOUtil.writeU(out,(String)data.get("testString3"));

        src = new ByteArrayInputStream(out.toByteArray());
        Map<String, Object> result = new HashMap<String, Object>();
        assertEquals(offset, packetIO.readArray(src, result, getTemplate("../combined-primitive/test2.json"), 1));

        assertEquals(data.get("testByte1"), result.get("testByte1"));
        assertEquals(data.get("testByte2"), result.get("testByte2"));
        assertEquals(data.get("testByte3"), result.get("testByte3"));
        assertEquals(data.get("testShort1"), result.get("testShort1"));
        assertEquals(data.get("testShort2"), result.get("testShort2"));
        assertEquals(data.get("testShort3"), result.get("testShort3"));
        assertEquals(data.get("testInt1"), result.get("testInt1"));
        assertEquals(data.get("testInt2"), result.get("testInt2"));
        assertEquals(data.get("testInt3"), result.get("testInt3"));
        assertEquals(data.get("testLong1"), result.get("testLong1"));
        assertEquals(data.get("testLong2"), result.get("testLong2"));
        assertEquals(data.get("testLong3"), result.get("testLong3"));
        assertEquals(data.get("testDouble1"), result.get("testDouble1"));
        assertEquals(data.get("testDouble2"), result.get("testDouble2"));
        assertEquals(data.get("testDouble3"), result.get("testDouble3"));
        assertEquals(data.get("testString1"), result.get("testString1"));
        assertEquals(data.get("testString2"), result.get("testString2"));
        assertEquals(data.get("testString3"), result.get("testString3"));

    }

}
