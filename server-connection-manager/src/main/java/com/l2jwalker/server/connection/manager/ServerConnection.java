package com.l2jwalker.server.connection.manager;

public class ServerConnection {
    private String login;
    private String password;
    private byte[] process;

    public ServerConnection(String login, String password){
        this.login = login;
        this.password = password;

        //TODO all
        process = new byte[]{1, 2, 3, 4, 5};
    }

    //TODO for test
    public void changeProcess(){
        process = new byte[]{2, 5, 7, 1, 15};
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
