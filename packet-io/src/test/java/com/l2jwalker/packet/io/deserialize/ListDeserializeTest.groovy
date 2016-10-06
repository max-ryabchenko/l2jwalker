package com.l2jwalker.packet.io.deserialize

import com.l2jwalker.packet.io.AbstractIOTest
import com.l2jwalker.packet.io.Serializer
import com.l2jwalker.packet.io.TestObject2
import com.l2jwalker.util.io.IOUtil
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

import static org.junit.Assert.assertEquals

@RunWith(MockitoJUnitRunner.class)
class ListDeserializeTest extends AbstractIOTest {

    Serializer serializer

    List list
    Map data

    @Before
    public void before() {
        serializer = new Serializer();
        list = []
        data = [:]
        for (int i = 0; i < 5 + random.nextInt(5); i++) {
            list.add(new TestObject2(
                    double1: randomDouble(),
                    str1: randomString()
            ))
        }
    }

    @Test
    public void list1() {
        ByteArrayOutputStream out = new ByteArrayOutputStream()
        int offset = 0
        offset += IOUtil.writeC(out, list.size())
        list.each { TestObject2 object2 ->
            offset += IOUtil.writeU(out, object2.str1)
            offset += IOUtil.writeF(out, object2.double1)
        }
        byte[] result = out.toByteArray()

        assertEquals(offset, serializer.deserializeArray(new ByteArrayInputStream(result), data, getTemplate('../repeat/list1.js'), 1))
        assertEquals(['list': list], data)
    }

    @Test
    public void list2() {
        ByteArrayOutputStream out = new ByteArrayOutputStream()
        int offset = 0
        offset += IOUtil.writeW(out, list.size())
        list.each { TestObject2 object2 ->
            offset += IOUtil.writeU(out, object2.str1)
            offset += IOUtil.writeF(out, object2.double1)
        }
        byte[] result = out.toByteArray()

        assertEquals(offset, serializer.deserializeArray(new ByteArrayInputStream(result), data, getTemplate('../repeat/list2.js'), 1))
        assertEquals(['list': list], data)
    }

    @Test
    public void list4() {
        ByteArrayOutputStream out = new ByteArrayOutputStream()
        int offset = 0
        offset += IOUtil.writeD(out, list.size())
        list.each { TestObject2 object2 ->
            offset += IOUtil.writeU(out, object2.str1)
            offset += IOUtil.writeF(out, object2.double1)
        }
        byte[] result = out.toByteArray()

        assertEquals(offset, serializer.deserializeArray(new ByteArrayInputStream(result), data, getTemplate('../repeat/list4.js'), 1))
        assertEquals(['list': list], data)
    }

    @Test
    public void list8() {
        ByteArrayOutputStream out = new ByteArrayOutputStream()
        int offset = 0
        offset += IOUtil.writeQ(out, list.size())
        list.each { TestObject2 object2 ->
            offset += IOUtil.writeU(out, object2.str1)
            offset += IOUtil.writeF(out, object2.double1)
        }
        byte[] result = out.toByteArray()

        assertEquals(offset, serializer.deserializeArray(new ByteArrayInputStream(result), data, getTemplate('../repeat/list8.js'), 1))
        assertEquals(['list': list], data)
    }

}
