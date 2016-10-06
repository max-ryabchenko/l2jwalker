package com.l2jwalker.packet.io;

enum DataType {
    Byte, //1 byte
    Word, //2 byte
    DoubleWord, //4 byte
    QuadWord, //8 byte
    Float, //8 byte
    Bool1, //1 byte
    Bool2, //2 byte
    Bool4, //4 byte
    Bool8, //8 byte
    ByteArray, //length
    UnicodeString, //2 + 2 * length
    Object, //
    List1, //1 byte count
    List2, //2 byte count
    List4, //4 byte count
    List8  //8 byte count
}
