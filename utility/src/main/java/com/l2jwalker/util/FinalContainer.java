package com.l2jwalker.util;

public class FinalContainer<T> {

    private T inner;

    public FinalContainer(T init){
        this.set(init);
    }

    public T get() {
        return inner;
    }

    public void set(T inner) {
        this.inner = inner;
    }

}
