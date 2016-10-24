package com.l2jwalker.server.connection.manager;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

import static java.nio.channels.SelectionKey.OP_CONNECT;
import static java.nio.channels.SelectionKey.OP_READ;
import static java.nio.channels.SelectionKey.OP_WRITE;

public class ServerConnectionManager implements Runnable {
    private static ServerConnectionManager instance = new ServerConnectionManager();

    public static ServerConnectionManager getInstance() {
        return instance;
    }

    private static final Logger log = Logger.getLogger(ServerConnectionManager.class);

    //TODO idk how many bytes need to be for pre-allocation. So 2 mb.
    private final ByteBuffer buffer = ByteBuffer.allocate(16384);

    private Selector selector;
    private int numbersOfChannel = 0;

    private Map<SocketChannel, ServerConnection> map = new HashMap<SocketChannel, ServerConnection>();

    private ServerConnectionManager() {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            log.error(e);
        }
        new Thread(this).start();
    }

    public void run() {
        while(true){
            if(numbersOfChannel > 0) {
                break;
            }
        }
        try {
            while(true) {
                int num = selector.select();
                if (num == 0) {
                    continue;
                }
                selector.select();
                for (SelectionKey selectionKey : selector.selectedKeys()){
                    if (selectionKey.isConnectable()) {
                        ((SocketChannel) selectionKey.channel()).finishConnect();
                        selectionKey.interestOps(OP_READ);
                    } else if (selectionKey.isReadable()) {
                        buffer.clear();
                        ((SocketChannel) selectionKey.channel()).read(buffer);
                        /*
                        * For testing, process will return echo, but first byte will be changed as 255
                        */
                        map.get(selectionKey.channel()).setProcess(buffer.array());

                        selectionKey.interestOps(OP_WRITE);
                    } else if (selectionKey.isWritable()) {
                        ((SocketChannel) selectionKey.channel()).write(
                                ByteBuffer.wrap(map.get(selectionKey.channel()).getProcess())
                        );
                    }
                }
            }
        } catch (IOException e) {
            log.error(e);
        }
    }

    public int addConnection(String host, int port) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.register(selector, OP_CONNECT);
        log.info("Trying connect to " + host + ":" + port);
        channel.connect(new InetSocketAddress(host, port));
        //TODO login, password
        ServerConnection serverConnection = new ServerConnection("login", "password");
        map.put(channel, serverConnection);
        return ++numbersOfChannel;
    }

    public Map<SocketChannel, ServerConnection> getMap() {
        return map;
    }

}
