package com.l2jwalker.server.connection.manager;

import com.l2jwalker.util.PropertiesReader;
import com.l2jwalker.util.Util;

public class ServerConnection {
    private String login;
    private String password;

    //private static final byte[] STATIC_BLOWFISH_KEY = Util.hexStringToByteArray(PropertiesReader.getProperty("static_blowfish_key"));
    private static final byte[] STATIC_BLOWFISH_KEY = Util.hexStringToByteArray("6B60CB5B82CE90B1CC2B6C556C6C6C6C");

    private byte[] process;

    public ServerConnection(String login, String password){
        this.login = login;
        this.password = password;

        //TODO all
    }

    public void setProcess(byte[] process) {
        this.process = process;
        process[0] = (byte) 255;
    }

    public byte[] getProcess(){
        return process;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
