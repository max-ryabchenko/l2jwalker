package com.l2jwalker.character.etc;

import javolution.util.FastMap;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class AbstractKnownList<T extends KnownObject> {

    private final Map<Long, T> knownList;

    protected AbstractKnownList() {
        knownList = FastMap.newInstance();
    }

    protected AbstractKnownList(int capacity) {
        knownList = new FastMap<Long, T>(capacity);
    }

    protected Map<Long, T> getKnownList() {
        return knownList;
    }

    public synchronized boolean isContainsId(long id) {
        return getKnownList().containsKey(id);
    }

    public synchronized boolean isContains(T object) {
        return getKnownList().values().contains(object);
    }

    public synchronized T get(long id) {
        return getKnownList().get(id);
    }

    public synchronized T put(long id, T object) {
        return getKnownList().put(id, object);
    }

    public synchronized T add(T object) {
        return getKnownList().put(object.getId(), object);
    }

    public synchronized void putAll(Map<Long, T> objects) {
        getKnownList().putAll(objects);
    }

    public synchronized void addAll(List<T> objects) {
        objects.stream().forEach(object -> getKnownList().put(object.getId(), object));
    }

    public synchronized void set(Map<Long, T> objects) {
        getKnownList().clear();
        getKnownList().putAll(objects);
    }

    public synchronized T remove(long id) {
        return getKnownList().remove(id);
    }

    public synchronized void clear() {
        getKnownList().clear();
    }

    public synchronized boolean isEmpty() {
        return getKnownList().isEmpty();
    }

    public synchronized long size() {
        return getKnownList().size();
    }

    public T get(String name) throws NoSuchElementException {
        if (null == name) {
            return null;
        }
        return getKnownList().values().stream().filter(object -> name.equalsIgnoreCase(object.getName())).findFirst().get();
    }

}
