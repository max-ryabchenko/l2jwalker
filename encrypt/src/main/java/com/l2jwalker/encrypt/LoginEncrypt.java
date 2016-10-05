package com.l2jwalker.encrypt;

import org.apache.log4j.Logger;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.Random;

public class LoginEncrypt extends PacketEncrypter {

    private static final Logger LOG = Logger.getLogger(LoginEncrypt.class);

    private static final Random random = new Random();
    private static KeyPairGenerator keygen;
    private static RSAKeyGenParameterSpec spec;
    static {

        spec = new RSAKeyGenParameterSpec(1024, RSAKeyGenParameterSpec.F4);
        try {
            keygen = KeyPairGenerator.getInstance("RSA");
            keygen.initialize(spec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

    }

    private static int currentSessionId = -1;

    private static int generateSessionId(){
        return ++currentSessionId;
    }

    public LoginEncrypt() {
        encrypterType = EncryptType.LoginServerEncrypt;
    }

    private byte[] loginBlowFishKey;
    private byte[] publicRSAKey;
    private byte[] privateRSAKey;
    private int initXorKey;
    private int protocolId = 0;
    private int sessionId = 0;
    private int sessionKey1Part1 = 0x629CC8F5; //655E62B8 F5C89C62
    private int sessionKey1Part2 = 0x6A6CBA07; //A2BA97BA 07BA6C6A
    private int sessionKey2Part1 = 0;
    private int sessionKey2Part2 = 0;

    private boolean sessionKey1Part1Set = false;
    private boolean sessionKey1Part2Set = false;
    private boolean sessionKey2Part1Set = false;
    private boolean sessionKey2Part2Set = false;

    public void generate(){
        KeyPair keyPair = keygen.generateKeyPair();
        this.setPublicRSAKey(((RSAPublicKey)keyPair.getPublic()).getModulus().toByteArray());
        this.privateRSAKey = ((RSAPrivateKey)keyPair.getPrivate()).getModulus().toByteArray();
        this.setSessionId(generateSessionId());
        this.setLoginBlowFishKey(new byte[16]);
        random.nextBytes(this.getLoginBlowFishKey());
        this.setSessionKey1Part1(random.nextInt());
        this.setSessionKey1Part2(random.nextInt());
        this.setSessionKey2Part1(random.nextInt());
        this.setSessionKey2Part2(random.nextInt());
        this.initXorKey = random.nextInt();
    }

    public int getProtocolId() {
        if (0 == protocolId) {
            LOG.warn("protocolId=0");
        }
        return protocolId;
    }

    public int getSessionKey1Part1() {
        if (0 == sessionKey1Part1) {
            LOG.warn("sessionKey1Part1=0");
        }
        return sessionKey1Part1;
    }

    public int getSessionKey1Part2() {
        if (0 == sessionKey1Part2) {
            LOG.warn("sessionKey1Part2=0");
        }
        return sessionKey1Part2;
    }

    public int getSessionKey2Part1() {
        if (0 == sessionKey2Part1) {
            LOG.warn("sessionKey2Part1=0");
        }
        return sessionKey2Part1;
    }

    public int getSessionKey2Part2() {
        if (0 == sessionKey2Part2) {
            LOG.warn("sessionKey2Part2=0");
        }
        return sessionKey2Part2;
    }

    public boolean isSessionKey1Part1Set() {
        return sessionKey1Part1Set;
    }

    public boolean isSessionKey1Part2Set() {
        return sessionKey1Part2Set;
    }

    public boolean isSessionKey2Part1Set() {
        return sessionKey2Part1Set;
    }

    public boolean isSessionKey2Part2Set() {
        return sessionKey2Part2Set;
    }

    public void setProtocolId(int protocolId) {
        this.protocolId = protocolId;
    }

    public int getInitXorKey() {
        return initXorKey;
    }

    public byte[] getPublicRSAKey() {
        return publicRSAKey;
    }

    public void setPublicRSAKey(byte[] publicRSAKey) {
        this.publicRSAKey = publicRSAKey;
    }

    public byte[] getLoginBlowFishKey() {
        return loginBlowFishKey;
    }

    public void setLoginBlowFishKey(byte[] loginBlowFishKey) {
        this.loginBlowFishKey = loginBlowFishKey;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public void setSessionKey1Part1(int sessionKey1Part1) {
        this.sessionKey1Part1 = sessionKey1Part1;
    }

    public void setSessionKey1Part2(int sessionKey1Part2) {
        this.sessionKey1Part2 = sessionKey1Part2;
    }

    public void setSessionKey2Part1(int sessionKey2Part1) {
        this.sessionKey2Part1 = sessionKey2Part1;
    }

    public void setSessionKey2Part2(int sessionKey2Part2) {
        this.sessionKey2Part2 = sessionKey2Part2;
    }
}
