import com.l2jwalker.server.connection.manager.ServerConnectionManager;
import com.l2jwalker.util.Util;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServerConnectionManagerTest extends Assert {

    private static final Logger log = Logger.getLogger(ServerConnectionManagerTest.class);

    private byte[] bytesPacket;

    @Test
    public void testManager() throws IOException {
        bytesPacket = new byte[10];
        Random rand = new Random();
        rand.nextBytes(bytesPacket);

        byte[] bytesExpected = new byte[10];
        System.arraycopy(bytesPacket, 0, bytesExpected, 0, bytesPacket.length);
        bytesExpected[0] = (byte) 255;

        ServerConnectionManager manager = ServerConnectionManager.getInstance();
        ServerSocket ss = new ServerSocket(27015);
        manager.addConnection("127.0.0.1", 27015);

        Socket s = ss.accept();
        assertEquals(1, manager.getMap().size());
        OutputStream os = s.getOutputStream();
        InputStream is = s.getInputStream();
        os.write(bytesPacket);
        log.info("Send: " + Util.byteArrayToHexString(bytesPacket));
        is.read(bytesPacket);
        log.info("Read: " + Util.byteArrayToHexString(bytesPacket));
        for (int i = 0; i<bytesExpected.length; i++) {
            assertEquals(bytesExpected[i], bytesPacket[i]);
        }
    }
}
