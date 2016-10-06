package com.l2jwalker.packet.io.deserialize

import com.l2jwalker.packet.io.AbstractIOTest
import com.l2jwalker.packet.io.Serializer
import com.l2jwalker.packet.io.TestObject1
import com.l2jwalker.packet.io.TestObject2
import com.l2jwalker.util.io.IOUtil
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

import static org.junit.Assert.assertEquals

@RunWith(MockitoJUnitRunner.class)
class ObjectDeserializeTest extends AbstractIOTest {

    Serializer serializer

    Map<String, Object> data
    TestObject1 testObject1

    @Before
    public void before() {
        serializer = new Serializer()
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

        ByteArrayOutputStream out = new ByteArrayOutputStream()

        int offset = 0

        offset += IOUtil.writeC(out, testObject1.byteTest1)
        offset += IOUtil.writeC(out, testObject1.byteTest2)
        offset += IOUtil.writeW(out, testObject1.shortTest1)
        offset += IOUtil.writeW(out, testObject1.shortTest2)
        offset += IOUtil.writeD(out, testObject1.intTest1)
        offset += IOUtil.writeD(out, testObject1.intTest2)
        offset += IOUtil.writeQ(out, testObject1.longTest1)
        offset += IOUtil.writeQ(out, testObject1.longTest2)
        offset += IOUtil.writeF(out, testObject1.doubleTest1)
        offset += IOUtil.writeF(out, testObject1.doubleTest2)
        offset += IOUtil.writeU(out, testObject1.stringTest)

        offset += IOUtil.writeU(out, testObject1.testObject2.str1)
        offset += IOUtil.writeF(out, testObject1.testObject2.double1)

        byte[] result = out.toByteArray()

        Map data = [:]

        assertEquals(offset, serializer.deserializeArray(new ByteArrayInputStream(result), data, getTemplate("../complex/object.js"), 1))

        log.info(data)

        assertEquals(testObject1.byteTest1, data.get("testObject").byteTest1)
        assertEquals(testObject1.byteTest2, data.get("testObject").byteTest2)
        assertEquals(testObject1.shortTest1, data.get("testObject").shortTest1)
        assertEquals(testObject1.shortTest2, data.get("testObject").shortTest2)
        assertEquals(testObject1.intTest1, data.get("testObject").intTest1)
        assertEquals(testObject1.intTest2, data.get("testObject").intTest2)
        assertEquals(testObject1.longTest1, data.get("testObject").longTest1)
        assertEquals(testObject1.longTest2, data.get("testObject").longTest2)
        assertEquals(Double.doubleToLongBits(testObject1.doubleTest1), Double.doubleToLongBits(data.get("testObject").doubleTest1))
        assertEquals(Double.doubleToLongBits(testObject1.doubleTest2), Double.doubleToLongBits(data.get("testObject").doubleTest2))
        assertEquals(testObject1.stringTest, data.get("testObject").stringTest)
        assertEquals(testObject1.testObject2.str1, data.get("testObject").testObject2.str1)
        assertEquals(Double.doubleToLongBits(testObject1.testObject2.double1), Double.doubleToLongBits(data.get("testObject").testObject2.double1))

    }

}
