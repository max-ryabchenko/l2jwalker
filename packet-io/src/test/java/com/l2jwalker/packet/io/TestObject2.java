package com.l2jwalker.packet.io;

public class TestObject2 {

    private String str1;
    private double double1;

    @Override
    public boolean equals(Object o) {
        if (null == o || !(o instanceof TestObject2)) {
            return false;
        }
        TestObject2 to = (TestObject2) o;
        return
                getStr1().equals(to.getStr1()) &&
                        getDouble1() == to.getDouble1();
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public double getDouble1() {
        return double1;
    }

    public void setDouble1(double double1) {
        this.double1 = double1;
    }
}
