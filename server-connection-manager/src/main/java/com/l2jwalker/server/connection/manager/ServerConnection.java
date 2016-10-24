package com.l2jwalker.server.connection.manager;

import java.util.Random;

public class ServerConnection {
    private String login;
    private String password;
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
