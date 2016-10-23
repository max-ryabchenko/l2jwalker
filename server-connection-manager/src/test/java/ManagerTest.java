import com.l2jwalker.server.connection.manager.Manager;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Phill on 15.10.2016.
 */
public class ManagerTest extends Assert {
    @Test
    public void testManager() throws IOException {
        Manager manager = Manager.getInstance();
        ServerSocket ss = new ServerSocket(27015);
        manager.addConnection("127.0.0.1", 27015);

        Socket s = ss.accept();
        assertEquals(1, manager.getMap().size());
        InputStream is = s.getInputStream();
        //process return 5 bytes only. It will be changed.
        byte[] b = new byte[5];
        is.read(b);
        for(byte by : b){
            System.out.print(by);
        }
        System.out.println();
        manager.changeProcess();
        OutputStream os = s.getOutputStream();
        os.write(new byte[]{1, 3, 7});
        is.read(b);
        for(byte by : b){
            System.out.print(by);
        }

    }
}
