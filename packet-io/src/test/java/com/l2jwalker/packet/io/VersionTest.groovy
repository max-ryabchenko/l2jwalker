package com.l2jwalker.packet.io

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue

@RunWith(MockitoJUnitRunner.class)
class VersionTest {

    @Test
    public void test() {

        assertTrue(Serializer.checkVersion(null, 0))
        assertTrue(Serializer.checkVersion(null, 1))
        assertTrue(Serializer.checkVersion(null, 999))

        assertTrue(Serializer.checkVersion(0, 0))
        assertTrue(Serializer.checkVersion(1, 1))
        assertTrue(Serializer.checkVersion(999, 999))

        assertTrue(Serializer.checkVersion(0, null))
        assertTrue(Serializer.checkVersion(1, null))
        assertTrue(Serializer.checkVersion(999, null))

        assertTrue(Serializer.checkVersion(0, '0'))
        assertTrue(Serializer.checkVersion(0, '0+'))
        assertTrue(Serializer.checkVersion(0, '0-10'))
        assertTrue(Serializer.checkVersion(0, '0-0'))
        assertTrue(Serializer.checkVersion(1, '1'))
        assertTrue(Serializer.checkVersion(1, '1+'))
        assertTrue(Serializer.checkVersion(1, '0+'))
        assertTrue(Serializer.checkVersion(1, '1-9'))
        assertTrue(Serializer.checkVersion(1, '1-1'))
        assertTrue(Serializer.checkVersion(999, '999'))
        assertTrue(Serializer.checkVersion(999, '999+'))
        assertTrue(Serializer.checkVersion(999, '0+'))
        assertTrue(Serializer.checkVersion(999, '0-999'))
        assertTrue(Serializer.checkVersion(999, '999-9999'))


        assertFalse(Serializer.checkVersion(0, 1))
        assertFalse(Serializer.checkVersion(1, 0))
        assertFalse(Serializer.checkVersion(1, 3))
        assertFalse(Serializer.checkVersion(3, 1))
        assertFalse(Serializer.checkVersion(100, 99))
        assertFalse(Serializer.checkVersion(99, 100))

        assertFalse(Serializer.checkVersion(0, '1+'))
        assertFalse(Serializer.checkVersion(3, '5+'))
        assertFalse(Serializer.checkVersion(0, '1-9'))
        assertFalse(Serializer.checkVersion(1, '0'))
        assertFalse(Serializer.checkVersion(1, '2'))
        assertFalse(Serializer.checkVersion(9, 0))
        assertFalse(Serializer.checkVersion(9, '0-3'))

    }

}
