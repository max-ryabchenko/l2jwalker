package com.l2jwalker.packet.io

import com.l2jwalker.util.io.IOUtil
import org.apache.log4j.Logger
import org.json.JSONArray
import org.json.JSONObject

public class PacketIO {

    private static Logger LOG = Logger.getLogger(PacketIO.class)

    private static final Map<Attribute, List<String>> ATTRS = [:]
    static {
        ATTRS.put(Attribute.Version, ['version', 'v'])
        ATTRS.put(Attribute.Name, ['name', 'n'])
        ATTRS.put(Attribute.DataType, ['format', 'f'])
    }

    private static final Map<DataType, List<String>> DATA_TYPE = [:]
    static {
        DATA_TYPE.put(DataType.Byte, ['byte', 'c'])
        DATA_TYPE.put(DataType.Word, ['word', 'w'])
        DATA_TYPE.put(DataType.DoubleWord, ['integer', 'int', 'd'])
        DATA_TYPE.put(DataType.QuadWord, ['long', 'q'])
        DATA_TYPE.put(DataType.Float, ['float', 'f'])
        DATA_TYPE.put(DataType.Bool1, ['boolByte', 'bool1', 'b1', 'b'])
        DATA_TYPE.put(DataType.Bool2, ['boolWord', 'bool2', 'b2'])
        DATA_TYPE.put(DataType.Bool4, ['boolDoubleWord', 'bool4', 'b4'])
        DATA_TYPE.put(DataType.Bool8, ['boolQuadWord', 'bool8', 'b8'])
        DATA_TYPE.put(DataType.ByteArray, ['byteArray', 'a'])
        DATA_TYPE.put(DataType.UnicodeString, ['unicode', 'u'])
        DATA_TYPE.put(DataType.Object, ['object', 'o'])
        DATA_TYPE.put(DataType.List1, ['list1', 'l1'])
        DATA_TYPE.put(DataType.List2, ['list2', 'l2'])
        DATA_TYPE.put(DataType.List4, ['list4', 'l4'])
        DATA_TYPE.put(DataType.List8, ['list8', 'l8'])
    }

    private static final byte[] EMPTY_ARRAY = new byte[0];

    public int writeArray(OutputStream src, Object data, JSONArray template, Integer version) throws IOException {
        int writedBytesCount = 0
        for (int elemNum = 0; elemNum < template.length(); elemNum++) {
            writedBytesCount += writeObject(src, data, template.get(elemNum) as JSONObject, version)
        }
        writedBytesCount
    }

    public int writeObject(OutputStream src, Object data, JSONObject template, Integer version) throws IOException {
        if (!checkVersion(version, getAttr(template, Attribute.Version))) {
            return 0
        }
        String attrName = (String) getAttr(template, Attribute.Name)
        String dataTypeStr = (String) getAttr(template, Attribute.DataType)
        DataType dataType = findDataType(dataTypeStr)

        if ((null != attrName) && (data instanceof Map ? !data.containsKey(attrName) : !data[attrName])) {
            LOG.debug('Data map missing key [' + attrName + ']')
        }

        Object value = null == attrName ? data : (data instanceof Map ? data.get(attrName) : data[attrName])

        switch (dataType) {
            case DataType.Byte:
                return IOUtil.writeC(src, null == value ? 0 : value as int)
            case DataType.Word:
                return IOUtil.writeW(src, null == value ? 0 : value as int)
            case DataType.DoubleWord:
                return IOUtil.writeD(src, null == value ? 0 : value as int)
            case DataType.QuadWord:
                return IOUtil.writeQ(src, null == value ? (0 as long) : (value as long))
            case DataType.Float:
                return IOUtil.writeF(src, null == value ? (0 as double) : value as double)
            case DataType.Bool1:
                return IOUtil.writeC(src, null == value ? (0x00 as byte) : (value ? 0x01 as byte : 0x00 as byte))
            case DataType.Bool2:
                return IOUtil.writeW(src, null == value ? (0x0000 as short) : (value ? 0x0001 as short : 0x0000 as short))
            case DataType.Bool4:
                return IOUtil.writeD(src, null == value ? (0x00000000 as int) : (value ? 0x00000001 as int : 0x00000000 as int))
            case DataType.Bool8:
                return IOUtil.writeQ(src, null == value ? (0x0000000000000000 as long) : (value ? 0x0000000000000001 as long : 0x0000000000000000 as long))
            case DataType.ByteArray:
                return IOUtil.writeB(src, null == value ? EMPTY_ARRAY : value as byte[])
            case DataType.UnicodeString:
                return IOUtil.writeU(src, null == value ? '' : (String) value)
            case DataType.Object:
                if (template.get('inner') instanceof JSONArray) {
                    return writeArray(src, null == attrName ? data : data instanceof Map ? data.get(attrName) : data[attrName], (JSONArray) template.get('inner'), version)
                } else {
                    return writeObject(src, data instanceof Map ? data.get(attrName) : data[attrName], (JSONObject) template.get('inner'), version)
                }
            case DataType.List1:
                return IOUtil.writeC(src, value.size() as byte) + writeList(src, value as List, template, version)
            case DataType.List2:
                return IOUtil.writeW(src, value.size() as byte) + writeList(src, value as List, template, version)
            case DataType.List4:
                return IOUtil.writeD(src, value.size() as byte) + writeList(src, value as List, template, version)
            case DataType.List8:
                return IOUtil.writeQ(src, value.size() as byte) + writeList(src, value as List, template, version)
        }
        0
    }

    private int writeList(OutputStream src, List list, JSONObject template, int version) {
        int writedBytesCount = 0
        for (item in list) {
            writedBytesCount += writeObject(src, item, (JSONObject) template.get('inner'), version)
        }
        writedBytesCount
    }

    private static DataType findDataType(String dataTypeStr) {
        for (Map.Entry<DataType, List<String>> dataTypeNames : DATA_TYPE.entrySet()) {
            if (dataTypeNames.getKey() == DataType.ByteArray) {
                for (String byteArrayName : dataTypeNames.getValue()) {
                    if (dataTypeStr.startsWith(byteArrayName)) {
                        return DataType.ByteArray
                    }
                }
            } else {
                for (String dataTypeName : dataTypeNames.getValue()) {
                    if (dataTypeName.equalsIgnoreCase(dataTypeStr)) {
                        return dataTypeNames.getKey()
                    }
                }
            }
        }
        DataType.Object
    }

    private static Object getAttr(JSONObject elem, Attribute attribute) {
        for (String attrName in ATTRS.get(attribute)) {
            if (elem.has(attrName)) {
                return elem.get(attrName)
            }
        }
        null
    }

    static boolean checkVersion(Integer version, Object templateVersion) {
        if (null == templateVersion || null == version) {
            return true //version not set
        }
        if (templateVersion instanceof Integer) {
            return version.equals(templateVersion)
        }
        if (templateVersion instanceof String) {
            String verStr = (String) templateVersion
            if (-1 != verStr.indexOf('-')) {
                if (verStr.length() - 1 == verStr.indexOf('-')) {
                    return version <= Integer.parseInt(verStr.substring(0, verStr.length() - 1))
                } else {
                    return Integer.parseInt(verStr.substring(0, verStr.indexOf('-'))) <= version &&
                            version <= Integer.parseInt(verStr.substring(verStr.indexOf('-') + 1))
                }
            } else if (-1 != verStr.indexOf('+')) {
                return version >= Integer.parseInt(verStr.substring(0, verStr.length() - 1))
            } else {
                return version.equals(Integer.parseInt(verStr))
            }
        }
        false
    }

    public int readArray(InputStream src, Object data, JSONArray template, Integer version) {
        int readedBytesCount = 0
        for (int elemNum = 0; elemNum < template.length(); elemNum++) {
            readedBytesCount += readObject(src, data, template.get(elemNum) as JSONObject, version)
        }
        readedBytesCount
    }

    @SuppressWarnings("GroovyAccessibility")
    public int readObject(InputStream src, Object data, JSONObject template, Integer version) {
        if (!checkVersion(version, getAttr(template, Attribute.Version))) {
            return 0
        }
        String attrName = (String) getAttr(template, Attribute.Name)
        String dataTypeStr = (String) getAttr(template, Attribute.DataType)
        DataType dataType = findDataType(dataTypeStr)

        switch (dataType) {
            case DataType.Byte:
                if (data instanceof Map) {
                    data.put(attrName, IOUtil.readC(src))
                } else {
                    data[attrName] = IOUtil.readC(src)
                }
                return 1
            case DataType.Word:
                if (data instanceof Map) {
                    data.put(attrName, IOUtil.readW(src))
                } else {
                    data[attrName] = IOUtil.readW(src)
                }
                return 2
            case DataType.DoubleWord:
                if (data instanceof Map) {
                    data.put(attrName, IOUtil.readD(src))
                } else {
                    data[attrName] = IOUtil.readD(src)
                }
                return 4
            case DataType.QuadWord:
                if (data instanceof Map) {
                    data.put(attrName, IOUtil.readQ(src))
                } else {
                    data[attrName] = IOUtil.readQ(src)
                }
                return 8
            case DataType.Float:
                if (data instanceof Map) {
                    data.put(attrName, IOUtil.readF(src))
                } else {
                    data[attrName] = IOUtil.readF(src)
                }
                return 8
            case DataType.Bool1:
                if (data instanceof Map) {
                    data.put(attrName, 0x00 != IOUtil.readC(src))
                } else {
                    data[attrName] = IOUtil.readC(src)
                }
                return 1
            case DataType.Bool2:
                if (data instanceof Map) {
                    data.put(attrName, 0x0000 != IOUtil.readW(src))
                } else {
                    data[attrName] = IOUtil.readW(src)
                }
                return 2
            case DataType.Bool4:
                if (data instanceof Map) {
                    data.put(attrName, 0x00000000 != IOUtil.readD(src))
                } else {
                    data[attrName] = IOUtil.readD(src)
                }
                return 4
            case DataType.Bool8:
                if (data instanceof Map) {
                    data.put(attrName, 0x0000000000000000 != IOUtil.readQ(src))
                } else {
                    data[attrName] = IOUtil.readQ(src)
                }
                return 8
            case DataType.UnicodeString:
                String str = IOUtil.readU(src)
                if (data instanceof Map) {
                    data.put(attrName, str)
                } else {
                    data[attrName] = str
                }
                return 2 + 2 * str.length()
            case DataType.ByteArray:
                Integer len = null
                byte[] value
                try {
                    len = dataTypeStr.substring((DATA_TYPE.get(DataType.ByteArray) as List).get(0).length()).toInteger()
                    value = IOUtil.readB(src, len)
                } catch (Exception e) {
                    value = IOUtil.readB(src)
                }
                if (data instanceof Map) {
                    data.put(attrName, value)
                } else {
                    data[attrName] = value
                }
                return value.length
            case DataType.Object:
                Object o = this.getClass().classLoader.loadClass(dataTypeStr, true)?.newInstance()
                if (data instanceof Map) {
                    data.put(attrName, o)
                } else if (data instanceof List) {
                    data.add(o)
                } else {
                    data[attrName] = o
                }
                return readArray(src, o, template.get("inner") as JSONArray, version)
            case DataType.List1:
                return 1 + readList(IOUtil.readC(src), attrName, src, data, template, version)
            case DataType.List2:
                return 2 + readList(IOUtil.readW(src), attrName, src, data, template, version)
            case DataType.List4:
                return 4 + readList(IOUtil.readD(src), attrName, src, data, template, version)
            case DataType.List8:
                return 8 + readList(IOUtil.readQ(src), attrName, src, data, template, version)
        }
        0
    }

    private int readList(long count, String attrName, InputStream src, Object data, JSONObject template, int version) {
        int readedBytesCount = 0
        List list = []
        if (data instanceof Map) {
            data.put(attrName, list)
        } else {
            data[attrName] = list
        }
        while (count-- > 0) {
            readedBytesCount += readObject(src, list, template.get('inner') as JSONObject, version)
        }
        readedBytesCount
    }

}
