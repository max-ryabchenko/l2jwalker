package com.l2jwalker.entity;

public interface Copyable<T> {
    /**
     * Return a copy of the current object
     */
    T copy();

    /**
     * Copy the current properties to the given object
     */
    void copyTo(T t);
}
