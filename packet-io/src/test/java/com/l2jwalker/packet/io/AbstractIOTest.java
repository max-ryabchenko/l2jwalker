package com.l2jwalker.packet.io;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Random;

public abstract class AbstractIOTest {

    protected Logger log = Logger.getLogger(this.getClass());
    protected Random random = new Random();

    protected String randomString() {
        return new BigInteger(130, random).toString(32);
    }

    protected byte randomByte() {
        return (byte) random.nextInt();
    }

    protected short randomShort() {
        return (short) random.nextInt();
    }

    protected int randomInt() {
        return random.nextInt();
    }

    protected long randomLong() {
        return random.nextLong();
    }

    protected double randomDouble() {
        return random.nextDouble();
    }

    protected byte[] randomBytes(int count){
        byte[] out = new byte[count];
        random.nextBytes(out);
        return out;
    }

    protected JSONArray getTemplate(String path) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(path);
        String jsonStr = new String(IOUtils.toByteArray(inputStream));
        return new JSONArray(jsonStr);
    }

}
