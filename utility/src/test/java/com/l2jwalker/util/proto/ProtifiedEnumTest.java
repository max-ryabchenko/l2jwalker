package com.l2jwalker.util.proto;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.UUID;

public class ProtifiedEnumTest {

    enum TestEnum{
        Value1,
        Value2,
        Value3
    }

    enum TestProtoEnum{
        TestProtoEnum_Value1,
        TestProtoEnum_Value2,
        TestProtoEnum_Value3
    }

    @Test
    @Ignore
    public void test(){

        ProtifiedEnum<TestProtoEnum, TestEnum>  protifiedEnum = new ProtifiedEnum<TestProtoEnum, TestEnum>();

        Assert.assertEquals(TestProtoEnum.TestProtoEnum_Value1, protifiedEnum.getProto(TestEnum.Value1));
        Assert.assertEquals(TestProtoEnum.TestProtoEnum_Value2, protifiedEnum.getProto(TestEnum.Value2));
        Assert.assertEquals(TestProtoEnum.TestProtoEnum_Value3, protifiedEnum.getProto(TestEnum.Value3));

    }

    @Test
    public void t(){
        System.out.println(UUID.randomUUID());
    }

}
