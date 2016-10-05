package com.l2jwalker.util.proto;

import javolution.util.FastList;
import javolution.util.FastMap;
import org.apache.log4j.Logger;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Map;

public class ProtifiedEnum<ProtoEnum, Enum> {

    private static final Logger LOG = Logger.getLogger(ProtifiedEnum.class);

    private final Class protoClass;
    private final Class enumClass;
    private final Method valueOf;
    private final Method name;

    @SuppressWarnings("unchecked")
    public ProtifiedEnum() {
        enumClass = (Class<Enum>) getTypeArguments(ProtifiedEnum.class, getClass()).get(0);
        protoClass = (Class<ProtoEnum>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
//        enumClass = (Class<Enum>) ((ParameterizedType) getClass()
//                .getGenericSuperclass()).getActualTypeArguments()[1];
        Method tryValueOf = null;
        Method tryName = null;
        try {
            tryValueOf = protoClass.getMethod("valueOf", String.class);
            tryName = enumClass.getMethod("name");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            LOG.error(e);
        }
        valueOf = tryValueOf;
        name = tryName;
    }

    @SuppressWarnings("unchecked")
    public ProtoEnum getProto(Enum e) {
        try {
            return (ProtoEnum) valueOf.invoke(null, protoClass.getSimpleName() + "_" + name.invoke(e));
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
            LOG.error(e1);
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            LOG.error(e2);
        }
        return null;
    }

    public static Class<?> getClass(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        } else if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        } else if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            Class<?> componentClass = getClass(componentType);
            if (componentClass != null) {
                return Array.newInstance(componentClass, 0).getClass();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static <T> List<Class<?>> getTypeArguments(
            Class<T> baseClass, Class<? extends T> childClass) {
        Map<Type, Type> resolvedTypes = FastMap.newInstance();
        Type type = childClass;
        // start walking up the inheritance hierarchy until we hit baseClass
        while (!getClass(type).equals(baseClass)) {
            if (type instanceof Class) {
                // there is no useful information for us in raw types, so just keep going.
                type = ((Class) type).getGenericSuperclass();
            } else {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class<?> rawType = (Class) parameterizedType.getRawType();

                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                TypeVariable<?>[] typeParameters = rawType.getTypeParameters();
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    resolvedTypes.put(typeParameters[i], actualTypeArguments[i]);
                }

                if (!rawType.equals(baseClass)) {
                    type = rawType.getGenericSuperclass();
                }
            }
        }

        // finally, for each actual type argument provided to baseClass, determine (if possible)
        // the raw class for that type argument.
        Type[] actualTypeArguments;
        if (type instanceof Class) {
            actualTypeArguments = ((Class) type).getTypeParameters();
        } else {
            actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        }
        List<Class<?>> typeArgumentsAsClasses = FastList.newInstance();
        // resolve types by chasing down type variables.
        for (Type baseType : actualTypeArguments) {
            while (resolvedTypes.containsKey(baseType)) {
                baseType = resolvedTypes.get(baseType);
            }
            typeArgumentsAsClasses.add(getClass(baseType));
        }
        return typeArgumentsAsClasses;
    }
}
