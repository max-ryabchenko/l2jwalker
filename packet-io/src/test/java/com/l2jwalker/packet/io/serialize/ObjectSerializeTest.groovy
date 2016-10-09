package com.l2jwalker.packet.io.serialize

import com.l2jwalker.packet.io.AbstractIOTest
import com.l2jwalker.packet.io.PacketIO
import com.l2jwalker.packet.io.TestObject1
import com.l2jwalker.packet.io.TestObject2
import com.l2jwalker.util.Util
import com.l2jwalker.util.io.IOUtil
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

import static org.junit.Assert.assertEquals

@RunWith(MockitoJUnitRunner.class)
class ObjectSerializeTest extends AbstractIOTest {

    PacketIO serializer
    ByteArrayOutputStream out

    TestObject1 testObject1

    Map<String, Object> data

    @Before
    public void before() {
        serializer = new PacketIO()
        out = new ByteArrayOutputStream()
        testObject1 = new TestObject1(
                byteTest1: randomByte(),
                byteTest2: randomByte(),
                shortTest1: randomShort(),
                shortTest2: randomShort(),
                intTest1: randomInt(),
                intTest2: randomInt(),
                longTest1: randomLong(),
                longTest2: randomLong(),
                doubleTest1: randomDouble(),
                doubleTest2: randomDouble(),
                stringTest: randomString(),
                testObject2: new TestObject2(
                        str1: randomString(),
                        double1: randomDouble()
                )
        )
        data = ["testObject": testObject1]
    }

    @Test
    public void test1() {

        assertEquals(
                58 + 2 * (testObject1.getStringTest().length() + testObject1.getTestObject2().getStr1().length()),
                serializer.writeArray(out, data, getTemplate("../complex/object.js"), 1))

        byte[] result = out.toByteArray()
        log.info(Util.byteArrayToHexString(out.toByteArray()))

        int offset = 0;
        assertEquals(testObject1.byteTest1, IOUtil.readC(result, offset++))
        assertEquals(testObject1.byteTest2, IOUtil.readC(result, offset++))
        assertEquals(testObject1.shortTest1, IOUtil.readW(result, offset))
        offset += 2
        assertEquals(testObject1.shortTest2, IOUtil.readW(result, offset))
        offset += 2
        assertEquals(testObject1.intTest1, IOUtil.readD(result, offset))
        offset += 4
        assertEquals(testObject1.intTest2, IOUtil.readD(result, offset))
        offset += 4
        assertEquals(testObject1.longTest1, IOUtil.readQ(result, offset))
        offset += 8
        assertEquals(testObject1.longTest2, IOUtil.readQ(result, offset))
        offset += 8
        assertEquals(Double.doubleToLongBits(testObject1.doubleTest1), Double.doubleToLongBits(IOUtil.readF(result, offset)))
        offset += 8
        assertEquals(Double.doubleToLongBits(testObject1.doubleTest2), Double.doubleToLongBits(IOUtil.readF(result, offset)))
        offset += 8
        assertEquals(testObject1.stringTest, IOUtil.readU(result, offset))
        offset += 2 + 2 * testObject1.stringTest.length()
        assertEquals(testObject1.testObject2.str1, IOUtil.readU(result, offset))
        offset += 2 + 2 * testObject1.testObject2.str1.length()
        assertEquals(Double.doubleToLongBits(testObject1.testObject2.double1), Double.doubleToLongBits(IOUtil.readF(result, offset)))
    }

}
