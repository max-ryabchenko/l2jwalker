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

/**
 * Created by Phill on 15.10.2016.
 * Connection manager.
 */
public class Manager implements Runnable {
    private static Manager ourInstance = new Manager();

    public static Manager getInstance() {
        return ourInstance;
    }

    private final Logger log = Logger.getLogger(getClass());

    //TODO idk how many bytes need to be for pre-allocation. So 2 mb.
    private final ByteBuffer buffer = ByteBuffer.allocate(16384);

    private Selector selector;
    private int numbersOfChannel = 0;

    private Map<SocketChannel, ServerConnection> map = new HashMap<SocketChannel, ServerConnection>();

    private Manager() {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(this).start();
    }

    //Magic here
    public void run() {
        log.warn("Waiting for Channel in run()");
        while(true){
            if(numbersOfChannel > 0){break;}
        }
        log.warn("Ready for working");
        try {
            while(true){
                int num = selector.select();
                if (num == 0){continue;}
                selector.select();
                //Looks for events
                for (SelectionKey selectionKey : selector.selectedKeys()){
                    //If we have connect - approve it, and change event key as OP_WRITE for send first packet of bytes
                    if(selectionKey.isConnectable()){
                        ((SocketChannel) selectionKey.channel()).finishConnect();
                        selectionKey.interestOps(OP_WRITE);
                    //If we have read-event - reading new data, mb do smth and change event key as OP_WRITE for answer
                    }else if(selectionKey.isReadable()){
                        buffer.clear();
                        ((SocketChannel) selectionKey.channel()).read(buffer);
                        selectionKey.interestOps(OP_WRITE);
                    //When we ready for answer - we take from map our ServerConnection by Channel, and request getProcess(), which return
                    //for us some bytes. We wrap it and send.
                    }else if(selectionKey.isWritable()){
                        ((SocketChannel) selectionKey.channel()).write(
                                ByteBuffer.wrap(map.get(selectionKey.channel()).getProcess())
                        );
                        selectionKey.interestOps(OP_READ);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int addConnection(String host, int port) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.register(selector, OP_CONNECT);
        log.warn("Trying connect to " + host + ":" + port);
        channel.connect(new InetSocketAddress(host, port));
        //TODO login, password
        ServerConnection serverConnection = new ServerConnection("login", "password");
        map.put(channel, serverConnection);
        return ++numbersOfChannel;
    }

    //TODO for test
    public void changeProcess(){
        for(Map.Entry<SocketChannel, ServerConnection> entry : map.entrySet()){
            entry.getValue().changeProcess();

        }
    }

    public Map<SocketChannel, ServerConnection> getMap() {
        return map;
    }

}
