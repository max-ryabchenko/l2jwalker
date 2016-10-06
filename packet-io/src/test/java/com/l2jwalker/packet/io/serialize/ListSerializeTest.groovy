package com.l2jwalker.packet.io.serialize

import com.l2jwalker.packet.io.AbstractIOTest
import com.l2jwalker.packet.io.Serializer
import com.l2jwalker.packet.io.TestObject2
import com.l2jwalker.util.Util
import com.l2jwalker.util.io.IOUtil
import org.apache.commons.io.IOUtils
import org.json.JSONArray
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

import static org.junit.Assert.assertEquals

@RunWith(MockitoJUnitRunner.class)
class ListSerializeTest extends AbstractIOTest {

    Serializer serializer
    ByteArrayOutputStream out

    List testObject2list
    int len;

    @Before
    public void before() {
        serializer = new Serializer()
        out = new ByteArrayOutputStream()
        testObject2list = []
        for (int i = 5 + random.nextInt(10); i > 0; i--) {
            TestObject2 testObject2 = new TestObject2(
                    str1: randomString(),
                    double1: randomDouble()
            )
            testObject2list.add(testObject2)
            len += 10 + 2 * testObject2.str1.length()
        }
    }

    @Test
    public void test1() {

        assertEquals(1 + len, serializer.serializeArray(out, [list: testObject2list], getTemplate("../repeat/list1.js"), 1))

        byte[] result = out.toByteArray()
        log.info(Util.byteArrayToHexString(out.toByteArray()))

        int offset = 0
        assertEquals(testObject2list.size(), IOUtil.readC(result, offset++))

        for (int i = 0; i < testObject2list.size(); i++) {
            assertEquals(testObject2list.get(i).str1, IOUtil.readU(result, offset))
            offset += 2 + 2 * testObject2list.get(i).str1.length()
            assertEquals(Double.doubleToLongBits(testObject2list.get(i).double1), Double.doubleToLongBits(IOUtil.readF(result, offset)))
            offset += 8
        }

        assertEquals(offset, result.length)
    }

    @Test
    public void test2() {

        assertEquals(2 + len, serializer.serializeArray(out, [list: testObject2list], getTemplate("../repeat/list2.js"), 1))

        byte[] result = out.toByteArray()
        log.info(Util.byteArrayToHexString(out.toByteArray()))

        int offset = 0
        assertEquals(testObject2list.size(), IOUtil.readC(result, offset))
        offset += 2

        for (int i = 0; i < testObject2list.size(); i++) {
            assertEquals(testObject2list.get(i).str1, IOUtil.readU(result, offset))
            offset += 2 + 2 * testObject2list.get(i).str1.length()
            assertEquals(Double.doubleToLongBits(testObject2list.get(i).double1), Double.doubleToLongBits(IOUtil.readF(result, offset)))
            offset += 8
        }

        assertEquals(offset, result.length)
    }

    @Test
    public void test4() {

        assertEquals(4 + len, serializer.serializeArray(out, [list: testObject2list], getTemplate("../repeat/list4.js"), 1))

        byte[] result = out.toByteArray()
        log.info(Util.byteArrayToHexString(out.toByteArray()))

        int offset = 0
        assertEquals(testObject2list.size(), IOUtil.readC(result, offset))
        offset += 4

        for (int i = 0; i < testObject2list.size(); i++) {
            assertEquals(testObject2list.get(i).str1, IOUtil.readU(result, offset))
            offset += 2 + 2 * testObject2list.get(i).str1.length()
            assertEquals(Double.doubleToLongBits(testObject2list.get(i).double1), Double.doubleToLongBits(IOUtil.readF(result, offset)))
            offset += 8
        }

        assertEquals(offset, result.length)
    }

    @Test
    public void test8() {

        assertEquals(8 + len, serializer.serializeArray(out, [list: testObject2list], getTemplate("../repeat/list8.js"), 1))

        byte[] result = out.toByteArray()
        log.info(Util.byteArrayToHexString(out.toByteArray()))

        int offset = 0
        assertEquals(testObject2list.size(), IOUtil.readC(result, offset))
        offset += 8

        for (int i = 0; i < testObject2list.size(); i++) {
            assertEquals(testObject2list.get(i).str1, IOUtil.readU(result, offset))
            offset += 2 + 2 * testObject2list.get(i).str1.length()
            assertEquals(Double.doubleToLongBits(testObject2list.get(i).double1), Double.doubleToLongBits(IOUtil.readF(result, offset)))
            offset += 8
        }

        assertEquals(offset, result.length)
    }

}
