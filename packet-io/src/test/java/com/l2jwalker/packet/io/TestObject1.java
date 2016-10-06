package com.l2jwalker.packet.io;

public class TestObject1 {

    private byte byteTest1;
    private Byte byteTest2;

    private short shortTest1;
    private Short shortTest2;

    private int intTest1;
    private Integer intTest2;

    private long longTest1;
    private Long longTest2;

    private double doubleTest1;
    private Double doubleTest2;

    private String stringTest;

    private TestObject2 testObject2;

    @Override
    public boolean equals(Object o) {
        if (null == o || !(o instanceof TestObject1)) {
            return false;
        }
        TestObject1 to = (TestObject1) o;
        return
                getByteTest1() == to.getByteTest1() &&
                        getByteTest2().equals(to.getByteTest2()) &&
                        getShortTest1() == to.getShortTest1() &&
                        getShortTest2().equals(to.getShortTest2()) &&
                        getIntTest1() == to.getIntTest1() &&
                        getIntTest2().equals(to.getIntTest2()) &&
                        getLongTest1() == to.getLongTest1() &&
                        getLongTest2().equals(to.getLongTest2()) &&
                        getDoubleTest1() == to.getDoubleTest1() &&
                        getDoubleTest2().equals(to.getDoubleTest2()) &&
                        getStringTest().equals(to.getStringTest()) &&
                        getTestObject2().equals(to.getTestObject2());
    }

    public byte getByteTest1() {
        return byteTest1;
    }

    public void setByteTest1(byte byteTest1) {
        this.byteTest1 = byteTest1;
    }

    public Byte getByteTest2() {
        return byteTest2;
    }

    public void setByteTest2(Byte byteTest2) {
        this.byteTest2 = byteTest2;
    }

    public short getShortTest1() {
        return shortTest1;
    }

    public void setShortTest1(short shortTest1) {
        this.shortTest1 = shortTest1;
    }

    public Short getShortTest2() {
        return shortTest2;
    }

    public void setShortTest2(Short shortTest2) {
        this.shortTest2 = shortTest2;
    }

    public int getIntTest1() {
        return intTest1;
    }

    public void setIntTest1(int intTest1) {
        this.intTest1 = intTest1;
    }

    public Integer getIntTest2() {
        return intTest2;
    }

    public void setIntTest2(Integer intTest2) {
        this.intTest2 = intTest2;
    }

    public long getLongTest1() {
        return longTest1;
    }

    public void setLongTest1(long longTest1) {
        this.longTest1 = longTest1;
    }

    public Long getLongTest2() {
        return longTest2;
    }

    public void setLongTest2(Long longTest2) {
        this.longTest2 = longTest2;
    }

    public double getDoubleTest1() {
        return doubleTest1;
    }

    public void setDoubleTest1(double doubleTest1) {
        this.doubleTest1 = doubleTest1;
    }

    public Double getDoubleTest2() {
        return doubleTest2;
    }

    public void setDoubleTest2(Double doubleTest2) {
        this.doubleTest2 = doubleTest2;
    }

    public String getStringTest() {
        return stringTest;
    }

    public void setStringTest(String stringTest) {
        this.stringTest = stringTest;
    }

    public TestObject2 getTestObject2() {
        return testObject2;
    }

    public void setTestObject2(TestObject2 testObject2) {
        this.testObject2 = testObject2;
    }
}
