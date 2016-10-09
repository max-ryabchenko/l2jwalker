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

        assertTrue(PacketIO.checkVersion(null, 0))
        assertTrue(PacketIO.checkVersion(null, 1))
        assertTrue(PacketIO.checkVersion(null, 999))

        assertTrue(PacketIO.checkVersion(0, 0))
        assertTrue(PacketIO.checkVersion(1, 1))
        assertTrue(PacketIO.checkVersion(999, 999))

        assertTrue(PacketIO.checkVersion(0, null))
        assertTrue(PacketIO.checkVersion(1, null))
        assertTrue(PacketIO.checkVersion(999, null))

        assertTrue(PacketIO.checkVersion(0, '0'))
        assertTrue(PacketIO.checkVersion(0, '0+'))
        assertTrue(PacketIO.checkVersion(0, '0-10'))
        assertTrue(PacketIO.checkVersion(0, '0-0'))
        assertTrue(PacketIO.checkVersion(1, '1'))
        assertTrue(PacketIO.checkVersion(1, '1+'))
        assertTrue(PacketIO.checkVersion(1, '0+'))
        assertTrue(PacketIO.checkVersion(1, '1-9'))
        assertTrue(PacketIO.checkVersion(1, '1-1'))
        assertTrue(PacketIO.checkVersion(999, '999'))
        assertTrue(PacketIO.checkVersion(999, '999+'))
        assertTrue(PacketIO.checkVersion(999, '0+'))
        assertTrue(PacketIO.checkVersion(999, '0-999'))
        assertTrue(PacketIO.checkVersion(999, '999-9999'))


        assertFalse(PacketIO.checkVersion(0, 1))
        assertFalse(PacketIO.checkVersion(1, 0))
        assertFalse(PacketIO.checkVersion(1, 3))
        assertFalse(PacketIO.checkVersion(3, 1))
        assertFalse(PacketIO.checkVersion(100, 99))
        assertFalse(PacketIO.checkVersion(99, 100))

        assertFalse(PacketIO.checkVersion(0, '1+'))
        assertFalse(PacketIO.checkVersion(3, '5+'))
        assertFalse(PacketIO.checkVersion(0, '1-9'))
        assertFalse(PacketIO.checkVersion(1, '0'))
        assertFalse(PacketIO.checkVersion(1, '2'))
        assertFalse(PacketIO.checkVersion(9, 0))
        assertFalse(PacketIO.checkVersion(9, '0-3'))

    }

}
